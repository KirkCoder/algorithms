package graph;

import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {
    public static void main(String[] args) {
//        int[][] graph = {
////              {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13},
//                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //0
//                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1
//                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //2
//                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, //3
//                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, //4
//                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}, //5
//                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, //6
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, //7
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, //8
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, //9
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, //10
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, //11
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //12
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //13
//        };

        int[][] graph = {
//              {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //0
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //2
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, //3
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, //4
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}, //5
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, //6
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, //7
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, //8
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, //9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, //10
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, //11
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //12
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //13
        };
        List<Node> list = toGraphList(graph);
        print(list);
        print(sort(list));
    }

    private static List<Node> sort(List<Node> list) {
        int[] withIncome = new int[list.size()];
        List<Node> res = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            withIncome[i] = list.get(i).getIncome().size();
        }
        findNext(list, withIncome, res);
        return res;
    }

    private static void findNext(List<Node> list, int[] withIncome, List<Node> res) {
        boolean isNotFinish = false;
        boolean isCyclic = true;
        for (int i = 0; i < list.size(); i++) {
            if (withIncome[i] == 0) {
                isCyclic = false;
                Node item = list.get(i);
                res.add(item);
                withIncome[i] = -1;
                for (int j = 0; j < item.getOut().size(); j++) {
                    withIncome[item.getOut().get(j).getValue()]--;
                }
            }
            if (withIncome[i] > 0) isNotFinish = true;
        }
        if (isNotFinish && !isCyclic) findNext(list, withIncome, res);
    }

    private static void print(List<Node> list){
        for (Node node : list) {
            System.out.print(node.getValue() + ", ");
        }
        System.out.println(" ");
    }

    private static List<Node> toGraphList(int[][] graph) {
        List<Node> list = new ArrayList<>(graph.length);
        for (int i = 0; i < graph.length; i++) {
            list.add(new Node(i));
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] == 1) {
                    list.get(i).getOut().add(list.get(j));
                    list.get(j).getIncome().add(list.get(i));
                }
            }
        }
        return list;
    }
}
