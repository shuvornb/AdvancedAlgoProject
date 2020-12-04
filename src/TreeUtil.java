import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeUtil {
    public static void insertBulkNodes() throws IOException {
        BinarySearchTree bst = new BinarySearchTree();
        RedBlackTree rbt = new RedBlackTree();

        List<Integer> noOfNodes = new ArrayList<>();
        List<Integer> bstHeight = new ArrayList<>();
        List<Integer> rbtHeight = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<10000; i++) {
            noOfNodes.add(i+1);

            int key = random.nextInt(1000) +1;
            bst.insert(key);
            bstHeight.add(bst.height());

            rbt.insert(key);
            rbtHeight.add(rbt.height());
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("Output.txt"));
        String titleStr = "NodeCount    BSTHeight   RBTHeight\n==================================\n";
        writer.write(titleStr);

        for(int i=0;i<10000; i++) {
            String heightStr = noOfNodes.get(i) + "             " + bstHeight.get(i) + "            " + rbtHeight.get(i) + "\n";
            writer.write(heightStr);
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        insertBulkNodes();
    }
}
