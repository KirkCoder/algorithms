package sort;

//O*n^2
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        SortUtils.print(arr);
        SortUtils.print(sort(arr));
    }

    private static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) minIndex = j;
            }
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
        return arr;
    }
}
