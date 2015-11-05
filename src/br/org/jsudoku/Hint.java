/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

import java.util.Objects;

public class Hint {

    Cell cell;
    Integer digit;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.cell);
        hash = 41 * hash + Objects.hashCode(this.digit);
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
        final Hint other = (Hint) obj;
        if (!Objects.equals(this.cell, other.cell)) {
            return false;
        }
        if (!Objects.equals(this.digit, other.digit)) {
            return false;
        }
        return true;
    }
}
