package solver;

import common.BaseSolver;
import common.Heuristical;
import common.Movable;
import model.Node;

import java.util.List;
import java.util.PriorityQueue;

public class HeuristicSolver<T extends Movable<T>> extends BaseSolver<T> {
    public HeuristicSolver(T initialState, T finalState, Heuristical<T> heuristic) {
        super(initialState, finalState, new PriorityQueue<>((first, second) -> {
            int firstTableWeight = heuristic.getWeight(first.getData(), finalState);
            int secondTableWeight = heuristic.getWeight(second.getData(), finalState);
            return Integer.compare(firstTableWeight, secondTableWeight);
        }));
    }

    @Override
    protected void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves) {
        for (T move : possibleMoves) {
            getNodeQueue().add(new Node<>(currentNode, move));
            incrementMemoryCounter();
        }
    }
}
