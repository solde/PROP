package capaDomini;

import Exception.chessException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Daniel Palomo
 */
public class Human extends Player {

    //Basicament tot lo relacionat amb password es temporal 
    //private
    private String password;
    //Funció temporal per la primera entrega 

    /**
     * basic constructor without parametres
     */
    public Human() {
        this.id = null;
        this.wins = 0;
        this.loses = 0;
        this.ELO = 1000;
        this.OP_rating = 0;

        password = null;
    }

    /**
     * constructor with parametres
     *
     * @param id
     * @param pass
     * @param wins
     * @param loses
     * @param ELO
     * @param OP_rating
     */
    public Human(String id, String pass, int wins, int loses, double ELO, double OP_rating) {
        this.id = id;
        this.wins = wins;
        this.loses = loses;
        this.ELO = ELO;
        this.OP_rating = OP_rating;
        this.password = pass;
    }

    /**
     * constructor with parametres
     *
     * @param id
     * @param wins
     * @param loses
     * @param ELO
     * @param OP_rating
     */
    public Human(String id, int wins, int loses, double ELO, double OP_rating) {
        this.id = id;
        this.wins = wins;
        this.loses = loses;
        this.ELO = ELO;
        this.OP_rating = OP_rating;
    }

    /**
     * constructor with parametres
     *
     * @param id
     * @param pass
     */
    public Human(String id, String pass) {

        this.id = id;
        this.wins = 0;
        this.loses = 0;
        this.ELO = 1000;
        this.OP_rating = 0;
        this.password = pass;
    }

    /**
     * Changes the password in human
     *
     * @param password
     */
    public void setPassword(String password) {
        //Funció del controlador de ficheros (2 entrega)
        this.password = password;
    }

    /**
     * returns the password in human
     *
     * @return password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    Board makeMove(Board b, boolean color, int depth) throws Exception {
        throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
