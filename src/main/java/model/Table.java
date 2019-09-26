package model;

import common.Movable;
import utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table implements Movable<Table> {
    private static final int EMPTY_CELL_VALUE = 0;
    private final int[][] cells;
    private final int size;

    public Table(int[][] values) {
        this.size = values.length;
        this.cells = values;
    }

    public int[][] getCells() {
        return cells;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Table)) {
            return false;
        }
        Table tableObj = (Table) obj;
        return Arrays.deepEquals(tableObj.cells, this.cells);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                stringBuilder.append(cells[i][j]).append(' ');
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    @Override
    public List<Table> getPossibleMoves() {
        List<Table> moves = new ArrayList<>();
        int row = getEmptyCellRowIndex();
        int column = getEmptyCellColumnIndex();
        //There is maximum
        int[][] newCells;
        int temp;
        //Try swap left cell
        try {
            temp = cells[row][column - 1];
            newCells = ArrayUtils.cloneArray(cells);
            newCells[row][column - 1] = newCells[row][column];
            newCells[row][column] = temp;
            moves.add(new Table(newCells));
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        //Try swap right
        try {
            temp = cells[row][column + 1];
            newCells = ArrayUtils.cloneArray(cells);
            newCells[row][column + 1] = newCells[row][column];
            newCells[row][column] = temp;
            moves.add(new Table(newCells));
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        //Try swap up
        try {
            temp = cells[row - 1][column];
            newCells = ArrayUtils.cloneArray(cells);
            newCells[row - 1][column] = newCells[row][column];
            newCells[row][column] = temp;
            moves.add(new Table(newCells));
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        //Try swap down
        try {
            temp = cells[row + 1][column];
            newCells = ArrayUtils.cloneArray(cells);
            newCells[row + 1][column] = newCells[row][column];
            newCells[row][column] = temp;
            moves.add(new Table(newCells));
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return moves;
    }

    private int getEmptyCellColumnIndex() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j] == EMPTY_CELL_VALUE) {
                    return j;
                }
            }
        }
        return 0;
    }

    private int getEmptyCellRowIndex() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j] == EMPTY_CELL_VALUE) {
                    return i;
                }
            }
        }
        return 0;
    }
}
