package adventofcode.utils;

public class IntMath {
    private static final int[] pow10 = new int[32];
    
    static {
        pow10[0] = 1;
        for (int i = 1; i < pow10.length; i++) {
            pow10[i] = pow10[i - 1] * 10;
        }
    }

    public static int pow10(int n) {
        if (n < 0 || n >= 32) {
            return 0;
        }
        return pow10[n];
    }

    public static int ceilPowerOfTwoMinusOne(int n) {
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
    }

    public static int sgn(int n) {
        return ((-n) >>> 31) - (n >>> 31);
    }

    public static int gcd(int a, int b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;
        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
