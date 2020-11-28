package sorting;

import java.io.*;

public class InsertionSort {
    public void sort(long[] array) {
        int n = array.length;
        for (int i=1; i<n; ++i) {
            long key = array[i];
            int j = i-1;
            while (j>=0 && array[j] > key)
            {
                array[j+1] = array[j];
                j = j-1;
            }
            array[j+1] = key;
        }
    }

    // Driver code
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        Util.generateOutput(fileName, "InsertionSort");
    }
}
