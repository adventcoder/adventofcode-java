package adventofcode.calendar.year2018.day04;

import adventofcode.utils.Range;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;

import static adventofcode.utils.Iterables.collect;

public class Log {
    public final DateTime ts;
    public final String message;

    public Log(String line) {
        ts = new DateTime(line.substring(1, 17));
        message = line.substring(19);
    }

    @Override
    public String toString() {
        return "[" + ts + "] " + message;
    }

    public static List<Log> parseLogs(String input) {
        return collect(Log::new, Arrays.asList(input.split("\n")));
    }

    public static void forEachNap(String input, BiConsumer<Integer, Range> action) {
        Integer id = null;
        DateTime napStart = null;
        List<Log> logs = parseLogs(input);
        logs.sort(Comparator.comparing((log) -> log.ts));
        for (Log log : logs) {
            if (log.message.matches("Guard #\\d+ begins shift")) {
                id = Integer.parseInt(log.message.substring(7, log.message.length() - 13));
                napStart = null;
            } else if (log.message.equals("falls asleep")) {
                assert log.ts.hour == 0;
                napStart = log.ts;
            } else if (napStart != null && log.message.equals("wakes up")) {
                assert log.ts.truncateHour().equals(napStart.truncateHour());
                // System.out.println("Guard #" + id + " slept for " + (log.instant.minute - sleepStart.minute) + " minutes from " + sleepStart.minute + " to " + log.instant.minute);
                action.accept(id, new Range(napStart.minute, log.ts.minute));
            }
        }
    }
}
