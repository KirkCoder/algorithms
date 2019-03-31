package tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinatorics {
    public static void main(String[] args) {
        char[] chars = {'A', 'B', 'C', 'D', 'E'};
        List<String> result = new ArrayList<>();
        find(chars,3, result, new ArrayList<>());
        print(result);
    }

    private static void find(char[] chars, int count, List<String> result, List<Character> stack) {
        if (chars.length < 1 || count < 1) return;
        if (count == 1) {
            StringBuilder tmp = new StringBuilder();
            for (Character s : stack) {
                tmp.append(s);
            }
            tmp.append(chars[0]);
            result.add(tmp.toString());
        }
            char[] next = Arrays.copyOfRange(chars, 1, chars.length);
            List<Character> tmpStack = new ArrayList<>(stack);
            stack.add(chars[0]);
            find(next, count - 1, result, stack);
            find(next, count, result, tmpStack);
    }

    private static void print(List<String> list){
        for (String s : list) {
            System.out.print(s + ", ");
        }
        System.out.println(" ");
    }
}
