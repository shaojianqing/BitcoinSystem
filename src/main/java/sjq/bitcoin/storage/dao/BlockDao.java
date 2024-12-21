package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.storage.data.page.Page;
import sjq.bitcoin.storage.domain.Block;
import sjq.bitcoin.storage.manager.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BlockDao {

    private static String INSERT_BLOCK_FORMAT =
            "insert into " +
                    "block(block_hash, parent_hash, merkle_root, block_height, version, difficulty, nonce, " +
                    "sync_status, verify_status, timestamp, trx_count, create_time, modify_time) " +
                    "values('%s', '%s', '%s', '%ld', '%ld', '%ld', '%ld', '%s', '%s', '%ld', '%ld', now(), now())";

    private static String QUERY_BEST_BLOCK_FORMAT =
            "select " +
                    "block_hash, parent_hash, merkle_root, block_height, version, " +
                    "difficulty, nonce, sync_status, verify_status, timestamp, trx_count, create_time, modify_time " +
                    "from " +
                    "block " +
                    "order by block_height desc " +
                    "limit 1";

    private static String QUERY_BLOCK_BY_HASH_FORMAT =
            "select " +
                    "block_hash, parent_hash, merkle_root, block_height, version, " +
                    "difficulty, nonce, sync_status, verify_status, timestamp, trx_count, create_time, modify_time " +
                    "from " +
                    "block " +
                    "where " +
                    "block_hash = '%s' " +
                    "limit 1";


    @Autowire
    private DatabaseManager databaseManager;

    public Block getBestBlock() {
        try {
            String querySql = QUERY_BEST_BLOCK_FORMAT;
            ResultSet resultSet = databaseManager.executeQuery(querySql);
            if (resultSet.next()) {
                return prepareBlockData(resultSet);
            }
        } catch (Exception e) {
            Logger.error("can not query block data from database, error:%s", e.getMessage());
        }
        return null;
    }

    public Block getBlockByHash(String blockHash) {
        try {
            String querySql = String.format(QUERY_BLOCK_BY_HASH_FORMAT, blockHash);
            ResultSet resultSet = databaseManager.executeQuery(querySql);
            if (resultSet.next()) {
                return prepareBlockData(resultSet);
            }
        } catch (Exception e) {
            Logger.error("can not query block data from database, error:%s", e.getMessage());
        }
        return null;
    }

    public Page<Block> searchBlockPage(Integer blockHeight, String blockHash, Integer startTimestamp, Integer endTimestamp) {
        return null;
    }

    public boolean saveBlock(Block block) {
        try {
            String insertSql = String.format(INSERT_BLOCK_FORMAT, block.getBlockHash(), block.getParentHash(), block.getMerkleRoot(),
                    block.getBlockHeight(), block.getVersion(), block.getDifficulty(), block.getNonce(), block.getSyncStatus(),
                    block.getVerifyStatus(), block.getTimestamp(), block.getTrxCount());

            int count = databaseManager.executeUpdate(insertSql);
            return count > 0;
        }catch (Exception e) {
            Logger.error("can not insert block data into database, error:%s", e.getMessage());
        }
        return false;
    }

    private Block prepareBlockData(ResultSet resultSet) throws SQLException {
        String blockHash = resultSet.getString("block_hash");
        String parentHash = resultSet.getString("parent_hash");
        String merkleRoot = resultSet.getString("merkle_root");
        Long blockHeight = resultSet.getLong("block_height");
        Long version = resultSet.getLong("version");
        Long difficulty = resultSet.getLong("difficulty");
        Long nonce = resultSet.getLong("nonce");
        Long trxCount = resultSet.getLong("trx_count");
        String syncStatus = resultSet.getString("sync_status");
        String verifyStatus = resultSet.getString("verify_status");
        Long timestamp = resultSet.getLong("timestamp");
        Date createTime = resultSet.getDate("create_time");
        Date modifyTime = resultSet.getDate("modify_time");

        Block block = new Block(version, blockHeight, blockHash,
                parentHash, merkleRoot, timestamp, difficulty, nonce, trxCount);
        block.setSyncStatus(syncStatus);
        block.setVerifyStatus(verifyStatus);
        block.setCreateTime(createTime);
        block.setModifyTime(modifyTime);

        return block;
    }
}
