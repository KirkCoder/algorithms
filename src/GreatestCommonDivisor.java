
public class GreatestCommonDivisor {
    public static void main(String[] args) {
        System.out.println(gcd(2, 273));
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
}
