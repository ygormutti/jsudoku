/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.jsudoku.ginga;

import com.sun.dtv.lwuit.Font;
import com.sun.dtv.lwuit.Form;
import com.sun.dtv.lwuit.Label;
import java.awt.Color;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;

public class JSudokuXlet implements Xlet {

    XletContext context;

    public void initXlet(XletContext xc) throws XletStateChangeException {
        context = xc;
    }

    public void startXlet() throws XletStateChangeException {

        // In LWUIT it is required to instantiate a form in order to display
        // contents into the screen.
        // Here a Form is instantiated with the "Hello World!" string as title.
        Form mainForm = new Form("[Form Title]: Hello World!");

        // Let's set a translucent LIGHT_GRAY background for the form.
        // Video will be visible behing the form.
        mainForm.getStyle().setBgTransparency(60);
        mainForm.getStyle().setBgColor(Color.LIGHT_GRAY);

        // Lets adjust the visuals of the Form's title bar too.
        mainForm.getTitleStyle().setBgTransparency(60);
        mainForm.getTitleStyle().setBgColor(Color.WHITE);
        mainForm.getTitleStyle().setFgColor(Color.WHITE);

        //Finally, lets create a Label...
        Label label = new Label("[Label]: Hello World!");
        label.getStyle().setBgTransparency(0);
        label.getStyle().setFgColor(Color.WHITE);
        label.getStyle().setFont(Font.createSystemFont(
                Font.FACE_SYSTEM, Font.STYLE_PLAIN, 20));
        //... and add the label to the Form
        mainForm.addComponent(label);

        // In order to the form become visible, show() must be called.
        mainForm.show();

        //Lets tests the System.out of the emulator
        System.out.println("##### [System.out]: Hello World! ");
    }

    public void pauseXlet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void destroyXlet(boolean bln) throws XletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
