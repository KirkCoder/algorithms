package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InsertionSortObjects {
    public static void main(String[] args){

        List<Integer> list = new ArrayList<>(Arrays.asList(78, 2, 1, 14, 255, -6, 3, 2, 2, -19, 102));
        print(list);
        print(sort(list));
    }

    private static <T extends Comparable<T>> List<T> sort(List<T> list){
        for(int i = 1; i < list.size(); i++){
            T element = list.get(i);
            for(int j = i - 1; j > -1; j--){
                if(list.get(j).compareTo(element) > 0){
                    Collections.swap(list, j + 1, j);
                } else {
                    break;
                }
            }
        }
        return list;
    }

    private static void print(List<Integer> list){
        for(Integer value : list){
            System.out.print(value.toString() + ", ");
        }
        System.out.println(" ");
    }
}
