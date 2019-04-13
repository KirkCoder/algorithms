package sort;

public class SortUtils {

    public static int[] getArray() {
        int[] arr = new int[34];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 99);
        }
        return arr;
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println(" ");
    }
}
