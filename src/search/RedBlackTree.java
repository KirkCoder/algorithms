package search;

public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node{
        int key;
        char value;
        Node left, right;
        int nodeNumbers;
        boolean color;

        public Node(int key, char value, int nodeNumbers, boolean color) {
            this.key = key;
            this.value = value;
            this.nodeNumbers = nodeNumbers;
            this.color = color;
        }
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.nodeNumbers;
        }
    }

    private boolean isRed(Node x){
        if (x == null) return false;
        return x.color == RED;
    }

    Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.nodeNumbers = h.nodeNumbers;
        h.nodeNumbers = 1 + size(h.left) + size(h.right);
        return x;
    }

    Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.nodeNumbers = h.nodeNumbers;
        h.nodeNumbers = 1 + size(h.left) + size(h.right);
        return x;
    }

    void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    void put(int key, char val){
        root = put(root, key, val);
    }

    private Node put(Node h, int key, char val) {
        if (h == null){
            return new Node(key, val, 1, RED);
        }
        if (key < h.key){
            h.left = put(h.left, key, val);
        } else if (key > h.key){
            h.right = put(h.right, key, val);
        } else {
            h.value = val;
        }

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.nodeNumbers = size(h.left) + size(h.right) + 1;
        return h;
    }

//    public void deleteMin() {
//
//        // if both children of root are black, set root to red
//        if (!isRed(root.left) && !isRed(root.right))
//            root.color = RED;
//
//        root = deleteMin(root);
//        // assert check();
//    }
//
//    // delete the key-value pair with the minimum key rooted at h
//    private Node deleteMin(Node h) {
//        if (h.left == null)
//            return null;
//
//        if (!isRed(h.left) && !isRed(h.left.left))
//            h = moveRedLeft(h);
//
//        h.left = deleteMin(h.left);
//        return balance(h);
//    }
//
//    public void deleteMax() {
//        if (isEmpty()) throw new NoSuchElementException("BST underflow");
//
//        // if both children of root are black, set root to red
//        if (!isRed(root.left) && !isRed(root.right))
//            root.color = RED;
//
//        root = deleteMax(root);
//        if (!isEmpty()) root.color = BLACK;
//        // assert check();
//    }
//
//    // delete the key-value pair with the maximum key rooted at h
//    private Node deleteMax(Node h) {
//        if (isRed(h.left))
//            h = rotateRight(h);
//
//        if (h.right == null)
//            return null;
//
//        if (!isRed(h.right) && !isRed(h.right.left))
//            h = moveRedRight(h);
//
//        h.right = deleteMax(h.right);
//
//        return balance(h);
//    }
//
//    public void delete(Key key) {
//        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
//        if (!contains(key)) return;
//
//        // if both children of root are black, set root to red
//        if (!isRed(root.left) && !isRed(root.right))
//            root.color = RED;
//
//        root = delete(root, key);
//        if (!isEmpty()) root.color = BLACK;
//        // assert check();
//    }
//
//    // delete the key-value pair with the given key rooted at h
//    private Node delete(Node h, Key key) {
//        // assert get(h, key) != null;
//
//        if (key.compareTo(h.key) < 0)  {
//            if (!isRed(h.left) && !isRed(h.left.left))
//                h = moveRedLeft(h);
//            h.left = delete(h.left, key);
//        }
//        else {
//            if (isRed(h.left))
//                h = rotateRight(h);
//            if (key.compareTo(h.key) == 0 && (h.right == null))
//                return null;
//            if (!isRed(h.right) && !isRed(h.right.left))
//                h = moveRedRight(h);
//            if (key.compareTo(h.key) == 0) {
//                Node x = min(h.right);
//                h.key = x.key;
//                h.val = x.val;
//                // h.val = get(h.right, min(h.right).key);
//                // h.key = min(h.right).key;
//                h.right = deleteMin(h.right);
//            }
//            else h.right = delete(h.right, key);
//        }
//        return balance(h);
//    }
//
//    private Node balance(Node h) {
//        // assert (h != null);
//
//        if (isRed(h.right))                      h = rotateLeft(h);
//        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
//        if (isRed(h.left) && isRed(h.right))     flipColors(h);
//
//        h.size = size(h.left) + size(h.right) + 1;
//        return h;
//    }
//
//    private Node moveRedRight(Node h) {
//        // assert (h != null);
//        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
//        flipColors(h);
//        if (isRed(h.left.left)) {
//            h = rotateRight(h);
//            flipColors(h);
//        }
//        return h;
//    }
//
//    private Node moveRedLeft(Node h) {
//        // assert (h != null);
//        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);
//
//        flipColors(h);
//        if (isRed(h.right.left)) {
//            h.right = rotateRight(h.right);
//            h = rotateLeft(h);
//            flipColors(h);
//        }
//        return h;
//    }
}






































