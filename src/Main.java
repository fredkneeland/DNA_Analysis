/**
 * Created by fredkneeland on 5/1/17.
 */
public class Main {
    public static void main(String[] args) {
        FileReader reader = new FileReader("./dna/chromosome1.fa");

        String[] lines = reader.getFile();

        System.out.println("len: " + lines.length);
    }
}
