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
public class CtrlDomainCreator {
    private Board B;
    private Problem P;
        
    public CtrlDomainCreator() {
        B = new Board();
        P = new Problem();
        Pl = new Human();
    }
    
    public void createNewCustomBoard(){
        B = new Board();
    }

    public addPieceToBoard(int X, int Y, char Type){
        try{
            B.addPieceAt(X, Y, Type);
        }
        catch(Exception e){
            System.out.println("Cannot add piece");
        }
    }
    
    public createNewProblem(String Name int N_mov, String Theme, boolean atk, boolean first_turn){
        String fenCode = B.toFEN();
        P = new Problem(fenCode, Name, N_mov, Theme, atk, first_turn);
        if(P.verify())  CD.storeProblem( P.getProblemInfo() );
        else throw new chessException("Problem has no solution");
    }
    
    public createNewPlayer(String Name, String Password){
        if(!CD.checkUser(Name)){
            Pl = new Human(Name, Password);
            CD.storeUser(Pl.getHumanInfo());
        }
        else throw new chessEsception("Username is in use");
    }
}
