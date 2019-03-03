package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchObject {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(-19, -6, 1, 2, 2, 3, 14, 78, 102, 255));
        System.out.println(binarySearch(list, 3));
    }

    public static <T extends Comparable<T>> int binarySearch(List<T> list, T item) {
        return binary(list, 0, list.size() - 1, item);
    }

    private static <T extends Comparable<T>> int binary(List<T> list, int start, int end, T item) {
        if (start > end) return -1;
        int position = (start + end) / 2;
        T selected = list.get(position);
        if (selected.equals(item)) return position;
        if (selected.compareTo(item) > 0) {
            return binary(list, 0, position - 1, item);
        } else {
            return binary(list, position + 1, end, item);
        }
    }
}
