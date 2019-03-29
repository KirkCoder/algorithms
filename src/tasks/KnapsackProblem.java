package tasks;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(8);
        list.add(7);
        list.add(6);
        list.add(5);

        Knapsack knapsack = new Knapsack(20);
        for (int i = 0; i < list.size(); i++) {
            List<Integer> nList = new ArrayList<>(list);
            List<Integer> sequence = new ArrayList<>(list.size());
            int stuff = nList.remove(i);
            sequence.add(stuff);
            knapsack.tryPut(sequence);
            putStuff(knapsack, nList, sequence);
        }
        knapsack.showResult();
    }

    private static void putStuff(Knapsack knapsack, List<Integer> list, List<Integer> sequence) {
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++) {
            List<Integer> nList = new ArrayList<>(list);
            List<Integer> nSequence = new ArrayList<>(sequence);
            int stuff = nList.remove(i);
            nSequence.add(stuff);
            knapsack.tryPut(nSequence);
            putStuff(knapsack, nList, nSequence);
        }
    }
}

class Knapsack {
    private int maxWeight;
    private int currentWeight = 0;
    private List<Integer> current = new ArrayList<>();

    Knapsack(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    void tryPut(List<Integer> sequence) {
        int weight = getWeight(sequence);
        if (weight <= maxWeight && weight > currentWeight) {
            current = sequence;
            currentWeight = weight;
        }
    }

    private int getWeight(List<Integer> sequence) {
        int res = 0;
        for (Integer integer : sequence) {
            res = res + integer;
        }
        return res;
    }

    void showResult() {
        for (Integer integer : current) {
            System.out.print(integer + ", ");
        }
        System.out.println(" ");
        System.out.println(currentWeight);
    }
}
