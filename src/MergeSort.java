import java.io.IOException;

public class MergeSort {
    void merge(long[] array, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        long[] leftSubArray = new long[n1];
        long[] rightSubArray = new long[n2];

        for (int i = 0; i < n1; ++i)
            leftSubArray[i] = array[l + i];
        for (int j = 0; j < n2; ++j)
            rightSubArray[j] = array[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (leftSubArray[i] <= rightSubArray[j]) {
                array[k] = leftSubArray[i];
                i++;
            }
            else {
                array[k] = rightSubArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftSubArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightSubArray[j];
            j++;
            k++;
        }
    }

    public void sort(long[] array, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(array, l, m);
            sort(array, m + 1, r);

            // Merge the sorted halves
            merge(array, l, m, r);
        }
    }

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        SortingUtil.generateOutput(fileName, "MergeSort");
    }
}
