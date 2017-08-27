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

    public int[][] getAllSpacedInverseComplimentColors(String dna) {
        int dnaLength = dna.length();
        int[][] colors = new int[dnaLength][3];

        ArrayList<int[]>[] pairs = new ArrayList[20];
        int[] pairsCurrentIndex = new int[20];
        int[] pairsCurrentStart = new int[20];
        int[] pairsCurrentEnd = new int[20];

        for (int i = 0; i < 20; i++) {
            pairs[i] = InverseCompliment.getAllInverseComplimentPairs(dna, i);
            pairsCurrentIndex[i] = 0;
            pairsCurrentStart[i] = pairs[i].get(0)[0];
            pairsCurrentEnd[i] = pairs[i].get(0)[1];
        }

        for (int i = 0; i < dnaLength; i++) {
            int red = 0;
            int green = 0;
            int blue = 0;

            for (int j = 0; j < pairs.length; j++) {
                if (i >= pairsCurrentStart[j]) {
                    if (i <= pairsCurrentEnd[j]) {
                        if (j <= 6) {
                            red = 255;
                        } else if (j <= 12) {
                            green = 255;
                        } else {
                            blue = 255;
                        }

                    } else {
                        pairsCurrentIndex[j]++;
                        if (pairsCurrentIndex[j] < pairs[j].size()) {
                            pairsCurrentStart[j] = pairs[j].get(pairsCurrentIndex[j])[0];
                            pairsCurrentEnd[j] = pairs[j].get(pairsCurrentIndex[j])[1];
                        }
                    }
                }
            }

            colors[i][0] = red;
            colors[i][1] = green;
            colors[i][2] = blue;
        }

        return colors;
    }


    public int[][] getSpacedInverseComplimentColors(String dna) {
        int dnaLength = dna.length();
        int[][] colors = new int[dnaLength][3];

        ArrayList<int[]> pairs0 = InverseCompliment.getAllInverseComplimentPairs(dna, 0);
        ArrayList<int[]> pairs5 = InverseCompliment.getAllInverseComplimentPairs(dna, 5);
        ArrayList<int[]> pairs10 = InverseCompliment.getAllInverseComplimentPairs(dna, 10);

        int pair0CurrentIndex = 0;
        int pair5CurrentIndex = 0;
        int pair10CurrentIndex = 0;

        int pair0CurrentStart = pairs0.get(0)[0];
        int pair5CurrentStart = pairs5.get(0)[0];
        int pair10CurrentStart = pairs10.get(0)[0];

        int pair0CurrentEnd = pairs0.get(0)[1];
        int pair5CurrentEnd = pairs5.get(0)[1];
        int pair10CurrentEnd = pairs10.get(0)[1];

        for (int i = 0; i < dnaLength; i++) {
            int red = 0;
            int green = 0;
            int blue = 0;

            // grab red color
            if (i >= pair0CurrentStart) {
                if (i <= pair0CurrentEnd) {
                    red = 255;
                } else {
                    pair0CurrentIndex++;
                    if (pair0CurrentIndex < pairs0.size()) {
                        pair0CurrentStart = pairs0.get(pair0CurrentIndex)[0];
                        pair0CurrentEnd = pairs0.get(pair0CurrentIndex)[1];
                    }
                }
            }

            // grab green color
            if (i >= pair5CurrentStart) {
                if (i <= pair5CurrentEnd) {
                    green = 255;
                } else {
                    pair5CurrentIndex++;
                    if (pair5CurrentIndex < pairs5.size()) {
                        pair5CurrentStart = pairs5.get(pair5CurrentIndex)[0];
                        pair5CurrentEnd = pairs5.get(pair5CurrentIndex)[1];
                    }
                }
            }

            // grab blue color
            if (i >= pair10CurrentStart) {
                if (i <= pair10CurrentEnd) {
                    blue = 255;
                } else {
                    pair10CurrentIndex++;
                    if (pair10CurrentIndex < pairs10.size()) {
                        pair10CurrentStart = pairs10.get(pair10CurrentIndex)[0];
                        pair10CurrentEnd = pairs10.get(pair10CurrentIndex)[1];
                    }
                }
            }

            colors[i][0] = red;
            colors[i][1] = green;
            colors[i][2] = blue;
        }

        return colors;
    }

    public int[][] getWrappedInverseComplimentColors(String dna) {
        int dnaLength = dna.length();
        int[][] colors = new int[dnaLength][3];

        int currentSpotInStack = 0;
        char[] letters = new char[dnaLength];

        int longest = 0;
        int current = 0;
        int totalNumberOfCompliments = 0;
        int totalLengthOfCompliments = 0;

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
                if (current > 1) {
                    totalNumberOfCompliments++;
                    totalLengthOfCompliments += current;
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

        System.out.println("longest: " + longest + " Average: " + (totalLengthOfCompliments / totalNumberOfCompliments) + " total number: " + totalNumberOfCompliments + " total length: " + totalLengthOfCompliments);
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
