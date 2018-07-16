package sort;

import java.util.Arrays;

// O(n*Log*n)
public class MergeSort {
    public static void main(String[] args) {
        int[] res = sort(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    private static int[] arr = {108, 15, 50, 4, 8, 42, 23, 16, 71};
//    private static int[] arr = {5, 1, 4, 12, 0, 7, 214, -1, 4};
//    static int[]arr = {1, 4, 5, 12};

    private static int[] sort(int[] a) {
        if (a.length < 2) return a;
        int mid = (a.length / 2);
        int[] left = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);
        return merge(sort(left), sort(right));
    }

    private static int[] merge(int[] left, int[] right) {

        int length = left.length + right.length;
        int[] ar = new int[length];

        int i1 = 0;
        int i2 = 0;
        int i = 0;

        while (i1 < left.length && i2 < right.length && i < length) {
            if (left[i1] > right[i2]) {
                ar[i++] = right[i2++];
            } else {
                ar[i++] = left[i1++];
            }
        }

        while (i1 < left.length){
            if (i < length){
                ar[i++] = left[i1++];
            }
        }

        while (i2 < right.length){
            if (i < length){
                ar[i++] = right[i2++];
            }
        }

        return ar;
    }
}
