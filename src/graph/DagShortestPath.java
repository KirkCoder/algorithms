package graph;

import java.util.List;

public class DagShortestPath {

    public static void main(String[] args) {
        int[][] arr = {
                {0, 5, 3, 0, 0, 0},
                {0, 0, 2, 6, 0, 0},
                {0, 0, 0, 7, 4, 2},
                {0, 0, 0, 0, -1, 1},
                {0, 0, 0, 0, 0, -2},
                {0, 0, 0, 0, 0, 0}
        };


        List<Node> list = Utils.toGraphList(arr);
        Utils.print(list);
        find(list, 1);
    }

    private static void find(List<Node> list, int from) {
        int[] shortest = new int[list.size()];
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < shortest.length; i++) {
            shortest[i] = max;
        }
        shortest[from] = 0;
        relaxing(list, from, shortest, list.get(from).getOut());
        System.out.println(shortest[5]);
    }

    private static void relax(int from, int to, int[] shortest, List<Node> list) {
        int i = 78;
        int weight = shortest[from] + list.get(from).getOutWeight().get(to);
        if (shortest[to] > weight){
            shortest[to] = weight;
        }
        relaxing(list, to, shortest, list.get(to).getOut());
    }

    private static void relaxing(List<Node> list, int from, int[] shortest, List<Node> outs) {
        for (Node out : outs) {
            relax(from, out.getIndex(), shortest, list);
        }
    }
}
