import java.util.Scanner;

public class RedBlackTree {
    private RBTNode root;
    private RBTNode TNULL;

    public RedBlackTree() {
        TNULL = new RBTNode();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    public void insert(int key) {
        RBTNode node = new RBTNode();
        node.parent = null;
        node.key = key;
        node.left = TNULL;
        node.right = TNULL;
        node.color = 1;

        RBTNode y = null;
        RBTNode x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.key < y.key) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null){
            node.color = 0;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    private void fixInsert(RBTNode node){
        RBTNode uncleNode;
        while (node.parent.color == 1) {
            if (node.parent == node.parent.parent.right) {
                uncleNode = node.parent.parent.left; // uncle

                if (uncleNode.color == 1) {
                    uncleNode.color = 0;
                    node.parent.color = 0;
                    node.parent.parent.color = 1;
                    node = node.parent.parent;
                }
                else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = 0;
                    node.parent.parent.color = 1;
                    leftRotate(node.parent.parent);
                }
            }
            else {
                uncleNode = node.parent.parent.right; // uncle

                if (uncleNode.color == 1) {
                    // mirror case 3.1
                    uncleNode.color = 0;
                    node.parent.color = 0;
                    node.parent.parent.color = 1;
                    node = node.parent.parent;
                }
                else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = 0;
                    node.parent.parent.color = 1;
                    rightRotate(node.parent.parent);
                }
            }
            if (node == root) {
                break;
            }
        }
        root.color = 0;
    }

    public void leftRotate(RBTNode node) {
        RBTNode temp = node.right;
        node.right = temp.left;
        if (temp.left != TNULL) {
            temp.left.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            this.root = temp;
        }
        else if (node == node.parent.left) {
            node.parent.left = temp;
        }
        else {
            node.parent.right = temp;
        }
        temp.left = node;
        node.parent = temp;
    }

    public void rightRotate(RBTNode node) {
        RBTNode temp = node.left;
        node.left = temp.right;
        if (temp.right != TNULL) {
            temp.right.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            this.root = temp;
        }
        else if (node == node.parent.right) {
            node.parent.right = temp;
        }
        else {
            node.parent.left = temp;
        }
        temp.right = node;
        node.parent = temp;
    }

    public void delete(int key) {
        deleteNodeHelper(this.root, key);
    }

    private void deleteNodeHelper(RBTNode node, int key) {
        // find the node containing key
        RBTNode z = TNULL;
        RBTNode x, y;
        while (node != TNULL){
            if (node.key == key) {
                z = node;
            }
            if (node.key <= key) {
                node = node.right;
            }
            else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Could not find the key");
            return;
        }

        y = z;
        int yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        }
        else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        }
        else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            }
            else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == 0){
            fixDelete(x);
        }
    }

    private void rbTransplant(RBTNode u, RBTNode v){
        if (u.parent == null) {
            root = v;
        }
        else if (u == u.parent.left){
            u.parent.left = v;
        }
        else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    public RBTNode minimum(RBTNode node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    private void fixDelete(RBTNode node) {
        RBTNode s;
        while (node != root && node.color == 0) {
            if (node == node.parent.left) {
                s = node.parent.right;
                if (s.color == 1) {
                    s.color = 0;
                    node.parent.color = 1;
                    leftRotate(node.parent);
                    s = node.parent.right;
                }
                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    node = node.parent;
                }
                else {
                    if (s.right.color == 0) {
                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = node.parent.right;
                    }
                    s.color = node.parent.color;
                    node.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(node.parent);
                    node = root;
                }
            }
            else {
                s = node.parent.left;
                if (s.color == 1) {
                    s.color = 0;
                    node.parent.color = 1;
                    rightRotate(node.parent);
                    s = node.parent.left;
                }
                if (s.right.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    node = node.parent;
                }
                else {
                    if (s.left.color == 0) {
                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = node.parent.left;
                    }
                    s.color = node.parent.color;
                    node.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(node.parent);
                    node = root;
                }
            }
        }
        node.color = 0;
    }

    private void printHelper(RBTNode root, String indent, boolean last) {
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            String sColor = root.color == 1?"RED":"BLACK";
            System.out.println(root.key + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    public void prettyPrint() {
        printHelper(this.root, "", true);
    }

    public int height(){
        return getHeightOfBST(root);
    }

    int getHeightOfBST(RBTNode node) {
        if(node == null) return -1;
        return Math.max(1 + getHeightOfBST(node.left), 1 + getHeightOfBST(node.right));
    }

    public void printUserMenu() {
        System.out.println("===================================================================");
        System.out.println("To insert a node        -       insert <key>");
        System.out.println("To delete a node        -       delete <key>");
        System.out.println("To print the tree       -       print");
        System.out.println("To print the height     -       height");
        System.out.println("To exit the program     -       exit");
        System.out.println("===================================================================\n\n");
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
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
