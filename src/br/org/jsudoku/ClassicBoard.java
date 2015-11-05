/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClassicBoard {

    public static final int BOARD_DIMENSION = 9;
    public static final int BOX_DIMENSION = 3;
    Integer[][] cells;
    // Memoization caches
    Map<Cell, Set<Cell>> neighbors;
    Set<Cell> errors;
    Set<Hint> hints;

    public ClassicBoard() {
        cells = new Integer[BOARD_DIMENSION][BOARD_DIMENSION];
        neighbors = new HashMap<>();
        errors = new HashSet<>();
        hints = new HashSet<>();
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
        if (digit != null && (digit < 1 || digit > 9)) {
            throw new IllegalArgumentException("digit must be between 1-9 or null");
        }
        cells[row][column] = digit;

        // errors and hints should be refeshed
        errors = null;
        hints = null;
    }

    public Set<Cell> setDigitAndGetErrors(Cell cell, Integer digit) {
        return setDigitAndGetErrors(cell.getRow(), cell.getColumn(), digit);
    }

    public Set<Cell> setDigitAndGetErrors(int row, int column, Integer digit) {
        setDigit(row, column, digit);
        return getErrors();
    }

    public Set<Cell> getNeighbors(Cell cell) {
        return getNeighbors(cell, true);
    }

    public Set<Hint> getHints() {
        if (hints == null) {
            computeErrorsAndHints();
        }

        return hints;
    }

    public Set<Cell> getErrors() {
        if (errors == null) {
            computeErrorsAndHints();
        }

        return errors;
    }

    private Set<Cell> getNeighbors(Cell cell, boolean clone) {
        Set<Cell> result = neighbors.get(cell);

        if (result == null) {
            result = new HashSet<>();
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
            result = new HashSet<>(result);
        }
        return result;
    }

    private void computeErrorsAndHints() {
        if (errors == null) { // || hints = null
            errors = new HashSet<>();
            hints = new HashSet<>();

            for (int c = 0; c < BOARD_DIMENSION; c++) {
                for (int r = 0; r < BOARD_DIMENSION; r++) {
                    Cell cell = new Cell(r, c);

                    // cells on the same box/line/column can't contain the same digit
                    errors.addAll(getNeighborsWithSameDigit(cell));

                    Collection<Integer> possibleDigits = getPossibleDigits(cell);

                    if (possibleDigits.size() < 1) {
                        // all cells must have at least one possible digit
                        errors.add(cell);
                    } else if (possibleDigits.size() == 1) {
                        hints.add(new Hint(cell, possibleDigits.iterator().next()));
                    }
                }
            }
        }
    }

    private Collection<Cell> getNeighborsWithSameDigit(Cell cell) {
        Set<Cell> nwsd = new HashSet<>();
        Integer digit = getDigit(cell);

        if (digit != null) {
            for (Cell neighbor : getNeighbors(cell, false)) {
                if (getDigit(neighbor).equals(digit)) {
                    nwsd.add(neighbor);
                }
            }

            nwsd.add(cell);
        }

        return nwsd;
    }

    private Collection<Integer> getPossibleDigits(Cell cell) {
        Collection<Integer> digits = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            digits.add(Integer.valueOf(i));
        }

        for (Cell neighbor : getNeighbors(cell, false)) {
            Integer digit = getDigit(neighbor);
            if (digit != null) {
                digits.remove(digit);
            }
        }

        return digits;
    }
}
