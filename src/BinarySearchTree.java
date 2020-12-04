import java.util.Scanner;

public class BinarySearchTree {
    BSTNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int key) {
        root = insertIntoBST(root, key);
    }

    BSTNode insertIntoBST(BSTNode root, int key) {

        if (root == null) {
            root = new BSTNode(key);
            return root;
        }

        if (key < root.key)
            root.left = insertIntoBST(root.left, key);
        else if (key > root.key)
            root.right = insertIntoBST(root.right, key);

        return root;
    }

    void delete(int key) {
        root = deleteFromBST(root, key);
    }

    BSTNode deleteFromBST(BSTNode root, int key) {
        if (root == null) return root;

        if (key < root.key) root.left = deleteFromBST(root.left, key);
        else if (key > root.key) root.right = deleteFromBST(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.key = minValue(root.right);
            root.right = deleteFromBST(root.right, root.key);
        }
        return root;
    }

    int minValue(BSTNode root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    void inorder() {
        if(root != null) inorderTraversal(root);
    }

    void inorderTraversal(BSTNode node) {
        if(node.left != null) inorderTraversal(node.left);
        System.out.print(node.key + " ");
        if(node.right != null) inorderTraversal(node.right);
    }

    void preorder() {
        if(root != null) preorderTraversal(root);
    }

    void preorderTraversal(BSTNode node) {
        System.out.print(node.key + " ");
        if(node.left != null) preorderTraversal(node.left);
        if(node.right != null) preorderTraversal(node.right);
    }

    void postorder() {
        if(root != null) postorderTraversal(root);
    }

    void postorderTraversal(BSTNode node) {
        if(node.left != null) postorderTraversal(node.left);
        if(node.right != null) postorderTraversal(node.right);
        System.out.print(node.key + " ");
    }

    public int height(){
        return getHeightOfBST(root);
    }

    int getHeightOfBST(BSTNode node) {
        if(node == null) return 0;
        return Math.max(1 + getHeightOfBST(node.left), 1 + getHeightOfBST(node.right));
    }

    private void printHelper(BSTNode root, String indent, boolean last) {
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

    public void printUserMenu() {
        System.out.println("===================================================================");
        System.out.println("To insert a node            -       insert <key>");
        System.out.println("To delete a node            -       delete <key>");
        System.out.println("To get inorder traversal    -       inorder");
        System.out.println("To get preorder traversal   -       preorder");
        System.out.println("To get postorder traversal  -       postorder");
        System.out.println("To print the tree           -       print");
        System.out.println("To print the height         -       height");
        System.out.println("To exit the program         -       exit");
        System.out.println("===================================================================\n\n");
    }

    // Driver Code
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            tree.printUserMenu();
            String command = scanner.nextLine();
            if(command.contains("insert")) {
                int key = Integer.parseInt(command.split(" ")[1]);
                tree.insert(key);
                System.out.println("Node inserted.\n");
            }
            else if(command.contains("delete")) {
                int key = Integer.parseInt(command.split(" ")[1]);
                tree.delete(key);
                System.out.println("Node deleted.\n");
            }
            else if(command.equals("inorder")) {
                tree.inorder();
                System.out.println("\n");
            }
            else if(command.equals("preorder")) {
                tree.preorder();
                System.out.println("\n");
            }
            else if(command.equals("postorder")) {
                tree.postorder();
                System.out.println("\n");
            }
            else if(command.equals("print")) {
                tree.prettyPrint();
                System.out.println("\n");
            }
            else if(command.equals("height")) {
                System.out.println("Height of the tree: " + tree.height() + "\n");
            }
            else if(command.equals("exit")) {
                System.out.println("Exiting the program.\n");
                break;
            }
            else {
                System.out.println("Invalid command.\n");
            }
        }
    }
}
