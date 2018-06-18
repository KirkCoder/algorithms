package sort;

//O*n^2
public class BubbleSort {
    public static void main(String[] args) {
        sort();
        sout();
    }

    private static int[]arr = {5, 1, 4, 12, 0, 7, 214, -1, 4};
//    static int[]arr = {1, 4, 5, 12};

    private static void sort(){
        boolean isChange = false;
        int k = arr.length - 1; // сколько проходов нужно для {2, 3} -> 1
        for (int z = 0; z < arr.length - 1; z++){
            for (int i = 0; i < k; i++) {
                if (arr[i] > arr[i + 1]) {
                    int buf = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = buf;
                    isChange = true;
                }

                if (arr[k - i - 1] > arr[k -i]){ // оптимизация
                    int buf = arr[k - i - 1];
                    arr[k -i - 1] = arr[k - i];
                    arr[k - i] = buf;
                }
            }
            k--; // оптимизация
            if (!isChange)return; // оптимизация
        }
    }

    private static void sout() {
        for (int i : arr) {
            System.out.println(i);
        }
    }

}
