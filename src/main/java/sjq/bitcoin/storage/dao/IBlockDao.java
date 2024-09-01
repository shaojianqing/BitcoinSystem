package sjq.bitcoin.storage.dao;

import sjq.bitcoin.storage.data.Page;
import sjq.bitcoin.storage.domain.Block;

public interface IBlockDao {

    Block getBlockByHash(String blockHash);

    Page<Block> searchBlockPage(Integer blockHeight, String blockHash, Integer startTimestamp, Integer endTimestamp);

    boolean saveBlock(Block block);
}
