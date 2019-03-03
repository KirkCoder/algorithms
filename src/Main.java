public class Main {
    public static void main(String[] args) {
        String s = "abcde";
        System.out.println(s.substring(0, 1));
        String res = s.substring(0, 1) + s.substring(2);
        System.out.println(res);
        res = "7" + res.charAt(1);
        System.out.println(res);
    }
}
