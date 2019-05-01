package tasks;

public class Linked<T> {

    public static void main(String[] args) {
        Linked<Integer> list = new Linked<>();
        for (int i = 0; i < 10; i++) {
            list.put(new Node<Integer>(i));
        }
        list.print();
        list.reverse();
        list.print();
    }

    private Node<T> start;
    private Node<T> end;

    public void put(Node<T> node) {
        if (start == null) {
            start = node;
            end = node;
        } else {
            node.prev = end;
            end.next = node;
            end = node;
        }
    }

    public Node<T> get(int position) {
        Node<T> tmpNode = start;
        for (int i = 0; i < position; i++) {
            tmpNode = tmpNode.next;
            if (tmpNode == null) return null;
        }
        return tmpNode.next;
    }

    public void reverse() {
        Node<T> current = start;
        Node<T> prev = null;
        while (current != null) {
            Node<T> tmp = current.next;
            current.next = prev;
            current.prev = tmp;
            prev = current;
            current = tmp;
        }
        start = prev;
    }

    public void print() {
        Node<T> next = start;
        while (next != null) {
            System.out.print(next.getValue().toString() + ", ");
            next = next.next;
        }
        System.out.println(" ");
    }

    static class Node<T> {

        Node<T> next;
        Node<T> prev;

        private T value;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }
}


