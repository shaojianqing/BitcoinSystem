package sjq.bitcoin.script;

import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.utility.AssertUtils;
import sjq.bitcoin.utility.ByteUtils;
import sjq.bitcoin.utility.CryptoUtils;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static sjq.bitcoin.script.OperandStack.MAX_SCRIPT_ELEMENT_SIZE;

public class InstructionTable {

    private static final byte[] EMPTY = new byte[]{};

    private static Map<Short, Class<? extends Instruction>> instructionMap = new HashMap<>();

    static {
        instructionMap.put(ScriptOpcode.OP_PUSH_1, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_2, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_3, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_4, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_5, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_6, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_7, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_8, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_9, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_10, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_11, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_12, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_13, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_14, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_15, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_16, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_17, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_18, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_19, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_20, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_21, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_22, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_23, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_24, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_25, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_26, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_27, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_28, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_29, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_30, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_31, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_32, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_33, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_34, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_35, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_36, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_37, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_38, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_39, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_40, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_41, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_42, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_43, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_44, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_45, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_46, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_47, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_48, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_49, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_50, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_51, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_52, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_53, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_54, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_55, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_56, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_57, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_58, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_59, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_60, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_61, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_62, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_63, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_64, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_65, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_66, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_67, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_68, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_69, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_70, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_71, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_72, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_73, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_74, OpPushInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSH_75, OpPushInstruction.class);

        instructionMap.put(ScriptOpcode.OP_1NEGATE, OpPushNegative1Instruction.class);

        instructionMap.put(ScriptOpcode.OP_PUSHDATA1, OpPushData1Instruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSHDATA2, OpPushData2Instruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSHDATA4, OpPushData4Instruction.class);

        instructionMap.put(ScriptOpcode.OP_IF, OpIfInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NOTIF, OpNotIfInstruction.class);
        instructionMap.put(ScriptOpcode.OP_ELSE, OpElseInstruction.class);
        instructionMap.put(ScriptOpcode.OP_ENDIF, OpEndIfInstruction.class);
        instructionMap.put(ScriptOpcode.OP_VERIFY, OpVerifyInstruction.class);
        instructionMap.put(ScriptOpcode.OP_RETURN, OpReturnInstruction.class);

        instructionMap.put(ScriptOpcode.OP_TOALTSTACK, OpToAltStackInstruction.class);
        instructionMap.put(ScriptOpcode.OP_FROMALTSTACK, OpFromAltStackInstruction.class);
        instructionMap.put(ScriptOpcode.OP_IFDUP, OpIfDupInstruction.class);
        instructionMap.put(ScriptOpcode.OP_DEPTH, OpDepthInstruction.class);
        instructionMap.put(ScriptOpcode.OP_DROP, OpDropInstruction.class);
        instructionMap.put(ScriptOpcode.OP_DUP, OpDupInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NIP, OpNipInstruction.class);
        instructionMap.put(ScriptOpcode.OP_OVER, OpOverInstruction.class);
        instructionMap.put(ScriptOpcode.OP_PICK, OpPickInstruction.class);
        instructionMap.put(ScriptOpcode.OP_ROLL, OpRollInstruction.class);
        instructionMap.put(ScriptOpcode.OP_ROT, OpRotInstruction.class);
        instructionMap.put(ScriptOpcode.OP_SWAP, OpSwapInstruction.class);
        instructionMap.put(ScriptOpcode.OP_TUCK, OpTuckInstruction.class);
        instructionMap.put(ScriptOpcode.OP_2DROP, OpDrop2Instruction.class);
        instructionMap.put(ScriptOpcode.OP_2DUP, OpDup2Instruction.class);
        instructionMap.put(ScriptOpcode.OP_3DUP, OpDup3Instruction.class);
        instructionMap.put(ScriptOpcode.OP_2OVER, OpOver2Instruction.class);
        instructionMap.put(ScriptOpcode.OP_2ROT, OpRot2Instruction.class);
        instructionMap.put(ScriptOpcode.OP_2SWAP, OpSwap2Instruction.class);

        instructionMap.put(ScriptOpcode.OP_CAT, OpCatInstruction.class);
        instructionMap.put(ScriptOpcode.OP_SUBSTR, OpSubStrInstruction.class);
        instructionMap.put(ScriptOpcode.OP_LEFT, OpLeftInstruction.class);
        instructionMap.put(ScriptOpcode.OP_RIGHT, OpRightInstruction.class);
        instructionMap.put(ScriptOpcode.OP_SIZE, OpSizeInstruction.class);

        instructionMap.put(ScriptOpcode.OP_1ADD, OpAdd1Instruction.class);
        instructionMap.put(ScriptOpcode.OP_1SUB, OpSub1Instruction.class);
        instructionMap.put(ScriptOpcode.OP_2MUL, OpMul2Instruction.class);
        instructionMap.put(ScriptOpcode.OP_2DIV, OpDiv2Instruction.class);
        instructionMap.put(ScriptOpcode.OP_NEGATE, OpNegateInstruction.class);
        instructionMap.put(ScriptOpcode.OP_ABS, OpAbsInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NOT, OpNotInstruction.class);
        instructionMap.put(ScriptOpcode.OP_0NOTEQUAL, OpNotEqual0Instruction.class);
        instructionMap.put(ScriptOpcode.OP_ADD, OpAddInstruction.class);
        instructionMap.put(ScriptOpcode.OP_SUB, OpSubInstruction.class);
        instructionMap.put(ScriptOpcode.OP_MUL, OpMulInstruction.class);
        instructionMap.put(ScriptOpcode.OP_DIV, OpDivInstruction.class);
        instructionMap.put(ScriptOpcode.OP_MOD, OpModInstruction.class);
        instructionMap.put(ScriptOpcode.OP_LSHIFT, OpShiftLeftInstruction.class);
        instructionMap.put(ScriptOpcode.OP_RSHIFT, OpShiftRightInstruction.class);
        instructionMap.put(ScriptOpcode.OP_BOOLAND, OpBoolAndInstruction.class);
        instructionMap.put(ScriptOpcode.OP_BOOLOR, OpBoolOrInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NUMEQUAL, OpNumEqualInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NUMEQUALVERIFY, OpNumEqualVerifyInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NUMNOTEQUAL, OpNumNotEqualInstruction.class);
        instructionMap.put(ScriptOpcode.OP_LESSTHAN, OpLessThanInstruction.class);
        instructionMap.put(ScriptOpcode.OP_GREATERTHAN, OpGreaterThanInstruction.class);
        instructionMap.put(ScriptOpcode.OP_LESSTHANOREQUAL, OpLessThanOrEqualInstruction.class);
        instructionMap.put(ScriptOpcode.OP_GREATERTHANOREQUAL, OpGreaterThanOrEqualInstruction.class);
        instructionMap.put(ScriptOpcode.OP_MIN, OpMinInstruction.class);
        instructionMap.put(ScriptOpcode.OP_MAX, OpMaxInstruction.class);
        instructionMap.put(ScriptOpcode.OP_WITHIN, OpWithinInstruction.class);

        instructionMap.put(ScriptOpcode.OP_INVERT, OpInvertInstruction.class);
        instructionMap.put(ScriptOpcode.OP_AND, OpAndInstruction.class);
        instructionMap.put(ScriptOpcode.OP_OR, OpOrInstruction.class);
        instructionMap.put(ScriptOpcode.OP_XOR, OpXorInstruction.class);
        instructionMap.put(ScriptOpcode.OP_EQUAL, OpEqualInstruction.class);
        instructionMap.put(ScriptOpcode.OP_EQUALVERIFY, OpEqualVerifyInstruction.class);

        instructionMap.put(ScriptOpcode.OP_RIPEMD160, OpRipemd160Instruction.class);
        instructionMap.put(ScriptOpcode.OP_SHA1, OpSha1Instruction.class);
        instructionMap.put(ScriptOpcode.OP_SHA256, OpSha256Instruction.class);
        instructionMap.put(ScriptOpcode.OP_HASH160, OpHash160Instruction.class);
        instructionMap.put(ScriptOpcode.OP_HASH256, OpHash256Instruction.class);
        instructionMap.put(ScriptOpcode.OP_CODESEPARATOR, OpCodeSeparatorInstruction.class);
        instructionMap.put(ScriptOpcode.OP_CHECKSIG, OpCheckSigInstruction.class);
        instructionMap.put(ScriptOpcode.OP_CHECKSIGVERIFY, OpCheckSigVerifyInstruction.class);
        instructionMap.put(ScriptOpcode.OP_CHECKMULTISIG, OpCheckMultiSigInstruction.class);
        instructionMap.put(ScriptOpcode.OP_CHECKMULTISIGVERIFY, OpCheckMultiSigVerifyInstruction.class);
        instructionMap.put(ScriptOpcode.OP_CHECKSIGADD, OpCheckSigAddInstruction.class);

        instructionMap.put(ScriptOpcode.OP_CHECKLOCKTIMEVERIFY, OpCheckLockTimeVerifyAddInstruction.class);
        instructionMap.put(ScriptOpcode.OP_CHECKSEQUENCEVERIFY, OpCheckSequenceVerifyAddInstruction.class);

        instructionMap.put(ScriptOpcode.OP_NOP, OpNopInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NOP1, OpNopInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NOP4, OpNopInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NOP5, OpNopInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NOP6, OpNopInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NOP7, OpNopInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NOP8, OpNopInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NOP9, OpNopInstruction.class);
        instructionMap.put(ScriptOpcode.OP_NOP10, OpNopInstruction.class);

        instructionMap.put(ScriptOpcode.OP_RESERVED, OpReservedInstruction.class);
        instructionMap.put(ScriptOpcode.OP_RESERVED1, OpReservedInstruction.class);
        instructionMap.put(ScriptOpcode.OP_RESERVED2, OpReservedInstruction.class);

        instructionMap.put(ScriptOpcode.OP_VER, OpVerInstruction.class);
        instructionMap.put(ScriptOpcode.OP_VERIF, OpVerInstruction.class);
        instructionMap.put(ScriptOpcode.OP_VERNOTIF, OpVerInstruction.class);

        instructionMap.put(ScriptOpcode.OP_INVALIDOPCODE, OpInvalidOpCodeInstruction.class);
    }

    public static Instruction newInstructionByOpCode(short opCode) throws Exception {
        if (instructionMap.containsKey(opCode)) {
            Class<? extends Instruction> clazz = instructionMap.get(opCode);
            Instruction instruction = clazz.getConstructor().newInstance();
            instruction.setOpCode(opCode);
            return instruction;
        }
        throw new ScriptException(String.format("can not find instruction for opCode:%d", opCode));
    }

    private static abstract class AbstractInstruction implements Instruction {

        protected short code;

        protected String name;

        public AbstractInstruction() {
        }

        public AbstractInstruction(short opCode, String opName) {
            this.code = opCode;
            this.name = opName;
        }

        public void fetch(ByteBuffer scriptBuffer) {
        }

        public void execute(OperandStack stack) {
        }

        public int getInstructionSize() {
            int operCodeLength = 1;
            int dataSizeLength = 0;
            if (code == ScriptOpcode.OP_PUSHDATA1) {
                dataSizeLength = 1;
            } else if (code == ScriptOpcode.OP_PUSHDATA2) {
                dataSizeLength = 2;
            } else if (code == ScriptOpcode.OP_PUSHDATA4) {
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
            if (code > ScriptOpcode.OP_PUSHDATA4) {
                buffer.put((byte)code);
            } else if (operand != null) {
                if (code < ScriptOpcode.OP_PUSHDATA1) {
                    checkInstruction(code == operand.length, "code and operand length do not match!");
                    buffer.put((byte)code);
                } else if (code == ScriptOpcode.OP_PUSHDATA1) {
                    checkInstruction(operand.length <= 0xFF, "operand length is not correct!");
                    buffer.put((byte) code);
                    buffer.put((byte) operand.length);
                } else if (code == ScriptOpcode.OP_PUSHDATA2) {
                    checkInstruction(operand.length <= 0xFFFF, "operand length is not correct!");
                    buffer.put((byte) code);
                    ByteUtils.writeInt16LE(operand.length, buffer);
                } else {
                    checkInstruction(operand.length <= MAX_SCRIPT_ELEMENT_SIZE, "operand length is not correct!");
                    buffer.put((byte) code);
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

        public short getOpCode() {
            return code;
        }

        public void setOpCode(short opCode) {
            this.code = opCode;
        }

        public String getOpName() {
            return name;
        }

        public byte[] getOperand() {
            return null;
        }

        public void setOperand(byte[] operand) {
        }
    }

    private static class OpPushInstruction extends AbstractInstruction {

        private byte[] data;

        public OpPushInstruction() {
            super((short)0, "OP_PUSH");
        }

        public void fetch(ByteBuffer scriptBuffer) {
            int length = code;
            data = ByteUtils.readBytesByLength(scriptBuffer, length);
        }

        public void execute(OperandStack stack) {
            if (code==0) {
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

        public OpPushData1Instruction() {
            super(ScriptOpcode.OP_PUSHDATA1, "OP_PUSHDATA1");
        }

        public void fetch(ByteBuffer scriptBuffer) {
            byte length = ByteUtils.readByte(scriptBuffer);
            data = ByteUtils.readBytesByLength(scriptBuffer, length);
        }

        public void execute(OperandStack stack) {
            stack.push(data);
        }
    }

    private static class OpPushData2Instruction extends AbstractInstruction {

        private byte[] data;

        public OpPushData2Instruction() {
            super(ScriptOpcode.OP_PUSHDATA2, "OP_PUSHDATA2");
        }

        public void fetch(ByteBuffer scriptBuffer) {
            short length = ByteUtils.readUint16LE(scriptBuffer);
            data = ByteUtils.readBytesByLength(scriptBuffer, length);
        }

        public void execute(OperandStack stack) {
            stack.push(data);
        }
    }

    private static class OpPushData4Instruction extends AbstractInstruction {

        private byte[] data;

        public OpPushData4Instruction() {
            super(ScriptOpcode.OP_PUSHDATA4, "OP_PUSHDATA4");
        }

        public void fetch(ByteBuffer scriptBuffer) {
            int length = ByteUtils.readUint32LE(scriptBuffer);
            data = ByteUtils.readBytesByLength(scriptBuffer, length);
        }

        public void execute(OperandStack stack) {
            stack.push(data);
        }
    }

    private static class OpPushNegative1Instruction extends AbstractInstruction {

        public OpPushNegative1Instruction() {
            super(ScriptOpcode.OP_1NEGATE, "OP_1NEGATE");
        }

        public void execute(OperandStack stack) {
            byte[] items = new byte[]{-1};
            stack.push(items);
        }
    }

    private static class OpIfInstruction extends AbstractInstruction {

        public OpIfInstruction() {
            super(ScriptOpcode.OP_IF, "OP_IF");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpNotIfInstruction extends AbstractInstruction {

        public OpNotIfInstruction() {
            super(ScriptOpcode.OP_NOTIF, "OP_NOTIF");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpElseInstruction extends AbstractInstruction {

        public OpElseInstruction() {
            super(ScriptOpcode.OP_ELSE, "OP_ELSE");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpEndIfInstruction extends AbstractInstruction {

        public OpEndIfInstruction() {
            super(ScriptOpcode.OP_ENDIF, "OP_ENDIF");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpVerifyInstruction extends AbstractInstruction {

        public OpVerifyInstruction() {
            super(ScriptOpcode.OP_VERIFY, "OP_VERIFY");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpReturnInstruction extends AbstractInstruction {

        public OpReturnInstruction() {
            super(ScriptOpcode.OP_RETURN, "OP_RETURN");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpToAltStackInstruction extends AbstractInstruction {

        public OpToAltStackInstruction() {
            super(ScriptOpcode.OP_TOALTSTACK, "OP_TOALTSTACK");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpFromAltStackInstruction extends AbstractInstruction {

        public OpFromAltStackInstruction() {
            super(ScriptOpcode.OP_FROMALTSTACK, "OP_FROMALTSTACK");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpIfDupInstruction extends AbstractInstruction {

        public OpIfDupInstruction() {
            super(ScriptOpcode.OP_IFDUP, "OP_IFDUP");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpDepthInstruction extends AbstractInstruction {

        public OpDepthInstruction() {
            super(ScriptOpcode.OP_DEPTH, "OP_DEPTH");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpDropInstruction extends AbstractInstruction {

        public OpDropInstruction() {
            super(ScriptOpcode.OP_DROP, "OP_DROP");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpDupInstruction extends AbstractInstruction {

        public OpDupInstruction() {
            super(ScriptOpcode.OP_DUP, "OP_DUP");
        }

        public void execute(OperandStack stack) {
            byte[] element = stack.peek();
            stack.push(element);
        }
    }

    private static class OpNipInstruction extends AbstractInstruction {

        public OpNipInstruction() {
            super(ScriptOpcode.OP_NIP, "OP_NIP");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpOverInstruction extends AbstractInstruction {

        public OpOverInstruction() {
            super(ScriptOpcode.OP_OVER, "OP_OVER");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpPickInstruction extends AbstractInstruction {

        public OpPickInstruction() {
            super(ScriptOpcode.OP_PICK, "OP_PICK");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpRollInstruction extends AbstractInstruction {

        public OpRollInstruction() {
            super(ScriptOpcode.OP_ROLL, "OP_ROLL");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpRotInstruction extends AbstractInstruction {

        public OpRotInstruction() {
            super(ScriptOpcode.OP_ROT, "OP_ROT");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpSwapInstruction extends AbstractInstruction {

        public OpSwapInstruction() {
            super(ScriptOpcode.OP_SWAP, "OP_SWAP");
        }

        public void execute(OperandStack stack) {
            byte[] element2 = stack.pop();
            byte[] element1 = stack.pop();

            stack.push(element2);
            stack.push(element1);
        }
    }

    private static class OpTuckInstruction extends AbstractInstruction {

        public OpTuckInstruction() {
            super(ScriptOpcode.OP_TUCK, "OP_TUCK");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpDrop2Instruction extends AbstractInstruction {

        public OpDrop2Instruction() {
            super(ScriptOpcode.OP_2DROP, "OP_2DROP");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpDup2Instruction extends AbstractInstruction {

        public OpDup2Instruction() {
            super(ScriptOpcode.OP_2DUP, "OP_2DUP");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpDup3Instruction extends AbstractInstruction {

        public OpDup3Instruction() {
            super(ScriptOpcode.OP_3DUP, "OP_3DUP");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpOver2Instruction extends AbstractInstruction {

        public OpOver2Instruction() {
            super(ScriptOpcode.OP_2OVER, "OP_2OVER");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpRot2Instruction extends AbstractInstruction {

        public OpRot2Instruction() {
            super(ScriptOpcode.OP_2ROT, "OP_2ROT");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpSwap2Instruction extends AbstractInstruction {

        public OpSwap2Instruction() {
            super(ScriptOpcode.OP_2SWAP, "OP_2SWAP");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpCatInstruction extends AbstractInstruction {

        public OpCatInstruction() {
            super(ScriptOpcode.OP_CAT, "OP_CAT");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpSubStrInstruction extends AbstractInstruction {

        public OpSubStrInstruction() {
            super(ScriptOpcode.OP_SUBSTR, "OP_SUBSTR");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpInstruction extends AbstractInstruction {

        public OpInstruction() {
            super(ScriptOpcode.OP_RETURN, "OP_RETURN");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpLeftInstruction extends AbstractInstruction {

        public OpLeftInstruction() {
            super(ScriptOpcode.OP_LEFT, "OP_LEFT");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpRightInstruction extends AbstractInstruction {

        public OpRightInstruction() {
            super(ScriptOpcode.OP_RIGHT, "OP_RIGHT");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpSizeInstruction extends AbstractInstruction {

        public OpSizeInstruction() {
            super(ScriptOpcode.OP_SIZE, "OP_SIZE");
        }

        public void execute(OperandStack stack) {

        }
    }


    private static class OpAdd1Instruction extends AbstractInstruction {

        public OpAdd1Instruction() {
            super(ScriptOpcode.OP_1ADD, "OP_1ADD");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpSub1Instruction extends AbstractInstruction {

        public OpSub1Instruction() {
            super(ScriptOpcode.OP_1SUB, "OP_1SUB");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpMul2Instruction extends AbstractInstruction {

        public OpMul2Instruction() {
            super(ScriptOpcode.OP_2MUL, "OP_2MUL");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpDiv2Instruction extends AbstractInstruction {

        public OpDiv2Instruction() {
            super(ScriptOpcode.OP_2DIV, "OP_2DIV");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpNegateInstruction extends AbstractInstruction {

        public OpNegateInstruction() {
            super(ScriptOpcode.OP_NEGATE, "OP_NEGATE");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpAbsInstruction extends AbstractInstruction {

        public OpAbsInstruction() {
            super(ScriptOpcode.OP_ABS, "OP_ABS");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpNotInstruction extends AbstractInstruction {

        public OpNotInstruction() {
            super(ScriptOpcode.OP_NOT, "OP_NOT");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpNotEqual0Instruction extends AbstractInstruction {

        public OpNotEqual0Instruction() {
            super(ScriptOpcode.OP_0NOTEQUAL, "OP_0NOTEQUAL");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpAddInstruction extends AbstractInstruction {

        public OpAddInstruction() {
            super(ScriptOpcode.OP_ADD, "OP_ADD");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpSubInstruction extends AbstractInstruction {

        public OpSubInstruction() {
            super(ScriptOpcode.OP_SUB, "OP_SUB");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpMulInstruction extends AbstractInstruction {

        public OpMulInstruction() {
            super(ScriptOpcode.OP_MUL, "OP_MUL");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpDivInstruction extends AbstractInstruction {

        public OpDivInstruction() {
            super(ScriptOpcode.OP_DIV, "OP_DIV");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpModInstruction extends AbstractInstruction {

        public OpModInstruction() {
            super(ScriptOpcode.OP_MOD, "OP_MOD");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpShiftLeftInstruction extends AbstractInstruction {

        public OpShiftLeftInstruction() {
            super(ScriptOpcode.OP_LSHIFT, "OP_LSHIFT");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpShiftRightInstruction extends AbstractInstruction {

        public OpShiftRightInstruction() {
            super(ScriptOpcode.OP_RSHIFT, "OP_RSHIFT");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpBoolAndInstruction extends AbstractInstruction {

        public OpBoolAndInstruction() {
            super(ScriptOpcode.OP_BOOLAND, "OP_BOOLAND");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpBoolOrInstruction extends AbstractInstruction {

        public OpBoolOrInstruction() {
            super(ScriptOpcode.OP_BOOLOR, "OP_BOOLOR");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpNumEqualInstruction extends AbstractInstruction {

        public OpNumEqualInstruction() {
            super(ScriptOpcode.OP_NUMEQUAL, "OP_NUMEQUAL");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpNumEqualVerifyInstruction extends AbstractInstruction {

        public OpNumEqualVerifyInstruction() {
            super(ScriptOpcode.OP_NUMEQUALVERIFY, "OP_NUMEQUALVERIFY");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpNumNotEqualInstruction extends AbstractInstruction {

        public OpNumNotEqualInstruction() {
            super(ScriptOpcode.OP_NUMNOTEQUAL, "OP_NUMNOTEQUAL");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpLessThanInstruction extends AbstractInstruction {

        public OpLessThanInstruction() {
            super(ScriptOpcode.OP_LESSTHAN, "OP_LESSTHAN");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpGreaterThanInstruction extends AbstractInstruction {

        public OpGreaterThanInstruction() {
            super(ScriptOpcode.OP_GREATERTHAN, "OP_GREATERTHAN");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpLessThanOrEqualInstruction extends AbstractInstruction {

        public OpLessThanOrEqualInstruction() {
            super(ScriptOpcode.OP_LESSTHANOREQUAL, "OP_LESSTHANOREQUAL");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpGreaterThanOrEqualInstruction extends AbstractInstruction {

        public OpGreaterThanOrEqualInstruction() {
            super(ScriptOpcode.OP_GREATERTHANOREQUAL, "OP_GREATERTHANOREQUAL");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpMinInstruction extends AbstractInstruction {

        public OpMinInstruction() {
            super(ScriptOpcode.OP_MIN, "OP_MIN");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpMaxInstruction extends AbstractInstruction {

        public OpMaxInstruction() {
            super(ScriptOpcode.OP_MAX, "OP_MAX");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpWithinInstruction extends AbstractInstruction {

        public OpWithinInstruction() {
            super(ScriptOpcode.OP_WITHIN, "OP_WITHIN");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpInvertInstruction extends AbstractInstruction {

        public OpInvertInstruction() {
            super(ScriptOpcode.OP_INVERT, "OP_INVERT");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpAndInstruction extends AbstractInstruction {

        public OpAndInstruction() {
            super(ScriptOpcode.OP_AND, "OP_AND");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpOrInstruction extends AbstractInstruction {

        public OpOrInstruction() {
            super(ScriptOpcode.OP_OR, "OP_OR");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpXorInstruction extends AbstractInstruction {

        public OpXorInstruction() {
            super(ScriptOpcode.OP_XOR, "OP_XOR");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpEqualInstruction extends AbstractInstruction {

        public OpEqualInstruction() {
            super(ScriptOpcode.OP_EQUAL, "OP_EQUAL");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpEqualVerifyInstruction extends AbstractInstruction {

        public OpEqualVerifyInstruction() {
            super(ScriptOpcode.OP_EQUALVERIFY, "OP_EQUALVERIFY");
        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpRipemd160Instruction extends AbstractInstruction  {

        public OpRipemd160Instruction() {
            super(ScriptOpcode.OP_RIPEMD160, "OP_RIPEMD160");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpSha1Instruction extends AbstractInstruction  {

        public OpSha1Instruction() {
            super(ScriptOpcode.OP_SHA1, "OP_SHA1");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpSha256Instruction extends AbstractInstruction  {

        public OpSha256Instruction() {
            super(ScriptOpcode.OP_SHA256, "OP_SHA256");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {
            try {
                byte[] source = stack.pop();
                AssertUtils.notNull(source,
                        "the input source for sha256 can not be null!");
                byte[] result = CryptoUtils.sha256(source);
                stack.push(result);
            } catch (Exception e) {
                String message = String.format("script execution exception, " +
                        "opCode:%s, opName:%s, message:%s", getOpCode(), getOpName(), e.getMessage());
                throw new ScriptException(message);
            }
        }
    }

    private static class OpHash160Instruction extends AbstractInstruction {

        public OpHash160Instruction() {
            super(ScriptOpcode.OP_HASH160, "OP_HASH160");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpCodeSeparatorInstruction extends AbstractInstruction {

        public OpCodeSeparatorInstruction() {
            super(ScriptOpcode.OP_CODESEPARATOR, "OP_CODESEPARATOR");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpHash256Instruction extends AbstractInstruction {

        public OpHash256Instruction() {
            super(ScriptOpcode.OP_HASH256, "OP_HASH256");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {
            try {
                byte[] source = stack.pop();
                AssertUtils.notNull(source, "the input source for hash256 can not be null!");
                byte[] result = CryptoUtils.hash256(source);
                stack.push(result);
            } catch (Exception e) {
                String message = String.format("script execution exception, " +
                        "opCode:%s, opName:%s, message:%s", getOpCode(), getOpName(), e.getMessage());
                throw new ScriptException(message);
            }
        }
    }

    private static class OpCheckSigVerifyInstruction extends AbstractInstruction {

        public OpCheckSigVerifyInstruction() {
            super(ScriptOpcode.OP_CHECKSIGVERIFY, "OP_CHECKSIGVERIFY");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpCheckSigInstruction extends AbstractInstruction {

        public OpCheckSigInstruction() {
            super(ScriptOpcode.OP_CHECKSIG, "OP_CHECKSIG");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpCheckMultiSigInstruction extends AbstractInstruction {

        public OpCheckMultiSigInstruction() {
            super(ScriptOpcode.OP_CHECKMULTISIG, "OP_CHECKMULTISIG");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpCheckMultiSigVerifyInstruction extends AbstractInstruction {

        public OpCheckMultiSigVerifyInstruction() {
            super(ScriptOpcode.OP_CHECKMULTISIGVERIFY, "OP_CHECKMULTISIGVERIFY");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpCheckSigAddInstruction extends AbstractInstruction {

        public OpCheckSigAddInstruction() {
            super(ScriptOpcode.OP_CHECKSIGADD, "OP_CHECKSIGADD");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpCheckLockTimeVerifyAddInstruction extends AbstractInstruction {

        public OpCheckLockTimeVerifyAddInstruction() {
            super(ScriptOpcode.OP_CHECKLOCKTIMEVERIFY, "OP_CHECKLOCKTIMEVERIFY");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpCheckSequenceVerifyAddInstruction extends AbstractInstruction {

        public OpCheckSequenceVerifyAddInstruction() {
            super(ScriptOpcode.OP_CHECKSEQUENCEVERIFY, "OP_CHECKSEQUENCEVERIFY");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

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
}