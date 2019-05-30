/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;
import java.util.ArrayList;
import javafx.util.Pair;

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
    protected boolean winner;
    
    

    /**
     * Constructor with parameters
     *
     * @param turn
     */
    public GameAbs(boolean turn) {
        this.turn = turn;       
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
        P = new Problem(fenCode, Name, Theme, diff, N_mov, atk, first_turn, verified);
        B = new Board(fenCode);
    }
    
    public void setProblem(Problem p) throws chessException{
        this.P = p;
        B = new Board(p.getFenCode());
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
        if(playerId.equalsIgnoreCase("AIHard")){
            P1 = new AIHard();
        }
        else if (playerId.equalsIgnoreCase("AIEasy")){
            P1 = new AIEasy();
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
        if(playerId.equalsIgnoreCase("AIHard")){
            P2 = new AIHard();
        }
        else if (playerId.equalsIgnoreCase("AIEasy")){
            P2 = new AIEasy();
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
    public void setAI1difficulty(int dif) throws chessException {
        if (dif == 1){
            this.P1 = new AIEasy();         
        } 
        else if (dif == 2){
            this.P1 = new AIHard();
        }
        else throw new chessException("Dificultad incorrecta");
        
    }
    
    public void setAI2difficulty(int dif) throws chessException {
        if (dif == 1){
            this.P2 = new AIEasy();         
        } 
        else if (dif == 2){
            this.P2 = new AIHard();
        }
        else throw new chessException("Dificultad incorrecta");
        
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
    
    public boolean getWinner(){
        return winner;
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
    public abstract boolean playMatch(Boolean color) throws chessException;
    public abstract void resetTimers();
    public abstract ArrayList<int[]> possibleMovements(boolean color);
    public abstract char[][] getBoard() throws chessException;
    
    
    public void setRanking(ArrayList<Pair<Long, String>> Ranking){
        P.setRanking(Ranking);
    }
    
    public ArrayList<Pair<Long, String>> getRanking(){
        return P.getRanking();
    }
    
    
}
