import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calibration {

    public static void main(String[] args) {
        List<Integer> frequencies = new ArrayList<>();
        try {
            frequencies = input("/inputDay1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Frequency frequency = new Frequency(0);
        for (Integer frequencyModificator : frequencies) {
            frequency.add(new Frequency(frequencyModificator));
        }

        System.out.println(frequency);


        frequency = new Frequency(0);
        Set<Frequency> frequenciesHistory = new HashSet<>();
        frequenciesHistory.add(new Frequency(0));

        boolean frequencyFind = false;

        while (!frequencyFind) {
            for (Integer frequencyModificator : frequencies) {
                frequency.add(new Frequency(frequencyModificator));

                if(!frequenciesHistory.add(frequency)) {
                    System.out.println(frequency);
                    frequencyFind = true;
                    break;
                }
            }
        }


    }

    private static List<Integer> input(String fileName) throws IOException {

        List<Integer> frequencies = new ArrayList<>();

        InputStream is = Frequency.class.getResourceAsStream("/inputDay1");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;

        while ((line = reader.readLine()) != null){
            if (line.contains("+")) {
                frequencies.add(Integer.valueOf(line.substring(1)));
            } else {
                frequencies.add(Integer.valueOf(line));
            }
        }

        return  frequencies;
    }
}
