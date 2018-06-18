package sort;

//O*n^2
public class InsertionSort {
    public static void main(String[] args) {
        sort();
        sout();
    }

    private static int[] arr = {23, 42, 4, 16, 8, 15};

    private static void sort(){
        for (int i = 1; i < arr.length; i++) {
            int element = arr[i];
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > element){
                    arr[j] = arr[j - 1];
                    arr[j - 1] = element;
                }
            }
        }
    }

    private static void sout(){
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
