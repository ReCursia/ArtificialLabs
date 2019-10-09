package solver;

import common.BaseSolver;
import common.Heuristical;
import common.Movable;
import model.Node;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HeuristicSolver<T extends Movable<T>> extends BaseSolver<T> {
    private Heuristical<T> heuristic;

    public HeuristicSolver(T initialState, T finalState, Heuristical<T> heuristic) {
        super(initialState, finalState, new PriorityQueue<>(Comparator.comparingInt(Node::getCost)));
        this.heuristic = heuristic;
    }

    @Override
    protected void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves) {
        for (T move : possibleMoves) {
            int cost = heuristic.getWeight(move, getFinalState());
            getNodeQueue().add(new Node<>(currentNode, move, cost));
            incrementMemoryCounter();
        }
    }
}
