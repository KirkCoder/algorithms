package sort;

// O(n^2) or O(n*Log*n)
public class QuickSort {
    private static int[] arr = {23, 42, 4, 16, 8, 15};

    public static void main(String[] args) {
        sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void sort(int[]a, int from, int to){
        if (from >= to) return;
        int element = a[to];
        int l = from - 1;
        int r = from - 1;
        int u = to - 1;
        for (int i = from; i <= u; i++) {
            if (a[i] <= element){
                l++;
                r++;
                int buf = a[l];
                a[l] = a[i];
                a[i] = buf;
            } else {
                r++;
                int buf = a[r];
                a[r] = a[i];
                a[i] = buf;
            }
        }
        int buf = a[l + 1];
        a[l + 1] = a[to];
        a[to] = buf;
        sort(a, 0, l - 1);
        sort(a, l + 1, to);
    }
}