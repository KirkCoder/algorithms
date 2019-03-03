package search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(binarySearch(arr, -10));
    }

    private static int binarySearch(int[] arr, int x) {
        return binary(arr, x, 0, arr.length - 1);
    }

    private static int binary(int[] arr, int x, int start, int end) {
        if (start > end) return -1;
        int position = (start + end) / 2;
        if (arr[position] == x) return position;
        if (arr[position] < x) return binary(arr, x, position + 1, end);
        else return binary(arr, x, start, position - 1);
    }
}
