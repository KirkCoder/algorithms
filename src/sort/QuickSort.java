package sort;

import javax.swing.tree.TreeNode;

// O(n^2) or O(n*Log*n)
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        SortUtils.print(arr);
        SortUtils.print(sort(arr));
    }

    private static int[] sort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void quickSort(int[] arr, int from, int to){
        if(from >= to) return;
        int index = (from + to) / 2;
        int element = arr[index];
        arr[index] = arr[to];
        arr[to] = element;
        int separator = from - 1;
        for(int i = from; i < to; i++){
            if(arr[i] < element){
                separator++;
                int tmp = arr[separator];
                arr[separator] = arr[i];
                arr[i] = tmp;
            }
        }
        separator++;
        int tmp = arr[separator];
        arr[separator] = element;
        arr[to] = tmp;
        quickSort(arr, from, separator - 1);
        quickSort(arr, separator + 1, to);
    }
}
