package sort;

public class QuickSortTmp {

    public static void main(String[] args) {
        int[] arr = {78, 2, 1, 14, 255, -6, 3, 2, -19, 102, 7};
        print(arr);
        sort(arr, 0, arr.length - 1);
        print(arr);
    }

    public static void sort(int[] arr, int start, int end){
        if (start >= end) return;
        int position = (start + end) / 2;
        int tmp = arr[end];
        arr[end] = arr[position];
        arr[position] = tmp;
        int delimiter = start - 1;
        for(int i = start; i < end; i++){
            if (arr[i] <= arr[end]){
                delimiter++;
                int iTmp = arr[i];
                arr[i] = arr[delimiter];
                arr[delimiter] = iTmp;
            }
        }
        int sTmp = arr[end];
        arr[end] = arr[delimiter + 1];
        arr[delimiter + 1] = sTmp;
        sort(arr, start, delimiter);
        sort(arr, delimiter + 2, end);
    }

    private static void print(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println(" ");
    }
}
