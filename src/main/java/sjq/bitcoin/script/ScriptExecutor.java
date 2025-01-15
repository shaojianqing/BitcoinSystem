package sjq.bitcoin.script;


import java.util.List;

public class ScriptExecutor {

    public static boolean verify(ScriptProgram program) {
        OperandStack operandStack = new OperandStack();
        List<Instruction> instructions = program.getInstructions();

        for(Instruction instruction:instructions) {
            instruction.execute(operandStack);
        }

        return operandStack.peekSuccess();
    }
}
