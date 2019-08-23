package my.coding.challenge;

/**
 * A binary tree node which is either a leaf node or a parent with left and right child nodes
 */
public class TreeNode {
    private Integer value;
    private Boolean isLeaf = true;
    private String word;
    private TreeNode leftNode;
    private TreeNode rightNode;

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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean isLeafNode() {
        return isLeaf;
    }

    public void changeLeafStatus(Boolean leaf) {
        isLeaf = leaf;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public TreeNode(Integer value, String word) {
        this.value = value;
        this.word = word;
    }

    public TreeNode(Integer value, String word, Boolean isLeaf) {
        this.isLeaf = isLeaf;
        this.value = value;
        this.word = word;
    }

    public TreeNode(Integer value, String word, TreeNode leftChild, TreeNode rightChild) {
        this.value = value;
        this.word = word;
        this.leftNode = leftChild;
        this.rightNode = rightChild;
        this.isLeaf = false;
    }

    /**
     * This method makes a parent node from 2 child nodes
     * @param nodeA left child node
     * @param nodeB right chile node
     * @return a new parent node
     */
    public static TreeNode makeParentNode(TreeNode nodeA, TreeNode nodeB) {
        TreeNode parent = new TreeNode(nodeA.value + nodeB.value, null, nodeA, nodeB);
        return parent;
    }

    /**
     * This method prints the whole tree to console.
     * @param rootNode the root node to begin
     */
    public static void printToConsole(TreeNode rootNode) {
        recursivePrint(rootNode, 0);
    }

    /**
     * This method traverses and prints all nodes which originate from given node.
     * @param node the node to print
     * @param level the level of given node, the deeper it is in the tree, the higher the level is
     */
    private static void recursivePrint(TreeNode node, int level) {
        System.out.println();
        System.out.print(String.copyValueOf(new char[level]).replace("\0","..."));
        System.out.print(node);
        if (node.getLeftNode() != null) {
            recursivePrint(node.getLeftNode(), level + 1);
        }
        if (node.getRightNode() != null) {
            recursivePrint(node.getRightNode(), level + 1);
        }
    }

    @Override
    public String toString() {
        return "Node " + value + " " + (word != null ? word : "");
    }
}
