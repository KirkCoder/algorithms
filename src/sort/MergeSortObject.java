package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortObject {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(78, 2, 1, 14, 255, -6, 3, 2, -19, 102));
        System.out.println(list);
        System.out.println(sort(list));
    }

    private static <T extends Comparable<T>> List<T> sort(List<T> list) {
        if (list.size() == 1) return list;
        return merge(
                sort(list.subList(0, list.size() / 2)),
                sort(list.subList(list.size() / 2, list.size()))
        );
    }

    private static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
        List<T> res = new ArrayList<>(left.size() + right.size());
        int iLeft = 0;
        int iRight = 0;

        while (iLeft < left.size() && iRight < right.size()) {
            if (left.get(iLeft).compareTo(right.get(iRight)) > 0) {
                res.add(right.get(iRight++));
            } else {
                res.add(left.get(iLeft++));
            }
        }

        while (iLeft < left.size()) {
            res.add(left.get(iLeft++));
        }

        while (iRight < right.size()) {
            res.add(right.get(iRight++));
        }
        return res;
    }
}
