package sort;

import java.util.Arrays;

// O(n*Log*n)
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        SortUtils.print(arr);
        SortUtils.print(sort(arr));
    }

    public static int[] sort(int[] arr) {
        if (arr.length == 1) return arr;
        int middle = arr.length / 2;
        return merge(
                sort(Arrays.copyOfRange(arr, 0, middle)),
                sort(Arrays.copyOfRange(arr, middle, arr.length))
        );
    }

    private static int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];

        int iLeft = 0;
        int iRight = 0;
        int iRes = 0;

        while (iLeft < left.length && iRight < right.length) {
            if (left[iLeft] <= right[iRight]) {
                res[iRes++] = left[iLeft++];
            } else {
                res[iRes++] = right[iRight++];
            }
        }

        while (iLeft < left.length) {
            res[iRes++] = left[iLeft++];
        }

        while (iRight < right.length) {
            res[iRes++] = right[iRight++];
        }
        return res;
    }
}
