package sjq.bitcoin.script;

import sjq.bitcoin.crypto.ECDSAKey;
import sjq.bitcoin.crypto.transaction.SignatureContext;
import sjq.bitcoin.service.data.BitcoinAddress;
import sjq.bitcoin.service.data.LegacyAddress;
import sjq.bitcoin.service.data.SegwitAddress;
import sjq.bitcoin.utility.HexUtils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptProgram {

    private final List<Instruction> instructions;

    private SignatureContext signatureContext;

    private ScriptProgram(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public static ScriptProgram build() {
        return new ScriptProgram(null);
    }

    public static ScriptProgram build(byte[] scriptData) throws Exception {
        List<Instruction> instructions = new ArrayList<>();
        ScriptProgram scriptProgram = new ScriptProgram(instructions);

        ByteBuffer scriptBuffer = ByteBuffer.wrap(scriptData);
        while(scriptBuffer.position()<scriptBuffer.limit()) {
            byte byteCode = scriptBuffer.get();
            short operationCode = (short)(byteCode & 0xFF);
            ScriptOpCode opCode = ScriptOpCode.getScriptOpCodeByCode(operationCode);
            Instruction instruction = InstructionTable.newInstructionByOpCode(opCode, scriptProgram);
            instruction.fetch(scriptBuffer);
            instructions.add(instruction);
        }

        return scriptProgram;
    }

    public static boolean verify(SignatureContext signatureContext,
                                 ScriptProgram scriptSignatureProgram, ScriptProgram scriptPubKeyProgram) {
        // Since They share the same signature context for both signature and pubKey script program,
        // here signatureContext should be set for both of them in advance.
        scriptSignatureProgram.initSignatureContext(signatureContext);
        scriptPubKeyProgram.initSignatureContext(signatureContext);

        // Both signature and public Key script program share the same operand stack instance. After
        // signature script program finishes execution, public key script program should start execution
        // with the same operand stack instance, so that the verification data chain could be verified
        // continuously and precisely.
        OperandStack operandStack = new OperandStack();
        scriptSignatureProgram.executeScript(operandStack);
        scriptPubKeyProgram.executeScript(operandStack);
        byte[] result = operandStack.pop();
        return Arrays.equals(result, InstructionTable.TRUE);
    }

    private void initSignatureContext(SignatureContext signatureContext) {
        this.signatureContext = signatureContext;
    }

    private void executeScript(OperandStack stack) {
        int index = 0;
        while(index<instructions.size()) {
            Instruction instruction = instructions.get(index);
            instruction.execute(stack);
            index++;
        }
    }

    public ScriptProgram data(byte[] data) throws Exception {
        byte[] operand = Arrays.copyOf(data, data.length);
        short operCode = ScriptConstant.OP_PUSH_0;
        if (operand.length==1) {

        } else if (operand.length< ScriptConstant.OP_PUSHDATA1) {
            operCode = (short)operand.length;
        } else if (operand.length<256) {
            operCode = ScriptConstant.OP_PUSHDATA1;
        } else if (operand.length<65536) {
            operCode = ScriptConstant.OP_PUSHDATA2;
        }

        ScriptOpCode opCode = ScriptOpCode.getScriptOpCodeByCode(operCode);
        Instruction instruction = InstructionTable.newInstructionByOpCode(opCode, this);
        instruction.setOperand(operand);
        int position = this.instructions.size();
        this.instructions.add(position, instruction);

        return this;
    }

    public ScriptProgram opCode(short operCode) throws Exception {
        if (operCode <= ScriptConstant.OP_PUSHDATA4) {
            throw new ScriptException(String.format("opCode is not correct with value:%d", operCode));
        }
        ScriptOpCode opCode = ScriptOpCode.getScriptOpCodeByCode(operCode);
        Instruction instruction = InstructionTable.newInstructionByOpCode(opCode, this);
        int position = this.instructions.size();
        this.instructions.add(position, instruction);
        return this;
    }

    public byte[] program() {
        int totalInstructionSize = instructions.stream().mapToInt(Instruction::getInstructionSize).sum();
        ByteBuffer buffer = ByteBuffer.allocate(totalInstructionSize);
        for (Instruction instruction:instructions) {
            buffer.put(instruction.getInstructionByte());
        }
        return buffer.array();
    }

    public BitcoinAddress getDestAddress(BitcoinNetwork network) {
        if (isP2SH()) {
            byte[] addressBytes = extractHashFromP2SH();
            return LegacyAddress.fromScriptHash(network, addressBytes);
        } else if (isP2PK()) {
            byte[] publicKey = extractKeyFromP2PK();
            ECDSAKey ecdsaKey = ECDSAKey.getInstanceFromPublicKey(publicKey);
            return ecdsaKey.generateAddress(network, ScriptType.P2PKH);
        } else if (isP2PKH()) {
            byte[] addressBytes = extractHashFromP2PKH();
            return LegacyAddress.fromPubKeyHash(network, addressBytes);
        } else if (isP2WH()) {
            byte[] addressBytes = extractHashFromP2WH();
            return SegwitAddress.fromHash(network, addressBytes);
        } else if (isP2TR()) {
            byte[] keyBytes = extractKeyFromP2TR();
            return SegwitAddress.fromProgram(network, 1, keyBytes);
        } else {
            throw new ScriptException("The bitcoin address is not valid script type!");
        }
    }

    public byte[] extractKeyFromP2PK() {
        if (isP2PK()) {
            return instructions.get(0).getOperand();
        } else {
            throw new ScriptException("The script is not P2PK type!");
        }
    }

    public byte[] extractHashFromP2PKH() {
        if (isP2PKH()) {
            return instructions.get(2).getOperand();
        } else {
            throw new ScriptException("The script is not P2PKH type!");
        }
    }

    public byte[] extractHashFromP2SH() {
        if (isP2SH()) {
            return instructions.get(1).getOperand();
        } else {
            throw new ScriptException("The script is not P2SH type!");
        }
    }

    public byte[] extractHashFromP2WH() {
        if (isP2WH()) {
            return instructions.get(1).getOperand();
        } else {
            throw new ScriptException("The script is not P2WH type!");
        }
    }

    public byte[] extractKeyFromP2TR() {
        if (isP2TR()) {
            return instructions.get(1).getOperand();
        } else {
            throw new ScriptException("The script is not P2TR type!");
        }
    }

    public boolean isP2PK() {
        if (instructions.size() != 2) {
            return false;
        }
        if (instructions.get(1).getOpCode() != ScriptOpCode.OP_CHECKSIG) {
            return false;
        }
        Instruction instruction = instructions.get(0);
        if (instruction.isOpCode()) {
            return false;
        }
        byte[] data = instruction.getOperand();
        return data != null && data.length > 1;
    }

    public boolean isP2PKH() {
        if (instructions.size() != 5) {
            return false;
        }
        if (instructions.get(0).getOpCode() != ScriptOpCode.OP_DUP) {
            return false;
        }
        if (instructions.get(1).getOpCode() != ScriptOpCode.OP_HASH160) {
            return false;
        }
        byte[] instructionData = instructions.get(2).getOperand();
        if (instructionData==null) {
            return false;
        }
        if (instructionData.length != LegacyAddress.ADDRESS_LENGTH) {
            return false;
        }
        if (instructions.get(3).getOpCode() != ScriptOpCode.OP_EQUALVERIFY) {
            return false;
        }
        if (instructions.get(4).getOpCode() != ScriptOpCode.OP_CHECKSIG) {
            return false;
        }
        return true;
    }

    public boolean isP2SH() {
        if (instructions.size() != 3) {
            return false;
        }
        if (instructions.get(0).getOpCode() != ScriptOpCode.OP_HASH160) {
            return false;
        }
        if (instructions.get(1).getOpCode() != ScriptOpCode.OP_PUSH_20) {
            return false;
        }
        Instruction instruction = instructions.get(2);
        if (instruction.getOpCode() != ScriptOpCode.OP_EQUAL) {
            return false;
        }
        byte[] data = instruction.getOperand();
        if (data == null || data.length != LegacyAddress.ADDRESS_LENGTH) {
            return false;
        }
        return true;
    }

    public boolean isP2WH() {
        if (instructions.size() != 2) {
            return false;
        }
        ScriptOpCode opCode = instructions.get(0).getOpCode();
        if (ScriptOpCode.OP_0.getCode() != opCode.getCode()) {
            return false;
        }
        byte[] data = instructions.get(1).getOperand();
        if (data == null) {
            return false;
        }
        return data.length == SegwitAddress.WITNESS_PROGRAM_LENGTH_PKH
                || data.length == SegwitAddress.WITNESS_PROGRAM_LENGTH_SH;
    }

    public boolean isP2WPKH() {
        if (instructions.size() != 2) {
            return false;
        }
        ScriptOpCode opCode = instructions.get(0).getOpCode();
        if (ScriptOpCode.OP_0.getCode() != opCode.getCode()) {
            return false;
        }
        byte[] data = instructions.get(1).getOperand();
        if (data == null || data.length != SegwitAddress.WITNESS_PROGRAM_LENGTH_PKH) {
            return false;
        }
        return true;
    }

    public boolean isP2WSH() {
        if (instructions.size() != 2) {
            return false;
        }
        ScriptOpCode opCode = instructions.get(0).getOpCode();
        if (ScriptOpCode.OP_0.getCode() != opCode.getCode()) {
            return false;
        }
        byte[] data = instructions.get(1).getOperand();
        if (data == null || data.length != SegwitAddress.WITNESS_PROGRAM_LENGTH_SH) {
            return false;
        }
        return true;
    }

    public boolean isP2TR() {
        if (instructions.size() != 2) {
            return false;
        }
        ScriptOpCode opCode = instructions.get(0).getOpCode();
        if (ScriptOpCode.OP_1.getCode() != opCode.getCode()) {
            return false;
        }
        byte[] data = instructions.get(1).getOperand();
        if (data == null || data.length != SegwitAddress.WITNESS_PROGRAM_LENGTH_TR) {
            return false;
        }
        return true;
    }

    public String format() {
        StringBuilder builder = new StringBuilder();
        for (Instruction instruction:instructions) {
            ScriptOpCode opCode = instruction.getOpCode();
            if (builder.length()>0) {
                builder.append(" ");
            }
            builder.append(opCode.getName());
            byte[] operand = instruction.getOperand();
            if (operand != null && operand.length>0) {
                if (builder.length()>0) {
                    builder.append(" ");
                }
                String operandStr = String.format("[%s]", HexUtils.formatHex(operand));
                builder.append(operandStr);
            }
        }
        return builder.toString();
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public SignatureContext getSignatureContext() {
        return signatureContext;
    }
}
