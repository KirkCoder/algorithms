package test;

import java.util.ArrayList;
import java.util.List;

public class ExampleRadixSort {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int[] num = {170, 45, 75, 90, 802, 287645, 24, 66,23,234,3,232,44, 54, 354, 54, 54};

        radixsort(num);

        for (int h : num)
            System.out.print(h + " ");
    }

    public static void radixsort(int[] input) {

        List<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        // sort
        boolean flag = false;
        int tmp = -1, divisor = 1;
        while (!flag) {
            flag = true;
            // split input between lists
            for (Integer i : input) {
                tmp = i / divisor;
                buckets[tmp % 10].add(i);
                if (flag && tmp > 0) {
                    flag = false;
                }
            }
            // empty lists into input array
            int a = 0;
            for (int b = 0; b < 10; b++) {
                for (Integer i : buckets[b]) {
                    input[a++] = i;
                }
                buckets[b].clear();
            }
            // move to next digit
            divisor *= 10;
        }
    }
}
