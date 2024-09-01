package sjq.bitcoin.storage.domain;

import java.util.Date;

public class Block {

    private Long version;

    private Long blockHeight;

    private String blockHash;

    private String parentHash;

    private String merkleRoot;

    private Long timestamp;

    private Long difficulty;

    private Long nonce;

    private Long trxCount;

    private Date createTime;

    private Date modifyTime;

    public Block(Long version, Long blockHeight, String blockHash, String parentHash,
                 String merkleRoot, Long timestamp, Long difficulty, Long nonce, Long trxCount) {
        this.version = version;
        this.blockHeight = blockHeight;
        this.blockHash = blockHash;
        this.parentHash = parentHash;
        this.merkleRoot = merkleRoot;
        this.timestamp = timestamp;
        this.difficulty = difficulty;
        this.nonce = nonce;
        this.trxCount = trxCount;
        this.createTime = new Date();
        this.modifyTime = new Date();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    public String getParentHash() {
        return parentHash;
    }

    public void setParentHash(String parentHash) {
        this.parentHash = parentHash;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public Long getTrxCount() {
        return trxCount;
    }

    public void setTrxCount(Long trxCount) {
        this.trxCount = trxCount;
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
