import java.util.*;


public class StartAndStop {
    public static ArrayList<String> startCodons = new ArrayList<>(Arrays.asList("ATG"));
    public static ArrayList<String> endCodons = new ArrayList<>(Arrays.asList("TAA", "TAG", "TGA"));

    public static int[][] getColorsForWords(String dna) {
        int[][] colors = new int[dna.length()][3];

        boolean inStartCodon = false;
        int lastStartCodon = -5;
        int codonCount = 0;

        for (int i = 0; i < dna.length() - 3; i++) {
            String current = dna.substring(i, i + 3);
//            System.out.println(current);
            if (inStartCodon && (i - lastStartCodon > 3)) {
                if (endCodons.contains(current)) {
                    inStartCodon = false;
                }
            } else if (!inStartCodon) {
                if (startCodons.contains(current)) {
                    codonCount++;
                    inStartCodon = true;
                    lastStartCodon = i;
                }
            }

            if (inStartCodon) {
                if (i - lastStartCodon < 3) {
                    colors[i][0] = 255;
                    colors[i][1] = 0;
                    colors[i][2] = 0;
                } else {
                    colors[i][0] = 255;
                    colors[i][1] = 255;
                    colors[i][2] = 255;
                }

            } else {
                colors[i][0] = 0;
                colors[i][1] = 0;
                colors[i][2] = 0;
            }
        }

        System.out.println("Codon Count: " + codonCount);

        return colors;
    }
}
