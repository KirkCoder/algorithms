package sort;

//O(n^2) O(n*logN)
public class InsertionShellSort {
    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        SortUtils.print(arr);
        SortUtils.print(sort(arr));
    }

    private static int[] sort(int[] arr) {
        int index = arr.length / 3;
        while (index >= 1) {
            for (int i = 0; i < index; i++) {
                shell(arr, i, index);
                SortUtils.print(arr);
            }
            index = index / 3;
        }
        return arr;
    }

    private static void shell(int[] arr, int start, int index) {
        for (int i = start; i < arr.length; i = i + index) {
            int element = arr[i];
            for (int j = i - index; j > -1; j = j - index) {
                if (arr[j] > element) {
                    arr[j + index] = arr[j];
                    arr[j] = element;
                } else {
                    break;
                }
            }
        }
    }
}
