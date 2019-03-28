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
    private player* wPlayer;
    private player* bPlayer;
    
    void Game(boolen initialTurn, player* wPlayer, player* bPlayer){
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
            
}
