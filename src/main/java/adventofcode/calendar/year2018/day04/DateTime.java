package adventofcode.calendar.year2018.day04;

public class DateTime implements Comparable<DateTime> {
    public final int year;
    public final int month;
    public final int day;
    public final int hour;
    public final int minute;

    public DateTime(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
    
    public DateTime(String str) {
        String[] dateTime = str.split(" ");
        String[] date = dateTime[0].split("-");
        String[] time = dateTime[1].split(":");
        year = Integer.parseInt(date[0]);
        month = Integer.parseInt(date[1]);
        day = Integer.parseInt(date[2]);
        hour = Integer.parseInt(time[0]);
        minute = Integer.parseInt(time[1]);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DateTime) {
            DateTime other = (DateTime) obj;
            return year == other.year &&
                    month == other.month &&
                    day == other.day &&
                    hour == other.hour && 
                    minute == other.minute;
        }
        return false;
    }

    @Override
    public int compareTo(DateTime other) {
        int cmp = Integer.compare(year, other.year);
        if (cmp == 0) {
            cmp = Integer.compare(month, other.month);
            if (cmp == 0) {
                cmp = Integer.compare(day, other.day);
                if (cmp == 0) {
                    cmp = Integer.compare(hour, other.hour);
                    if (cmp == 0) {
                        cmp = Integer.compare(minute, other.minute);
                    }
                }
            }
        }
        return cmp;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
    }
}
