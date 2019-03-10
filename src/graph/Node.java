package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int value;

    private List<Node> income = new ArrayList<>();
    private List<Node> out = new ArrayList<>();

    public Node(int value) {
        this.value = value;
    }

    public List<Node> getIncome() {
        return income;
    }

    public List<Node> getOut() {
        return out;
    }

    public int getValue() {
        return value;
    }
}
