/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

public class Cell {

    int digit;
    int row;
    int column;

    public Cell(int row, int column, int digit) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("row and column indexes must be positive");
        }
        this.row = row;
        this.column = column;
        this.digit = digit;
    }

    public Cell(Cell original) {
        this(original.row, original.column, original.digit);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getDigit() {
        return digit;
    }

    public boolean isWritable() {
        return false;
    }

    protected void setDigit(int digit) {
        this.digit = digit;
    }

    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.row;
        hash = 97 * hash + this.column;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "Cell{" + "digit=" + digit + ", row=" + row + ", column=" + column + '}';
    }
}
