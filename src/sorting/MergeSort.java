package sorting;

public class MergeSort {
    void merge(int[] array, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int[] leftSubArray = new int[n1];
        int[] rightSubArray = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            leftSubArray[i] = array[l + i];
        for (int j = 0; j < n2; ++j)
            rightSubArray[j] = array[m + 1 + j];

        // Merge the temporary arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
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

        // Copy remaining elements of leftSubArray[] if any
        while (i < n1) {
            array[k] = leftSubArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightSubArray if any
        while (j < n2) {
            array[k] = rightSubArray[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int[] array, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(array, l, m);
            sort(array, m + 1, r);

            // Merge the sorted halves
            merge(array, l, m, r);
        }
    }

    // A utility function to print array of size n
    public static void printArray(int[] arr) {
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }

    // Driver code
    public static void main(String[] args) {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);
        printArray(arr);
    }
}
