/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ClassicBoard {

    public static final int BOARD_SIZE = 9;
    public static final int BOX_SIZE = 3;
    public static final int CELLS_PER_BOX = BOX_SIZE * BOX_SIZE;
    public static final int BOXES_PER_SIDE = BOARD_SIZE / BOX_SIZE;
    public static final int BOXES = BOXES_PER_SIDE * BOXES_PER_SIDE;
    WritableCell[][] cells;
    // Memoization caches
    Map neighbors;
    Set errors;
    Set hints;
    Set readOnlyCells;

    public ClassicBoard(Cell[] initialHints) {
        cells = new WritableCell[BOARD_SIZE][BOARD_SIZE];

        // fill initial values
        readOnlyCells = new HashSet();
        for (int i = 0; i < initialHints.length; i++) {
            Cell hint = initialHints[i];

            WritableCell cellCopy = new WritableCell(hint);
            cells[hint.getRow()][hint.getColumn()] = cellCopy;
            readOnlyCells.add(cellCopy);
        }

        // creates the empty cells
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (cells[row][column] == null) {
                    cells[row][column] = new WritableCell(row, column, 0);
                }
            }
        }

        neighbors = new HashMap(); // cell X neighbor set
        errors = new HashSet();
        hints = new HashSet();
    }

    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public void setHint(Cell hint) throws ReadOnlyCellException {
        setDigit(getCell(hint.getRow(), hint.getColumn()), hint.getDigit());
    }

    public Set setDigit(Cell cell, int digit) throws ReadOnlyCellException {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("digit must be between 0-9");
        }

        if (readOnlyCells.contains(cell)) {
            throw new ReadOnlyCellException("cell is read only");
        }

        cell.setDigit(digit);

        // errors and hints should be refeshed
        errors = null;
        hints = null;

        return getErrors();
    }

    public Set getHints() {
        if (hints == null) {
            computeErrorsAndHints();
        }

        return hints;
    }

    public Set getErrors() {
        if (errors == null) {
            computeErrorsAndHints();
        }

        return errors;
    }

    public Cell[][] getBoxesCells() {
        Cell[][] ret = new Cell[BOXES][CELLS_PER_BOX];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                int boxRow = row / BOX_SIZE;
                int boxColumn = column / BOX_SIZE;
                int boxNumber = row * 3 + column;

                int boxStartRow = boxRow * BOX_SIZE;
                int boxStartColumn = boxColumn * BOX_SIZE;

                ret[boxNumber] = (Cell[]) getBoxNeighbors(boxStartRow, boxStartColumn).toArray(new Cell[CELLS_PER_BOX]);
            }
        }
        return ret;
    }

    private Set getBoxNeighbors(int boxStartRow, int boxStartColumn) {
        Set result = new HashSet();

        // neighbors at the same box
        for (int c = boxStartColumn; c < boxStartColumn + BOX_SIZE; c++) {
            for (int r = boxStartRow; r < boxStartRow + BOX_SIZE; r++) {
                result.add(getCell(r, c));
            }
        }

        return result;
    }

    private Set getNeighbors(Cell cell) {
        Set result = (Set) neighbors.get(cell);

        if (result == null) {
            result = new HashSet();
            int row = cell.getRow();
            int column = cell.getColumn();

            // neighbors at the same row
            for (int i = 0; i < BOARD_SIZE; i++) {
                result.add(getCell(row, i));
            }

            // neighbors at the same column
            for (int i = 0; i < BOARD_SIZE; i++) {
                result.add(getCell(i, column));
            }

            int boxStartRow = (row / BOX_SIZE) * BOX_SIZE;
            int boxStartColumn = (column / BOX_SIZE) * BOX_SIZE;

            result.addAll(getBoxNeighbors(boxStartRow, boxStartColumn));

            // removes the cell itself
            result.remove(getCell(row, column));

            neighbors.put(cell, result);
        }

        return result;
    }

    private void computeErrorsAndHints() {
        if (errors == null) { // || hints = null
            errors = new HashSet();
            hints = new HashSet();

            for (int c = 0; c < BOARD_SIZE; c++) {
                for (int r = 0; r < BOARD_SIZE; r++) {
                    Cell cell = getCell(r, c);

                    // cells on the same box/line/column can't contain the same digit
                    errors.addAll(getNeighborsWithSameDigit(cell));

                    Collection possibleDigits = getPossibleDigits(cell);

                    if (possibleDigits.size() < 1) {
                        // all cells must have at least one possible digit
                        errors.add(cell);
                    } else if (possibleDigits.size() == 1) {
                        WritableCell hint = new WritableCell(cell);
                        hint.setDigit(((Integer)possibleDigits.iterator().next()).intValue());
                        hints.add(hint);
                    }
                }
            }
        }
    }

    private Collection getNeighborsWithSameDigit(Cell cell) {
        Set nwsd = new HashSet();
        int digit = cell.getDigit();

        if (digit != 0) {
            for (Iterator i = getNeighbors(cell).iterator(); i.hasNext();) {
                Cell neighbor = (Cell) i.next();
                if (neighbor.getDigit() == digit) {
                    nwsd.add(neighbor);
                }
            }

            nwsd.add(cell);
        }

        return nwsd;
    }

    private Collection getPossibleDigits(Cell cell) {
        Collection digits = new ArrayList();
        for (int i = 1; i < 10; i++) {
            digits.add(new Integer(i));
        }

        for (Iterator i = getNeighbors(cell).iterator(); i.hasNext();) {
            Cell neighbor = (Cell) i.next();
            int digit = neighbor.getDigit();
            if (digit != 0) {
                digits.remove(new Integer(digit));
            }
        }

        return digits;
    }
}
