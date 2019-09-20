package solver;

import common.BaseSolver;
import common.Movable;
import model.Node;

import java.util.List;

public class BfsSolver<T extends Movable<T>> extends BaseSolver<T> {

    public BfsSolver(T initialState, T finalState) {
        super(initialState, finalState);
    }

    @Override
    protected void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves) {
        for (T move : possibleMoves) {
            if (isUnsolved(move)) {
                //TODO this is DFS, not BFS
                getNodeDeque().add(new Node<>(currentNode, move));
                incrementMemoryCounter();
            }
        }
    }
}
