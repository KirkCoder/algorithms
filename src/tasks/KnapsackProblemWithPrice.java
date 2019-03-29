package tasks;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblemWithPrice {

    public static void main(String[] args) {
        List<Stuff> list = new ArrayList<>();
        list.add(new Stuff(11, 1000));
        list.add(new Stuff(8, 1));
        list.add(new Stuff(7, 100));
        list.add(new Stuff(6, 10000));
        list.add(new Stuff(5, 400));

        Knapsack knapsack = new Knapsack(20);
        for (int i = 0; i < list.size(); i++) {
            List<Stuff> nList = new ArrayList<>(list);
            List<Stuff> sequence = new ArrayList<>(list.size());
            Stuff stuff = nList.remove(i);
            sequence.add(stuff);
            knapsack.tryPut(sequence);
            putStuff(knapsack, nList, sequence);
        }
        knapsack.showResult();
    }

    private static void putStuff(Knapsack knapsack, List<Stuff> list, List<Stuff> sequence) {
        if (list.isEmpty()) return;
        for (int i = 0; i < list.size(); i++) {
            List<Stuff> nList = new ArrayList<>(list);
            List<Stuff> nSequence = new ArrayList<>(sequence);
            Stuff stuff = nList.remove(i);
            nSequence.add(stuff);
            knapsack.tryPut(nSequence);
            putStuff(knapsack, nList, nSequence);
        }
    }

    static class Knapsack {
        private int maxWeight;
        private int currentWeight = 0;
        private int currentPrice = 0;
        private List<Stuff> current = new ArrayList<>();

        Knapsack(int maxWeight) {
            this.maxWeight = maxWeight;
        }

        void tryPut(List<Stuff> sequence) {
            int weight = getWeight(sequence);
            int price = getPrice(sequence);
            if (weight <= maxWeight && currentPrice < price) {
                current = sequence;
                currentWeight = weight;
                currentPrice = price;
            }
        }

        private int getWeight(List<Stuff> sequence) {
            int res = 0;
            for (Stuff stuff : sequence) {
                res = res + stuff.weight;
            }
            return res;
        }

        private int getPrice(List<Stuff> sequence) {
            int res = 0;
            for (Stuff stuff : sequence) {
                res = res + stuff.price;
            }
            return res;
        }

        void showResult() {
            for (Stuff stuff : current) {
                System.out.print(stuff + ", ");
            }
            System.out.println(" ");
            System.out.println("weight: " + currentWeight);
            System.out.println("price: " + currentPrice);
        }
    }

    static class Stuff {
        int weight;
        int price;

        Stuff(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
}
