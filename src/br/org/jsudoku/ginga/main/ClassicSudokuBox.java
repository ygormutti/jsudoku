/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku.ginga.main;

import br.org.jsudoku.ClassicBoard;
import com.sun.dtv.lwuit.Container;
import com.sun.dtv.lwuit.layouts.GridLayout;
import com.sun.dtv.lwuit.plaf.Style;

public class ClassicSudokuBox extends Container {

    static Style style;

    public ClassicSudokuBox(CellButton[] buttons) {
        super();

        setLayout(new GridLayout(ClassicBoard.BOX_SIZE, ClassicBoard.BOX_SIZE));
        initStyle();

        for (int i = 0; i < buttons.length; i++) {
            addComponent(buttons[i]);
        }
    }

    private void initStyle() {
        if (style == null) {
            style = new Style();

            //style.setMargin(10, 10, 10, 10);
        }

        setStyle(style);
    }
}
