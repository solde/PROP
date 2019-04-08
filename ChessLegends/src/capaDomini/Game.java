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
    private double timerW;
    private double timerB;
    private String wPlayer;
    private String bPlayer;
    private String prblemId;
    
    private Player P1;
    private Player P2;
    
    private Problem P;
    private Board B;

    void Game(boolean initialTurn, String wPlayer, String pswP1, String bPlayer, String pswP2) {
        this.turn = initialTurn;
        this.wPlayer = wPlayer;
        this.bPlayer = bPlayer;
        
        if(wPlayer.equals("AI1")){
            P1 = AI1();
        }
        else{
            P1 = Human();
            //P1.login(wPlayer, pswP1);
        }
        if(bPlayer.equals("AI1")){
            P2 = AI1();
        }
        else{
            P2 = Human();
            //P2.login(bPlayer, pswP2);
        }
        
        timerW = 0;
        timerB = 0;
    }
    
    void setProblem(String problemName){
        P.load(problemName);
    }
    
    public int gameLoop() { //let me execute pls thx u
        
        B.setFEN_code(P.getFenCode);
    
        boolean checkMate = false;

        //Init problem and loads
        P.load(problemId);
        
        //Game loop
        for (int i = 0; i < P.getMovs() ++i) {
            String toDo;
            if (turn) {
                long t0 = System.currentTimeMillis();
                toDo = P.playTurn(wPlayer);
                t0 = System.currentTimeMillis() - t0;
                timerW += t0;
            } else {
                long t0 = System.currentTimeMillis();
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
                long t0 = System.currentTimeMillis();
                toDo = P.playTurn(wPlayer);
                t0 = System.currentTimeMillis() - t0;
                timerW += t0;
            } else {
                long t0 = System.currentTimeMillis();
                toDo = P.playTurn(bPlayer);
                t0 = System.currentTimeMillis() - t0;
                timerB += t0;
            }
            B.move_piece(toDo);
            turn = !turn;
            checkMate = B.isCheckMate());
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

}
