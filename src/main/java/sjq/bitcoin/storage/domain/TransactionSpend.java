package sjq.bitcoin.storage.domain;

import java.sql.Timestamp;

public class TransactionSpend {

    private Long id;

    private String transactionHash;

    private String fromTransactionHash;

    private Long transactionOutputIndex;

    private Timestamp createTime;

    private Timestamp modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public String getFromTransactionHash() {
        return fromTransactionHash;
    }

    public void setFromTransactionHash(String fromTransactionHash) {
        this.fromTransactionHash = fromTransactionHash;
    }

    public Long getTransactionOutputIndex() {
        return transactionOutputIndex;
    }

    public void setTransactionOutputIndex(Long transactionOutputIndex) {
        this.transactionOutputIndex = transactionOutputIndex;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }
}
