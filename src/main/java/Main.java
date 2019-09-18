import common.BaseSolver;
import model.Node;
import model.Table;
import solver.BfsSolver;
import solver.HeuristicSolver;
import utils.heuristics.ManhattanDistanceHeuristic;
import utils.heuristics.NotFittedHeuristic;

public class Main {
    public static void main(String[] args) {

        int[][] initialState =
                {{4, 8, 1},
                        {0, 3, 6},
                        {2, 7, 5}};
        int[][] finalState =
                {{1, 2, 3},
                        {8, 0, 4},
                        {7, 6, 5}};

        BaseSolver<Table> solver = new BfsSolver<>(new Table(initialState), new Table(finalState));

        //BaseSolver<Table> solver = new HeuristicSolver<>(new Table(initialState), new Table(finalState), new NotFittedHeuristic());

        Node<Table> result = solver.solve();

        //Print out result
        if (result != null) {
            Node<Table> node = result;
            do {
                System.out.println(node.getData().toString());
            } while ((node = node.getParent()) != null);
        }
        //Print memory and steps
        System.out.println("/*-------*/");
        System.out.println("Memory: " + solver.getMemoryCounter());
        System.out.println("Steps: " + solver.getStepsCounter());
        System.out.println("/*-------*/");
    }
}
