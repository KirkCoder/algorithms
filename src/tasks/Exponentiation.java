package tasks;

public class Exponentiation {
    public static void main(String[] args) {
        System.out.println(exponent(3, 1, 11));
    }

    private static long exponent(long value, long x, int exp) {
        if (exp == 1) return value * x;
        if (exp % 2 == 0) {
            return exponent(value * value, x, exp / 2);
        } else {
            return exponent(value * value, x * value, (exp - 1) / 2);
        }
    }
}
