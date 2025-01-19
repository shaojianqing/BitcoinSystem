package sjq.bitcoin.service;

import sjq.bitcoin.configuration.NetworkConfiguration;
import sjq.bitcoin.context.Autowire;
import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.logger.Logger;
import sjq.bitcoin.message.data.BlockHeader;
import sjq.bitcoin.storage.dao.BlockDao;
import sjq.bitcoin.storage.domain.Block;
import sjq.bitcoin.utility.HexUtils;

public class BlockService {

    private static final NetworkConfiguration configuration = NetworkConfiguration.getConfiguration();

    private static final Block GENESIS_BLOCK = new Block();

    @Autowire
    private BlockDao blockDao;

    @Autowire
    private TransactionService transactionService;

    public BlockService() {
        try {
            GENESIS_BLOCK.setBlockHeight(0l);
            GENESIS_BLOCK.setDifficulty(configuration.getGenesisBlockDifficulty());
            GENESIS_BLOCK.setNonce(configuration.getGenesisBlockNonce());
            GENESIS_BLOCK.setVersion(configuration.getGenesisBlockVersion());
            GENESIS_BLOCK.setTimestamp(configuration.getGenesisBlockTimestamp());
            GENESIS_BLOCK.setBlockHash(calculateBlockHash(GENESIS_BLOCK));
        } catch (Exception e) {
            Logger.fatal("can not initiate genesis block,exception:%s", e);
            //System.exit(-1);
        }
    }

    public Block getGenesisBlock() {
        return GENESIS_BLOCK;
    }

    public Block getBestBlock() {
        return null;
    }

    public boolean saveBlock(Block block) {
        return blockDao.saveBlock(block);
    }

    private String calculateBlockHash(Block block) throws Exception {
        BlockHeader blockHeader = new BlockHeader();
        blockHeader.setVersion(block.getVersion());
        String parentHash = block.getParentHash();
        blockHeader.setParentHash(Hash.wrap(HexUtils.parseHex(parentHash)));
        String merkelRoot = block.getMerkleRoot();
        blockHeader.setMerkleRoot(Hash.wrap(HexUtils.parseHex(merkelRoot)));
        blockHeader.setTimestamp(block.getTimestamp());
        blockHeader.setDifficulty(block.getDifficulty());
        blockHeader.setNonce(block.getNonce());
        Hash blockHash = blockHeader.calculateHash();
        return blockHash.hexValue();
    }
}
