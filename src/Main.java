import java.awt.*;
import java.util.*;

/**
 * Created by fredkneeland on 5/1/17.
 */
public class Main {
    public static void main(String[] args) {
//        FileReader reader = new FileReader("./dna/chromosome1.fa");
//        FileReader reader = new FileReader("./dna/chromo1Section1.txt");

//        drawImage();
//        generateImageForFile("./dna/smallChromosome.txt", "small1", 35, 100);
//        generateRandomDNAStrands(1, 10);
//        generateImageForFile("./dna/chromo1Section1.txt", "section1", 130, 10000);

//        Map<String, Integer>[][] maps = new HashMap<String, Integer>()[185][3];

//        generateAbsoluteImagesForChromo1_v2();
//        generateAbsoluteImagesForChromo1();

        generateImageForFile("./dna/Bible.txt", "bibleOutput", 130, 10000);

//        generateImageForFile("./dna/smallChromosome.txt", "./RelativeWordDensities/small1", 35, 100);

//        generateImageForFile("./dna/random2.txt", "random2", 130, 10000);
//        generateImageForFile("./dna/random3.txt", "random3", 130, 10000);
//        generateImageForFile("./dna/random4.txt", "random4", 130, 10000);
//        generateImageForFile("./dna/random5.txt", "random5", 130, 10000);
//        generateImageForFile("./dna/random6.txt", "random6", 130, 10000);
//        generateImageForFile("./dna/random7.txt", "random7", 130, 10000);
//        generateImageForFile("./dna/random8.txt", "random8", 130, 10000);
//        generateImageForFile("./dna/random9.txt", "random9", 130, 10000);
//        generateImageForFile("./dna/random10.txt", "random10", 130, 10000);
//
//        for (int i = 0; i < 175; i++) {
//            generateImageForFile("./dna/chromo1Section" + i + ".txt", "section" + i, 130, 10000);
//        }

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

    public static void generateAbsoluteImagesForChromo1_v2() {
        String[] dna = new String[185];
        FileReader reader = null;


        DNA_word_finder smallFinder = new DNA_word_finder("", 3);
        DNA_word_finder mediumFinder = new DNA_word_finder("", 5);
        DNA_word_finder largeFinder = new DNA_word_finder("", 8);

        for (int i = 0; i < dna.length; i++) {
            if (i < 10) {
                reader = new FileReader("./dna/random" + (i+1) + ".txt");
            } else {
                reader = new FileReader("./dna/chromo1Section" + (i-10) + ".txt");
            }

            reader.getFile();
            dna[i] = reader.merge();

            smallFinder.addToSizes(dna[i]);
            mediumFinder.addToSizes(dna[i]);
            largeFinder.addToSizes(dna[i]);
        }

        int smallMin = smallFinder.getMinAndMax()[0];
        int smallMax = smallFinder.getMinAndMax()[1];
        int mediumMin = mediumFinder.getMinAndMax()[0];
        int mediumMax = mediumFinder.getMinAndMax()[1];
        int largeMin = largeFinder.getMinAndMax()[0];
        int largeMax = largeFinder.getMinAndMax()[1];

        // draw the images
        for (int i = 0; i < dna.length; i++) {
            System.out.println("generating file for: " + i);
            String outputFile;

            if (i < 10) {
                outputFile = "./AbsoluteWordDensities2/random" + (i+1);
            } else {
                outputFile = "./AbsoluteWordDensities2/section" + (i-9);
            }

            ColorsForWords colors = new ColorsForWords();
            try {
                smallFinder.dna = dna[i];
                mediumFinder.dna = dna[i];
                largeFinder.dna = dna[i];
                ImageBuilder.generate(outputFile, colors.getColorsFromFinders(smallFinder, mediumFinder, largeFinder,
                        dna[i], smallMax, smallMin, mediumMax, mediumMin, largeMax, largeMin), 130, 10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void generateAbsoluteImagesForChromo1() {
        DNA_word_finder[][] finders = new DNA_word_finder[185][3];
        String[] dna = new String[185];
        FileReader reader = null;

        int averageMax1 = 0, averageMin1 = 0, averageMax2 = 0, averageMin2 = 0, averageMax3 = 0, averageMin3 = 0;
        int count = 0;

        for (int i = 0; i < finders.length; i++) {
            if (i < 10) {
                reader = new FileReader("./dna/random" + (i+1) + ".txt");
            } else {
                reader = new FileReader("./dna/chromo1Section" + (i-10) + ".txt");
            }

            reader.getFile();
            dna[i] = reader.merge();
            finders[i][0] = new DNA_word_finder(dna[i], 3);
            finders[i][0].getSizes2();

            finders[i][1] = new DNA_word_finder(dna[i], 5);
            finders[i][1].getSizes2();

            finders[i][2] = new DNA_word_finder(dna[i], 8);
            finders[i][2].getSizes2();

            int[] minNMax = finders[i][0].getMinAndMax();
            averageMax1 += minNMax[1];
            averageMin1 += minNMax[0];

            minNMax = finders[i][1].getMinAndMax();
            averageMax2 += minNMax[1];
            averageMin2 += minNMax[0];

            minNMax = finders[i][2].getMinAndMax();
            averageMax3 += minNMax[1];
            averageMin3 += minNMax[0];

            count++;

            System.out.println("Current Averages Max1: " + (averageMax1 / count) + " Max2: " + (averageMax2 / count) +
                    " Max3: " + (averageMax3 / count) + " Min1: " + (averageMin1 / count) + " Min2: " + (averageMin2 / count) +
                    " Min3: " + (averageMin3 / count));
            System.out.println("count: " + count);
        }

        averageMax1 /= count;
        averageMax2 /= count;
        averageMax3 /= count;

        averageMin1 /= count;
        averageMin2 /= count;
        averageMin3 /= count;

        // draw the images
        for (int i = 0; i < finders.length; i++) {
            System.out.println("generating file for: " + i);
            String outputFile;

            if (i < 10) {
                outputFile = "./AbsoluteWordDensities/random" + (i+1);
            } else {
                outputFile = "./AbsoluteWordDensities/section" + (i-9);
            }

            ColorsForWords colors = new ColorsForWords();
            try {
                ImageBuilder.generate(outputFile, colors.getColorsFromFinders(finders[i][0], finders[i][1], finders[i][2], dna[i], averageMax1, averageMin1, averageMax2, averageMin2, averageMax3, averageMin3), 130, 10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
