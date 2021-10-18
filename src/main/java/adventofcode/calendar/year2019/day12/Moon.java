package adventofcode.calendar.year2019.day12;

import java.util.List;

public class Moon {
    private final int[] pos;
    private final int[] vel;

    public Moon(Moon other) {
        pos = other.pos.clone();
        vel = other.vel.clone();
    }

    public Moon(int[] pos, int[] vel) {
        this.pos = pos;
        this.vel = vel;
    }

    public void applyGravity(List<Moon> moons, int i) {
        for (Moon moon : moons) {
            if (moon != this) {
                vel[i] += Math.signum(moon.pos[i] - this.pos[i]);
            }
        }
    }

    public void applyVelocity(int i) {
        pos[i] += vel[i];
    }

    public int totalEnergy() {
        int pot = 0;
        int kin = 0;
        for (int i = 0; i < Vector.axes.length; i++) {
            pot += Math.abs(pos[i]);
            kin += Math.abs(vel[i]);
        }
        return pot * kin;
    }

    public boolean equals(Moon other, int i) {
        return pos[i] == other.pos[i] && vel[i] == other.vel[i];
    }

    public void print() {
        System.out.println("pos=" + Vector.format(pos) + ", vel=" + Vector.format(vel));
    }
}
