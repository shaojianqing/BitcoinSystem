package sjq.bitcoin.script;

import java.nio.ByteBuffer;

public interface Instruction {

    void fetch(ByteBuffer scriptBuffer);

    void execute(OperandStack stack);

    ScriptOpCode getOpCode();

    void setOpCode(ScriptOpCode opCode);

    boolean isOpCode();

    byte[] getOperand();

    void setOperand(byte[] operand);

    int getInstructionSize();

    byte[] getInstructionByte();
}
