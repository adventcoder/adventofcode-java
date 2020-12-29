package adventofcode.utils;

import static java.lang.Math.abs;
import static java.lang.Math.floorMod;

public class MoreMath {
    public static int sgn(int x) {
        return ((-x) >>> 31) - (x >>> 31);
    }

    public static int[] extendedGCD(int a, int b) {
        int[] A = { abs(a), sgn(a), 0 };
        int[] B = { abs(b), 0, sgn(b) };
        while (B[0] != 0) {
            int q = A[0] / B[0];
            A[0] -= q * B[0];
            A[1] -= q * B[1];
            A[2] -= q * B[2];
            int[] C = A;
            A = B;
            B = C;
        }
        return A;
    }

    public static int gcd(int a, int b) {
        return extendedGCD(a, b)[0];
    }

    public static int modInverse(int a, int m) {
        int[] D = extendedGCD(a, m);
        if (D[0] != 1) throw new ArithmeticException(a + " not invertible mod " + m);
        return floorMod(D[1], m);
    }
}
