package sort;

public class CombSort {

    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        SortUtils.print(arr);
        SortUtils.print(sort(arr));
    }

    private static int[] sort(int[] arr) {

        int gap = arr.length;
        boolean isChange = false;
        while (gap != 1 || isChange) {
            isChange = false;
            if(gap > 1)gap = gap / 2;
            for (int i = 0; i < arr.length - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    int tmp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = tmp;
                    isChange = true;
                }
            }
        }
        return arr;
    }
}
