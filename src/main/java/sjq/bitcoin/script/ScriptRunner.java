package sjq.bitcoin.script;

import java.util.List;

public class ScriptRunner {

    private  List<Instruction> instructionList;

    private ScriptRunner() {
    }

    public static ScriptRunner parse(byte[] scriptData, long createTime) {
        return new ScriptRunner();
    }
}
