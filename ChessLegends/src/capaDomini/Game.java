/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;

/**
 *
 * @author David Soldevila <3
 */
public class Game extends GameAbs {
    private long timerW;
    private long timerB;
    private String wPlayer;
    private String bPlayer;
    

    
    void Game(boolean initialTurn) {
        turn = initialTurn;
        
        timerW = 0;
        timerB = 0;
    }
    
    void Game(){
        timerW = 0;
        timerB = 0;
        turn = true;
    }

    /**
     *
     */
    @Override
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
    

    
    /**
     *
     * @param sX
     * @param sY
     * @param dX
     * @param dY
     * @param color
     * @param time
     * @throws chessException
     */
    @Override
    public void movePiece(int sX, int sY, int dX, int dY, boolean color, long time) throws chessException{
        if(color != this.turn){
            throw new chessException("You are not the owner of the piece");
        }
        else{
            try{
                B.movePiece(sX, sY, dX, dY, color);
            }
            catch (chessException e){
                throw new chessException("Can't move");
            }
        }
    }    

    @Override
    public boolean playMatch() throws chessException {
        throw new UnsupportedOperationException("You have to play, slacker"); //To change body of generated methods, choose Tools | Templates.
    }
}
