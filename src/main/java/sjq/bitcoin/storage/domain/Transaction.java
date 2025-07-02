package sjq.bitcoin.storage.domain;

import java.sql.Timestamp;

public class Transaction {

    private String transactionHash;

    private Long messageVersion;

    private Long blockHeight;

    private String blockHash;

    private Long transactionLockTime;

    private Timestamp createTime;

    private Timestamp modifyTime;

    public Long getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(Long messageVersion) {
        this.messageVersion = messageVersion;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(Long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public Long getTransactionLockTime() {
        return transactionLockTime;
    }

    public void setTransactionLockTime(Long transactionLockTime) {
        this.transactionLockTime = transactionLockTime;
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
