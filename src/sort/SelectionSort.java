package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

//O*n^2
public class SelectionSort {
    public static void main(String[] args) {
        sort();
        sout();
    }

    private static int[] arr = {23, 42, 4, 16, 8, 15};

    private static void sort(){
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }

    private static void sout(){
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
