package utils.heuristics;

import common.Heuristical;
import model.Table;

public class ManhattanDistanceHeuristical implements Heuristical<Table> {
    @Override
    public int getWeight(Table state, Table finalState) {
        int distanceCounter = 0;
        int[][] stateCells = state.getCells();
        int[][] finalStateCells = finalState.getCells();
        for (int i = 0; i < stateCells.length; i++) {
            for (int j = 0; j < stateCells[i].length; j++) {
                for (int k = 0; k < stateCells.length; k++) {
                    for (int l = 0; l < stateCells[k].length; l++) {
                        if (stateCells[i][j] == finalStateCells[k][l]) {
                            distanceCounter += Math.abs(k - i) + Math.abs(l - j);
                        }
                    }
                }
            }
        }
        return distanceCounter;
    }
}
