package search;

// 2*LgN
public class BinaryTree {

    public static final char NO_VALUE = 'N';
    private Node root;

    private class Node {
        private int key;
        private char value;
        private Node left, right;
        private int NodeCount;

        public Node(int key, char value, int nodeCount) {
            this.key = key;
            this.NodeCount = nodeCount;
            this.value = value;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.NodeCount;
        }
    }

    public char get(int key){
        return get(root, key);
    }

    private char get(Node x, int key) {
        if (x == null) {
            return NO_VALUE;
        }
        if (key < x.key){
            return get(x.left, key);
        } else if (key > x.key){
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    public void put(int key, char v){
        root = put(root, key, v);
    }

    private Node put(Node x, int key, char v){
        if (x == null){
            return new Node(key, v, 1);
        }
        if (key < x.key){
            return x.left = put(x.left, key, v);
        } else if (key > x.key){
            return x.right = put(x.right, key, v);
        }
        x.NodeCount = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int min(){
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left == null) return x;
        return min(x.left);
    }


    public int max(){
        return max(root).key;
    }

    private Node max(Node x){
        if (x.right == null) return x;
        return min(x.right);
    }
}


















