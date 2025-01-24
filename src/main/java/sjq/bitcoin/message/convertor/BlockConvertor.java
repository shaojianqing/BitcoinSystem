package sjq.bitcoin.message.convertor;

import sjq.bitcoin.message.data.BlockHeader;
import sjq.bitcoin.storage.domain.Block;

public class BlockConvertor {

    public static Block convertBlockFromHeader(BlockHeader header) {
        Block block = new Block(header.getVersion(), 0l, header.getBlockHash().hexValue(),
                header.getPrevBlockHash().hexValue(), header.getMerkleRoot().hexValue(), header.getTimestamp(),
                header.getDifficulty(), header.getNonce(), 0l);
        return block;
    }
}
