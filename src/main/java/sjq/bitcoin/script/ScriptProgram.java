package sjq.bitcoin.script;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptProgram {

    private List<Instruction> instructions = new ArrayList<Instruction>();

    public static ScriptProgram parse(byte[] scriptData) throws Exception {

        ByteBuffer scriptBuffer = ByteBuffer.wrap(scriptData);
        ScriptProgram program = new ScriptProgram();

        while(scriptBuffer.position()<scriptBuffer.limit()) {
            short opCode = scriptBuffer.get();
            Instruction instruction = InstructionTable.newInstructionByOpCode(opCode);
            instruction.fetch(scriptBuffer);
            program.addInstruction(instruction);
        }

        return program;
    }

    public static ScriptProgram build() {
        return new ScriptProgram();
    }

    public ScriptProgram data(byte[] data) throws Exception {
        byte[] operand = Arrays.copyOf(data, data.length);
        short operCode = ScriptOpcode.OP_0;
        if (operand.length==1) {

        } else if (operand.length<ScriptOpcode.OP_PUSHDATA1) {
            operCode = (short)operand.length;
        } else if (operand.length<256) {
            operCode = ScriptOpcode.OP_PUSHDATA1;
        } else if (operand.length<65536) {
            operCode = ScriptOpcode.OP_PUSHDATA2;
        }

        Instruction instruction = InstructionTable.newInstructionByOpCode(operCode);
        instruction.setOperand(operand);
        int position = this.instructions.size();
        this.instructions.add(position, instruction);

        return this;
    }

    public ScriptProgram opCode(short opCode) throws Exception {
        if (opCode <= ScriptOpcode.OP_PUSHDATA4) {
            throw new ScriptException(String.format("opCode is not correct with value:%d", opCode));
        }
        Instruction instruction = InstructionTable.newInstructionByOpCode(opCode);
        int position = this.instructions.size();
        this.instructions.add(position, instruction);
        return this;
    }

    public byte[] program() {
        int totalInstructionSize = instructions.stream().mapToInt(Instruction::getInstructionSize).sum();
        ByteBuffer buffer = ByteBuffer.allocate(totalInstructionSize);
        for (Instruction instruction:instructions) {
            buffer.put(instruction.getInstructionByte());
        }
        return buffer.array();
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
