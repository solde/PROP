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
public class CtrlDomainGame {
    private Game G;
    private Board B;
    private Problem P;
    
    public CtrlDomainGame() {
        G = new Game();
        B = new Board();
        P = new Problem();
    }
    
    public void loadProblemTest(String fenCode, String Name, int diff, String, N_mov, String Theme, int atk){
        //(fenCode, Name, diff, N_mov, Theme, atk)
        P = new Problem(fenCode, Name, diff, N_mvo, Theme, atk);
    }
    
    public void moveWhitePiece(int sX, int sY, int dX, int dY){
    }
}
