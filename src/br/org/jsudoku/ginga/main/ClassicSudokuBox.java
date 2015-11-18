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

public class ClassicSudokuBox extends Container {

    Style style;

    public ClassicSudokuBox(CellButton[] buttons) {
        super();

        setLayout(new GridLayout(ClassicBoard.BOX_SIZE, ClassicBoard.BOX_SIZE));
        initStyle();

        for (int i = 0; i < buttons.length; i++) {
            addComponent(buttons[i]);
        }
    }

    private void initStyle() {
        style = getStyle();

        style.setMargin(5, 5, 5, 5);
        style.setBgColor(Color.BLACK);
    }
}
