/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku.ginga.main;

import br.org.jsudoku.BoardFactory;
import br.org.jsudoku.Cell;
import br.org.jsudoku.ClassicBoard;
import com.sun.dtv.lwuit.Font;
import com.sun.dtv.lwuit.Form;
import com.sun.dtv.lwuit.Label;
import com.sun.dtv.lwuit.events.ActionEvent;
import com.sun.dtv.lwuit.events.ActionListener;
import com.sun.dtv.lwuit.layouts.BorderLayout;
import com.sun.dtv.lwuit.plaf.Style;
import com.sun.dtv.ui.event.RemoteControlEvent;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class MainForm extends Form {

    static Style style;
    ClassicBoard board;
    ClassicSudokuBoard boardWidget;
    Map cellButtonMap;
    CellButton[][] cellButtonGrid;
    CellButton currentButton;

    public MainForm() {
        super();
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

        Label label = new Label("jSudoku");
        label.getStyle().setBgTransparency(255);
        label.getStyle().setFgColor(Color.WHITE);
        label.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, 40));
        addComponent(BorderLayout.NORTH, label);

        board = BoardFactory.getClassicBoard();

        cellButtonMap = new HashMap();
        Cell[][] boxesCells = board.getBoxesCells();
        ClassicSudokuBox[] boxes = new ClassicSudokuBox[ClassicBoard.BOXES_PER_BOARD];
        for (int box = 0; box < ClassicBoard.BOXES_PER_BOARD; box++) {
            CellButton[] boxButtons = new CellButton[ClassicBoard.CELLS_PER_BOX];
            for (int i = 0; i < ClassicBoard.CELLS_PER_BOX; i++) {
                Cell cell = boxesCells[box][i];

                CellButton bt = new CellButton(cell);
                if (currentButton == null) {
                    currentButton = bt;
                }

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
    }

    public void keyPressed(int key) {
        if (key == KeyEvent.VK_UP) {
            System.out.println("up");
        } else if (key == KeyEvent.VK_DOWN) {
            System.out.println("down");
        } else if (key == KeyEvent.VK_LEFT) {
            System.out.println("left");
        } else if (key == KeyEvent.VK_RIGHT) {
            System.out.println("right");
        } else if (key == KeyEvent.VK_0) {
            System.out.println("0");
        } else if (key == RemoteControlEvent.VK_COLORED_KEY_0) {
            System.out.println("red");
        }

    }

    private void initEvents() {
        this.addKeyListener(RemoteControlEvent.VK_UP, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.out.println("up2");
            }
        });
        this.addKeyListener(RemoteControlEvent.VK_COLORED_KEY_0, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.out.println("red2");
            }
        });
    }
}
