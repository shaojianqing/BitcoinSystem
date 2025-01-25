package sjq.bitcoin.storage.dao;

import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.orm.template.SqlMapClientTemplate;
import sjq.bitcoin.service.data.page.Page;
import sjq.bitcoin.storage.domain.Block;

import java.util.HashMap;
import java.util.Map;

public class BlockDao {

    private static String GET_BEST_BLOCK = "sjq.bitcoin.storage.domain.Block.getBestBlock";

    private static String GET_BLOCK_BY_HASH = "sjq.bitcoin.storage.domain.Block.getBlockByHash";

    private static String SAVE_BLOCK = "sjq.bitcoin.storage.domain.Block.saveBlock";

    private static String UPDATE_BLOCK_HEIGHT = "sjq.bitcoin.storage.domain.Block.updateBlockHeight";


    @Autowire
    private SqlMapClientTemplate sqlMapClientTemplate;

    public Block getBestBlock() throws Exception {
        return (Block)sqlMapClientTemplate.queryForObject(GET_BEST_BLOCK);
    }

    public Block getBlockByHash(String blockHash) throws Exception {
        return (Block)sqlMapClientTemplate.queryForObject(GET_BLOCK_BY_HASH, blockHash);
    }

    public Page<Block> searchBlockPage(Integer blockHeight, String blockHash, Integer startTimestamp, Integer endTimestamp) {
        return null;
    }

    public boolean saveBlock(Block block) throws Exception {
        int count = sqlMapClientTemplate.execute(SAVE_BLOCK, block);
        return count==1;
    }

    public boolean updateBlockHeight(String blockHash, Long blockHeight) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("blockHash", blockHash);
        param.put("blockHeight", blockHeight);
        int count = sqlMapClientTemplate.execute(UPDATE_BLOCK_HEIGHT, param);
        return count==1;
    }
}
