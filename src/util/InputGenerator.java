package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InputGenerator {
    private static final long MAX_VALUE = 4294967296L;
    private static final long MIN_VALUE = 0L;

    public static void generateInput (int noOfInstances, int noOfIntegersPerInstance, String fileName) throws IOException {
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

    public static void main(String[] args) throws IOException {
        generateInput(20, 100000, "input100000.txt");
    }
}
