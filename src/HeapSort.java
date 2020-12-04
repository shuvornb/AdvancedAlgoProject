import java.io.IOException;

public class HeapSort {
    public void sort(long[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);

        for (int i = n - 1; i > 0; i--) {
            long temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    void heapify(long[] array, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && array[l] > array[largest])
            largest = l;

        if (r < n && array[r] > array[largest])
            largest = r;

        if (largest != i) {
            long swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
        }
    }

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        SortingUtil.generateOutput(fileName, "HeapSort");
    }
}