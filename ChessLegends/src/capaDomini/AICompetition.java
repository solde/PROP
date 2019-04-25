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
public class AICompetition extends GameAbs{
    private int N;
    private int WhiteWins;
    private int BlackWins;
    
    public AICompetition(){
        WhiteWins = 0;
        BlackWins = 0;
    }

    public AICompetition(int N) {
        this.N = N;
        BlackWins = 0;
        WhiteWins = 0;        
    }
    
    public void setN(int N){
        this.N = N;
    }
    
    public int getN(){
        return N;
    }
    
    public void resetWinCounters(){
        WhiteWins = 0;
        BlackWins = 0;
    }
    
    public int getWhiteWins(){
        return this.WhiteWins;
    }
    
    public int getBlackWins(){
        return this.BlackWins;
    }
    
    /**
     *
     * @return true if the winner is the white player, fals if the winner is the black player
     * @throws chessException
     */
    @Override
    public void playMatch() throws chessException{
        for(int i = 0; i < this.N; ++i){
            int turn_cont = 0;
            while(turn_cont < this.P.getN_mov()){
                B = AI1.makeMove(B, turn, 3);
                B = AI1.makeMove(B, !turn, 3);
                turn_cont += 1;
            }
            if(B.isCheckMate(true)){
                WhiteWins += 1;
            }
            else if(B.isCheckMate(false)){
                WhiteWins += 1;
            }
            else{
                if(P.getATK()) BlackWins += 1;
                else WhiteWins += 1;
            }
        }
    }

    @Override
    public void movePiece(int sX, int sY, int dX, int dY, boolean color, long time) throws chessException {
        throw new UnsupportedOperationException("You are a mere spectator");
    }

    @Override
    public void resetTimers() {
        throw new UnsupportedOperationException("AI"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<int[]> possibleMovements(boolean color) {
        throw new UnsupportedOperationException("You are a mere spectator"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
