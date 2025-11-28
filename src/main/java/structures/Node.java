package structures;

public class Node<T> {
     T value;
     Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return this.value;
    }

    public Node<T> getNext() {
        return next;
    }

}
