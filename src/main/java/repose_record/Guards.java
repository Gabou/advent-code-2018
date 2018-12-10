package repose_record;

import reader.InputReader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Guards {

    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.input("/inputDay4");
        SortedSet<String> objects = new TreeSet<>(input);

        Map<Integer, SortedSet<String>> stateByGuard = new HashMap<>();
        int actualGuard = 0;

        for (String string : objects) {
            if(string.contains("shift")) {
                actualGuard = Integer.valueOf(string.substring(string.indexOf("#")+1,string.indexOf(" begins")));
                if (stateByGuard.containsKey(actualGuard)) {
                    stateByGuard.get(actualGuard).add(string);
                } else {
                    SortedSet<String> strings = new TreeSet<>();
                    strings.add(string);
                    stateByGuard.put(actualGuard,strings);
                }
            } else {
                stateByGuard.get(actualGuard).add(string);
            }
        }


        for (Map.Entry<Integer, SortedSet<String>> integerSortedSetEntry : stateByGuard.entrySet()) {
            System.out.println(integerSortedSetEntry.getKey());
            for (String s : integerSortedSetEntry.getValue()) {
                System.out.println("       " + s);
            }

        }

        /*
        for (String object : objects) {
            System.out.println(object);
        }
        */
    }

}
