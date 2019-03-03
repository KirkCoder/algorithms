package sort;

//O*n^2
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {78, 2, 1, 14, 255, -6, 3, 2, -19, 102};
        print(arr);
        print(sort(arr));
    }

    private static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minPosition = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minPosition = j;
                }
            }
            arr[minPosition] = arr[i];
            arr[i] = min;
        }
        return arr;
    }

    private static void print(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println(" ");
    }

//    public static void main(String[] args) {
//        sort();
//        sout();
//    }
//
//    private static int[] arr = {23, 42, 4, 16, 8, 15};
//
//    private static void sort(){
//        for (int i = 0; i < arr.length - 1; i++) {
//            int min = arr[i];
//            int minIndex = i;
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[j] < min){
//                    min = arr[j];
//                    minIndex = j;
//                }
//            }
//            arr[minIndex] = arr[i];
//            arr[i] = min;
//        }
//    }
//
//    private static void sout(){
//        for (int i : arr) {
//            System.out.println(i);
//        }
//    }
}
