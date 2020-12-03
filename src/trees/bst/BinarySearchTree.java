package trees.bst;

import trees.rbt.RBTNode;

public class BinarySearchTree {
    BSTNode root;

    public BinarySearchTree() {
        root = null;
    }

    // This method mainly calls insertIntoBST()
    public void insert(int key) {
        root = insertIntoBST(root, key);
    }

    // A recursive function to insert a new key in BST
    BSTNode insertIntoBST(BSTNode root, int key) {

        // If the tree is empty, return a new node
        if (root == null) {
            root = new BSTNode(key);
            return root;
        }

        // Otherwise, find out the appropriate position recursively
        if (key < root.key)
            root.left = insertIntoBST(root.left, key);
        else if (key > root.key)
            root.right = insertIntoBST(root.right, key);

        return root;
    }

    // This method mainly calls inorderTraversal()
    void inorder() {
        inorderTraversal(root);
    }

    // A utility function to do inorder traversal of BST
    void inorderTraversal(BSTNode node) {
        if(node.left != null) inorderTraversal(node.left);
        System.out.print(node.key + " ");
        if(node.right != null) inorderTraversal(node.right);
    }

    // This method mainly calls preorderTraversal()
    void preorder() {
        preorderTraversal(root);
    }

    // A utility function to do preorder traversal of BST
    void preorderTraversal(BSTNode node) {
        System.out.print(node.key + " ");
        if(node.left != null) preorderTraversal(node.left);
        if(node.right != null) preorderTraversal(node.right);
    }

    // This method mainly calls postorderTraversal()
    void postorder() {
        postorderTraversal(root);
    }

    // A utility function to do postorder traversal of BST
    void postorderTraversal(BSTNode node) {
        if(node.left != null) postorderTraversal(node.left);
        if(node.right != null) postorderTraversal(node.right);
        System.out.print(node.key + " ");
    }

    // This method mainly calls getHeightOfBST()
    public int height(){
        return getHeightOfBST(root);
    }

    // A recursive utility function to get the height of BST
    int getHeightOfBST(BSTNode node) {
        if(node == null) return 0;
        return Math.max(1 + getHeightOfBST(node.left), 1 + getHeightOfBST(node.right));
    }

    private void printHelper(BSTNode root, String indent, boolean last) {
        // print the tree structure on the screen
        if (root != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            System.out.println(root.key);
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    public void prettyPrint() {
        printHelper(this.root, "", true);
    }

    // Driver Code
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // print inorder traversal of the BST
        tree.inorder();
        System.out.println("\n");

        // print preorder traversal of the BST
        tree.preorder();
        System.out.println("\n");

        // print postorder traversal of the BST
        tree.postorder();
        System.out.println("\n");

        // print height of the tree
        System.out.println(tree.height());

        // print the whole tree
        tree.prettyPrint();
    }
}
