package dna.analysis.file;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {


    public void writeStringToFile(String string, String fileName) {
        try {
            // Assume default encoding.
            java.io.FileWriter fileWriter =
                    new java.io.FileWriter(fileName);

            // Always wrap dna.analysis.file.FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

                bufferedWriter.write("" + string);

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public void writeToFile(String[] strings, String fileName, int start, int stop) {
        try {
            // Assume default encoding.
            java.io.FileWriter fileWriter =
                    new java.io.FileWriter(fileName);

            // Always wrap dna.analysis.file.FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            for (int i = start; i < stop && i < strings.length; i++) {
                bufferedWriter.write("" + strings[i]);
            }

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

    }
}
