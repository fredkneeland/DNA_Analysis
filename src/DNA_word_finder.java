import java.util.*;

public class DNA_word_finder {
    public String dna;
    private int size;
    private Map<String, Integer> sizes;

    public DNA_word_finder(String dna, int size) {
        this.dna = dna;
        this.size = size;
        this.sizes = new HashMap<>();
    }

    public Map getSizes() {
        if (!sizes.isEmpty()) {
            return sizes;
        }

        // loop over the entire strand and look for words
        int length = dna.length() - size;
        for (int i = 0; i < length; i++) {
            String currentWord = dna.substring(i, i+size);
            int currentCount = 0;

            // if we have already calculated this word then just continue
            if (sizes.containsKey(currentWord)) continue;

            for (int j = 0; j < length; j++) {
                // don't count ourselves
                if (j == i) continue;

                String newString = dna.substring(j, j+size);

                if (newString.equals(currentWord)) {
                    currentCount++;
                }
            }

            sizes.put(currentWord, currentCount);

        }

        return sizes;
    }

    public Map addToSizes(String addDna) {
        // loop over the entire strand and look for words
        int length = addDna.length() - size;
        for (int i = 0; i < length; i++) {
            String currentWord = addDna.substring(i, i+size);

            if (containsNonDNA(currentWord)) continue;

            // if we have already calculated this word then just continue
            if (sizes.containsKey(currentWord)) {
                sizes.put(currentWord, sizes.get(currentWord) + 1);
            } else {
                sizes.put(currentWord, 1);
            }
        }

        return sizes;
    }

    public Map getSizes2() {
        if (!sizes.isEmpty()) {
            return sizes;
        }

        // loop over the entire strand and look for words
        int length = dna.length() - size;
        for (int i = 0; i < length; i++) {
            String currentWord = dna.substring(i, i+size);

            if (containsNonDNA(currentWord)) continue;

            // if we have already calculated this word then just continue
            if (sizes.containsKey(currentWord)) {
                sizes.put(currentWord, sizes.get(currentWord) + 1);
            } else {
                sizes.put(currentWord, 1);
            }
        }

        return sizes;
    }

    public boolean containsNonDNA(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != 'G' && string.charAt(i) != 'T' && string.charAt(i) != 'A' && string.charAt(i) != 'C') {
                return true;
            }
        }

        return false;
    }

    public Map getTopWordsAndInverseCompliments(int size) {
        if (sizes.isEmpty()) {
            this.getSizes2();
        }

        Map<String, Integer> temp = new HashMap<>(sizes);
        Map<String, Integer> values = new HashMap<>();


        for (int i = 0; i < size; i++) {
            String largestValue = getMaxValue(temp);
            // temp.get(largestValue)
            values.put(largestValue, i * 2);

            String inverse = getInverseCompliment(largestValue);

            values.put(inverse, (i * 2) + 1);
            System.out.println("Largest value: " + largestValue + " appeared: " + temp.get(largestValue) + " inverse: " + inverse + " appeared: " + temp.get(inverse));
            temp.remove(largestValue);
            temp.remove(inverse);
        }

        return values;
    }

    public int getWordCountFromMap(int index, Map<String, Integer> map) {
        for (int i = 0; i < Math.min(index+1, size); i++) {
            int val = index - i;

            if (val + size > dna.length()) continue;

            String word = dna.substring(val, val + size);

            if (!map.containsKey(word)) continue;

            return map.get(word);
        }

        return -1;
    }

    public String getMaxValue(Map<String, Integer> map) {
        int largest = 0;
        String largestKey = null;
        for (String key : map.keySet()) {
            if (map.get(key) > largest) {
                largest = map.get(key);
                largestKey = key;
            }
        }

        return largestKey;
    }

    public String getInverseCompliment(String string) {
        String newString = "";

        for (int i = string.length(); --i>=0; ) {
            switch (string.charAt(i)) {
                case 'G':
                    newString += 'C';
                    break;
                case 'C':
                    newString += 'G';
                    break;
                case 'A':
                    newString += 'T';
                    break;
                case 'T':
                    newString += 'A';
                    break;
            }
        }

        return newString;
    }

    public int getMaxWordCount(int index) {
        int max = 0;

        for (int i = 0; i < Math.min(index+1, size); i++) {
            int val = index - i;

            if (val + size > dna.length()) continue;

            String word = dna.substring(val, val + size);

            if (!sizes.containsKey(word)) continue;

            int current = sizes.get(word);

            if (current > max) {
                max = current;
            }
        }

        return max;
    }


    public int[] getMinAndMax() {
        int max = 0;
        int min = 999999999;

        for (Map.Entry<String, Integer> word : sizes.entrySet()) {
            if (max < word.getValue()) {
                max = word.getValue();
            }

            if (min > word.getValue()) {
                min = word.getValue();
            }
        }

        return new int[]{min, max};
    }

    public void printSizes(int threshold) {
        int underThresholdWords = 0;
        int overThresholdWords = 0;
        int average = 0;
        int max = 0;
        int min = 999999999;

        for (Map.Entry<String, Integer> word : sizes.entrySet()) {
            average += word.getValue();

            if (max < word.getValue()) {
                max = word.getValue();
            }

            if (min > word.getValue()) {
                min = word.getValue();
            }

            if (word.getValue() > threshold) {
                System.out.println("Key: " + word.getKey() + ": Value: " + word.getValue());
                overThresholdWords++;
            } else {
                underThresholdWords++;
            }
        }

        System.out.println("average: " + average / sizes.size());
        System.out.println("max: " + max);
        System.out.println("min: " + min);
        System.out.println("words under threshold: " + underThresholdWords);
        System.out.println("words over threshold: " + overThresholdWords);
    }

}
