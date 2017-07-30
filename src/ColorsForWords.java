import java.util.*;

public class ColorsForWords {
    public int[][] getColorsForWords(String dna) {
        int dnaLength = dna.length();
        int[][] colors = new int[dnaLength][3];

        // find all short words r -> value
        DNA_word_finder finder = new DNA_word_finder(dna, 3);

        finder.getSizes2();
        int[] minNMax = finder.getMinAndMax();
        double min = minNMax[0];
        double max = minNMax[1];

        System.out.println("small");
        System.out.println("min: " + min);
        System.out.println("max"  + max);

        for (int i = 0; i < dnaLength; i++) {
            int val = finder.getMaxWordCount(i);
            val = (int) (((val) / max) * 250);

//            System.out.println("value: " + val);
            colors[i][0] = val;
        }


        // find all medium words b -> value
        finder = new DNA_word_finder(dna, 5);

        finder.getSizes2();
        minNMax = finder.getMinAndMax();
        min = minNMax[0];
        max = minNMax[1];

        System.out.println("medium");
        System.out.println("min: " + min);
        System.out.println("max"  + max);

        for (int i = 0; i < dnaLength; i++) {
            int val = finder.getMaxWordCount(i);
            colors[i][1] = (int) (((val - min) / max) * 250);
        }

        System.out.println("done with medium");

        // find all long words g -> value
        finder = new DNA_word_finder(dna, 8);

        finder.getSizes2();
        minNMax = finder.getMinAndMax();
        min = minNMax[0];
        max = minNMax[1];

        System.out.println("large");
        System.out.println("min: " + min);
        System.out.println("max"  + max);

        for (int i = 0; i < dnaLength; i++) {
            int val = finder.getMaxWordCount(i);
            colors[i][2] = (int) (((val - min) / max) * 250);
        }

        System.out.println("values: " + colors[dnaLength-1][0] + ", " + colors[dnaLength-1][1] + ", " + colors[dnaLength-1][2]);

        return colors;
    }

    public char complimentOf(char a) {
        switch (a) {
            case 'A':
                return 'T';
            case 'T':
                return 'A';
            case 'G':
                return 'C';
            case 'C':
                return 'G';
            default:
                return 'q'; // to prevent 'N' from showing up on the unknown regions
        }
    }

    public int[][] getWrappedInverseComplimentColors(String dna) {
        int dnaLength = dna.length();
        int[][] colors = new int[dnaLength][3];

        int currentSpotInStack = 0;
        char[] letters = new char[dnaLength];

        int longest = 0;
        int current = 0;

        for (int i = 0; i < dnaLength; i++) {
            letters[i] = dna.charAt(i);
            if (i == 0) {
                continue;
            }

            if (currentSpotInStack < 0) {
                System.out.println("Before start of stack");
                currentSpotInStack = i - 1;
            }

            boolean val = letters[currentSpotInStack] == complimentOf(dna.charAt(i));

            if (!val) {
                colors[i][0] = 0;
                colors[i][1] = 0;
                colors[i][2] = 0;

                currentSpotInStack = i;

                if (current > longest) {
                    longest = current;
                }
                current = 0;
            } else {
                colors[i][1] = 0;

                // before;
                colors[i][2] = 0;
                colors[i][0] = 255;

                // after TODO: implement this
                colors[currentSpotInStack][0] = 0;
                colors[currentSpotInStack][2] = 255;

                // update counter
                currentSpotInStack--;
                current++;
            }
        }

        System.out.println("longest: " + longest);
        return colors;
    }

    public int[][] getInverseComplimentColors(DNA_word_finder finder1, String dna) {
        Map top3 = finder1.getTopWordsAndInverseCompliments(3);
        int dnaLength = dna.length();
        int[][] colors = new int[dnaLength][3];
        System.out.println("top3: " + top3);

        for (int i = 0; i < dnaLength; i++) {
            int val = finder1.getWordCountFromMap(i, top3);

            if (val == -1) {
                colors[i][0] = 0;
                colors[i][1] = 0;
                colors[i][2] = 0;
            } else {
                colors[i][1] = 0;

                if (val % 2 == 0) {
                    colors[i][2] = 0;

                    colors[i][0] = 255 - (25 * val);

                } else {
                    colors[i][0] = 0;

                    colors[i][2] = 255 - (25 * val);
                }
            }
        }


        return colors;
    }



    public int[][] getColorsFromFinders(DNA_word_finder finder1, DNA_word_finder finder2, DNA_word_finder finder3, String dna, int min1, int max1, int min2, int max2, int min3, int max3) {
        int dnaLength = dna.length();
        int[][] colors = new int[dnaLength][3];

        // find all short words r -> value
        finder1.getSizes2();
        double min = min1;
        double max = max1;

        for (int i = 0; i < dnaLength; i++) {
            int val = finder1.getMaxWordCount(i);
            colors[i][0] = (int) Math.min(250, (((val - min) / (max - min)) * 250));
        }


        // find all medium words b -> value
        finder2.getSizes2();
        min = min2;
        max = max2;

        for (int i = 0; i < dnaLength; i++) {
            int val = finder2.getMaxWordCount(i);
            colors[i][1] = (int) Math.min(250, (((val - min) / (max - min)) * 250));
        }

        // find all long words g -> value
        finder3.getSizes2();
        min = min3;
        max = max3;

        for (int i = 0; i < dnaLength; i++) {
            int val = finder3.getMaxWordCount(i);
            colors[i][2] = (int) Math.min(250, (((val - min) / (max - min)) * 250));
        }

        return colors;
    }

    public int[][] getAbsoluteColorsForDNA(String dna, int min1, int max1, int min2, int max2, int min3, int max3) {
        int dnaLength = dna.length();
        int[][] colors = new int[dnaLength][3];

        // find all short words r -> value
        DNA_word_finder finder = new DNA_word_finder(dna, 3);

        finder.getSizes2();
        double min = min1;
        double max = max1;

        for (int i = 0; i < dnaLength; i++) {
            int val = finder.getMaxWordCount(i);
            colors[i][0] = (int) (((val - min) / max) * 250);
        }


        // find all medium words b -> value
        finder = new DNA_word_finder(dna, 5);

        finder.getSizes2();
        min = min2;
        max = max2;

        for (int i = 0; i < dnaLength; i++) {
            int val = finder.getMaxWordCount(i);
            colors[i][1] = (int) (((val - min) / max) * 250);
        }

        // find all long words g -> value
        finder = new DNA_word_finder(dna, 8);

        finder.getSizes2();
        min = min3;
        max = max3;

        for (int i = 0; i < dnaLength; i++) {
            int val = finder.getMaxWordCount(i);
            colors[i][2] = (int) (((val - min) / max) * 250);
        }

        return colors;
    }
}
