package tasks;

import java.util.Stack;

public class TowerOfHanoi {

    private static Stack<String> a = new Stack<>();
    private static Stack<String> b = new Stack<>();
    private static Stack<String> c = new Stack<>();

    public static void main(String[] args) {
        a.push("6");
        a.push("5");
        a.push("4");
        a.push("3");
        a.push("2");
        a.push("1");
        print();
        doHanoi(a.size(), a, b, c);
    }

    private static void doHanoi(int units, Stack<String> from, Stack<String> tmp, Stack<String> to) {
        if (units == 0) return;
        doHanoi(units - 1, from, to, tmp);
        int lust = from.size() - 1;
        to.push(from.lastElement());
        from.remove(lust);
        print();
        doHanoi(units - 1, tmp, from, to);
    }

    private static void print() {
        System.out.print("A: ");
        for (String s : a) {
            System.out.print(s + ", ");
        }
        System.out.println(" ");
        System.out.print("B: ");
        for (String s : b) {
            System.out.print(s + ", ");
        }
        System.out.println(" ");
        System.out.print("C: ");
        for (String s : c) {
            System.out.print(s + ", ");
        }
        System.out.println(" ");
        System.out.println("=============");
    }
}
