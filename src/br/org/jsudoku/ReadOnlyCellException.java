/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.jsudoku;

/**
 *
 * @author Ygor Mutti <ygormutti AT gmail.com>
 */
public class ReadOnlyCellException extends Exception {

    /**
     * Creates a new instance of
     * <code>ReadOnlyCellException</code> without detail message.
     */
    public ReadOnlyCellException() {
    }

    /**
     * Constructs an instance of
     * <code>ReadOnlyCellException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ReadOnlyCellException(String msg) {
        super(msg);
    }
}
