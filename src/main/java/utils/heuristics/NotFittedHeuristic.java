package utils.heuristics;

import common.Heuristical;
import model.Table;

public class NotFittedHeuristic implements Heuristical<Table> {
    @Override
    public int getWeight(Table state, Table finalState) {
        int counter = 0;
        int[][] stateCells = state.getCells();
        int[][] finalStateCells = finalState.getCells();

        for (int i = 0; i < stateCells.length; i++) {
            for (int j = 0; j < stateCells[i].length; j++) {
                if (stateCells[i][j] != finalStateCells[i][j]) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
