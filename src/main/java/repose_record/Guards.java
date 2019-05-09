package repose_record;

import reader.InputReader;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Guards {

    static GuardKataResult guardKata() throws IOException {
        List<String> input = InputReader.input("/inputDay4");
        List<String> records = input.stream().sorted().collect(Collectors.toList());

        Map<Integer, List<String>> recordsByGuard = groupRecordByGuard(records);

        Map<Integer, SleepCountByMinute> frequencyOfSleepTimeByGuard = mapValues(recordsByGuard, sleepRecordsForGuard -> {
            List<SleepPeriod> sleepPeriodForGuard = parseSleepPeriods(sleepRecordsForGuard);
            return SleepCountByMinute.aggregateSleepFrequencies(sleepPeriodForGuard);
        });

        int guardWithBiggestSleepTime = getGuardWithBiggestSleepTime(frequencyOfSleepTimeByGuard);
        LocalTime minuteWhenSleepingMost = frequencyOfSleepTimeByGuard.get(guardWithBiggestSleepTime).getMinuteSleptTheMost();

        int result1 = guardWithBiggestSleepTime * minuteWhenSleepingMost.getMinute();

        //Guard sleeping the more frequently at wich minute
        int guardId = getGuardIdWithMaxFrequencyOfSleep(frequencyOfSleepTimeByGuard);
        LocalTime minuteWhereSleepingTheMost = frequencyOfSleepTimeByGuard.get(guardId).getMinuteSleptTheMost();

        int result2 = guardId * minuteWhereSleepingTheMost.getMinute();

        return new GuardKataResult(result1, result2);
    }

    private static int getGuardIdWithMaxFrequencyOfSleep(Map<Integer, SleepCountByMinute> frequencyOfSleepTimeByGuard) {
        return frequencyOfSleepTimeByGuard.entrySet().stream()
                .max(comparing(input -> input.getValue().getMaxFrequencyOfSleep()))
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new);
    }

    private static int getGuardWithBiggestSleepTime(Map<Integer, SleepCountByMinute> frequencyOfSleepTimeByGuard) {
        return frequencyOfSleepTimeByGuard.keySet().stream()
                .max(comparing(guardId -> frequencyOfSleepTimeByGuard.get(guardId).countTotalSleepTimeForGuard()))
                .orElse(0);
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

    private static List<SleepPeriod> parseSleepPeriods(List<String> sleepRecordsForGuard) {
        LocalTime asleep = LocalTime.of(0, 0);

        List<SleepPeriod> sleepPeriodForGuard = new ArrayList<>();
        for (String record : sleepRecordsForGuard) {
            if (record.contains("asleep")) {
                asleep = parseMinuteFromRecord(record);
            } else if (record.contains("wakes")) {
                LocalTime wakeUp = parseMinuteFromRecord(record);
                sleepPeriodForGuard.add(new SleepPeriod(asleep, wakeUp));
            }
        }
        return sleepPeriodForGuard;
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
