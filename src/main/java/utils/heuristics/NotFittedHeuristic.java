package utils.heuristics;

import common.Heuristical;
import model.Table;

public class NotFittedHeuristic implements Heuristical<Table> {
    @Override
    public int getWeight(Table state, Table finalState) {
        int counter = 0;
        int[][] stateCells = state.getCells();
        int[][] finalStateCells = finalState.getCells();
        int size = state.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (stateCells[i][j] == 0) {
                    //nothing
                } else if (stateCells[i][j] != finalStateCells[i][j]) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
