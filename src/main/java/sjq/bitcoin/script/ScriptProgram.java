package sjq.bitcoin.script;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ScriptProgram {

    private List<Instruction> instructions = new ArrayList<Instruction>();

    public static ScriptProgram parse(byte[] scriptData) {

        ByteBuffer scriptBuffer = ByteBuffer.wrap(scriptData);
        ScriptProgram program = new ScriptProgram();

        while(scriptBuffer.position()<scriptBuffer.limit()) {
            short opCode = scriptBuffer.get();
            Instruction instruction = InstructionTable.getInstructionByOpCode(opCode);
            instruction.fetch(scriptBuffer);
            program.addInstruction(instruction);
        }

        return program;
    }

    public boolean isP2PK() {
        return false;
    }

    public boolean isP2PKH() {
        return false;
    }

    public boolean isP2SH() {
        return false;
    }

    public boolean isP2WPKH() {
        return false;
    }

    private void addInstruction(Instruction instruction) {
        if (instruction!=null) {
            instructions.add(instruction);
        }
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }
}
