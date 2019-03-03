import java.util.TreeSet;

public class LeastCommonMultiple {
    public static void main(String[] args) {
        System.out.println("lcm: " + lcm(20, 16));
    }

    private static int lcm(int a, int b){
        int i = 1;
        TreeSet<Integer> ts = new TreeSet<>();
        while(i != Integer.MAX_VALUE){
            int aMultiple = a * i;
            int bMultiple = b * i;
            if(ts.contains(aMultiple)) return aMultiple;
            ts.add(aMultiple);
            if (ts.contains(bMultiple)) return bMultiple;
            ts.add(bMultiple);
            i++;
        }
        return -1;
    }
}
