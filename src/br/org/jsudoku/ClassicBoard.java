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

    public static final int BOARD_DIMENSION = 9;
    public static final int BOX_DIMENSION = 3;
    Integer[][] cells;
    // Memoization caches
    Map neighbors;
    Set errors;
    Set hints;

    public ClassicBoard() {
        cells = new Integer[BOARD_DIMENSION][BOARD_DIMENSION];
        neighbors = new HashMap();
        errors = new HashSet();
        hints = new HashSet();
    }

    public Integer getDigit(Cell cell) {
        return getDigit(cell.getRow(), cell.getColumn());
    }

    public Integer getDigit(int row, int column) {
        return cells[row][column];
    }

    public void setDigit(Hint hint) {
        setDigit(hint.getCell(), hint.getDigit());
    }

    public void setDigit(Cell cell, Integer digit) {
        setDigit(cell.getRow(), cell.getColumn(), digit);
    }

    public void setDigit(int row, int column, Integer digit) {
        if (digit != null && (digit.intValue() < 1 || digit.intValue() > 9)) {
            throw new IllegalArgumentException("digit must be between 1-9 or null");
        }
        cells[row][column] = digit;

        // errors and hints should be refeshed
        errors = null;
        hints = null;
    }

    public Set setDigitAndGetErrors(Cell cell, Integer digit) {
        return setDigitAndGetErrors(cell.getRow(), cell.getColumn(), digit);
    }

    public Set setDigitAndGetErrors(int row, int column, Integer digit) {
        setDigit(row, column, digit);
        return getErrors();
    }

    public Set getNeighbors(Cell cell) {
        return getNeighbors(cell, true);
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

    private Set getNeighbors(Cell cell, boolean clone) {
        Set result = (Set) neighbors.get(cell);

        if (result == null) {
            result = new HashSet();
            int row = cell.getRow();
            int column = cell.getColumn();

            // neighbors at the same row
            for (int i = 0; i < BOARD_DIMENSION; i++) {
                result.add(new Cell(row, i));
            }

            // neighbors at the same column
            for (int i = 0; i < BOARD_DIMENSION; i++) {
                result.add(new Cell(i, column));
            }

            int boxRow = (row / BOX_DIMENSION) * BOX_DIMENSION;
            int boxColumn = (row / BOX_DIMENSION) * BOX_DIMENSION;

            // neighbors at the same box
            for (int c = boxColumn; c < boxColumn + BOX_DIMENSION; c++) {
                for (int r = boxRow; r < boxRow + BOX_DIMENSION; r++) {
                    result.add(new Cell(r, c));
                }
            }

            // removes the cell itself
            result.remove(new Cell(row, column));

            neighbors.put(cell, result);
        }

        if (clone) {
            result = new HashSet(result);
        }
        return result;
    }

    private void computeErrorsAndHints() {
        if (errors == null) { // || hints = null
            errors = new HashSet();
            hints = new HashSet();

            for (int c = 0; c < BOARD_DIMENSION; c++) {
                for (int r = 0; r < BOARD_DIMENSION; r++) {
                    Cell cell = new Cell(r, c);

                    // cells on the same box/line/column can't contain the same digit
                    errors.addAll(getNeighborsWithSameDigit(cell));

                    Collection possibleDigits = getPossibleDigits(cell);

                    if (possibleDigits.size() < 1) {
                        // all cells must have at least one possible digit
                        errors.add(cell);
                    } else if (possibleDigits.size() == 1) {
                        Hint hint = new Hint(cell, (Integer) possibleDigits.iterator().next());
                        hints.add(hint);
                    }
                }
            }
        }
    }

    private Collection getNeighborsWithSameDigit(Cell cell) {
        Set nwsd = new HashSet();
        Integer digit = getDigit(cell);

        if (digit != null) {
            for (Iterator i = getNeighbors(cell, false).iterator(); i.hasNext();) {
                Cell neighbor = (Cell) i.next();
                if (getDigit(neighbor).equals(digit)) {
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

        for (Iterator i = getNeighbors(cell, false).iterator(); i.hasNext();) {
            Cell neighbor = (Cell) i.next();
            Integer digit = getDigit(neighbor);
            if (digit != null) {
                digits.remove(digit);
            }
        }

        return digits;
    }
}
