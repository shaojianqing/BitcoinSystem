package sjq.bitcoin.service;

import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.service.data.TransactionData;

import java.util.List;

public class TransactionService {

    public TransactionData getGenesisTransaction() {
        return null;
    }

    public Hash calculateMerkleRoot(List<TransactionData> transactions) {
        return null;
    }

    public boolean acceptTransaction(TransactionData transaction) {
        return false;
    }
}
