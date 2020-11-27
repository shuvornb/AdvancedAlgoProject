package trees.rbt;

public class RedBlackTree {
    private TreeNode root;

    // Constructor
    RedBlackTree() {
        root = null;
    }

    // This method mainly calls insertIntoTreeUsingBSTProperty()
    void insert(int key) {
        TreeNode node = new TreeNode(key);
        root = insertIntoTreeUsingBSTProperty(root, node);
        fixViolations(node);
    }

    private void fixViolations(TreeNode node) {
    }

    // A recursive function to insert a new key in tree
    TreeNode insertIntoTreeUsingBSTProperty(TreeNode root, TreeNode node) {

        if (root == null) {
            return node;
        }

        if (node.key < root.key){
            root.left = insertIntoTreeUsingBSTProperty(root.left, node);
            root.left.parent = root;
        }
        else if (node.key > root.key) {
            root.right = insertIntoTreeUsingBSTProperty(root.right, node);
            root.right.parent = root;
        }
        return root;
    }
}
