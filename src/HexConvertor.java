import java.util.HashMap;

public class HexConvertor {
    public HashMap<String, String> hashMap = new HashMap<>();

    public HexConvertor() {
        hashMap.put("0", "AA");
        hashMap.put("1", "AT");
        hashMap.put("2", "AG");
        hashMap.put("3", "AC");
        hashMap.put("4", "TA");
        hashMap.put("5", "TT");
        hashMap.put("6", "TG");
        hashMap.put("7", "TC");
        hashMap.put("8", "GA");
        hashMap.put("9", "GT");
        hashMap.put("A", "GG");
        hashMap.put("B", "GC");
        hashMap.put("C", "CA");
        hashMap.put("D", "CT");
        hashMap.put("E", "CG");
        hashMap.put("F", "CC");
    }

    public String getDNAString(String hexCharacter) {
        return hashMap.get(hexCharacter);
    }

    public String convertHexFileToDNA(String file) {
        FileReader reader = new FileReader(file);

        reader.getFile();
        String string = reader.merge();
        String newString = "";

        for (int i = 0; i < string.length(); i++) {
            newString += getDNAString("" + string.charAt(i));
        }

        return newString;
    }
}
