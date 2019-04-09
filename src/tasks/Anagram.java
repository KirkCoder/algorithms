package tasks;

public class Anagram {

    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c', 'd'};
        anagram(arr, 0);
    }

    private static void anagram(char[] arr, int position) {
        if (position == arr.length - 1) return;
        for (int i = position; i < arr.length; i++) {
            anagram(arr, position + 1);
            if (position == arr.length - 2) print(arr);
            shift(arr, position);
        }
    }

    private static void shift(char[] arr, int position) {
        char tmp = arr[position];
        for (int j = position + 1; j < arr.length; j++) {
            arr[j - 1] = arr[j];
        }
        arr[arr.length - 1] = tmp;
    }

    private static void print(char[] arr) {
        for (char c : arr) {
            System.out.print(c);
        }
        System.out.println(" ");
    }
}
