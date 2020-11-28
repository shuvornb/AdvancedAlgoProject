package sorting;

import java.io.IOException;

public class QuickSort {
    /* This function takes last element as pivot, places
    the pivot element at its correct position in sorted
    array, and places all smaller (smaller than pivot)
    to left of pivot and all greater elements to right
    of pivot */
    int partition(long[] array, int low, int high) {
        long pivot = array[high];
        int i = (low-1);
        for (int j=low; j<high; j++) {
            if (array[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                long temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        long temp = array[i+1];
        array[i+1] = array[high];
        array[high] = temp;

        return i+1;
    }


    // The main function that implements QuickSort()
    public void sort(long[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            sort(array, low, pi-1);
            sort(array, pi+1, high);
        }
    }

    // Driver program
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        Util.generateOutput(fileName, "QuickSort");
    }
}
