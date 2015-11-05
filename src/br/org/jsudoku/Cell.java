/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

public class Cell {

    int row;
    int column;

    public Cell(int row, int column) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("row and column indexes must be positive");
        }
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.row;
        hash = 89 * hash + this.column;
        return hash;
    }

    @Override
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
}
