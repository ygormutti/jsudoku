/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

import java.util.ArrayList;

public class BoardFactory {

    static int currentBoard = 0;
    static ClassicBoard[] classicBoards;

    public static ClassicBoard getClassicBoard() {
        if (classicBoards == null) {
            initClassicBoards();
        }

        if (currentBoard == classicBoards.length) {
            currentBoard = 0;
        }

        return classicBoards[currentBoard++];
    }
    private static void initClassicBoards() {
        ArrayList list = new ArrayList();

        int[][] hints = new int[][]{
            new int[]{0, 0, 9, 8, 5, 0, 0, 2, 0},
            new int[]{0, 5, 0, 9, 0, 3, 0, 7, 0},
            new int[]{7, 0, 0, 0, 0, 0, 3, 0, 0},
            //
            new int[]{4, 0, 2, 0, 0, 9, 0, 0, 0},
            new int[]{0, 0, 1, 4, 0, 0, 7, 0, 0},
            new int[]{6, 0, 7, 0, 0, 5, 0, 0, 0},
            //
            new int[]{1, 0, 0, 0, 0, 0, 5, 0, 0},
            new int[]{0, 2, 0, 1, 0, 4, 0, 3, 0},
            new int[]{0, 0, 3, 5, 6, 0, 0, 1, 0},};
        list.add(new ClassicBoard(convertMatrixToHints(hints)));

        hints = new int[][]{
            new int[]{0, 4, 0, 0, 1, 6, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 3, 4, 0, 0},
            new int[]{5, 0, 0, 4, 8, 0, 0, 7, 0},
            //
            new int[]{0, 2, 4, 0, 0, 0, 7, 0, 0},
            new int[]{0, 0, 9, 0, 2, 7, 0, 0, 0},
            new int[]{0, 3, 6, 0, 0, 0, 9, 0, 0},
            //
            new int[]{2, 0, 0, 8, 6, 0, 0, 9, 0},
            new int[]{0, 0, 0, 0, 0, 5, 2, 0, 0},
            new int[]{0, 1, 0, 0, 7, 4, 0, 0, 0},};
        list.add(new ClassicBoard(convertMatrixToHints(hints)));

        hints = new int[][]{
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{9, 7, 0, 5, 0, 8, 0, 2, 3},
            new int[]{5, 0, 5, 1, 0, 2, 9, 0, 0},
            //
            new int[]{0, 0, 0, 4, 0, 1, 0, 0, 0},
            new int[]{0, 8, 0, 4, 0, 1, 0, 0, 0},
            new int[]{3, 0, 0, 0, 9, 0, 0, 0, 4},
            //
            new int[]{0, 0, 0, 0, 6, 0, 0, 0, 0},
            new int[]{0, 9, 0, 0, 5, 0, 0, 3, 0},
            new int[]{0, 0, 4, 9, 0, 7, 8, 0, 0},};
        list.add(new ClassicBoard(convertMatrixToHints(hints)));

        hints = new int[][]{
            new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{9, 7, 0, 5, 0, 8, 0, 2, 3},
            new int[]{5, 0, 5, 1, 0, 2, 9, 0, 0},
            //
            new int[]{0, 0, 0, 4, 0, 1, 0, 0, 0},
            new int[]{0, 8, 0, 4, 0, 1, 0, 0, 0},
            new int[]{3, 0, 0, 0, 9, 0, 0, 0, 4},
            //
            new int[]{0, 0, 0, 0, 6, 0, 0, 0, 0},
            new int[]{0, 9, 0, 0, 5, 0, 0, 3, 0},
            new int[]{0, 0, 4, 9, 0, 7, 8, 0, 0},};
        list.add(new ClassicBoard(convertMatrixToHints(hints)));

        hints = new int[][]{
            new int[]{0, 0, 6, 0, 3, 0, 4, 0, 1},
            new int[]{7, 0, 0, 0, 0, 1, 0, 0, 6},
            new int[]{0, 4, 5, 2, 0, 6, 3, 0, 0},
            //
            new int[]{9, 0, 0, 0, 7, 8, 1, 3, 2},
            new int[]{3, 2, 1, 5, 0, 0, 0, 6, 0},
            new int[]{6, 7, 0, 0, 0, 2, 0, 4, 6},
            //
            new int[]{0, 8, 0, 0, 5, 4, 0, 9, 0},
            new int[]{4, 6, 3, 0, 2, 7, 0, 1, 5},
            new int[]{0, 1, 9, 8, 0, 3, 2, 0, 4},};
        list.add(new ClassicBoard(convertMatrixToHints(hints)));

        classicBoards = (ClassicBoard[]) list.toArray(new ClassicBoard[list.size()]);
    }

    private static Cell[] convertMatrixToHints(int[][] matrix) {
        ArrayList hints = new ArrayList();

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                int digit = matrix[r][c];
                if (digit != 0) {
                    hints.add(new Cell(r, c, digit));
                }
            }
        }

        return (Cell[]) hints.toArray(new Cell[hints.size()]);
    }
}
