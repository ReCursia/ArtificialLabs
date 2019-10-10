package common;

import model.Node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public abstract class BaseSolver<T extends Movable<T>> {
    private final static int MEMORY_INCREMENT = 1;
    private final static int STEP_INCREMENT = 1;
    private final List<T> disclosedStates;
    private final Queue<Node<T>> nodeQueue;
    private final T initialState;
    private final T finalState;
    private int stepsCounter;
    private int memoryCounter;

    protected BaseSolver(T initialState, T finalState, Queue<Node<T>> nodeQueue) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.nodeQueue = nodeQueue;
        disclosedStates = new ArrayList<>();
    }

    protected T getInitialState() {
        return initialState;
    }

    protected T getFinalState() {
        return finalState;
    }

    protected Queue<Node<T>> getNodeQueue() {
        return nodeQueue;
    }

    protected abstract void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves);

    public Node<T> solve() throws IOException {
        //Initiation
        FileWriter fstream = new FileWriter("D:/log.txt", true); //true tells to append data.
        BufferedWriter out = new BufferedWriter(fstream);
        //Adding root
        nodeQueue.add(new Node<>(null, initialState));
        incrementMemoryCounter();
        while (!nodeQueue.isEmpty()) {
            Node<T> currentNode = nodeQueue.poll();
            incrementStepsCounter();
            //Check final state
            if (isEqualToFinalState(currentNode)) {
                return currentNode;
            }
            //Stream
            List<T> possibleMoves = currentNode.getData().getPossibleMoves().stream()
                    .filter(this::isUnsolved)
                    .collect(Collectors.toList());
            addUnsolvedNodes(currentNode, possibleMoves);
            disclosedStates.add(currentNode.getData());
        }
        return null;
    }

    private boolean isEqualToFinalState(Node<T> currentNode) {
        return currentNode.getData().equals(finalState);
    }

    private boolean isUnsolved(T t) {
        return !disclosedStates.contains(t);
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
