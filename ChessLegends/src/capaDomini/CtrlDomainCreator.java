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
     */
    public void addPieceToBoard(int X, int Y, char Type){
        try{
            B.addPieceAt(X, Y, Type);
        }
        catch(Exception e){
            System.out.println("Cannot add piece");
        }
    }
    
    /**
     *
     * @param Name
     * @param N_mov
     * @param Theme
     * @param atk
     * @param first_turn
     */
    public void createNewProblem(String Name, int N_mov, String Theme, boolean atk, boolean first_turn){
        String fenCode = B.toFEN();
        P = new Problem(fenCode, Name, N_mov, Theme, atk, first_turn);
        if(P.verify())  CD.storeProblem( P.getProblemInfo() );
        else throw new chessException("Problem has no solution");
    }
    
    /**
     *
     * @param Name
     * @param Password
     * @throws chessException
     */
    public void createNewPlayer(String Name, String Password) throws chessException{
        if(!CD.checkUser(Name)){
            Pl = new Human(Name, Password);
            CD.storeUser(Pl.getHumanInfo());
        }
        else throw new chessException("Username is in use");
    }
}
