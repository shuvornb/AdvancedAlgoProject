import java.io.IOException;

public class QuickSort {
    int partition(long[] array, int low, int high) {
        long pivot = array[high];
        int i = (low-1);
        for (int j=low; j<high; j++) {
            if (array[j] < pivot) {
                i++;

                long temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        long temp = array[i+1];
        array[i+1] = array[high];
        array[high] = temp;

        return i+1;
    }

    public void sort(long[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            sort(array, low, pi-1);
            sort(array, pi+1, high);
        }
    }

    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        SortingUtil.generateOutput(fileName, "QuickSort");
    }
}
