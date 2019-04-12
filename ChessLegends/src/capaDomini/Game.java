/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

/**
 *
 * @author David Soldevila <3
 */
public class Game {

    private boolean turn;
    private long timerW;
    private long timerB;
    private String wPlayer;
    private String bPlayer;
    
    private Player P1;
    private Player P2;
    
    void Game(boolean initialTurn) {
        this.turn = initialTurn;
        
        timerW = 0;
        timerB = 0;
    }
    
    void Game(){
        timerW = 0;
        timerB = 0;
        turn = true;
    }

    public void resetTimers(){
        timerW = 0;
        timerB = 0;
    }
    
    public void addTimeW(long t){
        timerW += t;
    }
    
    public void addTimeB(long t){
        timerB += t;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isTurn() {
        return turn;
    }

    public double getTimerW() {
        return timerW;
    }

    public double getTimerB() {
        return timerB;
    }

    public String getwPlayer() {
        return wPlayer;
    }

    public String getbPlayer() {
        return bPlayer;
    }

    public void setTimerW(long timerW) {
        this.timerW = timerW;
    }

    public void setTimerB(long timerB) {
        this.timerB = timerB;
    }

    public void setwPlayer(String wPlayer) {
        this.wPlayer = wPlayer;
    }

    public void setbPlayer(String bPlayer) {
        this.bPlayer = bPlayer;
    }
    
    public void setPlayer1(String playerId, int wins, int loses, int ELO, int OP_rating){
        P1 = new Human(playerId, wins, loses, ELO, OP_rating);
    }
    
    public void setPlayer2(String playerId, int wins, int loses, int ELO, int OP_rating){
        P2 = new Human(playerId, wins, loses, ELO, OP_rating);
    }
    
    public void setAi1AsPlayer(boolean b){
        if(b){
            P1 = new AI1();
        }
        else{
            P2 = new AI1();
        }
    }
}
