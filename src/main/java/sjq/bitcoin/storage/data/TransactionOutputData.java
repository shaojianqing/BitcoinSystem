package sjq.bitcoin.storage.data;

import sjq.bitcoin.monetary.Coin;
import sjq.bitcoin.script.ScriptProgram;

public class TransactionOutputData {

    private TransactionData parentTransaction;

    private ScriptProgram program;

    private Coin value;

    public TransactionData getParentTransaction() {
        return parentTransaction;
    }

    public void setParentTransaction(TransactionData parentTransaction) {
        this.parentTransaction = parentTransaction;
    }
}
