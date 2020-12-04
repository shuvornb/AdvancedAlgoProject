import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SortingUtil {
    public static void generateInput (int noOfInstances, int noOfIntegersPerInstance, String fileName) throws IOException {
        final long MAX_VALUE = 4294967296L;
        final long MIN_VALUE = 0L;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for(int i=0; i<noOfInstances ;i++) {
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<noOfIntegersPerInstance; j++) {
                long generatedLong = MIN_VALUE + (long) (Math.random() * (MAX_VALUE - MIN_VALUE));
                sb.append(generatedLong);
                if(j < noOfIntegersPerInstance - 1) sb.append(" ");
            }
            if(i < noOfInstances-1) sb.append("\n");
            writer.write(sb.toString());
        }
        writer.close();
    }

    public static void generateOutput(String fileName, String sortingAlgo) throws IOException {
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        String fileNameStr = "File: " + fileName + "\n\n";
        writer.write(fileNameStr);

        String st;
        InsertionSort insertionSort = new InsertionSort();
        QuickSort quickSort = new QuickSort();
        MergeSort mergeSort = new MergeSort();
        HeapSort heapSort = new HeapSort();


        int counter = 1;
        long intervalSum = 0;
        long processStartTime = System.currentTimeMillis();
        List<Double> intervalList = new ArrayList<>();

        while ((st = reader.readLine()) != null) {
            String[] parts = st.split(" ");
            long[] array = new long[parts.length];
            for(int i=0; i<parts.length; i++) {
                array[i] = Long.parseLong(parts[i]);
            }

            long startTime = System.currentTimeMillis();

            if(sortingAlgo.equals("InsertionSort")) insertionSort.sort(array);
            if(sortingAlgo.equals("QuickSort")) quickSort.sort(array, 0, array.length-1);
            if(sortingAlgo.equals("MergeSort")) mergeSort.sort(array, 0, array.length-1);
            if(sortingAlgo.equals("HeapSort")) heapSort.sort(array);

            long endTime = System.currentTimeMillis();
            long interval = endTime - startTime;

            String result = "Instance: " + counter + " Start: " + startTime + " End: " + endTime + " Interval: " + interval + "\n";
            writer.write(result);

            for (long j : array) writer.write(j + " ");
            writer.write("\n\n");

            counter ++;
            intervalSum += interval;
            intervalList.add((double) interval);
        }

        double avgTime = (double) intervalSum / (counter-1);
        String avgTimeStr = "\nAverage Time: " + avgTime;
        writer.write(avgTimeStr);

        String sdStr = "\nStandard Deviation: " + String.format("%.2f", calculateSD(intervalList));
        writer.write(sdStr);

        long processEndTime = System.currentTimeMillis();
        long totalTime = processEndTime - processStartTime;
        String totalTimeStr = "\nTotal Time: " + totalTime;
        writer.write(totalTimeStr);
        writer.close();
    }

    public static double calculateSD(List<Double> numArray) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.size();

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }

    public static void printArray(long[] arr) {
        for (long j : arr) System.out.print(j + " ");
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        generateInput(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
    }
}