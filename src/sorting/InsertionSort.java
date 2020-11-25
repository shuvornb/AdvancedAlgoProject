package sorting;

public class InsertionSort {
    public void sort(int[] array) {
        int n = array.length;
        for (int i=1; i<n; ++i) {
            int key = array[i];
            int j = i-1;
            while (j>=0 && array[j] > key)
            {
                array[j+1] = array[j];
                j = j-1;
            }
            array[j+1] = key;
        }
    }

    public static void printArray(int[] arr) {
        for (int j : arr) System.out.print(j + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6};
        InsertionSort ob = new InsertionSort();
        ob.sort(array);

        printArray(array);
    }
}
