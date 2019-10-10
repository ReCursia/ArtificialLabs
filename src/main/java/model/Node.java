package model;

public class Node<T> {
    private final Node<T> parent;
    private final T data;
    private final int depth;
    private final int cost;

    public Node(Node<T> parent, T data, int cost) {
        this.depth = (parent != null) ? parent.depth + 1 : 0;
        this.parent = parent;
        this.data = data;
        this.cost = cost;
    }

    public Node(Node<T> parent, T data) {
        this(parent, data, 0); //Sure?
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        return this.data.equals(((Node) obj).data);
    }

    @Override
    public String toString() {
        return data.toString();
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
