package utils.heuristics;

import common.Heuristical;
import model.Table;

public class ManhattanDistanceHeuristic implements Heuristical<Table> {
    @Override
    public int getWeight(Table state, Table finalState) {
        int distanceCounter = 0;
        int[][] stateCells = state.getCells();
        int[][] finalStateCells = finalState.getCells();
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
