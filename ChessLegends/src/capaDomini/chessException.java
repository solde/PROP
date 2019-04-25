/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

/**
 *
 * @author Familia
 */
public class chessException extends Exception {

    /**
     * Creates a new instance of <code>chessException</code> without detail
     * message.
     */
    public chessException() {
    }

    /**
     * Constructs an instance of <code>chessException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public chessException(String msg) {
        super(msg);
    }
}
