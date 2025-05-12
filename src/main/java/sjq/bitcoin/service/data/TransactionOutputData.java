package sjq.bitcoin.service.data;

import sjq.bitcoin.monetary.Coin;
import sjq.bitcoin.script.ScriptProgram;

public class TransactionOutputData {

    private TransactionData parentTransaction;

    private ScriptProgram program;

    private byte[] scriptPubKey;

    private Coin value;

    public TransactionData getParentTransaction() {
        return parentTransaction;
    }

    public void setParentTransaction(TransactionData parentTransaction) {
        this.parentTransaction = parentTransaction;
    }

    public ScriptProgram getProgram() {
        return program;
    }

    public void setProgram(ScriptProgram program) {
        this.program = program;
    }

    public byte[] getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(byte[] scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public Coin getValue() {
        return value;
    }

    public void setValue(Coin value) {
        this.value = value;
    }
}
