package test;

public class Test {

    public static void main(String[] args){
        int i = 0;
        int j = ~i;
        array4(new int[]{1, 2, 4, 4, 5, 6, 4, 7, 8});
        array4(new int[3]);
    }

    public static int[] array4(int[] arr) {
        int pos = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                pos = i;
            }
        }
        if (pos == -1) {
            throw new RuntimeException("4 нет в массиве");

        }
        int [] result = new int[arr.length - 1 - pos];
        for (int i = pos + 1, j = 0; i < arr.length; i++, j++) {
            result[j] = arr[i];
        }

        for (int i : result) {
            System.out.println(i);
        }

        return result;
    }
}