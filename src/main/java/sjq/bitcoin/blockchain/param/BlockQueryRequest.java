package sjq.bitcoin.blockchain.param;

public class BlockQueryRequest {

    private String blockHeight;

    private String blockHash;

    private String verifyStatus;

    private BlockQueryRequest(String blockHeight, String blockHash, String verifyStatus) {
        this.blockHeight = blockHeight;
        this.blockHash = blockHash;
        this.verifyStatus = verifyStatus;
    }

    public static BlockQueryRequest build() {
        return new BlockQueryRequest(null, null, null);
    }

    public static BlockQueryRequest build(String blockHeight, String blockHash, String blockStatus) {
        return new BlockQueryRequest(blockHeight, blockHash, blockStatus);
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

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}
