package sjq.bitcoin.script;

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

        instructionMap.put(ScriptOpcode.OP_PUSHDATA1, OpPushData1Instruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSHDATA2, OpPushData2Instruction.class);
        instructionMap.put(ScriptOpcode.OP_PUSHDATA4, OpPushData4Instruction.class);

        instructionMap.put(ScriptOpcode.OP_SHA1, OpSha256Instruction.class);
        instructionMap.put(ScriptOpcode.OP_SHA256, OpSha256Instruction.class);
        instructionMap.put(ScriptOpcode.OP_HASH256, OpHash256Instruction.class);

        instructionMap.put(ScriptOpcode.OP_CHECKSIG, OpCheckSigInstruction.class);
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

        public AbstractInstruction(short opCode, String opName) {
            this.code = opCode;
            this.name = opName;
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
            data = ByteUtils.readBytesByLength(scriptBuffer, code);
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

        public OpPushData1Instruction() {
            super(ScriptOpcode.OP_PUSHDATA1, "OP_PUSHDATA1");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpPushData2Instruction extends AbstractInstruction {

        public OpPushData2Instruction() {
            super(ScriptOpcode.OP_PUSHDATA2, "OP_PUSHDATA2");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }
    }

    private static class OpPushData4Instruction extends AbstractInstruction {

        public OpPushData4Instruction() {
            super(ScriptOpcode.OP_PUSHDATA4, "OP_PUSHDATA4");
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

    private static class OpHash256Instruction extends AbstractInstruction {

        public OpHash256Instruction() {
            super(ScriptOpcode.OP_HASH256, "OP_HASH256");
        }

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {
            try {
                byte[] source = stack.pop();
                AssertUtils.notNull(source,
                        "the input source for hash256 can not be null!");
                byte[] result = CryptoUtils.hash256(source);
                stack.push(result);
            } catch (Exception e) {
                String message = String.format("script execution exception, " +
                        "opCode:%s, opName:%s, message:%s", getOpCode(), getOpName(), e.getMessage());
                throw new ScriptException(message);
            }
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
}




