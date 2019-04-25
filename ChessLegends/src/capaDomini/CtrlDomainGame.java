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
    private GameAbs G;
        
    public CtrlDomainGame() {
        G = new Game();
    }
    
    public CtrlDomainGame(int N){
        G = new AICompetition(N);
    }
    
    public void loadProblemTest(String problemInfo){
        //(fenCode, Name, diff, N_mov, Theme, atk)
        G.setProblem(problemInfo);
    }
    
    public void authPlayer1Test(String playerId, String playerPassword, int wins, int loses, int ELO, int OP_rating){
        G.setPlayer1(playerId, wins, loses, ELO, OP_rating);
    }
    
    public void authPlayer2Test(String playerId, String playerPassword, int wins, int loses, int ELO, int OP_rating){
        G.setPlayer2(playerId, wins, loses, ELO, OP_rating);
    }
    
    /**
     *
     * @param sX
     * @param sY
     * @param dX
     * @param dY
     * @param time
     * @return
     */
    public int moveWhitePiece(int sX, int sY, int dX, int dY, long time){
        try{
            if(G.isTurn()){
                G.movePiece(sX, sY, dX, dY, true, time);
            }
            else return -1;
        }
        catch(chessException e){
            System.out.println("Can't move");
        }
        return 0;
    }
    
    /**
     *
     * @param sX
     * @param sY
     * @param dX
     * @param dY
     * @param time
     * @return
     */
    public int moveBlackPiece(int sX, int sY, int dX, int dY, long time){
        try{
            G.movePiece(sX, sY, dX, dY, false, time);
        }
        catch(chessException e){
            System.out.println("Can't move");
        }
        return 0;
    }

    public GameAbs getG() {
        return G;
    }

    public void setG(GameAbs G) {
        this.G = G;
    }
    
    
    public void initGame(){
        G = new Game();
        G.resetTimers();
    }
    
    public void initCompetition(boolean player1, boolean player2){
        G.setPlayer1("AI1", 0, 0, 1000, 1000);
        G.setPlayer2("AI1", 0, 0, 1000, 1000);
        //Fer coses d'estadistiques
    }
   
}
