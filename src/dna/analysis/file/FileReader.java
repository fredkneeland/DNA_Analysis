package dna.analysis.file;

import java.io.BufferedReader;

/**
 * Created by fredkneeland on 5/1/17.
 */
public class FileReader {
    private String fileName;
    private String[] lines;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    public String[] getFile() {
        // if we already have the lines then return them
        if (lines != null) {
            return lines;
        }

        try {
            // dna.analysis.file.FileReader reads text files in the default encoding.
            java.io.FileReader fileReader =
                    new java.io.FileReader(fileName);

            // Always wrap dna.analysis.file.FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            // count the number of lines in the file
            int counter = 0;
            while(bufferedReader.readLine() != null) {
                counter++;
            }

            lines = new String[counter];
            // re-set up reader
            fileReader =
                    new java.io.FileReader(fileName);

            bufferedReader =
                    new BufferedReader(fileReader);

            // loop over the lines and put them into string
            String line;
            int i = 0;
            while((line = bufferedReader.readLine()) != null) {
                lines[i++] = line;
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return lines;
    }

    public String merge() {
        String string = "";

        for (int i = 0; i < lines.length; i++) {
            string += lines[i];
        }


        return string;
    }

//    public String[] returnStringsOfSize(int size) {
//        int lineSize = lines[0].length();
//        int sizeCountInLine = lineSize / size;
//        int lineSizeRemainder = lineSize % size;
//
//        int newLineCount = lineSize * sizeCountInLine + (lineSize * lineSizeRemainder) / size;
//
//        String[] newLines = new String[newLineCount];
//        int currentRow = 0;
//        int currentCol = 0;
//
//        for (int i = 0; i < newLineCount; i++) {
//            if (lines[currentRow].length() < (currentCol + size)) {
//                newLines[i] = lines[currentRow].substring(currentCol, currentCol+size);
//            } else if (currentRow < lines.length - 1) {
//                newLines[i] = lines[currentRow].substring(currentCol) + lines[currentRow+1].substring(0, lineSize - currentCol);
//            }
//
//            // update counters
//            currentCol = (currentCol + size) % lineSize;
//            if (currentCol < size) {
//                currentRow++;
//            }
//        }
//
//        return newLines;
//    }
}
