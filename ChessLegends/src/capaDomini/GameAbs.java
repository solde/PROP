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
    private Player P1;
    private Player P2;
    
    Board B;
    Problem P;
    
    protected boolean turn;

    /**
     * Constructor with parameters
     *
     * @param turn
     */
    public GameAbs(boolean turn) {
        this.turn = turn;
        B = new Board();
    }

    /**
     * basic constructor
     *
     */
    public GameAbs() {
        B = new Board();
    }
    
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    /**
     * Return whose turn is
     *
     * @return boolean
     */
    public boolean isTurn() {
        return turn;
    }
    
    /**
     * Set a problem with the desired parmeters
     *
     * @param fenCode
     * @param Name
     * @param diff
     * @param N_mov
     * @param Theme
     * @param atk
     * @param first_turn
     * @throws chessException
     */
    public void setProblem(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn, boolean verified) throws chessException{
        P = new Problem(fenCode, Name, diff, N_mov, Theme, atk, first_turn, verified);
        B = new Board(fenCode);
    }
    
    /**
     * Set the player 1 with the desired parameters
     *
     * @param playerId
     * @param wins
     * @param loses
     * @param ELO
     * @param OP_rating
     */
    public void setPlayer1(String playerId, int wins, int loses, double ELO, double OP_rating){
        if(playerId.equalsIgnoreCase("AI1")){
            P1 = new AI1();
        }
        else{
            P1 = new Human(playerId, wins, loses, ELO, OP_rating);
        }
    }
    
    /**
     * Set the player 2 with the desired parameters
     *
     * @param playerId
     * @param wins
     * @param loses
     * @param ELO
     * @param OP_rating
     */
    public void setPlayer2(String playerId, int wins, int loses, int ELO, int OP_rating){
        if(playerId.equalsIgnoreCase("AI1")){
            P2 = new AI1();
        }
        else{
            P2 = new Human(playerId, wins, loses, ELO, OP_rating);
        }
    }

    public Player getP1() {
        return P1;
    }

    public void setP1(Player P1) {
        this.P1 = P1;
    }

    public Player getP2() {
        return P2;
    }

    public void setP2(Player P2) {
        this.P2 = P2;
    }

    public Board getB() {
        return B;
    }

    public void setB(Board B) {
        this.B = B;
    }
    
    public String getPlayer1Info(){
        return "";
    }
    
    public String getPlayer2Info(){
        return "";
    }
    public void setN(int N) throws chessException{
        throw new chessException("No hace falta cambiar los turnos aqui");
    }
    
    /**
     * Set the board with the initial conditions, the initial FEN
     *
     * @throws chessException
     */
    public void resetBoard() throws chessException{
        Board bAux = new Board(this.P.getFenCode());
        this.setB(bAux);
    }
    
    /**
     *
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
    public abstract void playMatch(Boolean color) throws chessException;
    public abstract void resetTimers();
    public abstract ArrayList<int[]> possibleMovements(boolean color);
    public abstract char[][] getBoard() throws chessException;
    
    
}
