package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0}
        };
        List<Node> list = Utils.toGraphList(graph);
        Queue<Node> stack = new LinkedList<>();
        visit(7, list, stack);
        Utils.printVisited(list);
    }

    private static void visit(int item, List<Node> list, Queue<Node> queue) {
        Node node = list.get(item);
        node.setVisited(true);
        for (int i = 0; i < node.getOut().size(); i++) {
            Node tmpNode = node.getOut().get(i);
            if (!tmpNode.isVisited()) {
                tmpNode.setVisited(true);
                queue.add(tmpNode);
            }
        }

        if (!queue.isEmpty()) {
            Node next = queue.remove();
            visit(next.getIndex(), list, queue);
        }
    }
}
