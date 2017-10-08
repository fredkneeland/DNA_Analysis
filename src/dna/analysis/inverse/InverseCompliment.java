package dna.analysis.inverse;

import java.util.ArrayList;

/**
 * Created by fredkneeland on 8/14/17.
 */
public class InverseCompliment {
    // determines how many mistakes we allow in a string before identifying it as false
    public static final int percentAllowed = 5;

    public static ArrayList<int[]> getAllInverseComplimentPairs(String dna, int middleSize) {
        ArrayList<int[]> sizes = new ArrayList<>(1000);

        int len = dna.length();

        for (int i = 0; i < len; i++) {
//            String a = "";
//            String b = "";

            for (int j = i - middleSize - 1; j >= 0; j--) {

                if (isTrueInverse(dna.charAt(i), dna.charAt(j))) {
                    continue;
                }

//                int upperBound = i;// + (middleSize - j);
//                if (i - middleSize >= 0 && i + middleSize < len) {
//                    if (isTrueInverse(dna.charAt(i), dna.charAt(j))) {
//                        continue;
//                    }
//
////                    a += dna.charAt(upperBound);
////                    b = dna.charAt(j) + b;
////
////                    if (stringsInverseCompliments(a, b)) {
////                        continue;
////                    }
//                }

                if ((i - j - middleSize) >= 20) {
//                    System.out.println("adding new size: " + i + " j: " + j);
                    sizes.add(new int[]{j + 1, i - middleSize});
                }

                break;
            }
        }

        return sizes;
    }

    public static boolean stringsInverseCompliments(String a, String b) {

        int mistakeCount = 0;

        for (int i = 0; i < a.length(); i++) {
//            System.out.println("a: "+ a + " b: " + b);
            if (isTrueInverse(a.charAt(i), b.charAt((b.length() - 1) - i))) continue;
//            if (((i + 1) < a.length() && isFlipped(a, b, i)) || isAddition(a, b, i) || isChanged(a, b, i)) mistakeCount++;
//            else return false;

//            if ((mistakeCount * 100) / a.length() > percentAllowed) {
//                return false;
//            }
        }

        return true;
    }

    public static boolean isTrueInverse(char a, char b) {
        switch (a) {
            case 'G':
                return b == 'C';
            case 'C':
                return b == 'G';
            case 'A':
                return b == 'T';
            case 'T':
                return b == 'A';
            default:
                return false;
        }
    }

    public static boolean isAddition(String a, String b, int index) {
        return false;
    }

    public static boolean isFlipped(String a, String b, int index) {
        return false;
    }

    public static boolean isChanged(String a, String b, int index) {
        return false;
    }
}
