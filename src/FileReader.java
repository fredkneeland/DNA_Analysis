import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Created by fredkneeland on 5/1/17.
 * Edited by chinmoy159, dated :- 10/8/2017
 */

public class FileReader
{
    private String fileName;
    private String[] lines;

    public FileReader(String fileName)
    {
        this.fileName = fileName;
    }
    
    public String[] getFile()
    {
        // if we already have the lines then return them
        if (lines != null) {
            return lines;
        }
        try {
            // Reading the file using the Scanner Class in Java
            Scanner Sc = new Scanner (new File(fileName));
            int counter, i;
            String St;

            for (counter = 0; Sc.hasNext(); St = Sc.nextLine());
            // counting the number of lines in the file.
            
            lines = new String[counter];
            //closing the file, before re-setting it
            Sc.close();
            
            // re-set up reader
            Sc = new Scanner (new File(fileName));
            // loop over the lines and put them into string
            for (i = 0; i < counter; ++i) {
                lines[i] = Sc.nextLine();
            }
            // Always close files.
            Sc.close();
        }
        catch(FileNotFoundException e) {
            // Catching the specific error line
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
