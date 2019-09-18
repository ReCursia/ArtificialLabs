package solver;

import common.BaseSolver;
import common.Movable;
import model.Node;

import java.util.List;

public class DfsSolver<T extends Movable<T>> extends BaseSolver<T> {

    public DfsSolver(T initialState, T finalState) {
        super(initialState, finalState);
    }

    @Override
    protected void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves) {
        for (T move : possibleMoves) {
            if (!isInDisclosedStates(move)) {
                getNodeDeque().addFirst(new Node<T>(currentNode, move));
                incrementMemoryCounter();
            }
        }
    }
}
