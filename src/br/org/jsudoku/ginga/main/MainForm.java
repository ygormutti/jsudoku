/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku.ginga.main;

import br.org.jsudoku.BoardFactory;
import br.org.jsudoku.Cell;
import br.org.jsudoku.ClassicBoard;
import br.org.jsudoku.ReadOnlyCellException;
import com.sun.dtv.lwuit.Font;
import com.sun.dtv.lwuit.Form;
import com.sun.dtv.lwuit.Label;
import com.sun.dtv.lwuit.layouts.BorderLayout;
import com.sun.dtv.lwuit.plaf.Style;
import com.sun.dtv.ui.Screen;
import com.sun.dtv.ui.event.KeyEvent;
import com.sun.dtv.ui.event.RemoteControlEvent;
import com.sun.dtv.ui.event.UserInputEventManager;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainForm extends Form {

    static Style style;
    ClassicBoard board;
    ClassicSudokuBoard boardWidget;
    Map cellButtonMap;
    CellButton[][] cellButtonGrid;

    public MainForm() {
        super();
        init();
    }

    private void init()
    {
        removeAll();
        setLayout(new BorderLayout());
        initStyle();
        initComponents();
        initEvents();
    }

    private void initStyle() {
        if (style == null) {
            style = new Style();

            style.setBgColor(Color.BLACK);
            style.setBgTransparency(0);
        }
        setStyle(style);
    }

    private void initComponents() {

        Label labelTitle = new Label("jSudoku");
        labelTitle.setAlignment(LEFT);
        labelTitle.getStyle().setBgTransparency(255);
        labelTitle.getStyle().setBgColor(Color.BLACK);
        labelTitle.getStyle().setFgColor(Color.WHITE);
        labelTitle.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, 40));
        addComponent(BorderLayout.NORTH, labelTitle);

        Label labelHelp = new Label("comandos");
        labelHelp.setAlignment(LEFT);
        labelHelp.getStyle().setBgTransparency(255);
        labelHelp.getStyle().setBgColor(Color.BLACK);
        labelHelp.getStyle().setFgColor(Color.WHITE);
        labelHelp.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, 30));
        addComponent(BorderLayout.SOUTH, labelHelp);

        board = BoardFactory.getClassicBoard();

        cellButtonMap = new HashMap();
        Cell[][] boxesCells = board.getBoxesCells();
        ClassicSudokuBox[] boxes = new ClassicSudokuBox[ClassicBoard.BOXES_PER_BOARD];
        for (int box = 0; box < ClassicBoard.BOXES_PER_BOARD; box++) {
            CellButton[] boxButtons = new CellButton[ClassicBoard.CELLS_PER_BOX];
            for (int i = 0; i < ClassicBoard.CELLS_PER_BOX; i++) {
                Cell cell = boxesCells[box][i];

                CellButton bt = new CellButton(cell);
                cellButtonMap.put(cell, bt);
                boxButtons[i] = bt;
            }
            boxes[box] = new ClassicSudokuBox(boxButtons);
        }
        boardWidget = new ClassicSudokuBoard(boxes);
        addComponent(BorderLayout.CENTER, boardWidget);

        cellButtonGrid = new CellButton[ClassicBoard.BOARD_SIZE][ClassicBoard.BOARD_SIZE];
        for (int r = 0; r < ClassicBoard.BOARD_SIZE; r++) {
            for (int c = 0; c < ClassicBoard.BOARD_SIZE; c++) {
                cellButtonGrid[r][c] = (CellButton) cellButtonMap.get(board.getCell(r, c));
            }
        }

        for (int r = 0; r < ClassicBoard.BOARD_SIZE; r++) {
            for (int c = 0; c < ClassicBoard.BOARD_SIZE; c++) {
                CellButton cb = cellButtonGrid[r][c];
                cb.setNextFocusUp(cellButtonGrid[mod(r - 1, ClassicBoard.BOARD_SIZE)][c]);
                cb.setNextFocusDown(cellButtonGrid[mod(r + 1, ClassicBoard.BOARD_SIZE)][c]);
                cb.setNextFocusRight(cellButtonGrid[r][mod(c + 1, ClassicBoard.BOARD_SIZE)]);
                cb.setNextFocusLeft(cellButtonGrid[r][mod(c - 1, ClassicBoard.BOARD_SIZE)]);
            }
        }
    }

    private int mod(int x, int y) {
        int remainder = x % y;
        if (x < 0) {
            remainder += y;
        }
        return remainder;
    }

    public ClassicBoard getBoard() {
        return board;
    }

    public CellButton getCurrentButton() {
        return (CellButton) getFocused();
    }

    public void keyPressed(int key) {
        if (key != RemoteControlEvent.VK_COLORED_KEY_0 && key != RemoteControlEvent.VK_COLORED_KEY_1) {
            super.keyPressed(key);
        }
    }

    public void keyReleased(int key) {

        if (key == RemoteControlEvent.VK_COLORED_KEY_0) { // red
        } else if (key == RemoteControlEvent.VK_COLORED_KEY_1) { // green
            init();
            repaint();
        } else if (key == RemoteControlEvent.VK_COLORED_KEY_2) { // yellow
        } else if (key == RemoteControlEvent.VK_COLORED_KEY_3) { // blue
            for (Iterator i = board.getHints().iterator(); i.hasNext();) {
                Cell hint = (Cell) i.next();
                try {
                    board.setDigit(hint, hint.getDigit());
                } catch (ReadOnlyCellException ex) {
                    ex.printStackTrace();
                }
                ((CellButton) cellButtonMap.get(hint)).update();
                break;
            }
        } else if (KeyEvent.VK_0 <= key && key <= KeyEvent.VK_9) {
            CellButton currentButton = getCurrentButton();
            Cell currentCell = currentButton.getCell();
            int digit = key - KeyEvent.VK_0;

            if (currentCell.getDigit() != digit && currentCell.isWritable()) {
                try {
                    Set errors = board.setDigit(currentCell, digit);

                    // clear previous errors
                    for (Iterator i = cellButtonMap.values().iterator(); i.hasNext();) {
                        CellButton cb = (CellButton) i.next();
                        cb.setError(false);
                    }

                    // shows the new ones
                    for (Iterator i = errors.iterator(); i.hasNext();) {
                        Cell error = (Cell) i.next();
                        CellButton cb = (CellButton) cellButtonMap.get(error);
                        cb.setError(true);
                    }
                } catch (ReadOnlyCellException ex) {
                    ex.printStackTrace();
                }
            }
            currentButton.update();
        }


    }

//    private void moveFocusTo(CellButton cb) {
////        if (currentButton != null) {
////            currentButton.setFocus(false);
////        }
//        currentButton = cb;
//        setFocused(currentButton);
//    }
    private void initEvents() {
        KeyEvent event_0 = new KeyEvent(this, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_0, '0');
        KeyEvent event_1 = new KeyEvent(this, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_1, '0');
        KeyEvent event_2 = new KeyEvent(this, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_2, '0');
        KeyEvent event_3 = new KeyEvent(this, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_3, '0');
        KeyEvent event_4 = new KeyEvent(this, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_4, '0');
        KeyEvent event_5 = new KeyEvent(this, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_5, '0');
        KeyEvent event_6 = new KeyEvent(this, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_6, '0');
        KeyEvent event_7 = new KeyEvent(this, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_7, '0');
        KeyEvent event_8 = new KeyEvent(this, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_8, '0');
        KeyEvent event_9 = new KeyEvent(this, KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_9, '0');

        Screen currentScreen = Screen.getCurrentScreen();
        UserInputEventManager inputManager = UserInputEventManager.getUserInputEventManager(currentScreen);

        inputManager.addUserInputEventListener(new NumericKeyReleasedListener(this, 0), event_0);
        inputManager.addUserInputEventListener(new NumericKeyReleasedListener(this, 1), event_1);
        inputManager.addUserInputEventListener(new NumericKeyReleasedListener(this, 2), event_2);
        inputManager.addUserInputEventListener(new NumericKeyReleasedListener(this, 3), event_3);
        inputManager.addUserInputEventListener(new NumericKeyReleasedListener(this, 4), event_4);
        inputManager.addUserInputEventListener(new NumericKeyReleasedListener(this, 5), event_5);
        inputManager.addUserInputEventListener(new NumericKeyReleasedListener(this, 6), event_6);
        inputManager.addUserInputEventListener(new NumericKeyReleasedListener(this, 7), event_7);
        inputManager.addUserInputEventListener(new NumericKeyReleasedListener(this, 8), event_8);
        inputManager.addUserInputEventListener(new NumericKeyReleasedListener(this, 9), event_9);

        Iterator i = cellButtonMap.values().iterator();
        while (i.hasNext()) {
            CellButton cb = (CellButton) i.next();
            cb.addFocusListener(new CellFocusListener(this, cb));
        }
    }

    public Map getCellButtonMap() {
        return cellButtonMap;
    }
}
