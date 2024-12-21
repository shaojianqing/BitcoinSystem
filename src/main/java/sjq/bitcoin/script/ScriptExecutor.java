package sjq.bitcoin.script;


import java.nio.ByteBuffer;

public class ScriptExecutor {

    public static boolean verify(byte[] scriptData) {
        OperandStack operandStack = new OperandStack();
        ByteBuffer scriptBuffer = ByteBuffer.wrap(scriptData);

        while(scriptBuffer.position()<scriptBuffer.limit()) {
            short opCode = scriptBuffer.get();
            Instruction instruction = InstructionTable.getInstructionByOpCode(opCode);
            instruction.execute(scriptBuffer,operandStack);
        }

        return operandStack.getResult();
    }
}
