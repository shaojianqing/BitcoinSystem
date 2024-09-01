package sjq.bitcoin.storage.dao.impl;

import sjq.bitcoin.storage.dao.IBlockDao;
import sjq.bitcoin.storage.data.Page;
import sjq.bitcoin.storage.domain.Block;
import sjq.bitcoin.storage.manager.DatabaseManager;

public class BlockDaoImpl implements IBlockDao {

    private DatabaseManager databaseManager;

    public BlockDaoImpl(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Block getBlockByHash(String blockHash) {
        return null;
    }

    public Page<Block> searchBlockPage(Integer blockHeight, String blockHash, Integer startTimestamp, Integer endTimestamp) {
        return null;
    }

    public boolean saveBlock(Block block) {
        return false;
    }
}
