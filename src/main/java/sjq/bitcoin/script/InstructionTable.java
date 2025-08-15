package sjq.bitcoin.script;

import sjq.bitcoin.crypto.ECDSATool;
import sjq.bitcoin.crypto.exception.SignatureDecodeException;
import sjq.bitcoin.crypto.transaction.SignatureContext;
import sjq.bitcoin.crypto.transaction.SignatureHashType;
import sjq.bitcoin.crypto.transaction.TransactionSignature;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.utility.AssertUtils;
import sjq.bitcoin.utility.ByteUtils;
import sjq.bitcoin.utility.HashUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static sjq.bitcoin.script.OperandStack.MAX_SCRIPT_ELEMENT_SIZE;

public class InstructionTable {

    public static final byte[] EMPTY = new byte[]{};

    public static final byte[] TRUE = new byte[]{1};

    public static final byte[] FALSE = new byte[]{};

    private static Map<ScriptOpCode, Class<? extends Instruction>> instructionMap = new HashMap<>();

    static {
        instructionMap.put(ScriptOpCode.OP_PUSH_0, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_1, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_2, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_3, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_4, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_5, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_6, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_7, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_8, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_9, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_10, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_11, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_12, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_13, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_14, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_15, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_16, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_17, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_18, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_19, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_20, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_21, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_22, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_23, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_24, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_25, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_26, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_27, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_28, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_29, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_30, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_31, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_32, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_33, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_34, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_35, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_36, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_37, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_38, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_39, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_40, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_41, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_42, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_43, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_44, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_45, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_46, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_47, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_48, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_49, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_50, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_51, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_52, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_53, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_54, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_55, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_56, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_57, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_58, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_59, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_60, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_61, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_62, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_63, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_64, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_65, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_66, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_67, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_68, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_69, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_70, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_71, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_72, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_73, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_74, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSH_75, OpPushInstruction.class);

        instructionMap.put(ScriptOpCode.OP_PUSHDATA1, OpPushData1Instruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSHDATA2, OpPushData2Instruction.class);
        instructionMap.put(ScriptOpCode.OP_PUSHDATA4, OpPushData4Instruction.class);

        instructionMap.put(ScriptOpCode.OP_NEGATE1, OpPushNegative1Instruction.class);

        instructionMap.put(ScriptOpCode.OP_1, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_2, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_3, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_4, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_5, OpPushInstruction.class);
        instructionMap.put(ScriptOpCode.OP_6, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_7, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_8, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_9, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_10, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_11, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_12, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_13, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_14, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_15, OpInstruction.class);
        instructionMap.put(ScriptOpCode.OP_16, OpInstruction.class);

        instructionMap.put(ScriptOpCode.OP_IF, OpIfInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOTIF, OpNotIfInstruction.class);
        instructionMap.put(ScriptOpCode.OP_ELSE, OpElseInstruction.class);
        instructionMap.put(ScriptOpCode.OP_ENDIF, OpEndIfInstruction.class);
        instructionMap.put(ScriptOpCode.OP_VERIFY, OpVerifyInstruction.class);
        instructionMap.put(ScriptOpCode.OP_RETURN, OpReturnInstruction.class);

        instructionMap.put(ScriptOpCode.OP_TOALTSTACK, OpToAltStackInstruction.class);
        instructionMap.put(ScriptOpCode.OP_FROMALTSTACK, OpFromAltStackInstruction.class);
        instructionMap.put(ScriptOpCode.OP_IFDUP, OpIfDupInstruction.class);
        instructionMap.put(ScriptOpCode.OP_DEPTH, OpDepthInstruction.class);
        instructionMap.put(ScriptOpCode.OP_DROP, OpDropInstruction.class);
        instructionMap.put(ScriptOpCode.OP_DUP, OpDupInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NIP, OpNipInstruction.class);
        instructionMap.put(ScriptOpCode.OP_OVER, OpOverInstruction.class);
        instructionMap.put(ScriptOpCode.OP_PICK, OpPickInstruction.class);
        instructionMap.put(ScriptOpCode.OP_ROLL, OpRollInstruction.class);
        instructionMap.put(ScriptOpCode.OP_ROT, OpRotInstruction.class);
        instructionMap.put(ScriptOpCode.OP_SWAP, OpSwapInstruction.class);
        instructionMap.put(ScriptOpCode.OP_TUCK, OpTuckInstruction.class);
        instructionMap.put(ScriptOpCode.OP_DROP2, OpDrop2Instruction.class);
        instructionMap.put(ScriptOpCode.OP_DUP2, OpDup2Instruction.class);
        instructionMap.put(ScriptOpCode.OP_DUP3, OpDup3Instruction.class);
        instructionMap.put(ScriptOpCode.OP_OVER2, OpOver2Instruction.class);
        instructionMap.put(ScriptOpCode.OP_ROT2, OpRot2Instruction.class);
        instructionMap.put(ScriptOpCode.OP_SWAP2, OpSwap2Instruction.class);

        instructionMap.put(ScriptOpCode.OP_CAT, OpCatInstruction.class);
        instructionMap.put(ScriptOpCode.OP_SUBSTR, OpSubStrInstruction.class);
        instructionMap.put(ScriptOpCode.OP_LEFT, OpLeftInstruction.class);
        instructionMap.put(ScriptOpCode.OP_RIGHT, OpRightInstruction.class);
        instructionMap.put(ScriptOpCode.OP_SIZE, OpSizeInstruction.class);

        instructionMap.put(ScriptOpCode.OP_ADD1, OpAdd1Instruction.class);
        instructionMap.put(ScriptOpCode.OP_SUB1, OpSub1Instruction.class);
        instructionMap.put(ScriptOpCode.OP_MUL2, OpMul2Instruction.class);
        instructionMap.put(ScriptOpCode.OP_DIV2, OpDiv2Instruction.class);
        instructionMap.put(ScriptOpCode.OP_NEGATE, OpNegateInstruction.class);
        instructionMap.put(ScriptOpCode.OP_ABS, OpAbsInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOT, OpNotInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOTEQUAL0, OpNotEqual0Instruction.class);
        instructionMap.put(ScriptOpCode.OP_ADD, OpAddInstruction.class);
        instructionMap.put(ScriptOpCode.OP_SUB, OpSubInstruction.class);
        instructionMap.put(ScriptOpCode.OP_MUL, OpMulInstruction.class);
        instructionMap.put(ScriptOpCode.OP_DIV, OpDivInstruction.class);
        instructionMap.put(ScriptOpCode.OP_MOD, OpModInstruction.class);
        instructionMap.put(ScriptOpCode.OP_LSHIFT, OpShiftLeftInstruction.class);
        instructionMap.put(ScriptOpCode.OP_RSHIFT, OpShiftRightInstruction.class);
        instructionMap.put(ScriptOpCode.OP_BOOLAND, OpBoolAndInstruction.class);
        instructionMap.put(ScriptOpCode.OP_BOOLOR, OpBoolOrInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NUMEQUAL, OpNumEqualInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NUMEQUALVERIFY, OpNumEqualVerifyInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NUMNOTEQUAL, OpNumNotEqualInstruction.class);
        instructionMap.put(ScriptOpCode.OP_LESSTHAN, OpLessThanInstruction.class);
        instructionMap.put(ScriptOpCode.OP_GREATERTHAN, OpGreaterThanInstruction.class);
        instructionMap.put(ScriptOpCode.OP_LESSTHANOREQUAL, OpLessThanOrEqualInstruction.class);
        instructionMap.put(ScriptOpCode.OP_GREATERTHANOREQUAL, OpGreaterThanOrEqualInstruction.class);
        instructionMap.put(ScriptOpCode.OP_MIN, OpMinInstruction.class);
        instructionMap.put(ScriptOpCode.OP_MAX, OpMaxInstruction.class);
        instructionMap.put(ScriptOpCode.OP_WITHIN, OpWithinInstruction.class);

        instructionMap.put(ScriptOpCode.OP_INVERT, OpInvertInstruction.class);
        instructionMap.put(ScriptOpCode.OP_AND, OpAndInstruction.class);
        instructionMap.put(ScriptOpCode.OP_OR, OpOrInstruction.class);
        instructionMap.put(ScriptOpCode.OP_XOR, OpXorInstruction.class);
        instructionMap.put(ScriptOpCode.OP_EQUAL, OpEqualInstruction.class);
        instructionMap.put(ScriptOpCode.OP_EQUALVERIFY, OpEqualVerifyInstruction.class);

        instructionMap.put(ScriptOpCode.OP_RIPEMD160, OpRipemd160Instruction.class);
        instructionMap.put(ScriptOpCode.OP_SHA1, OpSha1Instruction.class);
        instructionMap.put(ScriptOpCode.OP_SHA256, OpSha256Instruction.class);
        instructionMap.put(ScriptOpCode.OP_HASH160, OpHash160Instruction.class);
        instructionMap.put(ScriptOpCode.OP_HASH256, OpHash256Instruction.class);
        instructionMap.put(ScriptOpCode.OP_CODESEPARATOR, OpCodeSeparatorInstruction.class);
        instructionMap.put(ScriptOpCode.OP_CHECKSIG, OpCheckSignatureInstruction.class);
        instructionMap.put(ScriptOpCode.OP_CHECKSIGVERIFY, OpCheckSignatureVerifyInstruction.class);
        instructionMap.put(ScriptOpCode.OP_CHECKMULTISIG, OpCheckMultiSigInstruction.class);
        instructionMap.put(ScriptOpCode.OP_CHECKMULTISIGVERIFY, OpCheckMultiSigVerifyInstruction.class);
        instructionMap.put(ScriptOpCode.OP_CHECKSIGADD, OpCheckSigAddInstruction.class);

        instructionMap.put(ScriptOpCode.OP_CHECKLOCKTIMEVERIFY, OpCheckLockTimeVerifyAddInstruction.class);
        instructionMap.put(ScriptOpCode.OP_CHECKSEQUENCEVERIFY, OpCheckSequenceVerifyAddInstruction.class);

        instructionMap.put(ScriptOpCode.OP_NOP, OpNopInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOP1, OpNopInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOP4, OpNopInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOP5, OpNopInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOP6, OpNopInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOP7, OpNopInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOP8, OpNopInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOP9, OpNopInstruction.class);
        instructionMap.put(ScriptOpCode.OP_NOP10, OpNopInstruction.class);

        instructionMap.put(ScriptOpCode.OP_RESERVED, OpReservedInstruction.class);
        instructionMap.put(ScriptOpCode.OP_RESERVED1, OpReservedInstruction.class);
        instructionMap.put(ScriptOpCode.OP_RESERVED2, OpReservedInstruction.class);

        instructionMap.put(ScriptOpCode.OP_VER, OpVerInstruction.class);
        instructionMap.put(ScriptOpCode.OP_VERIF, OpVerInstruction.class);
        instructionMap.put(ScriptOpCode.OP_VERNOTIF, OpVerInstruction.class);

        instructionMap.put(ScriptOpCode.OP_INVALIDOPCODE, OpInvalidOpCodeInstruction.class);
    }

    public static Instruction newInstructionByOpCode(ScriptOpCode opCode, ScriptProgram runtime) throws Exception {
        if (instructionMap.containsKey(opCode)) {
            Class<? extends Instruction> clazz = instructionMap.get(opCode);
            Instruction instruction = clazz.newInstance();
            instruction.setOpCode(opCode);
            instruction.setScriptProgram(runtime);
            return instruction;
        }
        throw new ScriptException(String.format("can not find instruction for opCode:%s", opCode));
    }

    private static abstract class AbstractInstruction implements Instruction {

        protected ScriptOpCode code;

        protected ScriptProgram runtime;

        public AbstractInstruction(){
        }

        public void fetch(ByteBuffer scriptBuffer) {
        }

        public void execute(OperandStack stack) {
        }

        public int getInstructionSize() {
            int operCodeLength = 1;
            int dataSizeLength = 0;
            if (code == ScriptOpCode.OP_PUSHDATA1) {
                dataSizeLength = 1;
            } else if (code == ScriptOpCode.OP_PUSHDATA2) {
                dataSizeLength = 2;
            } else if (code == ScriptOpCode.OP_PUSHDATA4) {
                dataSizeLength = 4;
            }

            int operandLength = 0;
            byte[] operand = getOperand();
            if (operand!=null) {
                operandLength = operand.length;
            }
            return operCodeLength + dataSizeLength + operandLength;
        }

        public byte[] getInstructionByte() {
            byte[] operand = getOperand();
            int instructionSize = getInstructionSize();
            ByteBuffer buffer = ByteBuffer.allocate(instructionSize);
            if (code.getCode() > ScriptConstant.OP_PUSHDATA4) {
                buffer.put((byte)code.getCode());
            } else if (operand != null) {
                if (code.getCode() < ScriptConstant.OP_PUSHDATA1) {
                    checkInstruction(code.getCode() == operand.length, "code and operand length do not match!");
                    buffer.put((byte)code.getCode());
                } else if (code == ScriptOpCode.OP_PUSHDATA1) {
                    checkInstruction(operand.length <= 0xFF, "operand length is not correct!");
                    buffer.put((byte)code.getCode());
                    buffer.put((byte) operand.length);
                } else if (code == ScriptOpCode.OP_PUSHDATA2) {
                    checkInstruction(operand.length <= 0xFFFF, "operand length is not correct!");
                    buffer.put((byte)code.getCode());
                    ByteUtils.writeInt16LE(operand.length, buffer);
                } else {
                    checkInstruction(operand.length <= MAX_SCRIPT_ELEMENT_SIZE, "operand length is not correct!");
                    buffer.put((byte)code.getCode());
                    ByteUtils.writeInt32LE(operand.length, buffer);
                }
                buffer.put(operand);
            }
            return buffer.array();
        }

        private void checkInstruction(boolean condition, String message) {
            if (!condition) {
                throw new ScriptException(message);
            }
        }

        public boolean isOpCode() {
            return code.getCode() > ScriptConstant.OP_PUSHDATA4;
        }

        public ScriptOpCode getOpCode() {
            return code;
        }

        public void setOpCode(ScriptOpCode opCode) {
            this.code = opCode;
        }

        public byte[] getOperand() {
            return null;
        }

        public void setOperand(byte[] operand) {
        }

        public void setScriptProgram(ScriptProgram runtime) {
            this.runtime = runtime;
        }

        public ScriptProgram getRuntime() {
            return runtime;
        }
    }

    private static class OpPushInstruction extends AbstractInstruction {

        private byte[] data;

        @Override
        public void fetch(ByteBuffer scriptBuffer) {
            int length = code.getCode();
            data = ByteUtils.readBytesByLength(scriptBuffer, length);
        }

        @Override
        public void execute(OperandStack stack) {
            if (code== ScriptOpCode.OP_PUSH_0) {
                stack.push(EMPTY);
            } else {
                stack.push(data);
            }
        }

        public byte[] getOperand() {
            return data;
        }

        public void setOperand(byte[] operand) {
            this.data = operand;
        }
    }

    private static class OpPushData1Instruction extends AbstractInstruction {

        private byte[] data;

        @Override
        public void fetch(ByteBuffer scriptBuffer) {
            byte length = ByteUtils.readByte(scriptBuffer);
            data = ByteUtils.readBytesByLength(scriptBuffer, length);
        }

        @Override
        public void execute(OperandStack stack) {
            stack.push(data);
        }
    }

    private static class OpPushData2Instruction extends AbstractInstruction {

        private byte[] data;

        @Override
        public void fetch(ByteBuffer scriptBuffer) {
            int length = ByteUtils.readUint16LE(scriptBuffer);
            data = ByteUtils.readBytesByLength(scriptBuffer, length);
        }

        @Override
        public void execute(OperandStack stack) {
            stack.push(data);
        }
    }

    private static class OpPushData4Instruction extends AbstractInstruction {

        private byte[] data;

        @Override
        public void fetch(ByteBuffer scriptBuffer) {
            int length = (int)ByteUtils.readUint32LE(scriptBuffer);
            data = ByteUtils.readBytesByLength(scriptBuffer, length);
        }

        @Override
        public void execute(OperandStack stack) {
            stack.push(data);
        }
    }

    private static class OpPushNegative1Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            byte[] items = new byte[]{-1};
            stack.push(items);
        }
    }

    private static class OpIfInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpNotIfInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpElseInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpEndIfInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpVerifyInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpReturnInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpToAltStackInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpFromAltStackInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpIfDupInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpDepthInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpDropInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpDupInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            byte[] element = stack.peek();
            stack.push(element);
        }
    }

    private static class OpNipInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpOverInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpPickInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpRollInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpRotInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpSwapInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            byte[] element2 = stack.pop();
            byte[] element1 = stack.pop();

            stack.push(element2);
            stack.push(element1);
        }
    }

    private static class OpTuckInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpDrop2Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpDup2Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpDup3Instruction extends AbstractInstruction {

        public void execute(OperandStack stack) {

        }
    }

    private static class OpOver2Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpRot2Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpSwap2Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpCatInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpSubStrInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpLeftInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpRightInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpSizeInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpAdd1Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpSub1Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpMul2Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpDiv2Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpNegateInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpAbsInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpNotInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpNotEqual0Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpAddInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpSubInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpMulInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpDivInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpModInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpShiftLeftInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpShiftRightInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpBoolAndInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpBoolOrInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpNumEqualInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpNumEqualVerifyInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpNumNotEqualInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
        }
    }

    private static class OpLessThanInstruction extends AbstractInstruction {

        public OpLessThanInstruction(){
        }

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpGreaterThanInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpLessThanOrEqualInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpGreaterThanOrEqualInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpMinInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpMaxInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpWithinInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpInvertInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpAndInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpOrInstruction extends AbstractInstruction {

        public void execute(OperandStack stack) {

        }
    }

    private static class OpXorInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpEqualInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpEqualVerifyInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            if (stack.size()<2) {
                throw new ScriptException(String.format("script execution exception " +
                        "with %s, no enough elements in operand stack", getOpCode()));
            }

            byte[] data1 = stack.pop();
            byte[] data2 = stack.pop();

            boolean equal = Arrays.equals(data1, data2);
            if (!equal) {
                throw new ScriptException(String.format("execute instruction %s failure!", getOpCode().getName()));
            }
        }
    }

    private static class OpRipemd160Instruction extends AbstractInstruction  {

        @Override
        public void execute(OperandStack stack) {
            try {
                byte[] source = stack.pop();
                AssertUtils.notNull(source,
                        "the input source for ripemd160 can not be null!");

                byte[] result = HashUtils.ripeMd160(source);
                stack.push(result);
            } catch (Exception e) {
                String message = String.format("script execution exception, with %s, message:%s", getOpCode(), e.getMessage());
                throw new ScriptException(message);
            }
        }
    }

    private static class OpSha1Instruction extends AbstractInstruction  {

        @Override
        public void execute(OperandStack stack) {
            try {
                byte[] source = stack.pop();
                AssertUtils.notNull(source,
                        "the input source for sha1 can not be null!");
                byte[] result = HashUtils.sha1(source);
                stack.push(result);
            } catch (Exception e) {
                String message = String.format("script execution exception, with %s, message:%s", getOpCode(), e.getMessage());
                throw new ScriptException(message);
            }
        }
    }

    private static class OpSha256Instruction extends AbstractInstruction  {

        @Override
        public void execute(OperandStack stack) {
            try {
                byte[] source = stack.pop();
                AssertUtils.notNull(source,
                        "the input source for sha256 can not be null!");
                byte[] result = HashUtils.sha256(source);
                stack.push(result);
            } catch (Exception e) {
                String message = String.format("script execution exception, with %s, message:%s", getOpCode(), e.getMessage());
                throw new ScriptException(message);
            }
        }
    }

    private static class OpHash160Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            try {
                byte[] source = stack.pop();
                AssertUtils.notNull(source,
                        "the input source for sha256ToHash160 can not be null!");
                byte[] result = HashUtils.sha256ToHash160(source);
                stack.push(result);
            } catch (Exception e) {
                String message = String.format("script execution exception, with %s, message:%s", getOpCode(), e.getMessage());
                throw new ScriptException(message);
            }
        }
    }

    private static class OpCodeSeparatorInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpHash256Instruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            try {
                byte[] source = stack.pop();
                AssertUtils.notNull(source, "the input source for hash256 can not be null!");
                byte[] result = HashUtils.hash256(source);
                stack.push(result);
            } catch (Exception e) {
                String message = String.format("script execution exception with %s, message:%s", getOpCode(), e.getMessage());
                throw new ScriptException(message);
            }
        }
    }

    private static class OpCheckSignatureInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            boolean success = executeCheckSignature(this, stack);
            if (success) {
                stack.push(TRUE);
            } else {
                stack.push(FALSE);
            }
        }
    }

    private static class OpCheckSignatureVerifyInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            boolean success = executeCheckSignature(this, stack);
            if (!success) {
                String message = String.format("script execution exception with %s, check signature and verify failure!", getOpCode());
                throw new ScriptException(message);
            }
        }
    }

    private static class OpCheckMultiSigInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            executeCheckMultiSignature(this, stack);
        }
    }

    private static class OpCheckMultiSigVerifyInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            executeCheckMultiSignature(this, stack);
        }
    }

    private static class OpCheckSigAddInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpCheckLockTimeVerifyAddInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    private static class OpCheckSequenceVerifyAddInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {

        }
    }

    // If occurring in transaction, it should be treated as illegal or invalid transaction
    private static class OpReservedInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            throw new ScriptException("This is reserved operation code which makes the transaction invalid!");
        }
    }

    // If occurring in transaction, it should be treated as illegal or invalid transaction
    private static class OpVerInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            throw new ScriptException("This is reserved operation code which makes the transaction invalid!");
        }
    }

    // Just ignore this kind of operation code definition
    private static class OpNopInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            Logger.info("This is unused operation code for now, just ignore it and do nothing!");
        }
    }

    private static class OpInvalidOpCodeInstruction extends AbstractInstruction {

        @Override
        public void execute(OperandStack stack) {
            throw new ScriptException("This is invalid operation code as for the script system!");
        }
    }

    private static boolean executeCheckSignature(AbstractInstruction instruction, OperandStack operandStack) {
        try {
            ScriptProgram runtime = instruction.getRuntime();
            SignatureContext signatureContext = runtime.getSignatureContext();

            if (operandStack.size() < 2) {
                throw new ScriptException(String.format("script execution exception " +
                        "with %s, no enough elements in operand stack", instruction.getOpCode()));
            }

            byte[] pubKeyBytes = operandStack.pop();
            byte[] signatureBytes = operandStack.pop();

            TransactionSignature transactionSignature = TransactionSignature.decode(signatureBytes, true, true);
            SignatureHashType signatureHashType = transactionSignature.getType();

            Hash hash = signatureContext.generateHashForSignature(signatureHashType);
            return ECDSATool.verifySignature(hash, pubKeyBytes, transactionSignature);
        } catch (SignatureDecodeException se) {
            throw new ScriptException(String.format("script execution exception " +
                    "with %s, signature decode failure!", instruction.getOpCode()));
        } catch (IOException e) {
            throw new ScriptException(String.format("script execution exception " +
                    "with %s, generate hash for signature failure!", instruction.getOpCode()));
        }
    }

    private static void executeCheckMultiSignature(AbstractInstruction instruction, OperandStack operandStack) {

    }
}