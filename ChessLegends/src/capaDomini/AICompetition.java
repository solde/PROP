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
    
    public void setAI(boolean player1, boolean player2){
        if(!player1){
            P1 = new AI1(); 
        }
        else{
            P1 = new AI1();
            System.out.println("Not Implemented");
        }
        if(!player2){
            P2 = new AI1();
        }
        else{
            P2 = new AI1();
            System.out.println("Not implemented");
        }
    }
    
    public boolean gameLoop(){
        
    }
    
    public boolean playMatch() throws chessException{
        if(N == 0) throw new chessException("Competition is done");
        else{
            return false;
        }
    }
    
}
