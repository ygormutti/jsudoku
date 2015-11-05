/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.jsudoku.javatv;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

public class JSudokuXlet implements Xlet {

    XletContext context;

    @Override
    public void initXlet(XletContext xc) throws XletStateChangeException {
        context = xc;
    }

    @Override
    public void startXlet() throws XletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pauseXlet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroyXlet(boolean bln) throws XletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
