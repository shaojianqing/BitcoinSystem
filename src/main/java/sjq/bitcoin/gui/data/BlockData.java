package sjq.bitcoin.gui.data;

public class BlockData {

    private String blockHeight;

    private String blockHash;

    private String blockNonce;

    private String syncStatus;

    private String verifyStatus;

    private String blockTimestamp;

    public BlockData(String blockHeight, String blockHash, String blockNonce,
                     String syncStatus, String verifyStatus, String blockTimestamp) {
        this.blockHeight = blockHeight;
        this.blockHash = blockHash;
        this.blockNonce = blockNonce;
        this.syncStatus = syncStatus;
        this.verifyStatus = verifyStatus;
        this.blockTimestamp = blockTimestamp;
    }

    public String getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(String blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getBlockNonce() {
        return blockNonce;
    }

    public void setBlockNonce(String blockNonce) {
        this.blockNonce = blockNonce;
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

    public String getBlockTimestamp() {
        return blockTimestamp;
    }

    public void setBlockTimestamp(String blockTimestamp) {
        this.blockTimestamp = blockTimestamp;
    }
}
