package repose_record;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.Comparator.comparing;

public class SleepCountByMinute {
    private final Map<LocalTime, Integer> sleepCountByMinute;

    private SleepCountByMinute(Map<LocalTime, Integer> sleepCountByMinute) {
        this.sleepCountByMinute = sleepCountByMinute;
    }

    public static SleepCountByMinute aggregateSleepFrequencies(List<SleepPeriod> sleepPeriodForGuard) {
        Map<LocalTime, Integer> sleepCountByMinute = new HashMap<>();
        for (SleepPeriod sleepPeriod : sleepPeriodForGuard) {
            //todo: please extract iterator of minutes in the SleepPeriod class
            for (LocalTime minute = sleepPeriod.asleepTime; minute.isBefore(sleepPeriod.wakeUpTime); minute = minute.plus(1, MINUTES)) {
                Integer newFrequency = sleepCountByMinute.getOrDefault(minute, 0) + 1;
                sleepCountByMinute.put(minute, newFrequency);
            }
        }
        return new SleepCountByMinute(sleepCountByMinute);
    }

    int getMaxSleepCount() {
        return this.sleepCountByMinute.entrySet().stream()
                .max(comparing(Map.Entry::getValue))
                .map(Map.Entry::getValue)
                .orElse(0);
    }

    int totalSleepTime() {
        return this.sleepCountByMinute.values().stream()
                .mapToInt(value -> value)
                .sum();
    }

    LocalTime getMinuteSleptTheMost() {
        return this.sleepCountByMinute.entrySet().stream()
                .max(comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(LocalTime.of(0, 0));
    }
}
