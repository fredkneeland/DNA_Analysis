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
            // FileReader reads text files in the default encoding.
            java.io.FileReader fileReader =
                    new java.io.FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
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

    public String[] returnStringsOfSize(int size) {
        int lineSize = lines[0].length();
        int sizeCountInLine = lineSize / size;
        int lineSizeRemainder = lineSize % size;

        int newLineCount = lineSize * sizeCountInLine + (lineSize * lineSizeRemainder) / size;

        String[] newLines = new String[newLineCount];


        for (int i = 0; i < newLineCount; i++) {

        }

        return newLines;
    }
}
