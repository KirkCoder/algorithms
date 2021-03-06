package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {

    private int index;
    private boolean visited = false;

    private List<Node> income = new ArrayList<>();
    private List<Node> out = new ArrayList<>();
    private Map<Integer, Integer> outWeight = new HashMap<>();

    public Node(int index) {
        this.index = index;
    }

    public List<Node> getIncome() {
        return income;
    }

    public List<Node> getOut() {
        return out;
    }

    public Map<Integer, Integer> getOutWeight(){
        return outWeight;
    }

    public int getIndex() {
        return index;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
