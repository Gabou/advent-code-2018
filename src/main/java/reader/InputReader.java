package reader;

import frequency.Frequency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputReader {

    public static List<String> input(String fileName) throws IOException {

        List<String> lines = new ArrayList<>();

        InputStream is = Frequency.class.getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;

        while ((line = reader.readLine()) != null){
            lines.add(line);
        }

        return  lines;
    }
}
