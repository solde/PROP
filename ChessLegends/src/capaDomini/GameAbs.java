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
    
    protected Board B;
    
    protected boolean turn;

    public GameAbs(boolean turn) {
        this.turn = turn;
        B = new B();
    }

    public GameAbs() {
        B = new B();
    }
    
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isTurn() {
        return turn;
    }
    
    public void setFENcode(String FEN){
        B.setFEN_code(FEN);
    }
}
