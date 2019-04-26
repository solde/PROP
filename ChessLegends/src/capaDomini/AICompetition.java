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
    
    @Override
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
     * @return
     * @throws chessException
     */
    @Override
    public char[][] getBoard() throws chessException{
        throw new chessException("you cannot get a board from an AI competition");
    }
    
    /**
     *
     * @param color
     * @return true if the winner is the white player, fals if the winner is the black player
     * @throws chessException
     */
    @Override
    public void playMatch(Boolean color) throws chessException{
        for(int i = 0; i < this.N; ++i){
            int turn_cont = 0;
            while(turn_cont < this.P.getN_mov()){
                
                this.B = new Board(AI1.makeMove(this.B, turn, 2), false);
 
                
                this.B = new Board(AI1.makeMove(this.B, !turn, 2), false);
 
                
                turn_cont += 1;
                
            }
            if(B.isCheckMate(!P.getATK())){
                
                if (P.getATK()){
                    WhiteWins += 1;
                    System.out.println("WhiteWins");
                }
                else{
                    BlackWins += 1;
                    System.out.println("BlackWins");
                }
                
            }
            else{
                if(P.getATK()) {
                    BlackWins += 1;
                    System.out.println("BlackWins");
                }
                else {
                    WhiteWins += 1;
                    System.out.println("WhiteWins");
                }
            }
            
            resetBoard();
            int aux = i;
            ++aux;
            System.out.println("Gamesplayed: " + aux);
            
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
