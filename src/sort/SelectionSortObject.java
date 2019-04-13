package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SelectionSortObject {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(78, 2, 1, 14, 255, -6, 3, 2, -19, 102));
        System.out.println(list);
        System.out.println(sort(list));
    }

    private static <T extends Comparable<T>> List<T> sort(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int minIndex = i;
            for (int j = i; j < list.size(); j++) {
                if (list.get(minIndex).compareTo(list.get(j)) > 0) {
                    minIndex = j;
                }
            }
            Collections.swap(list, i, minIndex);
        }
        return list;
    }
}
