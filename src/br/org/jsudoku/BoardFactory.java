/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

import java.util.ArrayList;

public class BoardFactory {

    static int currentBoard = 0;
    static ClassicBoard[] classicBoards;

    public static Board getBoard() {
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

        classicBoards = (ClassicBoard[]) list.toArray(new ClassicBoard[list.size()]);
    }

    private static Hint[] convertMatrixToHints(int[][] matrix) {
        ArrayList hints = new ArrayList();

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                int digit = matrix[r][c];
                if (digit != 0) {
                    hints.add(new Hint(r, c, digit));
                }
            }
        }

        return (Hint[]) hints.toArray(new Hint[hints.size()]);
    }
}
