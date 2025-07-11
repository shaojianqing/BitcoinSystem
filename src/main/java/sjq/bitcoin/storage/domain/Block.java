package sjq.bitcoin.storage.domain;

import java.sql.Timestamp;

public class Block {

    public static final String STATUS_SYNC_HEADER = "SyncHeader";

    public static final String STATUS_SYNC_TRANSACTION = "SyncTransaction";

    public static final String STATUS_UN_VERIFY_HEADER = "UnVerifyHeader";

    public static final String STATUS_VERIFY_HEADER = "VerifyHeader";

    public static final String STATUS_VERIFY_TRANSACTION = "VerifyTransaction";

    private Long version;

    private Long blockHeight;

    private String blockHash;

    private String prevBlockHash;

    private String merkleRoot;

    private Long timestamp;

    private Long difficulty;

    private Long nonce;

    private Integer trxCount;

    private String syncStatus;

    private String verifyStatus;

    private Timestamp createTime;

    private Timestamp modifyTime;

    public Block(){
    }

    public Block(Long version, Long blockHeight, String blockHash, String prevBlockHash,
                 String merkleRoot, Long timestamp, Long difficulty, Long nonce, Integer trxCount) {
        this.version = version;
        this.blockHeight = blockHeight;
        this.blockHash = blockHash;
        this.prevBlockHash = prevBlockHash;
        this.merkleRoot = merkleRoot;
        this.timestamp = timestamp;
        this.difficulty = difficulty;
        this.nonce = nonce;
        this.trxCount = trxCount;
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

    public String getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
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

    public Integer getTrxCount() {
        return trxCount;
    }

    public void setTrxCount(Integer trxCount) {
        this.trxCount = trxCount;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
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
