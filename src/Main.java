import java.awt.*;

/**
 * Created by fredkneeland on 5/1/17.
 */
public class Main {
    public static void main(String[] args) {
//        FileReader reader = new FileReader("./dna/chromosome1.fa");
//        FileReader reader = new FileReader("./dna/chromo1Section1.txt");

        drawImage();
        generateImageForFile("./dna/smallChromosome.txt", "small1", 35, 100);
        generateRandomDNAStrands(1, 10);
        generateImageForFile("./dna/chromo1Section1.txt", "section1", 130, 10000);



//        System.out.println("done writing");
//
//        FileReader reader = new FileReader("./dna/random1.txt");
//        reader.getFile();
//
//        System.out.println("gathered file");
//
////         breakUpFile(reader);
//
//
//        String dna = reader.merge();
//        System.out.println("len: " + dna.length());
//
//        int wordSize = 5;
//        DNA_word_finder finder = new DNA_word_finder(dna, wordSize);
//
//        finder.getSizes();
//        finder.printSizes(50);
//        System.out.println("possible words: " + Math.pow(4, wordSize));


//        drawRandomImage();
    }

    public static void generateImageForFile(String fileName, String outputFile, int width, int height) {
        FileReader reader = new FileReader(fileName);
        reader.getFile();
        String dna = reader.merge();
        System.out.println("len: " + dna.length());
        ColorsForWords colors = new ColorsForWords();

        try {
            ImageBuilder.generate(outputFile, colors.getColorsForWords(dna), width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateRandomDNAStrands(int start, int count) {
        for (int i = 0; i < count; i++) {
            RandomGenerator.randomlyGenerateDNAFile("./dna/random" + (i + start) +".txt", 1305710);
            System.out.println("finished printing random strand: " + i);
        }
    }

    public static void drawImage() {
        int[][] values = new int[3500][3];

        for (int i = 0; i < values.length; i++) {
            if (i < 500) {
                values[i][0] = 0;
                values[i][1] = 0;
                values[i][2] = 0;
            } else {
                values[i][0] = 250;
                values[i][1] = 250;
                values[i][2] = 250;
            }

        }

        try {
            ImageBuilder.generate("pic1", values, 35, 100);
        } catch (Exception e) {}
    }

    public static void drawRandomImage() {

        int[][] values = new int[1300000][3];

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < 3; j++) {
                values[i][j] = (int) (Math.random() * 255);
            }
        }

        try {
            ImageBuilder.generate("pic1", values, 1000, 1300);
        } catch (Exception e) {}
    }

    public static void breakUpFile(FileReader reader) {
        FileWriter writer = new FileWriter();

        int size = reader.getFile().length / 175;
        for (int i = 0; i < 175; i++) {
            writer.writeToFile(reader.getFile(), "./dna/chromo1Section" + i + ".txt", i * size, (i + 1) * size);
        }
    }
}
