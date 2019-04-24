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
public class AICompetition extends GameAbs{
    private int N;
    
    public AICompetition(){
        
    }

    public AICompetition(int N) {
        this.N = N;
    }
    
    public void setN(int N){
        this.N = N;
    }
    
    public int getN(){
        return N;
    }
    
    /**
     *
     * @return true if the winner is the white player, fals if the winner is the black player
     * @throws chessException
     */
    @Override
    public boolean playMatch() throws chessException{
        if(N == 0) throw new chessException("Competition is done");
        else{
            while(N > 0 && !B.isCheckMate(turn)){
                AI1.makeMove(B, turn, N, 3);
            }
        }
        if(B.isCheckMate(true)){}
        return true;
          
    }

    @Override
    public void movePiece(int sX, int sY, int dX, int dY, boolean color, long time) throws chessException {
        throw new UnsupportedOperationException("You are a mere spectator");
    }

    @Override
    public void resetTimers() {
        throw new UnsupportedOperationException("AI"); //To change body of generated methods, choose Tools | Templates.
    }
    
}
