package model;

public class Node<T> {
    private Node<T> parent;
    private T data;
    private int depth;

    public Node(Node<T> parent, T data) {
        this.depth = (parent != null) ? parent.depth+1 : 0;
        this.parent = parent;
        this.data = data;
    }

    public int getDepth() {
        return depth;
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }
}
