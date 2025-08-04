package sjq.bitcoin.service.data;

import sjq.bitcoin.monetary.Coin;
import sjq.bitcoin.script.ScriptProgram;

public class TransactionOutputData {

    private TransactionData parentTransaction;

    private Long transactionOutputIndex;

    private byte[] scriptPubKey;

    private Coin coinValue;

    private ScriptProgram pubKeyProgram;

    public TransactionData getParentTransaction() {
        return parentTransaction;
    }

    public void setParentTransaction(TransactionData parentTransaction) {
        this.parentTransaction = parentTransaction;
    }

    public Long getTransactionOutputIndex() {
        return transactionOutputIndex;
    }

    public void setTransactionOutputIndex(Long transactionOutputIndex) {
        this.transactionOutputIndex = transactionOutputIndex;
    }

    public byte[] getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(byte[] scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public Coin getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(Coin coinValue) {
        this.coinValue = coinValue;
    }

    public ScriptProgram getPubKeyProgram() {
        return pubKeyProgram;
    }

    public void setPubKeyProgram(ScriptProgram pubKeyProgram) {
        this.pubKeyProgram = pubKeyProgram;
    }
}
