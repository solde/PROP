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
    
    public void setProblem(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn) throws chessException{
        P = new Problem(fenCode, Name, diff, N_mov, Theme, atk, first_turn);
        B = new Board(fenCode);
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
    
    public void resetBoard() throws chessException{
        Board bAux = new Board(this.P.getFenCode());
        this.setB(bAux);
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
    public abstract void playMatch(Boolean color) throws chessException;
    public abstract void resetTimers();
    public abstract ArrayList<int[]> possibleMovements(boolean color);
    public abstract char[][] getBoard() throws chessException;
    
    public static void printBoard(Board B){
        System.out.println("   0  1  2  3  4  5  6  7");
        for(int x = 0; x < 8; ++x){
            System.out.print(x + "|");
            for(int y = 0; y < 8; ++y){
                if(B.getPieceAt(x, y).isColor() && B.getPieceAt(x, y).getTypeOfPiece() != -1)System.out.print(" " + B.getPieceAt(x, y).getTypeOfPiece() + "|");
                else if(!B.getPieceAt(x, y).isColor() && B.getPieceAt(x, y).getTypeOfPiece() != -1)System.out.print("-" + B.getPieceAt(x, y).getTypeOfPiece() + "|");
                else System.out.print("  " + "|");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    
}
