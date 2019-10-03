import common.BaseSolver;
import model.Node;
import model.Table;
import solver.AStarSolver;
import solver.HeuristicSolver;
import utils.OutputUtils;
import utils.heuristics.ManhattanDistanceHeuristic;
import utils.heuristics.NotFittedHeuristic;

class Main {
    public static void main(String[] args) {

        int[][] initialState =
                {{4, 8, 1},
                        {0, 3, 6},
                        {2, 7, 5}};
        int[][] finalState =
                {{1, 2, 3},
                        {8, 0, 4},
                        {7, 6, 5}};

        //BaseSolver<Table> solver = new BfsSolver<>(new Table(initialState), new Table(finalState));
        //BaseSolver<Table> solver = new BiDirectionalSolver<>(new Table(initialState), new Table(finalState));
        BaseSolver<Table> solver = new AStarSolver<>(new Table(initialState), new Table(finalState), new NotFittedHeuristic());
        //BaseSolver<Table> solver = new HeuristicSolver<>(new Table(initialState), new Table(finalState), new ManhattanDistanceHeuristic());
        Node<Table> result = solver.solve();

        //Print out result
        OutputUtils.outputNodes(result);
        //Print memory and steps
        System.out.println("/*-------*/");
        System.out.println("Memory: " + solver.getMemoryCounter());
        System.out.println("Steps: " + solver.getStepsCounter());
        System.out.println("Depth: "+result.getDepth());
        System.out.println("/*-------*/");
    }
}
