package solver;

import common.BaseSolver;
import common.Movable;
import model.Node;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;

public class BfsSolver<T extends Movable<T>> extends BaseSolver<T> {

    public BfsSolver(T initialState, T finalState) {
        super(initialState, finalState, new ArrayDeque<>());
    }

    @Override
    protected void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves) {
        for (T move : possibleMoves) {
            getNodeQueue().addAll(Collections.singletonList(new Node<>(currentNode, move))); //add last
            incrementMemoryCounter();
        }
    }
}
