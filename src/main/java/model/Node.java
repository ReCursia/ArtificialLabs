package model;

public class Node<T> {
    private Node parent;
    private T data;
    private int cost;

    public Node(Node parent, T data) {
        this.parent = parent;
        this.data = data;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }
}
