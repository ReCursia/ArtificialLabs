package utils.heuristics;

import common.Heuristical;
import model.Table;

public class ManhattanDistanceHeuristic implements Heuristical<Table> {
    @Override
    public int getWeight(Table state, Table finalState) {
        int distanceCounter = 0;
        int[][] stateCells = state.getCells();
        int[][] finalStateCells = finalState.getCells();
        int size = state.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    for (int l = 0; l < size; l++) {
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
