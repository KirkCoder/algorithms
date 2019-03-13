package graph;

import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {
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
        Stack<Node> stack = new Stack<>();
        visit(7, list, stack);
        Utils.printVisited(list);
    }

    public static void visit(int index, List<Node> graph, Stack<Node> stack) {
        Node node = graph.get(index);
        node.setVisited(true);
        for(int i = 0; i < node.getOut().size(); i++){
            Node next = node.getOut().get(i);
            stack.push(next);
            visit(next.getIndex(), graph, stack);
        }
        if (!stack.isEmpty()){
            visit(stack.pop().getIndex(), graph, stack);
        }
    }
}
