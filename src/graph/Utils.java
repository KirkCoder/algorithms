package graph;

import java.util.ArrayList;
import java.util.List;

class Utils {
    static List<Node> toGraphList(int[][] graph) {
        List<Node> list = new ArrayList<>(graph.length);
        for (int i = 0; i < graph.length; i++) {
            list.add(new Node(i));
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] != 0) {
                    list.get(i).getOut().add(list.get(j));
                    list.get(i).getOutWeight().put(list.get(j).getIndex(), graph[i][j]);
                    list.get(j).getIncome().add(list.get(i));
                }
            }
        }
        return list;
    }

    static void print(List<Node> list) {
        for (Node node : list) {
            System.out.print(node.getIndex() + ", ");
        }
        System.out.println(" ");
    }

    static void print(int[][] arr) {
        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" ");
    }

    static void printVisited(List<Node> list){
        for (Node node : list) {
            if (node.isVisited()) System.out.print("visited " + node.getIndex() + ", ");
        }
        System.out.println(" ");
    }
}
