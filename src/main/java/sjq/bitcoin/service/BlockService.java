package sjq.bitcoin.service;

import org.apache.commons.lang3.StringUtils;
import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.BlockMessage;
import sjq.bitcoin.message.data.BlockHeader;
import sjq.bitcoin.service.convertor.BlockConvertor;
import sjq.bitcoin.storage.dao.BlockDao;
import sjq.bitcoin.storage.domain.Block;
import sjq.bitcoin.utility.HexUtils;

public class BlockService {

    private static final NetworkConfiguration configuration = NetworkConfiguration.getConfiguration();

    private Long GENESIS_BLOCK_HEIGHT = 0l;

    @Autowire
    private BlockDao blockDao;

    @Autowire
    private TransactionService transactionService;

    private Block GENESIS_BLOCK = new Block();

    public BlockService() {
        try {
            BlockMessage blockMessage = configuration.getGenesisBlock();
            GENESIS_BLOCK = BlockConvertor.convert(blockMessage, GENESIS_BLOCK_HEIGHT);
        } catch (Exception e) {
            Logger.fatal("can not initiate genesis block exception:%s", e);
            System.exit(-1);
        }
    }

    public Block getGenesisBlock() {
        return GENESIS_BLOCK;
    }

    public Block getBestBlock() throws Exception {
        Block bestBlock = blockDao.getBestBlock();
        if (bestBlock==null) {
            bestBlock = GENESIS_BLOCK;
        }
        return bestBlock;
    }

    public Block getBlockByHash(String blockHash) throws Exception {
        // Because genesis block is not stored in the database, so we tell
        // whether the block hash is equal to genesis block, and
        // then query block from database if not equal.
        if (StringUtils.equals(GENESIS_BLOCK.getBlockHash(), blockHash)) {
            return GENESIS_BLOCK;
        }
        return blockDao.getBlockByHash(blockHash);
    }

    public boolean saveBlock(Block block) throws Exception {
        // Only if the previous block has been stored in the database, the received
        // block can be saved into the database as expected. This is mainly for
        // data consistency consideration.
        Block prevBlock = getBlockByHash(block.getPrevBlockHash());
        if (prevBlock != null) {
            return blockDao.saveBlock(block);
        }
        Logger.warn("ignore block data without previous block in the database, block hash:%s", block.getBlockHash());
        return false;
    }

    private String calculateBlockHash(Block block) throws Exception {
        BlockHeader blockHeader = new BlockHeader();
        blockHeader.setVersion(block.getVersion());
        String prevBlockHash = block.getPrevBlockHash();
        blockHeader.setPrevBlockHash(Hash.wrap(HexUtils.parseHex(prevBlockHash)));
        String merkelRoot = block.getMerkleRoot();
        blockHeader.setMerkleRoot(Hash.wrap(HexUtils.parseHex(merkelRoot)));
        blockHeader.setTimestamp(block.getTimestamp());
        blockHeader.setDifficulty(block.getDifficulty());
        blockHeader.setNonce(block.getNonce());
        Hash blockHash = blockHeader.calculateHash();
        return blockHash.hexValue();
    }
}
