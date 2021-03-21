package adventofcode.calendar.year2019;

import adventofcode.calendar.AbstractDay;
import adventofcode.utils.MoreMath;

import java.util.function.Consumer;

public class Day2 extends AbstractDay {
    public Day2() {
        super(2019, 2);
    }

    @Override
    public void solve(String input, Consumer<Object> answers) {
        Trinomial program = run(input, Trinomial.X, Trinomial.Y);
        answers.accept(program.eval(12, 2));
        int[] root = program.add(new Trinomial(-19690720)).findRoot();
        answers.accept(root[0] * 100 + root[1]);
    }

    public Trinomial run(String program, Trinomial noun, Trinomial verb) {
        Trinomial[] mem = parse(program);
        mem[1] = noun;
        mem[2] = verb;
        // We don't start at zero since the first instruction would need us to read memory at variable memory addresses.
        // In the actual input the first 4 instructions are just used for setting up a palette of values to add or
        // multiply with, and the actual accumulation of the output doesn't start until after that, so we don't have to
        // start the computation until after that.
        for (int ip = 16; mem[ip].intValue() != 99; ip += 4) {
            int op = mem[ip].intValue();
            int a = mem[ip + 1].intValue();
            int b = mem[ip + 2].intValue();
            int c = mem[ip + 3].intValue();
            switch (op) {
            case 1:
                mem[c] = mem[a].add(mem[b]);
                break;
            case 2:
                mem[c] = mem[a].mul(mem[b]);
                break;
            default:
                throw new UnsupportedOperationException();
            }
        }
        return mem[0];
    }

    private Trinomial[] parse(String program) {
        String[] tokens = program.split(",");
        Trinomial[] mem = new Trinomial[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            mem[i] = new Trinomial(Integer.parseInt(tokens[i]));
        }
        return mem;
    }

    public static class Trinomial {
        public static final Trinomial X = new Trinomial(1, 0, 0);
        public static final Trinomial Y = new Trinomial(0, 1, 0);

        private final int a;
        private final int b;
        private final int c;

        public Trinomial(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Trinomial(int c) {
            this(0, 0, c);
        }

        private boolean isConstant() {
            return a == 0 && b == 0;
        }

        public int intValue() {
            if (!isConstant()) throw new UnsupportedOperationException();
            return c;
        }

        public int eval(int x, int y) {
            return a * x + b * y + c;
        }

        public int[] findRoot() {
            int d = MoreMath.gcd(a, b);
            if (c % d != 0) return null;
            int A = a / d, B = b / d, C = c / d;
            int y = Math.floorMod(-C * MoreMath.modInverse(B, A), A);
            int x = (-C - B * y) / A;
            return new int[] { x, y };
        }

        public Trinomial add(Trinomial other) {
            return new Trinomial(a + other.a, b + other.b, c + other.c);
        }

        public Trinomial mul(Trinomial other) {
            if (isConstant() && other.isConstant()) {
                return new Trinomial(c * other.c);
            } else if (isConstant()) {
                return new Trinomial(c * other.a, c * other.b, c * other.c);
            } else if (other.isConstant()) {
                return new Trinomial(a * other.c, b * other.c, c * other.c);
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }
}
