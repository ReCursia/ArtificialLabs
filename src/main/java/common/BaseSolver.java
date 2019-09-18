package common;

import model.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public abstract class BaseSolver<T extends Movable<T>> {
    private final static int MEMORY_INCREMENT = 1;
    private final static int STEP_INCREMENT = 1;
    private List<T> disclosedStates;
    private int stepsCounter;
    private int memoryCounter;
    private Deque<Node<T>> nodeDeque;
    private T initialState;
    private T finalState;
    private MovesListener<T> possibleMovesListener;

    public BaseSolver(T initialState, T finalState) {
        this.initialState = initialState;
        this.finalState = finalState;
        disclosedStates = new ArrayList<T>();
        nodeDeque = new ArrayDeque<>();
    }

    public void setPossibleMovesListener(MovesListener<T> possibleMovesListener) {
        this.possibleMovesListener = possibleMovesListener;
    }

    protected Deque<Node<T>> getNodeDeque() {
        return nodeDeque;
    }

    protected T getInitialState() {
        return initialState;
    }

    protected T getFinalState() {
        return finalState;
    }

    protected abstract void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves);

    public Node<T> solve() {
        //Adding root
        nodeDeque.add(new Node<T>(null, initialState));
        incrementMemoryCounter();
        while (!nodeDeque.isEmpty()) {
            incrementStepsCounter();
            Node<T> currentNode = nodeDeque.pollFirst();
            //Check final state
            if (isEqualToFinalState(currentNode)) {
                return currentNode;
            }
            List<T> possibleMoves = currentNode.getData().getPossibleMoves();
            notifyPossibleMovesListener(possibleMoves);
            addUnsolvedNodes(currentNode, possibleMoves);
            disclosedStates.add(currentNode.getData());
        }
        return null;
    }

    protected void notifyPossibleMovesListener(List<T> possibleMoves) {
        if (possibleMovesListener != null) {
            possibleMovesListener.notify(possibleMoves);
        }
    }

    protected boolean isEqualToFinalState(Node<T> currentNode) {
        return currentNode.getData().equals(finalState);
    }

    protected boolean isInDisclosedStates(T t) {
        return disclosedStates.contains(t);
    }

    protected void incrementMemoryCounter() {
        memoryCounter += MEMORY_INCREMENT;
    }

    protected void incrementStepsCounter() {
        stepsCounter += STEP_INCREMENT;
    }

    public int getMemoryCounter() {
        return memoryCounter;
    }

    public int getStepsCounter() {
        return stepsCounter;
    }
}
