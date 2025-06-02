package sjq.bitcoin.script;

import sjq.bitcoin.service.data.LegacyAddress;
import sjq.bitcoin.utility.HexUtils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScriptProgram {

    private List<Instruction> instructions = new ArrayList<>();

    public static ScriptProgram parse(byte[] scriptData) throws Exception {

        ByteBuffer scriptBuffer = ByteBuffer.wrap(scriptData);
        ScriptProgram program = new ScriptProgram();

        List<Instruction> instructions = new ArrayList<>();
        while(scriptBuffer.position()<scriptBuffer.limit()) {
            byte byteCode = scriptBuffer.get();
            short opCode = (short)(byteCode & 0xFF);
            Instruction instruction = InstructionTable.newInstructionByOpCode(opCode);
            instruction.fetch(scriptBuffer);
            instructions.add(instruction);
        }

        program.instructions = Collections.unmodifiableList(instructions);
        return program;
    }

    public static ScriptProgram build() {
        return new ScriptProgram();
    }

    public ScriptProgram data(byte[] data) throws Exception {
        byte[] operand = Arrays.copyOf(data, data.length);
        short operCode = ScriptOpcode.OP_0;
        if (operand.length==1) {

        } else if (operand.length<ScriptOpcode.OP_PUSHDATA1) {
            operCode = (short)operand.length;
        } else if (operand.length<256) {
            operCode = ScriptOpcode.OP_PUSHDATA1;
        } else if (operand.length<65536) {
            operCode = ScriptOpcode.OP_PUSHDATA2;
        }

        Instruction instruction = InstructionTable.newInstructionByOpCode(operCode);
        instruction.setOperand(operand);
        int position = this.instructions.size();
        this.instructions.add(position, instruction);

        return this;
    }

    public ScriptProgram opCode(short opCode) throws Exception {
        if (opCode <= ScriptOpcode.OP_PUSHDATA4) {
            throw new ScriptException(String.format("opCode is not correct with value:%d", opCode));
        }
        Instruction instruction = InstructionTable.newInstructionByOpCode(opCode);
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

    public boolean isP2PK() {
        return false;
    }

    public boolean isP2PKH() {
        if (instructions.size() != 5) {
            return false;
        }
        if (instructions.get(0).getOpCode() != ScriptOpcode.OP_DUP) {
            return false;
        }
        if (instructions.get(1).getOpCode() != ScriptOpcode.OP_HASH160) {
            return false;
        }
        byte[] instructionData = instructions.get(2).getOperand();
        if (instructionData==null) {
            return false;
        }
        if (instructionData.length != LegacyAddress.ADDRESS_LENGTH) {
            return false;
        }
        if (instructions.get(3).getOpCode() != ScriptOpcode.OP_EQUALVERIFY) {
            return false;
        }
        if (instructions.get(4).getOpCode() != ScriptOpcode.OP_CHECKSIG) {
            return false;
        }
        return true;
    }

    public boolean isP2SH() {
        return false;
    }

    public boolean isP2WPKH() {
        return false;
    }

    public String format() {
        StringBuilder builder = new StringBuilder();
        for (Instruction instruction:instructions) {
            short opCode = instruction.getOpCode();
            String opName = instruction.getOpName();
            if (opCode >= ScriptOpcode.OP_PUSH_1 && opCode <= ScriptOpcode.OP_PUSH_75) {
                opName = String.format("%s(%d)", opName, opCode);
            }
            if (builder.length()>0) {
                builder.append(" ");
            }
            builder.append(opName);
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
}
