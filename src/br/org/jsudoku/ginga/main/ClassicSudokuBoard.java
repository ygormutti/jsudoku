/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku.ginga.main;

import br.org.jsudoku.ClassicBoard;
import com.sun.dtv.lwuit.Container;
import com.sun.dtv.lwuit.layouts.GridLayout;
import com.sun.dtv.lwuit.plaf.Style;
import java.awt.Color;

public class ClassicSudokuBoard extends Container {

    static Style style;
    ClassicSudokuBox[] boxes;

    public ClassicSudokuBoard(ClassicSudokuBox[] boxes) {
        super();

        setLayout(new GridLayout(ClassicBoard.BOXES_PER_SIDE, ClassicBoard.BOXES_PER_SIDE));
        initStyle();

        for (int i = 0; i < boxes.length; i++) {
            addComponent(boxes[i]);
        }
    }

    private void initStyle() {
        if (style == null) {
            style = new Style();

            style.setMargin(10, 10, 250, 250);
            style.setBgColor(Color.BLACK);
        }

        this.setStyle(style);
    }
}
