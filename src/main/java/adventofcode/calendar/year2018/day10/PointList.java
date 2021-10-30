package adventofcode.calendar.year2018.day10;

import adventofcode.utils.Range;
import adventofcode.utils.Vector2D;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PointList {
    private final List<Vector2D> pos = new ArrayList<>();
    private final List<Vector2D> vel = new ArrayList<>();

    public PointList(String input) {
        Pattern pattern = Pattern.compile("(\\w+)=<([^,]*),([^>]*)>");
        for (String line : input.split("\n")) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int x = Integer.parseInt(matcher.group(2).trim());
                int y = Integer.parseInt(matcher.group(3).trim());
                if (matcher.group(1).equals("position")) {
                    pos.add(new Vector2D(x, y));
                } else if (matcher.group(1).equals("velocity")) {
                    vel.add(new Vector2D(x, y));
                }
            }
        }
    }

    public void tick(int t) {
        for (int i = 0; i < pos.size(); i++) {
            pos.get(i).addEq(vel.get(i), t);
        }
    }

    // var(A,B) = avg(Ax Bx+Ay By) - (avg(Ax) avg(Bx) + avg(Ay) avg(By))
    //
    // var(A,B) = (var(A+B) - var(A) - var(B))/2
    //
    private double variance(List<Vector2D> as, List<Vector2D> bs) {
        double Ax = 0.0;
        double Bx = 0.0;
        double Ay = 0.0;
        double By = 0.0;
        double dot = 0.0;
        for (int i = 0; i < as.size(); i++) {
            dot += (as.get(i).dot(bs.get(i)) - dot) / (i + 1);
            Ax += (as.get(i).x - Ax) / (i + 1);
            Ay += (as.get(i).y - Ay) / (i + 1);
            Bx += (bs.get(i).x - Bx) / (i + 1);
            By += (bs.get(i).y - By) / (i + 1);
        }
        return dot - (Ax * Bx + Ay * By);
    }

    // var(A+B t) = var(A) + 2 var(A,B) t + var(B) t^2
    public double variance(double t) {
        return variance(pos, pos) + 2 * variance(pos, vel) * t + variance(vel, vel) * t * t;
    }

    // f(t) = var(A+B t)
    //      = var(A) + 2 var(A,B) t + var(B) t^2
    //
    // f'(t) = 2 var(A,B) + 2 var(B) t
    //    t' = -var(A,B) / var(B)
    //
    public double minimizeVarianceApprox() {
        return -variance(pos, vel) / variance(vel, vel);
    }

    public int minimizeVariance() {
        int t = (int) minimizeVarianceApprox();
        return variance(t) < variance(t + 1) ? t : t + 1;
    }

    @Override
    public String toString() {
        Range xs = Range.empty();
        Range ys = Range.empty();
        for (Vector2D p : pos) {
            xs.add(p.x);
            ys.add(p.y);
        }
        StringBuilder[] lines = new StringBuilder[ys.size()];
        for (int i = 0; i < ys.size(); i++) {
            lines[i] = new StringBuilder(xs.size());
            while (lines[i].length() < xs.size()) {
                lines[i].append('.');
            }
        }
        for (Vector2D p : pos) {
            lines[p.y - ys.min].setCharAt(p.x - xs.min, '#');
        }
        return String.join("\n", lines);
    }
}
