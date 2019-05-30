/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;
import capaDades.CtrlDades;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
    
    private CtrlDades CD;
        
    /**
     * Basic Creator
     */
    public CtrlDomainCreator() throws IOException {
        B = new Board();
        P = new Problem();
        //Pl = new Human();
        
        CD = new CtrlDades();
    }
    
    /**
     *  Assign a new board to the creator
     */
    public void createNewCustomBoard(){
        B = new Board();
    }

    /**
     * Changes the piece layout on the creator board 
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
     * Assign a problem to the creator with the parameters given
     *
     * @param fen
     * @param Name
     * @param N_mov
     * @param Theme
     * @param atk
     * @param first_turn
     */
    public void createNewProblem(String fen, String Name, String Theme, int N_mov, boolean atk, boolean first_turn, boolean v) throws IOException, chessException{
        P = new Problem(fen, Name, N_mov, Theme, atk, first_turn, v);
        CD.createProblem(fen, Name, Theme, P.getDiff(), N_mov, atk, first_turn, v);
    }
    
    /**
     * Assign a player to the creator with the parameters given 
     * 
     * @param Name
     * @param Password
     */
    public void createNewPlayer(String Name, String Password) throws IOException, chessException{
        Pl = new Human(Name, Password);
        CD.createPlayer(Name, Password);
    }
    
    /**
     * Assign a problem to the creator with the parameters given
     * @param fen
     * @param Name
     * @param N_mov
     * @param Theme
     * @param atk
     * @param first_turn
     * @throws chessException
     */
    public void modifyProblemTest(String fen, String Name, int N_mov, String Theme, boolean atk, boolean first_turn) throws chessException{
        P = new Problem();
        P.setFenCode(fen);
    }
    
    /**
     *  Returns the FEN of a board
     * @param newName
     */
    public void saveProblemAsCopy(String newName){
        String newfen = B.toString();
    }
    
    /**
     * Returns information of the player
     * 
     * @return String
     */
    public String getPlayerInfo(){
        return Pl.getPlayerInfo();
    }

    /**
     * Returns information of the problem
     * 
     * @return String
     */
    public String getProblemInfo(String id) throws IOException, FileNotFoundException, chessException{
        return CD.getProblem(id);
    }
    
    /**
     * Adds a piece to the board with the desired parameters
     * 
     * @param x
     * @param y
     * @param type
     * @param color
     */
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
            case 'd':
                iType = -1;
                break;
        }
        B.addPieceToBoard(x, y, iType, color);
    }
    
    /**
     * Returns the "uncompressed" board, all the positions without the FEN
     * 
     * @return char[][]
     * @throws chessException
     */
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
    
    /**
     * Check if the given FEN is valid
     * 
     * @param fen
     * @return boolean
     */
    public boolean verifyFen(String fen){
        try {
            Board testB = new Board(fen);
        } catch (chessException ex) {
            return false;
        }
        return true;
    }
    

    /**
     * Check if a problem has a solution
     *
     * @return boolean
     * @throws chessException
     */
    public boolean verifyProblem(String fen, String name, String theme, int mov, boolean turn, boolean color) throws chessException{
       //String fenCode, String Name, int N_mov, String Theme, boolean atk, boolean first_turn
        P = new Problem(fen, name, mov, theme, turn, color );
        return P.verify();
    }
    
    /**
     * Set the problem with the desired parameters 
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
    public void loadProblemTest(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn, boolean verified) throws chessException{
        P = new Problem(fenCode, Name, Theme, diff, N_mov, atk, first_turn, verified);
        B = new Board(fenCode);
    }
    
    public void storeProblem() throws IOException, chessException{
        String Theme = P.getTheme();
        Theme = Theme.replace(" ", "_");
        CD.createProblem(P.getName(), P.getFenCode(), Theme, P.getN_mov(), P.getDiff(), P.getATK(), P.getFirstTurn(), P.isVerified());
    }
    
    public void updatePassword(String id, String oldPassword, String newPassword) throws IOException, IOException, FileNotFoundException, FileNotFoundException, chessException{
        CD.updatePassword(id, oldPassword, newPassword);
    }

    public ArrayList<String> getProblemList() throws IOException{
        return CD.listProblmes();
    }

    public void deleteProblem(String id) throws IOException, chessException{
        CD.eraseProblem(id);
    }

}
