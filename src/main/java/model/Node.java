package model;

public class Node<T> {
    private final Node<T> parent;
    private final T data;
    private final int depth;

    public Node(Node<T> parent, T data) {
        this.depth = (parent != null) ? parent.depth + 1 : 0;
        this.parent = parent;
        this.data = data;
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
