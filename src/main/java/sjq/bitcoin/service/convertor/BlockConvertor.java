package sjq.bitcoin.service.convertor;

import sjq.bitcoin.message.BlockMessage;
import sjq.bitcoin.storage.domain.Block;

public class BlockConvertor {

    public static Block convert(BlockMessage blockMessage, long blockHeight) {
        Block block = new Block();
        block.setBlockHeight(blockHeight);
        block.setNonce(blockMessage.getNonce());
        block.setVersion(blockMessage.getVersion());
        block.setTimestamp(blockMessage.getTimestamp());
        block.setDifficulty(blockMessage.getDifficulty());
        block.setBlockHash(blockMessage.getBlockHash().hexValue());
        block.setPrevBlockHash(blockMessage.getPrevBlockHash().hexValue());
        block.setMerkleRoot(blockMessage.getMerkleRoot().hexValue());
        block.setTrxCount((long) blockMessage.getTransactions().size());
        return block;
    }
}
