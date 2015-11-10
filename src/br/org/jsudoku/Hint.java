/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

public class Hint {

    Cell cell;
    Integer digit;

    public Hint(int row, int column, int digit){
        this(new Cell(row - 1, column - 1), new Integer(digit));
    }

    public Hint(Cell cell, Integer digit) {
        this.cell = cell;
        this.digit = digit;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Integer getDigit() {
        return digit;
    }

    public void setDigit(Integer digit) {
        this.digit = digit;
    }

    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.cell.hashCode();
        hash = 41 * hash + this.digit.hashCode();
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hint other = (Hint) obj;
        if (!this.cell.equals(other.cell)) {
            return false;
        }
        if (!this.digit.equals(other.digit)) {
            return false;
        }
        return true;
    }
}
