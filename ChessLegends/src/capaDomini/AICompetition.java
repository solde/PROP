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
     * @return
     * @throws chessException
     */
    @Override
    public char[][] getBoard() throws chessException{
        throw new chessException("you cannot get a board from an AI competition");
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
                System.out.println(turn_cont);
                B = AI1.makeMove(B, turn, 2);
                System.out.println("move 1 done");                
                B = AI1.makeMove(B, !turn, 2);
                System.out.println("move 2 done");
                turn_cont += 1;
            }
            if(B.isCheckMate(true)){
                BlackWins += 1;
                System.out.println("BlackWins");
            }
            else if(B.isCheckMate(false)){
                WhiteWins += 1;
                System.out.println("WhiteWins");

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
            int aux = this.getN();
            aux+=1;
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
