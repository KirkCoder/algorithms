import java.util.ArrayList;
import java.util.List;

public class LeesAlgorithm {
    public static final int NO_ZIP = 0;
    public static final int ONE_STEP = 1;
    public static final int NO_STEPS = 0;
    int[][] graph = {
            {0, 1, 0, 0, 0},
            {0, 0, 2, 0, 4},
            {0, 0, 0, 3, 0},
            {0, 1, 2, 0, 4},
            {0, 0, 0, 3, 0}};

    List<Node> nodes = new ArrayList<>();


    public static void main(String[] args) {
        LeesAlgorithm lA = new LeesAlgorithm();
        Node n = lA.getPath(0, 3);
        System.out.println("weight " + n.weight);
        System.out.println("Path");
        for (Integer p : n.path) {
            System.out.println(p);
        }
    }

    private Node getPath(int start, int end) {
        for (int i = 0; i < graph.length; i++) {
            nodes.add(new Node(i));
        }
        nodes.get(start).weight = ONE_STEP;

        wafe(start);

        return nodes.get(end);
    }

    private void wafe(int start) {
        ArrayList<Integer> next = new ArrayList<>();
        for (int i = 0; i < graph[start].length; i++) {
            int n = graph[start][i];
            if (n != NO_ZIP && nodes.get(n).weight == NO_STEPS){
                nodes.get(n).weight = nodes.get(start).weight + ONE_STEP;

                for (Integer nod : nodes.get(start).path) {
                    nodes.get(n).path.add(nod);
                }
                nodes.get(n).path.add(start);
                next.add(i);
            }
        }
        for (Integer n : next) {
            wafe(n);
        }
    }
}

class Node {
    int numb;
    List<Integer> path = new ArrayList<>();
    int weight = LeesAlgorithm.NO_STEPS;

    public Node(int numb) {
        this.numb = numb;
    }
}