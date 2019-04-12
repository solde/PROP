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
public class CtrlDomainGame {
    private Game G;
    private Board B;
    private Problem P;
        
    public CtrlDomainGame() {
        G = new Game();
        B = new Board();
        P = new Problem();
    }
    
    public void loadProblemTest(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn){
        //(fenCode, Name, diff, N_mov, Theme, atk)
        P = new Problem(fenCode, Name, diff, N_mov, Theme, atk, first_turn);
        B.setFEN_code( P.getFenCode() );
        initGame();
    }
    
    public void authPlayer1Test(String playerId, String playerPassword, int wins, int loses, int ELO, int OP_rating){
        if(playerId.equals("AI1")){
            G.setAi1AsPlayer(true);
        }
        else{
            G.setPlayer1(playerId, wins, loses, ELO, OP_rating);
        }
    }
    
    public void authPlayer2Test(String playerId, String playerPassword, int wins, int loses, int ELO, int OP_rating){
        if(playerId.equals("AI1")){
            G.setAi1AsPlayer(false);
        }
        else{
            G.setPlayer2(playerId, wins, loses, ELO, OP_rating);
        }
    }
    
    public int moveWhitePiece(int sX, int sY, int dX, int dY, long time){
        try{
            if(G.isTurn()){
                B.movePiece(sX, sY, dX, dY, true);
            }
            else return -1;
        }
        catch(chessException e){
            System.out.println("Can't move");
        }
        return 0;
    }
    
    public int moveBlackPiece(int sX, int sY, int dX, int dY, long time){
        try{
            if(G.isTurn()){
                B.movePiece(sX, sY, dX, dY, false);
            }
            else return -1;
        }
        catch(chessException e){
            System.out.println("Can't move");
        }
        return 0;
    }
    
    public void initGame(){
        G.setTurn(P.getFirstTurn());
        G.resetTimers();
    }
}
