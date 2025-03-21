package sjq.bitcoin.message.convertor;

import sjq.bitcoin.message.BlockMessage;
import sjq.bitcoin.message.data.BlockHeader;
import sjq.bitcoin.storage.domain.Block;

public class BlockConvertor {

    public static Block convertBlockFromHeader(BlockHeader header) {
        Block block = new Block(header.getVersion(), 0l, header.getBlockHash().hexValue(),
                header.getPrevBlockHash().hexValue(), header.getMerkleRoot().hexValue(), header.getTimestamp(),
                header.getDifficulty(), header.getNonce(), 0);
        return block;
    }

    public static Block convertBlockFromMessage(BlockMessage message) {
        Block block = new Block(message.getVersion(), 0l, message.getBlockHash().hexValue(),
                message.getPrevBlockHash().hexValue(), message.getMerkleRoot().hexValue(), message.getTimestamp(),
                message.getDifficulty(), message.getNonce(), 0);
        return block;
    }
}
