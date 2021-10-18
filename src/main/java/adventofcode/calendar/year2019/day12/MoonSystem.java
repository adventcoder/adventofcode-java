package adventofcode.calendar.year2019.day12;

import java.util.ArrayList;
import java.util.List;

public class MoonSystem {
    private final List<Moon> moons = new ArrayList<>();

    public MoonSystem(MoonSystem other) {
        for (Moon moon : other.moons) {
            moons.add(new Moon(moon));
        }
    }

    public MoonSystem(String input) {
        for (String line : input.split("\n")) {
            moons.add(new Moon(Vector.parse(line), Vector.zero()));
        }
    }

    public void tick() {
        for (int i = 0; i < Vector.axes.length; i++) {
            tick(i);
        }
    }

    public void tick(int i) {
        for (Moon moon : moons) {
            moon.applyGravity(moons, i);
        }
        for (Moon moon : moons) {
            moon.applyVelocity(i);
        }
    }

    public int totalEnergy() {
        int energy = 0;
        for (Moon moon : moons) {
            energy += moon.totalEnergy();
        }
        return energy;
    }

    public boolean equals(MoonSystem other, int i) {
        for (int j = 0; j < moons.size(); j++) {
            if (!moons.get(j).equals(other.moons.get(j), i)) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        for (Moon moon : moons) {
            moon.print();
        }
    }
}
