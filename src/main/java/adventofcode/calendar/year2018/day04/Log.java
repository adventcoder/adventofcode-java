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
        Integer guardId = null;
        DateTime napStart = null;
        List<Log> logs = parseLogs(input);
        logs.sort(Comparator.comparing((log) -> log.ts));
        for (Log log : logs) {
            if (log.message.matches("Guard #\\d+ begins shift")) {
                guardId = Integer.parseInt(log.message.substring(7, log.message.length() - 13));
                napStart = null;
            } else if (log.message.equals("falls asleep")) {
                napStart = log.ts;
                assert napStart.hour == 0;
            } else if (log.message.equals("wakes up") && napStart != null) {
                DateTime napEnd = log.ts;
                assert napEnd.hour == 0 && napEnd.day == napStart.day && napEnd.month == napStart.month && napEnd.year == napStart.year;
                action.accept(guardId, Range.exclusive(napStart.minute, napEnd.minute));
            }
        }
    }
}
