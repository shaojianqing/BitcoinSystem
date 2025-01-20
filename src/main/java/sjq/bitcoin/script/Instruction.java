package sjq.bitcoin.script;

import java.nio.ByteBuffer;

public interface Instruction {

    String getOpName();

    void fetch(ByteBuffer scriptBuffer);

    void execute(OperandStack stack);

    short getOpCode();

    void setOpCode(short opCode);

    byte[] getOperand();

    void setOperand(byte[] operand);

    int getInstructionSize();

    byte[] getInstructionByte();
}
