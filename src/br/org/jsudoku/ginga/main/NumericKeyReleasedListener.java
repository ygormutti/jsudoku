/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku.ginga.main;

import com.sun.dtv.lwuit.Component;
import com.sun.dtv.ui.event.UserInputEvent;
import com.sun.dtv.ui.event.UserInputEventListener;
import java.awt.event.KeyEvent;

public class NumericKeyReleasedListener implements UserInputEventListener {

    int key;
    Component component;

    public NumericKeyReleasedListener(Component form, int key) {
        this.key = key;
        this.component = form;
    }

    public void userInputEventReceived(UserInputEvent uie) {
        component.keyReleased(KeyEvent.VK_0 + key);
    }
}
