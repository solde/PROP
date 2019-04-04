/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

/**
 *
 * @author David Soldevila
 */
public class Game {

    private boolean turn;
    private double timerW;
    private double timerB;
    private String wPlayer;
    private String bPlayer;
    private String prblemId;

    void Game(boolen initialTurn, String wPlayer, String bPlayer) {
        this.turn = initialTurn;
        this.wPlayer = wPlayer;
        this.bPlayer = bPlayer;
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

    public String getPrblemId() {
        return prblemId;
    }

    public void setTimerW(double timerW) {
        this.timerW = timerW;
    }

    public void setTimerB(double timerB) {
        this.timerB = timerB;
    }

    public void setwPlayer(String wPlayer) {
        this.wPlayer = wPlayer;
    }

    public void setbPlayer(String bPlayer) {
        this.bPlayer = bPlayer;
    }

    public void setPrblemId(String prblemId) {
        this.prblemId = prblemId;
    }

    public int gameLoop() {
        Board B();
        Problem P();
        PlayerController P();
        boolean checkMate;

        //Init problem and loads
        P.load(problemId);
        B.setBoard(P.getFEN());
        
        //init Timers
        timerW = 0;
        timerB = 0;
        
        //Game loop
        for (int i = 0; i < P(getMovs); ++i) {
            String toDo;
            if (turn) {
                int t0 = System.currentTimeMillis();
                toDo = P.playTurn(wPlayer);
                t0 = System.currentTimeMillis() - t0;
                timerW += t0;
            } else {
                int t0 = System.currentTimeMillis();
                toDo = P.playTurn(bPlayer);
                t0 = System.currentTimeMillis() - t0;
                timerB += t0;
            }
            B.move_piece(toDo);
            turn = !turn;
            checkMate = b.jaqueMate(isCheckMate);
            if (checkMate) {
                break;
            }

            if (turn) {
                int t0 = System.currentTimeMillis();
                toDo = P.playTurn(wPlayer);
                t0 = System.currentTimeMillis() - t0;
                timerW += t0;
            } else {
                int t0 = System.currentTimeMillis();
                toDo = P.playTurn(bPlayer);
                t0 = System.currentTimeMillis() - t0;
                timerB += t0;
            }
            B.move_piece(toDo);
            turn = !turn;
            checkMate = B.jaqueMate(isCheckMate);
            if (checkMate) {
                break;
            }
        }
        
        //Update players stats
        boolean atk P.getAtk();
        boolean def P.getDef();
        if((atk and checkMate) or (def and !checkMate)){
            P.updateELO(this.wPlayer, this.timerW, this.bPlayer, this.timerB);
        }
        else{
            P.updateELO(this.bPlayer, this.timerB, this.wPlayer, this.timerW);
        }
    }

}
