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
    private int turnNumber;
    private double timerW;
    private double timerB;
    private string wPlayer;
    private string bPlayer;
    private string prblemId;

    void Game(boolen initialTurn, string wPlayer, string bPlayer) {
        this.turn = initialTurn;
        this.wPlayer = wPlayer;
        this.bPlayer = bPlayer;
    }

    getStatistics(int player_id) {
        Statistics stats();
        if (wPlayer -> getId() == player_id) {
            stats.time = this.timerW;
            stats.OpRating = bPlayer -> getELO;
        }
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void setTimerW(double timerW) {
        this.timerW = timerW;
    }

    public void setTimerB(double timerB) {
        this.timerB = timerB;
    }

    public void setwPlayer(string wPlayer) {
        this.wPlayer = wPlayer;
    }

    public void setbPlayer(string bPlayer) {
        this.bPlayer = bPlayer;
    }

    public void setPrblemId(string prblemId) {
        this.prblemId = prblemId;
    }

    int gameLoop() {
        Board B();
        Problem P();
        PlayerController P();
        boolean finish;

        //Init problem and loads
        P.load(problemId);
        B.setBoard(P.getFEN());
        
        //init Timers
        timerW = 0;
        timerB = 0;
        
        //Game loop
        for (int i = 0; i < P(getMovs); ++i) {
            string toDo;
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
            finish = b.jaqueMate(isCheckMate);
            if (finish) {
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
            finish = B.jaqueMate(isCheckMate);
            if (finish) {
                break;
            }
        }
        if(finish){
            P.get
        }
        
    }

}
