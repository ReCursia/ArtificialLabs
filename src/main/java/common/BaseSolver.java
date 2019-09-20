package common;

import model.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public abstract class BaseSolver<T extends Movable<T>> {
    private final static int MEMORY_INCREMENT = 1;
    private final static int STEP_INCREMENT = 1;
    private List<T> disclosedStates;
    private int stepsCounter;
    private int memoryCounter;
    private Queue<Node<T>> nodeDeque;
    private T initialState;
    private T finalState;

    protected BaseSolver(T initialState, T finalState) {
        this(initialState, finalState, new ArrayDeque<>());
    }

    protected BaseSolver(T initialState, T finalState, Queue<Node<T>> nodeDeque) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.nodeDeque = nodeDeque;
        disclosedStates = new ArrayList<>();
    }

    protected Queue<Node<T>> getNodeDeque() {
        return nodeDeque;
    }

    protected abstract void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves);

    public Node<T> solve() {
        //Adding root
        nodeDeque.add(new Node<>(null, initialState));
        incrementMemoryCounter();
        while (!nodeDeque.isEmpty()) {
            Node<T> currentNode = nodeDeque.poll();
            incrementStepsCounter();
            //Check final state
            if (isEqualToFinalState(currentNode)) {
                return currentNode;
            }

            List<T> possibleMoves = currentNode.getData().getPossibleMoves();
            addUnsolvedNodes(currentNode, possibleMoves);
            disclosedStates.add(currentNode.getData());
        }
        return null;
    }

    private boolean isEqualToFinalState(Node<T> currentNode) {
        return currentNode.getData().equals(finalState);
    }

    protected boolean isUnsolved(T t) {
        return !disclosedStates.contains(t);
    }

    protected void incrementMemoryCounter() {
        memoryCounter += MEMORY_INCREMENT;
    }

    private void incrementStepsCounter() {
        stepsCounter += STEP_INCREMENT;
    }

    public int getMemoryCounter() {
        return memoryCounter;
    }

    public int getStepsCounter() {
        return stepsCounter;
    }
}
