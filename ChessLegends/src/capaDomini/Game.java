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
    private boolean turn; //0 is black
    private int turnNumber;
    private double timerW;
    private double timerB;
    private string wPlayer;
    private string bPlayer;
    private string prblemId;
    
    void Game(boolen initialTurn, string wPlayer, string bPlayer){
        this.turn = initialTurn;
        this.wPlayer = wPlayer;
        this.bPlayer = bPlayer;
    }
    
    void initGame(){
        timerW = 0;
        timerB = 0;
        // ???
    }
    
    getStatistics(int player_id){
        Statistics stats();
        if(wPlayer->getId() == player_id){
            stats.time = this.timerW;
            stats.OpRating = bPlayer->getELO;
        }
    }
    
    int gameLoop(){
        Board B();
        Problem P();
        P.load(problemId);
        B.setBoard( P.getFEN() );
        for(int i = 0; i < P(getMovs; ++i){
            
        }
    }
            
}
