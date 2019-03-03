package sort;

public class InsertionSort {

    public static void main(String[] args){
        int[] arr = {78, 2, 1, 14, 255, -6, 3, 2, -19, 102};
        print(arr);
        int[] res = sort(arr);
        print(res);
    }

    private static  int[] sort(int[] arr){
        for(int i = 1; i < arr.length; i ++){
            int element = arr[i];
            for(int j = i - 1; j > -1; j--){
                if(arr[j] > element){
                    arr[j + 1] = arr[j];
                    arr[j] = element;
                }
            }
        }
        return arr;
    }

    private static void print(int[] array){
        for (int value : array) {
            System.out.print(value + ", ");
        }
        System.out.println(" ");
    }
}
