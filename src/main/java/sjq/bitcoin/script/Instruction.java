package sjq.bitcoin.script;

import java.nio.ByteBuffer;

public interface Instruction {

    short getOpCode();

    String getOpName();

    byte[] getOpData();

    void fetch(ByteBuffer scriptBuffer);

    void execute(OperandStack stack);
}
