package sjq.bitcoin.merkle;

import sjq.bitcoin.hash.Hash;

public class TreeNode {

    private Hash nodeHash;

    private TreeNode leftNode;

    private TreeNode rightNode;

    public TreeNode(Hash hash) {
        this.nodeHash = hash;
    }

    public Hash getNodeHash() {
        return nodeHash;
    }

    public void setNodeHash(Hash nodeHash) {
        this.nodeHash = nodeHash;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
}
