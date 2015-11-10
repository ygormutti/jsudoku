/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

public class WritableCell extends Cell {

    public WritableCell(int row, int column, int digit) {
        super(row, column, digit);
    }

    public WritableCell(Cell original) {
        super(original.row, original.column, original.digit);
    }

    public void setDigit(int digit) {
        super.setDigit(digit);
    }
}
