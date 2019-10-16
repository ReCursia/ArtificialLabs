package solver;

import common.BaseSolver;
import common.Heuristical;
import common.Movable;
import model.Node;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSolver<T extends Movable<T>> extends BaseSolver<T> {
    private Heuristical<T> heuristic;
    private HashMap<T, Integer> costSoFar;
    private List<T> costSoFar;

    public AStarSolver(T initialState, T finalState, Heuristical<T> heuristic) {
        super(initialState, finalState, new PriorityQueue<>(Comparator.comparingInt(Node::getCost)));
        this.heuristic = heuristic;
        this.costSoFar = new HashMap<>();
    }

    @Override
    public Node<T> solve() throws IOException {
        costSoFar.put(getInitialState(),0);
        return super.solve();
    }

    @Override
    protected void addUnsolvedNodes(Node<T> currentNode, List<T> possibleMoves) {
        for (T move : possibleMoves) {
            int newCost = costSoFar.get(currentNode.getData()) + 1;
            if ((!costSoFar.containsKey(move)) || newCost < costSoFar.get(move)) {
                costSoFar.put(move, newCost);
                int cost = heuristic.getWeight(move, getFinalState()) + newCost;
                getNodeQueue().add(new Node<>(currentNode, move, cost));
            }
            incrementMemoryCounter();
        }
    }
}
