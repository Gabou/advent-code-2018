package repose_record;

import java.time.LocalTime;

class SleepPeriod {
    final LocalTime asleepTime;
    final LocalTime wakeUpTime;

    public SleepPeriod(LocalTime asleepTime, LocalTime wakeUpTime) {
        this.asleepTime = asleepTime;
        this.wakeUpTime = wakeUpTime;
    }
}
