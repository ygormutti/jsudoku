/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku.ginga.main;

import br.org.jsudoku.Cell;
import br.org.jsudoku.WritableCell;
import com.sun.dtv.lwuit.Button;
import com.sun.dtv.lwuit.Font;
import com.sun.dtv.lwuit.plaf.Border;
import com.sun.dtv.lwuit.plaf.Style;
import com.sun.dtv.ui.ViewOnlyComponent;
import java.awt.Color;

public class CellButton extends Button {

    Cell cell;
    Style style;

    public CellButton(Cell cell) {
        this.cell = cell;
        initStyle();
        update();
    }

    public Cell getCell() {
        return cell;
    }

    public final void update() {
        int digit = cell.getDigit();
        setText(digit != 0 ? Integer.toString(digit) : "");
    }

    public void setHighlight(boolean highlight) {
        if (highlight) {
            style.setBgColor(Color.YELLOW);
        } else {
            style.setBgColor(Color.WHITE);
        }
    }

    public void setError(boolean error) {
        if (error) {
            style.setFgColor(Color.RED);
        } else {
            if (cell instanceof WritableCell) {
                style.setFgColor(Color.BLACK);
            } else {
                style.setFgColor(Color.GRAY);
            } // FIXME código repetido
        }
    }

    private void initStyle() {
        style = new Style();

        style.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, 40));
        style.setBorder(Border.createLineBorder(5, Color.LIGHT_GRAY));
        style.setBgColor(Color.WHITE);
        style.setBgSelectionColor(Color.ORANGE);
        style.setBgTransparency(255);

        if (cell instanceof WritableCell) {
            style.setFgColor(Color.BLACK);
        } else {
            style.setFgColor(Color.GRAY);
        }

        setAlignment(HORIZONTAL_ALIGN_CENTER);
        setStyle(style);
    }
}
