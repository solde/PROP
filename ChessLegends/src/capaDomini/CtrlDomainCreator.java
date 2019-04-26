/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;

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
     * @param Name
     * @param N_mov
     * @param Theme
     * @param atk
     * @param first_turn
     */
    public void createNewProblemTest(String Name, int N_mov, String Theme, boolean atk, boolean first_turn){
        String fenCode = null;
        try{
            fenCode = B.fenToString();
        }
        catch(chessException e){
            System.out.println("Unexpected Error");
        }
        P = new Problem(fenCode, Name, N_mov, Theme, atk, first_turn);
    }
    
    /**
     *
     * @param Name
     * @param Password
     * @throws chessException
     */
    public void createNewPlayerTest(String Name, String Password){
        Pl = new Human(Name, Password, 0, 0, 1000, 0);
    }
    
    public void modifyProblemTest(String Name, String FEN_code, int N_mov, String Theme, boolean atk, boolean first_turn) throws chessException{
        B.setFEN_code(FEN_code);
    }
    
    public void saveProblemAsCopy(String newName){
        String newFen = B.toString();
    }
    
    /*public String getPlayerInfo(){
        return P1.getPlayerInfo();
    }*/
    
    public String getProblemInfo(){
        return P.getProblemInfo();
    }
}
