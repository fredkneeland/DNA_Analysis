/**
 * Created by fredkneeland on 5/1/17.
 */
public class RandomGenerator {

    public static void randomlyGenerateDNAFile(String name, int size) {

        FileWriter writer = new FileWriter();

        String[] strings = new String[1];
        strings[0] = "";

        for (int i = 0; i < size; i++) {
            strings[0] += getLetter();
        }

        writer.writeToFile(strings, name, 0, 1);
    }

    public static String getLetter() {
        int val = (int) (Math.random() * 4);

        switch (val) {
            case 0:
                return "A";
            case 1:
                return "T";
            case 2:
                return "C";
            case 3:
                return "G";
            default:
                throw new Error("What is happening");
        }
    }
}
