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
 * @author David Soldevila <3
 */
public class Game extends GameAbs {

    private long timerW;
    private long timerB;

    public Game() {
        timerW = 0;
        timerB = 0;
         B = new Board();
        turn = true;
    }
    
    public Game(boolean initialTurn) {
        turn = initialTurn;
        B = new Board();
        timerW = 0;
        timerB = 0;
    }


    /**
     *
     */
    @Override
    public void resetTimers() {
        timerW = 0;
        timerB = 0;
    }

    public void addTimeW(long t) {
        timerW += t;
    }

    public void addTimeB(long t) {
        timerB += t;
    }

    public double getTimerW() {
        return timerW;
    }

    public double getTimerB() {
        return timerB;
    }

    public void setTimerW(long timerW) {
        this.timerW = timerW;
    }

    public void setTimerB(long timerB) {
        this.timerB = timerB;
    }

    /**
     * Funciton to move a piece at board.
     * 
     * @param sX X index of source possition
     * @param sY Y index of source possition
     * @param dX X index of destination possition
     * @param dY y index of destination possition
     * @param color color of the player that moves (treu = white, false = black)
     * @param time Time of the desicion
     * @throws chessException when you are not de owner of the piece or when the piece cannot be moved.
     */
    @Override
    public void movePiece(int sX, int sY, int dX, int dY, boolean color, long time) throws chessException {
        if (color != this.turn) {
            throw new chessException("You are not the owner of the piece");
        } else {
            try {
                B.movePiece(sX, sY, dX, dY, color);
            } catch (chessException e) {
                throw new chessException("Can't move");
            }
            turn = !turn;
        }
    }
    
    @Override
    public ArrayList<int[]> possibleMovements(boolean color){
        ArrayList<int[]> ret = new ArrayList<>();
        ArrayList<Piece> Pieces;
        Pieces = new ArrayList<>();
        if(color) Pieces = B.getWhitePiecesOnBoard();
        else Pieces = B.getBlackPiecesOnBoard();
        int[] to_add;
        to_add = new int[4];
        for(int i = 0; i < Pieces.size(); ++i){
            Piece p = Pieces.get(i);
            to_add[0] = p.getX();
            to_add[1] = p.getY();
            ArrayList<Pair<Integer, Integer>> pos_movs = p.get_poss_mov(B);
            
            for(int j = 0; j < pos_movs.size(); ++j){
                to_add[2] = pos_movs.get(j).getKey();
                to_add[3] = pos_movs.get(j).getValue();
                ret.add(to_add);
            }            
        }
        return ret;
    }
    
    public String getProblemInfo(){
        return P.getProblemInfo();
    }
    
    public char[][] getBoard(){
        char[][] chessBoard = new char[8][8];
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                switch(B.getPieceAt(i, j).getTypeOfPiece()){
                    case 0:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'K';
                        else chessBoard[i][j] = 'k';
                        break;
                    case 1:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'P';
                        else chessBoard[i][j] = 'p';
                        break;
                    case 5:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'R';
                        else chessBoard[i][j] = 'r';
                        break;
                    case 3:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'B';
                        else chessBoard[i][j] = 'b';
                        break;
                    case 4:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'N';
                        else chessBoard[i][j] = 'n';
                        break;
                    case 7:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'Q';
                        else chessBoard[i][j] = 'q';
                        break;
                    case -1:
                        chessBoard[i][j] = ' ';
                        break;
                }
            }
        }
        return chessBoard;
    }

    @Override
    public void playMatch() throws chessException {
        throw new UnsupportedOperationException("You have to play, slacker"); //To change body of generated methods, choose Tools | Templates.
    }
}
