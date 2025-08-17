package sjq.bitcoin.storage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.service.data.page.Page;
import sjq.bitcoin.storage.domain.Block;

public class BlockDao {

    private final static String GET_BEST_BLOCK = "sjq.bitcoin.storage.domain.Block.getBestBlock";

    private final static String GET_BLOCK_BY_HASH = "sjq.bitcoin.storage.domain.Block.getBlockByHash";

    private final static String COUNT_BLOCK_BY_HASH = "sjq.bitcoin.storage.domain.Block.countBlockByHash";

    private final static String QUERY_BLOCK_BY_SYNC_STATUS = "sjq.bitcoin.storage.domain.Block.queryBlockBySyncStatus";

    private final static String QUERY_BLOCK_WITH_CONDITION = "sjq.bitcoin.storage.domain.Block.queryBlockWithCondition";

    private final static String SAVE_BLOCK = "sjq.bitcoin.storage.domain.Block.saveBlock";

    private final static String UPDATE_BLOCK_HEIGHT = "sjq.bitcoin.storage.domain.Block.updateBlockHeight";

    private final static String UPDATE_BLOCK_SYNC_STATUS = "sjq.bitcoin.storage.domain.Block.updateBlockSyncStatus";

    private final static String UPDATE_BLOCK_VERIFY_STATUS = "sjq.bitcoin.storage.domain.Block.updateBlockVerifyStatus";

    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public Block getBestBlock() throws Exception {
        return (Block)sqlMapClientTemplate.queryForObject(GET_BEST_BLOCK);
    }

    public Block getBlockByHash(String blockHash) throws Exception {
        return (Block)sqlMapClientTemplate.queryForObject(GET_BLOCK_BY_HASH, blockHash);
    }

    public List<Block> queryBlockBatchBySyncStatus(String status, Integer limit) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("syncStatus", status);
        paramMap.put("limitSize", limit);
        return (List<Block>)sqlMapClientTemplate.queryForList(QUERY_BLOCK_BY_SYNC_STATUS, paramMap);
    }

    public List<Block> queryBlockWithCondition(String condition, Integer limit) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("queryCondition", condition);
        paramMap.put("limitSize", limit);
        return (List<Block>)sqlMapClientTemplate.queryForList(QUERY_BLOCK_WITH_CONDITION, paramMap);
    }

    public Page<Block> searchBlockPage(Integer blockHeight, String blockHash, Integer startTimestamp, Integer endTimestamp) {
        return null;
    }

    public boolean saveBlock(Block block) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_BLOCK, block);
        return count == 1;
    }

    public boolean existBlock(Block block) throws Exception {
        Long count = (Long)sqlMapClientTemplate.queryForObject(COUNT_BLOCK_BY_HASH, block);
        return (count == 1);
    }

    public boolean updateBlockHeight(String blockHash, Long blockHeight) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("blockHash", blockHash);
        param.put("blockHeight", blockHeight);
        int count = sqlMapClientTemplate.execute(UPDATE_BLOCK_HEIGHT, param);
        return count==1;
    }

    public boolean updateBlockSyncStatus(String blockHash, String oldStatus, String newStatus) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("blockHash", blockHash);
        param.put("oldSyncStatus", oldStatus);
        param.put("newSyncStatus", newStatus);
        int count = sqlMapClientTemplate.execute(UPDATE_BLOCK_SYNC_STATUS, param);
        return count==1;
    }

    public boolean updateBlockVerifyStatus(String blockHash, String oldStatus, String newStatus) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("blockHash", blockHash);
        param.put("oldVerifyStatus", oldStatus);
        param.put("newVerifyStatus", newStatus);
        int count = sqlMapClientTemplate.execute(UPDATE_BLOCK_VERIFY_STATUS, param);
        return count==1;
    }
}
