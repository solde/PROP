/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

/**
 *
 * @author David Soldevila
 */
public abstract class GameAbs {
    protected Player P1;
    protected Player P2;
    
    protected boolean turn;

    public GameAbs(boolean turn) {
        this.turn = turn;
    }

    public GameAbs() {
    }
    
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isTurn() {
        return turn;
    }
}
