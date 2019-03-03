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
            T min = list.get(i);
            int position = i;
            for (int j = i; j < list.size(); j++) {
                if (min.compareTo(list.get(j)) > 0) {
                    min = list.get(j);
                    position = j;
                }
            }
            Collections.swap(list, i, position);
        }
        return list;
    }
}
