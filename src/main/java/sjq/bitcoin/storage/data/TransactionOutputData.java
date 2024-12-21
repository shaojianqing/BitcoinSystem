package sjq.bitcoin.storage.data;

public class TransactionOutputData {

    private TransactionData parentTransaction;

    private byte[] scriptData;

    public TransactionData getParentTransaction() {
        return parentTransaction;
    }

    public void setParentTransaction(TransactionData parentTransaction) {
        this.parentTransaction = parentTransaction;
    }
}
