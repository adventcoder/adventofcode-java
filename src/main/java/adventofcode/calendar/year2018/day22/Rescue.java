package adventofcode.calendar.year2018.day22;

import adventofcode.utils.Vector2D;

import java.util.*;
import java.util.function.BiConsumer;

public class Rescue {
    private static final int SWITCH_TIME = 7;
    private static final int MOVE_TIME = 1;

    public static Rescue start() {
        return new Rescue(new Vector2D(0, 0), 1);
    }

    private final Vector2D pos;
    private final int gear;

    public Rescue(Vector2D pos, int gear) {
        this.pos = pos;
        this.gear = gear;
    }

    @Override
    public int hashCode() {
        return pos.hashCode() * 3 + gear;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rescue) {
            Rescue rescue = (Rescue) obj;
            return pos.equals(rescue.pos) && gear == rescue.gear;
        }
        return false;
    }

    public boolean isGoal(Cave cave) {
        return pos.equals(cave.target) && gear == 1;
    }

    public int estimateTimeToGoal(Cave cave) {
        int time = cave.target.sub(pos).abs() * MOVE_TIME;
        if (gear != 1) {
            time += SWITCH_TIME;
        }
        return time;
    }

    public Integer findTimeToGoal(Cave cave) {
        Map<Rescue, Integer> times = new HashMap<>();
        times.put(this, 0);
        PriorityQueue<Map.Entry<Rescue, Integer>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());
        queue.add(new AbstractMap.SimpleEntry<>(this, estimateTimeToGoal(cave)));
        while (!queue.isEmpty()) {
            Rescue rescue = queue.remove().getKey();
            int time = times.get(rescue);
            // System.out.println(rescue.pos + " " + rescue.gear + ": " + time);
            if (rescue.isGoal(cave)) {
                return time;
            }
            rescue.forEachNeighbour(cave, time, (newRescue, newTime) -> {
                Integer oldTime = times.get(newRescue);
                if (oldTime != null && oldTime <= newTime) return;
                times.put(newRescue, newTime);
                queue.add(new AbstractMap.SimpleEntry<>(newRescue, newTime + newRescue.estimateTimeToGoal(cave)));
            });
        }
        return null;
    }

    public void forEachNeighbour(Cave cave, int time, BiConsumer<Rescue, Integer> action) {
        for (Vector2D newPos : pos.neighbours()) {
            if (newPos.x >= 0 && newPos.y >= 0 && gear != cave.type(newPos)) {
                action.accept(new Rescue(newPos, gear), time + MOVE_TIME);
            }
        }
        action.accept(new Rescue(pos, 3 - gear - cave.type(pos)), time + SWITCH_TIME);
    }
}
