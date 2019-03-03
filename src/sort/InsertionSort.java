package sort;

//O*n^2
public class InsertionSort {
    public static void main(String[] args) {
        //sort();
		
		int inc = arr.length / 2;
        while (inc >= 1){
            for (int i = 0; i < inc; i++){
                shell(i, inc);
            }
            inc = inc / 2;
        }
		
        sout();
    }

    private static int[] arr = {108, 15, 50, 4, 8, 42, 23, 16, 71};

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

	private static void shell(int s, int inc) {
        for (int i = s; i < arr.length - 1; i = i + inc) {
            if (i + inc > arr.length - 1){
                break;
            } else {
                for (int j = i + inc; j - inc > 0; j = j - inc) {
                    if (arr[j - inc] > arr[i]){
                        int buf = arr[j - inc];
                        arr[j - inc] = arr[i];
                        arr[j] = buf;
                    }
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
