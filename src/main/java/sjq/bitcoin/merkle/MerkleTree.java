package sjq.bitcoin.merkle;

import sjq.bitcoin.hash.Hash;
import sjq.bitcoin.message.TransactionMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree {

    private TreeNode root;

    public static MerkleTree build(List<TransactionMessage> transactions,
                                   boolean useSegWitnessTransactionHash) throws IOException {

        List<TreeNode> treeNodeList = new ArrayList<>(transactions.size());
        for (TransactionMessage transaction:transactions) {
            if (useSegWitnessTransactionHash && transaction.isCoinbaseTransaction()) {
                treeNodeList.add(new TreeNode(Hash.ZERO_HASH));
            } else {
                if (useSegWitnessTransactionHash) {
                    treeNodeList.add(new TreeNode(transaction.getWitnessTransactionHash()));
                } else {
                    treeNodeList.add(new TreeNode(transaction.getTransactionHash()));
                }
            }
        }

        int levelOffset = 0;
        for (int levelSize = transactions.size(); levelSize>1; levelSize = (levelSize+1)/2) {
            for (int left = 0;left < levelSize;left += 2) {
                int right = Math.min(left + 1, levelSize - 1);
                TreeNode leftNode = treeNodeList.get(levelOffset + left);
                TreeNode rightNode = treeNodeList.get(levelOffset + right);

                byte[] hashBytes = Hash.calculateTwice(
                        leftNode.getNodeHash().serialize(), rightNode.getNodeHash().serialize());
                TreeNode parentNode = new TreeNode(Hash.wrapReversed(hashBytes));
                parentNode.setLeftNode(leftNode);
                parentNode.setRightNode(rightNode);
                treeNodeList.add(parentNode);
            }
        }
        MerkleTree merkleTree = new MerkleTree();
        if (treeNodeList.size()>0) {
            merkleTree.root = treeNodeList.get(treeNodeList.size()-1);
            return merkleTree;
        }
        return merkleTree;
    }

    public TreeNode getRoot() {
        return root;
    }
}
