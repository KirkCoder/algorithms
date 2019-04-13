package sort;

//O*n^2
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        SortUtils.print(arr);
        SortUtils.print(sort(arr));
    }

    private static int[] sort(int[] arr){
        int end = arr.length - 1;
        for(int i = 0; i < end; i++){
            int j = 0;
            int k = end;
            boolean isChange = false;
            while(j < k){
                if(arr[j] > arr[j + 1]){
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    isChange = true;
                }
                if (arr[k] < arr[k - 1]) {
                    int tmp = arr[k - 1];
                    arr[k - 1] = arr[k];
                    arr[k] = tmp;
                    isChange = true;
                }
                j++;
                k--;
            }
            if(!isChange) return arr;
        }
        return arr;
    }
}
