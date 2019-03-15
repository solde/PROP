/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

/**
 *
 * @author Daniel Palomo
 */
public abstract class Player {
    //private 

    private String id;
    private int wins;
    private int loses;
    private float ELO;
    private float OP_RATING;

    //public methods
    public abstract void play_turn();
    //public constructors & get/set

    public Player() {
        this.id = null;
        this.wins = 0;
        this.loses = 0;
        this.ELO = 0;
    }

    public Player(String id, int wins, int loses, float ELO) {
        this.id = id;
        this.wins = wins;
        this.loses = loses;
        this.ELO = ELO;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public float getELO() {
        return ELO;
    }

    public void setELO(float ELO) {
        this.ELO = ELO;
    }

    public void setOP_RATING(float OP_RATING){
        this.OP_RATING = OP_RATING;
    }

}
