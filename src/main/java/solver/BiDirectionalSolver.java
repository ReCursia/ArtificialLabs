package solver;

import common.BaseSolver;
import common.Movable;
import model.Node;

import java.util.*;
import java.util.stream.Collectors;

//TODO REWORK ALL THAT SHIT
public class BiDirectionalSolver<T extends Movable<T>> extends BaseSolver<T> {
    private Deque<Node<T>> initialToFinalDeque;
    private Deque<Node<T>> finalToInitialDeque;
    private List<T> initialToFinalDisclosedStates;
    private List<T> finalToInitialDisclosedStates;
    private boolean initialShouldMove;

    public BiDirectionalSolver(T initialState, T finalState) {
        super(initialState, finalState, null);
        initInitialSolver();
        initFinalSolver();
    }

    private boolean isUnsolved(T t, List<T> disclosedStates) {
        return !disclosedStates.contains(t);
    }

    private void initFinalSolver() {
        finalToInitialDeque = new ArrayDeque<>();
        finalToInitialDisclosedStates = new ArrayList<>();
        finalToInitialDeque.add(new Node<>(null, getFinalState()));
        incrementMemoryCounter();
    }

    private void initInitialSolver() {
        initialToFinalDeque = new ArrayDeque<>();
        initialToFinalDisclosedStates = new ArrayList<>();
        initialToFinalDeque.add(new Node<>(null, getInitialState()));
        incrementMemoryCounter();
    }

    @Override
    public Node<T> solve() {
        List<Node<T>> result;
        while ((((result = getMatchNotes()) == null))) {
            if (isInitialMovable()) {
                makeMove(initialToFinalDeque, initialToFinalDisclosedStates);
            } else if (isFinalMovable()) {
                makeMove(finalToInitialDeque, finalToInitialDisclosedStates);
            } else {
                break;
            }
            incrementStepsCounter();
            initialShouldMove = !initialShouldMove;
        }
        return getFullPath(result);
    }

    private Node<T> getFullPath(List<Node<T>> result) {
        List<T> firstArray = new ArrayList<>();
        Node<T> currentNode = result.get(0);
        while ((currentNode = currentNode.getParent()) != null) {
            firstArray.add(currentNode.getData());
        }
        List<T> secondArray = new ArrayList<>();
        currentNode = result.get(1);
        while ((currentNode = currentNode.getParent()) != null) {
            secondArray.add(currentNode.getData());
        }
        Collections.reverse(secondArray);
        //Merging
        Node<T> finalResult = null;
        firstArray.addAll(secondArray);
        for (T t : firstArray) {
            finalResult = new Node<T>(finalResult.getParent(), t);
        }
        return finalResult;
    }

    private boolean isFinalMovable() {
        return !finalToInitialDeque.isEmpty();
    }

    private boolean isInitialMovable() {
        return initialShouldMove && (!initialToFinalDeque.isEmpty());
    }

    private void makeMove(Deque<Node<T>> currentDeque, List<T> disclosedStates) {
        Node<T> currentNode = currentDeque.poll();

        //Stream
        List<T> possibleMoves = currentNode.getData().getPossibleMoves().stream()
                .filter(t -> isUnsolved(t, disclosedStates))
                .collect(Collectors.toList());
        for (T move : possibleMoves) {
            currentDeque.addAll(Collections.singletonList(new Node<>(currentNode, move))); //add last
            incrementMemoryCounter();
        }
        disclosedStates.add(currentNode.getData());
    }

    private List<Node<T>> getMatchNotes() {
        List<Node<T>> result;
        for (Node<T> firstNode : initialToFinalDeque) {
            for (Node<T> secondNode : finalToInitialDeque) {
                if (firstNode.equals(secondNode)) {
                    result = new ArrayList<>();
                    result.add(firstNode);
                    result.add(secondNode);
                    return result;
                }
            }
        }
        return null;
    }

    @Override
    protected void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves) {
        //unused, it's not okay by the way
    }
}
