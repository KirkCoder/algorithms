package sort;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        SortUtils.print(arr);
        SortUtils.print(sort(arr));
    }

    private static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int element = arr[i];
            for (int j = i - 1; j > -1; j--) {
                if (arr[j] > element) {
                    arr[j + 1] = arr[j];
                    arr[j] = element;
                } else {
                    break;
                }
            }
        }
        return arr;
    }
}
