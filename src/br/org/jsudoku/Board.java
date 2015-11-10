/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

import java.util.Set;
public interface Board {

    Integer getDigit(Cell cell);

    Integer getDigit(int row, int column);

    Set getErrors();

    Set getHints();

    Set getNeighbors(Cell cell);

    void setDigit(Hint hint) throws ReadOnlyCellException;

    void setDigit(int row, int column, Integer digit) throws ReadOnlyCellException;

    void setDigit(Cell cell, Integer digit) throws ReadOnlyCellException;

    Set setDigitAndGetErrors(Cell cell, Integer digit) throws ReadOnlyCellException;

    Set setDigitAndGetErrors(int row, int column, Integer digit) throws ReadOnlyCellException;

}
