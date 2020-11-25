package sorting;

public class QuickSort {
    /* This function takes last element as pivot, places
    the pivot element at its correct position in sorted
    array, and places all smaller (smaller than pivot)
    to left of pivot and all greater elements to right
    of pivot */
    int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low-1);
        for (int j=low; j<high; j++) {
            if (array[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = array[i+1];
        array[i+1] = array[high];
        array[high] = temp;

        return i+1;
    }


    // The main function that implements QuickSort()
    public void sort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            sort(array, low, pi-1);
            sort(array, pi+1, high);
        }
    }

    // A utility function to print array of size n
    public static void printArray(int[] arr) {
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }

    // Driver program
    public static void main(String[] args) {
        int[] array = {10, 7, 8, 9, 1, 5};
        int n = array.length;
        QuickSort ob = new QuickSort();
        ob.sort(array, 0, n-1);
        printArray(array);
    }
}
