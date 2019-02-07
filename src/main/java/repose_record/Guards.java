package repose_record;

import reader.InputReader;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Guards {

    static GuardKataResult guardKata() throws IOException {
        List<String> input = InputReader.input("/inputDay4");
        SortedSet<String> records = new TreeSet<>(input);

        Map<Integer, SortedSet<String>> stateByGuard = new HashMap<>();
        int actualGuard = 0;

        for (String record : records) {
            if (record.contains("shift")) {
                actualGuard = parseGuardId(record);
                if (!stateByGuard.containsKey(actualGuard)) {
                    stateByGuard.put(actualGuard, new TreeSet<>());
                }
                stateByGuard.get(actualGuard).add(record);
            } else {
                stateByGuard.get(actualGuard).add(record);
            }
        }

        Map<Integer, Map<LocalTime, Integer>> frequencyOfSleepTimeByGard = new HashMap<>();

        for (Map.Entry<Integer, SortedSet<String>> integerSortedSetEntry : stateByGuard.entrySet()) {
            Map<LocalTime, Integer> frequencyOfSleepTime = parseSleepRecords(integerSortedSetEntry.getValue());
            frequencyOfSleepTimeByGard.put(integerSortedSetEntry.getKey(), frequencyOfSleepTime);
        }

        int guardWithBiggestSleepTime = 0;
        int sleepTime = 0;
        LocalTime minuteWhenSleepingMost = LocalTime.of(0, 0);

        // Guard sleeping the more
        for (Map.Entry<Integer, Map<LocalTime, Integer>> integerMapEntry : frequencyOfSleepTimeByGard.entrySet()) {

            LocalTime minuteWhenSleepingMostForThisGuard = LocalTime.of(0, 0);
            int sleepingTime = 0;
            int moreFrequenciesOfSleep = 0;
            for (Map.Entry<LocalTime, Integer> localTimeIntegerEntry : integerMapEntry.getValue().entrySet()) {
                sleepingTime += localTimeIntegerEntry.getValue();
                if (localTimeIntegerEntry.getValue() > moreFrequenciesOfSleep) {
                    moreFrequenciesOfSleep = localTimeIntegerEntry.getValue();
                    minuteWhenSleepingMostForThisGuard = localTimeIntegerEntry.getKey();
                }
            }

            if (sleepingTime > sleepTime) {
                guardWithBiggestSleepTime = integerMapEntry.getKey();
                sleepTime = sleepingTime;
                minuteWhenSleepingMost = minuteWhenSleepingMostForThisGuard;
            }
        }

        int result1 = guardWithBiggestSleepTime * minuteWhenSleepingMost.getMinute();

        //Guard sleeping the more frequently at wich minute
        int guardId = 0;
        int moreFrequenciesOfSleep = 0;
        LocalTime minuteWhereSleepingTheMore = LocalTime.of(0, 0);

        for (Map.Entry<Integer, Map<LocalTime, Integer>> integerMapEntry : frequencyOfSleepTimeByGard.entrySet()) {

            LocalTime minuteWhenSleepingMostForThisGuard = LocalTime.of(0, 0);
            for (Map.Entry<LocalTime, Integer> localTimeIntegerEntry : integerMapEntry.getValue().entrySet()) {
                if (localTimeIntegerEntry.getValue() > moreFrequenciesOfSleep) {
                    guardId = integerMapEntry.getKey();
                    moreFrequenciesOfSleep = localTimeIntegerEntry.getValue();
                    minuteWhereSleepingTheMore = localTimeIntegerEntry.getKey();
                }
            }
        }

        int result2 = guardId * minuteWhereSleepingTheMore.getMinute();

        return new GuardKataResult(result1, result2);
    }

    private static Integer parseGuardId(String record) {
        return Integer.valueOf(record.substring(record.indexOf('#') + 1, record.indexOf(" begins")));
    }

    private static Map<LocalTime, Integer> parseSleepRecords(SortedSet<String> sleepRecordsForGuard) {
        Map<LocalTime, Integer> frequencyOfSleepTime = new HashMap<>();
        LocalTime timeSleepBegin = LocalTime.of(0, 0);

        for (String s : sleepRecordsForGuard) {
            if (s.contains("asleep")) {
                timeSleepBegin = LocalTime.parse(s.substring(s.indexOf(' ') + 1, s.indexOf("] ")));
            } else if (s.contains("wakes")) {
                LocalTime wakeUp = LocalTime.parse(s.substring(s.indexOf(' ') + 1, s.indexOf("] ")));

                while (!timeSleepBegin.equals(wakeUp)) {
                    Integer time = frequencyOfSleepTime.putIfAbsent(timeSleepBegin, 1);
                    if (time != null) {
                        frequencyOfSleepTime.put(timeSleepBegin,
                                frequencyOfSleepTime.get(timeSleepBegin) + 1);
                    }
                    timeSleepBegin = timeSleepBegin.plus(1, ChronoUnit.MINUTES);
                }
                timeSleepBegin = LocalTime.of(0, 0);
            }
        }

        return frequencyOfSleepTime;
    }

    public static void main(String[] args) throws IOException {
        GuardKataResult guardKataResult = guardKata();
        System.out.println(guardKataResult.part1);
        System.out.println("answer part 2 : " + guardKataResult.part2);
    }

    static class GuardKataResult {
        final int part1;
        final int part2;

        GuardKataResult(int part1, int part2) {
            this.part1 = part1;
            this.part2 = part2;
        }
    }

}
