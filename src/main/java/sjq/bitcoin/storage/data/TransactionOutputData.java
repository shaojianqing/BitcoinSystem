package sjq.bitcoin.storage.data;

import sjq.bitcoin.script.ScriptRunner;

public class TransactionOutputData {

    private TransactionData parentTransaction;

    private byte[] scriptBody;

    private ScriptRunner scriptPubKey;

    public TransactionData getParentTransaction() {
        return parentTransaction;
    }

    public void setParentTransaction(TransactionData parentTransaction) {
        this.parentTransaction = parentTransaction;
    }
}
