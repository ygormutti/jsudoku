/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku.ginga;

import br.org.jsudoku.ginga.main.MainForm;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

public class JSudokuXlet implements Xlet {

    XletContext context;

    public void initXlet(XletContext xc) throws XletStateChangeException {
        context = xc;
    }

    public void startXlet() throws XletStateChangeException {
        try {
            MainForm form = new MainForm();
            form.show();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void pauseXlet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void destroyXlet(boolean bln) throws XletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
