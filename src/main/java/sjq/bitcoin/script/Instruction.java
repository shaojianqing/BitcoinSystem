package sjq.bitcoin.script;

import java.nio.ByteBuffer;

public interface Instruction {

    short getOpCode();

    String getOpName();

    void execute(ByteBuffer scriptBuffer, OperandStack stack);
}
