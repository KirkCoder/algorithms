package sort;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 5, 0, 1, 6, 5, 1, 5, 3};
        print(arr);
        sort(arr, 7);
    }

    private static void sort(int[] arr, int m) {
        int[] equal = new int[m];
        for (int key : arr) {
            equal[key]++;
        }
        print(equal);

        int[] less = new int[m];
        for (int i = 1; i < less.length; i++) {
            less[i] = getSumm(equal, i);
        }

        print(less);

        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            int value = arr[i];
            int count = equal[value];
            int position = less[value];
            for (int k = position; k < position + count; k++) {
                res[k] = value;
            }
        }
        print(res);
    }

    private static int getSumm(int[] arr, int i) {
        int res = 0;
        for (int j = i - 1; j > -1; j--) {
            res += arr[j];
        }
        return res;
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println(" ");
    }
}
