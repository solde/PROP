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

    protected static final int DEFAULT_ELO = 1000;

    protected String id;
    protected int wins;
    protected int loses;
    protected float ELO;
    protected float OP_rating;



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

    public void incLoses() {
        this.loses = this.loses+1;
    }

    public void incWinds() {
        this.wins = this.wins+1;
    }

    public float getELO() {
        return ELO;
    }

    public void setELO(float ELO) {
        this.ELO = ELO;
    }
    public float getOP_rating() {
        return OP_rating;
    }

    public void setOP_rating(float OP_rating) {
        this.OP_rating = OP_rating;
    }

    public void incOP_rating(float OP_rating) {
        this.OP_rating = this.OP_rating + OP_rating;
    }
}