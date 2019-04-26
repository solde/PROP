/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Soldevila
 */
public class CtrlDomainCreator {
    private Board B;
    private Problem P;
    private Player Pl;
        
    /**
     *
     */
    public CtrlDomainCreator() {
        B = new Board();
        P = new Problem();
        Pl = new Human();
    }
    
    /**
     *
     */
    public void createNewCustomBoard(){
        B = new Board();
    }

    /**
     *
     * @param X
     * @param Y
     * @param Type
     * @param color
     */
    public void modifyBoardCell(int X, int Y, int Type, boolean color){
        B.addPieceToBoard(X, Y, Type, color);
    }
    
    /**
     *
     * @param fen
     * @param Name
     * @param N_mov
     * @param Theme
     * @param atk
     * @param first_turn
     */
    public void createNewProblemTest(String fen, String Name, int N_mov, String Theme, boolean atk, boolean first_turn){
        P = new Problem(fen, Name, N_mov, Theme, atk, first_turn);
    }
    
    /**
     *
     * @param Name
     * @param Password
     */
    public void createNewPlayerTest(String Name, String Password){
        Pl = new Human(Name, Password, 0, 0, 1000, 0);
    }
    
    public void modifyProblemTest(String fen, String Name, int N_mov, String Theme, boolean atk, boolean first_turn) throws chessException{
        P = new Problem();
        P.setFenCode(fen);
    }
    
    public void saveProblemAsCopy(String newName){
        String newFen = B.toString();
    }
    

    public String getPlayerInfo(){
        return P1.getPlayerInfo();
    }

    
    public String getProblemInfo(){
        return P.getProblemInfo();
    }
    
    public void addPieceAt(int x, int y, char type, boolean color){
        int iType = -1;
        switch(type){
            case 'q': 
                iType = 7;
                break;
            case 'k': 
                iType = 0;
                break;
            case 'n': 
                iType = 4;
                break;
            case 'p': 
                iType = 1;
                break;
            case 'b': 
                iType = 3;
                break;
        }
        B.addPieceToBoard(x, y, iType, color);
    }
    
    public char[][] getBoardInfo() throws chessException{
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
    
    public boolean verifyFen(String fen){
        try {
            Board testB = new Board(fen);
        } catch (chessException ex) {
            return false;
        }
        return true;
    }
    
    public String getFenCodeOfBoard() throws chessException{
        return B.fenToString();
    }
    
    public boolean verifyProblem() throws chessException{
        return P.verify();
    }
    
    public void loadProblemTest(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn){
        P = new Problem(fenCode, Name, diff, N_mov, Theme, atk, first_turn);
    }

}
