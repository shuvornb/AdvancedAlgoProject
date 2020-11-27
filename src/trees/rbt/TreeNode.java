package trees.rbt;

public class TreeNode {
    int key;
    TreeNode parent;
    TreeNode left;
    TreeNode right;
    int color;

    public TreeNode(int item) {
        key = item;
        parent = null;
        left = null;
        right = null;
        color = 1;
    }
}
