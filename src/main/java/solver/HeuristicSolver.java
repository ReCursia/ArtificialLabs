package solver;

import common.BaseSolver;
import common.Heuristical;
import common.Movable;
import model.Node;

import java.util.Collections;
import java.util.List;

public class HeuristicSolver<T extends Movable<T>> extends BaseSolver<T> {
    private Heuristical<T> heuristic;

    public HeuristicSolver(T initialState, T finalState, Heuristical<T> heuristic) {
        super(initialState, finalState);
        this.heuristic = heuristic;
    }

    @Override
    protected void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves) {
        sortWithHeuristic(possibleMoves);

        for (T move : possibleMoves) {
            if (isUnsolved(move)) {
                getNodeDeque().addFirst(new Node<T>(currentNode, move));
                incrementMemoryCounter();
            }
        }
    }

    protected void sortWithHeuristic(List<T> possibleMoves) {
        //Sort by decrease
        possibleMoves.sort((firstTable, secondTable) -> {
            int firstTableWeight = heuristic.getWeight(firstTable, getFinalState());
            int secondTableWeight = heuristic.getWeight(secondTable, getFinalState());
            return Integer.compare(secondTableWeight, firstTableWeight);
        });
    }
}
