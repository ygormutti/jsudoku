/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

import java.util.Set;
public interface Board {

    Cell[][] getBoxesCells();

    Cell getCell(int row, int column);

    Set getErrors();

    Set getHints();

    Set getNeighbors(Cell cell);

    Set setDigit(Cell cell, int digit) throws ReadOnlyCellException;

    void setHint(Cell hint) throws ReadOnlyCellException;

}
