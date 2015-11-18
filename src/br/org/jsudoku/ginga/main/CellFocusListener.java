/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku.ginga.main;

import br.org.jsudoku.Cell;
import com.sun.dtv.lwuit.Component;
import com.sun.dtv.lwuit.events.FocusListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CellFocusListener implements FocusListener {

    CellButton button;
    HashSet neighbors;

    public CellFocusListener(MainForm form, CellButton button) {
        this.button = button;

        Cell cell = button.getCell();
        Set neighborCells = form.getBoard().getNeighbors(cell);
        neighbors = new HashSet();

        Iterator i = neighborCells.iterator();
        while (i.hasNext()) {
            Cell n = (Cell) i.next();
            neighbors.add(form.getCellButtonMap().get(n));
        }
    }

    public void focusGained(Component cmpnt) {
        for (Iterator i = neighbors.iterator(); i.hasNext();) {
            CellButton ncb = (CellButton) i.next();
            ncb.setHighlight(true);
        }
    }

    public void focusLost(Component cmpnt) {
        for (Iterator i = neighbors.iterator(); i.hasNext();) {
            CellButton ncb = (CellButton) i.next();
            ncb.setHighlight(false);
        }
    }
}
