/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;
import java.util.ArrayList;

/**
 *
 * @author David Soldevila
 */
public abstract class GameAbs {
    protected Player P1;
    protected Player P2;
    
    protected Board B;
    protected Problem P;
    
    protected boolean turn;

    public GameAbs(boolean turn) {
        this.turn = turn;
        B = new Board();
    }

    public GameAbs() {
        B = new Board();
    }
    
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isTurn() {
        return turn;
    }
    
    public void setProblem(String problemInfo){
        P = new Problem(problemInfo);
    }
    
    public void setPlayer1(String playerId, int wins, int loses, int ELO, int OP_rating){
        if(playerId.equalsIgnoreCase("AI1")){
            P1 = new AI1();
        }
        else{
            P1 = new Human(playerId, wins, loses, ELO, OP_rating);
        }
    }
    
    public void setPlayer2(String playerId, int wins, int loses, int ELO, int OP_rating){
        if(playerId.equalsIgnoreCase("AI1")){
            P2 = new AI1();
        }
        else{
            P2 = new Human(playerId, wins, loses, ELO, OP_rating);
        }
    }
    
    public String getPlayer1Info(){
        return "";
    }
    
    public String getPlayer2Info(){
        return "";
    }
    
    /**
     *
     * @param sX
     * @param sY
     * @param dX
     * @param dY
     * @param color
     * @param time
     * @throws chessException
     */
    public abstract void movePiece(int sX, int sY, int dX, int dY, boolean color, long time) throws chessException;
    public abstract void playMatch() throws chessException;
    public abstract void resetTimers();
    public abstract ArrayList<int[]> possibleMovements(boolean color);
}
