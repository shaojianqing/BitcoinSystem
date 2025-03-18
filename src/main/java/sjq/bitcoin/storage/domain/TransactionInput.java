package sjq.bitcoin.storage.domain;

import java.util.Date;

public class TransactionInput {

    private Long id;

    private String transactionHash;

    private String fromTransactionHash;

    private Long sequence;

    private Long transactionOutputIndex;

    private String scriptSignature;

    private Long value;

    private Date createTime;

    private Date modifyTime;

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

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Long getTransactionOutputIndex() {
        return transactionOutputIndex;
    }

    public void setTransactionOutputIndex(Long transactionOutputIndex) {
        this.transactionOutputIndex = transactionOutputIndex;
    }

    public String getScriptSignature() {
        return scriptSignature;
    }

    public void setScriptSignature(String scriptSignature) {
        this.scriptSignature = scriptSignature;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
