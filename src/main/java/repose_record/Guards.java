package repose_record;

import reader.InputReader;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.Comparator.comparing;

public class Guards {

    static GuardKataResult guardKata() throws IOException {
        List<String> input = InputReader.input("/inputDay4");
        List<String> records = input.stream().sorted().collect(Collectors.toList());

        Map<Integer, List<String>> recordsByGuard = groupRecordByGuard(records);

        Map<Integer, Map<LocalTime, Integer>> frequencyOfSleepTimeByGuard = mapValues(recordsByGuard, Guards::parseSleepRecords);

        int guardWithBiggestSleepTime = getGuardWithBiggestSleepTime(frequencyOfSleepTimeByGuard);
        LocalTime minuteWhenSleepingMost = getMinuteSleptTheMost(frequencyOfSleepTimeByGuard.get(guardWithBiggestSleepTime));

        int result1 = guardWithBiggestSleepTime * minuteWhenSleepingMost.getMinute();

        //Guard sleeping the more frequently at wich minute
        int guardId = 0;
        int moreFrequenciesOfSleep = 0;
        LocalTime minuteWhereSleepingTheMore = LocalTime.of(0, 0);

        for (Map.Entry<Integer, Map<LocalTime, Integer>> integerMapEntry : frequencyOfSleepTimeByGuard.entrySet()) {

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

    private static int getGuardWithBiggestSleepTime(Map<Integer, Map<LocalTime, Integer>> frequencyOfSleepTimeByGuard) {
        return frequencyOfSleepTimeByGuard.keySet().stream()
                .max(comparing(guardId -> countTotalSleepTimeForGuard(frequencyOfSleepTimeByGuard.get(guardId))))
                .orElse(0);
    }

    private static LocalTime getMinuteSleptTheMost(Map<LocalTime, Integer> sleepCountByMinute) {
        return sleepCountByMinute.entrySet().stream()
                .max(comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(LocalTime.of(0, 0));
    }

    private static int countTotalSleepTimeForGuard(Map<LocalTime, Integer> sleepCountByMinute) {
        int sleepingTime = 0;

        for (Map.Entry<LocalTime, Integer> localTimeIntegerEntry : sleepCountByMinute.entrySet()) {
            sleepingTime += localTimeIntegerEntry.getValue();
        }
        return sleepingTime;
    }

    private static <K, V1, V2> Map<K, V2> mapValues(Map<K, V1> map, Function<V1, V2> valueMapper) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> valueMapper.apply(entry.getValue())));
    }

    private static Map<Integer, List<String>> groupRecordByGuard(List<String> records) {
        Map<Integer, List<String>> recordsByGuard = new HashMap<>();
        int currentGuard = 0;

        for (String record : records) {
            if (record.contains("shift")) {
                currentGuard = parseGuardId(record);
                recordsByGuard.computeIfAbsent(currentGuard, i -> new ArrayList<>());
            }
            recordsByGuard.get(currentGuard).add(record);
        }
        return recordsByGuard;
    }

    private static Integer parseGuardId(String record) {
        return Integer.valueOf(record.substring(record.indexOf('#') + 1, record.indexOf(" begins")));
    }

    private static Map<LocalTime, Integer> parseSleepRecords(List<String> sleepRecordsForGuard) {
        Map<LocalTime, Integer> frequencyOfSleepTime = new HashMap<>();
        LocalTime asleep = LocalTime.of(0, 0);

        for (String record : sleepRecordsForGuard) {
            if (record.contains("asleep")) {
                asleep = parseMinuteFromRecord(record);
            } else if (record.contains("wakes")) {
                LocalTime wakeUp = parseMinuteFromRecord(record);

                for (LocalTime minute = asleep; minute.isBefore(wakeUp); minute = minute.plus(1, MINUTES)) {
                    Integer newFrequency = frequencyOfSleepTime.getOrDefault(minute, 0) + 1;
                    frequencyOfSleepTime.put(minute, newFrequency);
                }
            }
        }

        return frequencyOfSleepTime;
    }

    private static LocalTime parseMinuteFromRecord(String record) {
        return LocalTime.parse(record.substring(record.indexOf(' ') + 1, record.indexOf("] ")));
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
