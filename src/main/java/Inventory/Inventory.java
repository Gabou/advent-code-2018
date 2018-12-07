import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        try {
            List<String> iDs = input("/inputDay2");
            System.out.println(inventory.checksum(iDs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int checksum(List<String> boxIDs) {
        int idsWithTwoSameLetters = 0;
        int idsWithThreeSameLetters = 0;

        for (String boxID : boxIDs) {
            Map<Character,Integer> numberOfEachIdLetters = new HashMap<>();
            for (char letter : boxID.toCharArray()) {
                if (numberOfEachIdLetters.containsKey(letter)) {
                    int newValue = numberOfEachIdLetters.get(letter) +1;
                    numberOfEachIdLetters.put(letter,newValue);
                } else {
                    numberOfEachIdLetters.put(letter,1);
                }
            }

            if (numberOfEachIdLetters.containsValue(2)) {
                idsWithTwoSameLetters++;
            }

            if (numberOfEachIdLetters.containsValue(3)) {
                idsWithThreeSameLetters++;
            }
        }

        return idsWithThreeSameLetters * idsWithTwoSameLetters;
    }

    private static List<String> input(String fileName) throws IOException {

        List<String> frequencies = new ArrayList<>();

        InputStream is = Frequency.class.getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;

        while ((line = reader.readLine()) != null){
            frequencies.add(line);
        }

        return  frequencies;
    }

}
