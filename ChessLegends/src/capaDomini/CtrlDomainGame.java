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
public class CtrlDomainGame {
    private GameAbs G;
    private int movCounter;
        
    public CtrlDomainGame() {
        G = new Game();
        movCounter = 0;
    }

    public void loadProblemTest(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn){
        //(fenCode, Name, diff, N_mov, Theme, atk)
        G.setProblem(fenCode, Name, diff, N_mov, Theme, atk, first_turn);
    }
    public void authPlayer1Test(String playerId, String playerPassword, int wins, int loses, int ELO, int OP_rating){
        G.setPlayer1(playerId, wins, loses, ELO, OP_rating);
    }
    
    public void authPlayer2Test(String playerId, String playerPassword, int wins, int loses, int ELO, int OP_rating){
        G.setPlayer2(playerId, wins, loses, ELO, OP_rating);
    }
    
    /**
     * Funciton to move a piece, the move must be from result of getPossibleMovments
     * @param sX
     * @param sY
     * @param dX
     * @param dY
     * @param color
     * @param time
     */
    public void movePiece(int sX, int sY, int dX, int dY, boolean color ,long time){
        try{
            G.movePiece(sX, sY, dX, dY, color, time);
            G.setTurn(!color);
            this.movCounter += 1;
        }
        catch(chessException e){
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<int[]> getPossibleMovements(boolean color){
        return G.possibleMovements(color);
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
    
    public char[][] getBoardInfo() throws chessException{
        char[][] ret = G.getBoard();
        return G.getBoard();
    }
    
    public int leftTurns(){
        return G.leftTurn();
    }
}
