package sjq.bitcoin.script;

import sjq.bitcoin.utility.AssertUtils;
import sjq.bitcoin.utility.ByteUtils;
import sjq.bitcoin.utility.CryptoUtils;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class InstructionTable {

    private static final byte[] EMPTY = new byte[]{};

    private static Map<Short, Instruction> instructionMap = new HashMap<Short, Instruction>();

    static {

        instructionMap.put(ScriptOpcode.OP_PUSH_1, new OpPushInstruction(ScriptOpcode.OP_PUSH_1));
        instructionMap.put(ScriptOpcode.OP_PUSH_2, new OpPushInstruction(ScriptOpcode.OP_PUSH_2));
        instructionMap.put(ScriptOpcode.OP_PUSH_3, new OpPushInstruction(ScriptOpcode.OP_PUSH_3));
        instructionMap.put(ScriptOpcode.OP_PUSH_4, new OpPushInstruction(ScriptOpcode.OP_PUSH_4));
        instructionMap.put(ScriptOpcode.OP_PUSH_5, new OpPushInstruction(ScriptOpcode.OP_PUSH_5));
        instructionMap.put(ScriptOpcode.OP_PUSH_6, new OpPushInstruction(ScriptOpcode.OP_PUSH_6));
        instructionMap.put(ScriptOpcode.OP_PUSH_7, new OpPushInstruction(ScriptOpcode.OP_PUSH_7));
        instructionMap.put(ScriptOpcode.OP_PUSH_8, new OpPushInstruction(ScriptOpcode.OP_PUSH_8));
        instructionMap.put(ScriptOpcode.OP_PUSH_9, new OpPushInstruction(ScriptOpcode.OP_PUSH_9));
        instructionMap.put(ScriptOpcode.OP_PUSH_10, new OpPushInstruction(ScriptOpcode.OP_PUSH_10));
        instructionMap.put(ScriptOpcode.OP_PUSH_11, new OpPushInstruction(ScriptOpcode.OP_PUSH_11));
        instructionMap.put(ScriptOpcode.OP_PUSH_12, new OpPushInstruction(ScriptOpcode.OP_PUSH_12));
        instructionMap.put(ScriptOpcode.OP_PUSH_13, new OpPushInstruction(ScriptOpcode.OP_PUSH_13));
        instructionMap.put(ScriptOpcode.OP_PUSH_14, new OpPushInstruction(ScriptOpcode.OP_PUSH_14));
        instructionMap.put(ScriptOpcode.OP_PUSH_15, new OpPushInstruction(ScriptOpcode.OP_PUSH_15));
        instructionMap.put(ScriptOpcode.OP_PUSH_16, new OpPushInstruction(ScriptOpcode.OP_PUSH_16));
        instructionMap.put(ScriptOpcode.OP_PUSH_17, new OpPushInstruction(ScriptOpcode.OP_PUSH_17));
        instructionMap.put(ScriptOpcode.OP_PUSH_18, new OpPushInstruction(ScriptOpcode.OP_PUSH_18));
        instructionMap.put(ScriptOpcode.OP_PUSH_19, new OpPushInstruction(ScriptOpcode.OP_PUSH_19));
        instructionMap.put(ScriptOpcode.OP_PUSH_20, new OpPushInstruction(ScriptOpcode.OP_PUSH_20));
        instructionMap.put(ScriptOpcode.OP_PUSH_21, new OpPushInstruction(ScriptOpcode.OP_PUSH_21));
        instructionMap.put(ScriptOpcode.OP_PUSH_22, new OpPushInstruction(ScriptOpcode.OP_PUSH_22));
        instructionMap.put(ScriptOpcode.OP_PUSH_23, new OpPushInstruction(ScriptOpcode.OP_PUSH_23));
        instructionMap.put(ScriptOpcode.OP_PUSH_24, new OpPushInstruction(ScriptOpcode.OP_PUSH_24));
        instructionMap.put(ScriptOpcode.OP_PUSH_25, new OpPushInstruction(ScriptOpcode.OP_PUSH_25));
        instructionMap.put(ScriptOpcode.OP_PUSH_26, new OpPushInstruction(ScriptOpcode.OP_PUSH_26));
        instructionMap.put(ScriptOpcode.OP_PUSH_27, new OpPushInstruction(ScriptOpcode.OP_PUSH_27));
        instructionMap.put(ScriptOpcode.OP_PUSH_28, new OpPushInstruction(ScriptOpcode.OP_PUSH_28));
        instructionMap.put(ScriptOpcode.OP_PUSH_29, new OpPushInstruction(ScriptOpcode.OP_PUSH_29));
        instructionMap.put(ScriptOpcode.OP_PUSH_30, new OpPushInstruction(ScriptOpcode.OP_PUSH_30));
        instructionMap.put(ScriptOpcode.OP_PUSH_31, new OpPushInstruction(ScriptOpcode.OP_PUSH_31));
        instructionMap.put(ScriptOpcode.OP_PUSH_32, new OpPushInstruction(ScriptOpcode.OP_PUSH_32));
        instructionMap.put(ScriptOpcode.OP_PUSH_33, new OpPushInstruction(ScriptOpcode.OP_PUSH_33));
        instructionMap.put(ScriptOpcode.OP_PUSH_34, new OpPushInstruction(ScriptOpcode.OP_PUSH_34));
        instructionMap.put(ScriptOpcode.OP_PUSH_35, new OpPushInstruction(ScriptOpcode.OP_PUSH_35));
        instructionMap.put(ScriptOpcode.OP_PUSH_36, new OpPushInstruction(ScriptOpcode.OP_PUSH_36));
        instructionMap.put(ScriptOpcode.OP_PUSH_37, new OpPushInstruction(ScriptOpcode.OP_PUSH_37));
        instructionMap.put(ScriptOpcode.OP_PUSH_38, new OpPushInstruction(ScriptOpcode.OP_PUSH_38));
        instructionMap.put(ScriptOpcode.OP_PUSH_39, new OpPushInstruction(ScriptOpcode.OP_PUSH_39));
        instructionMap.put(ScriptOpcode.OP_PUSH_40, new OpPushInstruction(ScriptOpcode.OP_PUSH_40));
        instructionMap.put(ScriptOpcode.OP_PUSH_41, new OpPushInstruction(ScriptOpcode.OP_PUSH_41));
        instructionMap.put(ScriptOpcode.OP_PUSH_42, new OpPushInstruction(ScriptOpcode.OP_PUSH_42));
        instructionMap.put(ScriptOpcode.OP_PUSH_43, new OpPushInstruction(ScriptOpcode.OP_PUSH_43));
        instructionMap.put(ScriptOpcode.OP_PUSH_44, new OpPushInstruction(ScriptOpcode.OP_PUSH_44));
        instructionMap.put(ScriptOpcode.OP_PUSH_45, new OpPushInstruction(ScriptOpcode.OP_PUSH_45));
        instructionMap.put(ScriptOpcode.OP_PUSH_46, new OpPushInstruction(ScriptOpcode.OP_PUSH_46));
        instructionMap.put(ScriptOpcode.OP_PUSH_47, new OpPushInstruction(ScriptOpcode.OP_PUSH_47));
        instructionMap.put(ScriptOpcode.OP_PUSH_48, new OpPushInstruction(ScriptOpcode.OP_PUSH_48));
        instructionMap.put(ScriptOpcode.OP_PUSH_49, new OpPushInstruction(ScriptOpcode.OP_PUSH_49));
        instructionMap.put(ScriptOpcode.OP_PUSH_50, new OpPushInstruction(ScriptOpcode.OP_PUSH_50));
        instructionMap.put(ScriptOpcode.OP_PUSH_51, new OpPushInstruction(ScriptOpcode.OP_PUSH_51));
        instructionMap.put(ScriptOpcode.OP_PUSH_52, new OpPushInstruction(ScriptOpcode.OP_PUSH_52));
        instructionMap.put(ScriptOpcode.OP_PUSH_53, new OpPushInstruction(ScriptOpcode.OP_PUSH_53));
        instructionMap.put(ScriptOpcode.OP_PUSH_54, new OpPushInstruction(ScriptOpcode.OP_PUSH_54));
        instructionMap.put(ScriptOpcode.OP_PUSH_55, new OpPushInstruction(ScriptOpcode.OP_PUSH_55));
        instructionMap.put(ScriptOpcode.OP_PUSH_56, new OpPushInstruction(ScriptOpcode.OP_PUSH_56));
        instructionMap.put(ScriptOpcode.OP_PUSH_57, new OpPushInstruction(ScriptOpcode.OP_PUSH_57));
        instructionMap.put(ScriptOpcode.OP_PUSH_58, new OpPushInstruction(ScriptOpcode.OP_PUSH_58));
        instructionMap.put(ScriptOpcode.OP_PUSH_59, new OpPushInstruction(ScriptOpcode.OP_PUSH_59));
        instructionMap.put(ScriptOpcode.OP_PUSH_60, new OpPushInstruction(ScriptOpcode.OP_PUSH_60));
        instructionMap.put(ScriptOpcode.OP_PUSH_61, new OpPushInstruction(ScriptOpcode.OP_PUSH_61));
        instructionMap.put(ScriptOpcode.OP_PUSH_62, new OpPushInstruction(ScriptOpcode.OP_PUSH_62));
        instructionMap.put(ScriptOpcode.OP_PUSH_63, new OpPushInstruction(ScriptOpcode.OP_PUSH_63));
        instructionMap.put(ScriptOpcode.OP_PUSH_64, new OpPushInstruction(ScriptOpcode.OP_PUSH_63));
        instructionMap.put(ScriptOpcode.OP_PUSH_65, new OpPushInstruction(ScriptOpcode.OP_PUSH_65));
        instructionMap.put(ScriptOpcode.OP_PUSH_66, new OpPushInstruction(ScriptOpcode.OP_PUSH_66));
        instructionMap.put(ScriptOpcode.OP_PUSH_67, new OpPushInstruction(ScriptOpcode.OP_PUSH_67));
        instructionMap.put(ScriptOpcode.OP_PUSH_68, new OpPushInstruction(ScriptOpcode.OP_PUSH_68));
        instructionMap.put(ScriptOpcode.OP_PUSH_69, new OpPushInstruction(ScriptOpcode.OP_PUSH_69));
        instructionMap.put(ScriptOpcode.OP_PUSH_70, new OpPushInstruction(ScriptOpcode.OP_PUSH_70));
        instructionMap.put(ScriptOpcode.OP_PUSH_71, new OpPushInstruction(ScriptOpcode.OP_PUSH_71));
        instructionMap.put(ScriptOpcode.OP_PUSH_72, new OpPushInstruction(ScriptOpcode.OP_PUSH_72));
        instructionMap.put(ScriptOpcode.OP_PUSH_73, new OpPushInstruction(ScriptOpcode.OP_PUSH_73));
        instructionMap.put(ScriptOpcode.OP_PUSH_74, new OpPushInstruction(ScriptOpcode.OP_PUSH_74));
        instructionMap.put(ScriptOpcode.OP_PUSH_75, new OpPushInstruction(ScriptOpcode.OP_PUSH_75));

        instructionMap.put(ScriptOpcode.OP_PUSHDATA1, new OpPushData1Insruction());
        instructionMap.put(ScriptOpcode.OP_PUSHDATA2, new OpPushData2Insruction());
        instructionMap.put(ScriptOpcode.OP_PUSHDATA4, new OpPushData4Insruction());

        instructionMap.put(ScriptOpcode.OP_SHA1, new OpSha256Instruction());
        instructionMap.put(ScriptOpcode.OP_SHA256, new OpSha256Instruction());
        instructionMap.put(ScriptOpcode.OP_HASH256, new OpHash256Instruction());
    }

    public static Instruction getInstructionByOpCode(short opCode) {
        if (instructionMap.containsKey(opCode)) {
            return instructionMap.get(opCode);
        }
        throw new ScriptException(String.format("can not find instruction for opCode:%d", opCode));
    }

    private static class OpPushInstruction implements Instruction {

        private short code;

        private byte[] data;

        public OpPushInstruction(short code) {
            this.code = code;
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

        public short getOpCode() {
            return code;
        }

        public String getOpName() {
            return "OP_PUSH";
        }

        public byte[] getOpData() {
            return data;
        }
    }

    private static class OpPushData1Insruction implements Instruction {

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }

        public short getOpCode() {
            return ScriptOpcode.OP_PUSHDATA1;
        }

        public String getOpName() {
            return "OP_PUSHDATA1";
        }

        public byte[] getOpData() {
            return null;
        }
    }

    private static class OpPushData2Insruction implements Instruction {

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }

        public short getOpCode() {
            return ScriptOpcode.OP_PUSHDATA2;
        }

        public String getOpName() {
            return "OP_PUSHDATA2";
        }

        public byte[] getOpData() {
            return null;
        }
    }

    private static class OpPushData4Insruction implements Instruction {

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }

        public short getOpCode() {
            return ScriptOpcode.OP_PUSHDATA4;
        }

        public String getOpName() {
            return "OP_PUSHDATA4";
        }

        public byte[] getOpData() {
            return null;
        }
    }

    private static class OpSha256Instruction implements Instruction {

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

        public short getOpCode() {
            return ScriptOpcode.OP_SHA256;
        }

        public String getOpName() {
            return "OP_SHA256";
        }

        public byte[] getOpData() {
            return null;
        }
    }

    private static class OpHash256Instruction implements Instruction {

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

        public short getOpCode() {
            return ScriptOpcode.OP_HASH256;
        }

        public String getOpName() {
            return "OP_HASH256";
        }

        public byte[] getOpData() {
            return null;
        }
    }

    private static class OpCheckSigInstruction implements Instruction {

        public void fetch(ByteBuffer scriptBuffer) {

        }

        public void execute(OperandStack stack) {

        }

        public short getOpCode() {
            return ScriptOpcode.OP_CHECKSIG;
        }

        public String getOpName() {
            return "OP_CHECKSIG";
        }

        public byte[] getOpData() {
            return null;
        }
    }
}




