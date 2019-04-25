package capaDomini;

import Exception.chessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Daniel Palomo
 */
public class Human extends Player {

    //Basicament tot lo relacionat amb password es temporal 
    //private
    private String password; //TEMP, ja millorarem la seguritat
    private Map<String, Float> times;
    //public methods

    public Map<String, Float> getTimes() {
        return times;
    }

    public void setTimes(Map<String, Float> times) {
        this.times = times;
    }

    public void addTime(String prob, Float time) {
        times.put(prob, time);
    }

    //Funció temporal per la primera entrega 
    public int autenticate(String pass) throws chessException, FileNotFoundException, IOException {
        String truePass = this.getPassword();
        if (pass.isEmpty()) {
            throw new chessException("No password introduced");
        } else if (!pass.equals(truePass)) {
            throw new chessException("Bad password");
        }

        return 0;

    }
    //public constructors & get/set

    /**
     * basic constructor without parametres
     */
    public Human() {
        this.id = null;
        this.wins = 0;
        this.loses = 0;
        this.ELO = 1000;
        this.OP_rating = 0;
        lineID = 0;
        setPassword(null);

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
    public Human(String id, String pass, int wins, int loses, float ELO, float OP_rating) {
        this.id = id;
        this.wins = wins;
        this.loses = loses;
        this.ELO = ELO;
        this.OP_rating = OP_rating;
        lineID = 3; //SUPERTEMP
        setPassword(pass);
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
    public Human(String id, int wins, int loses, float ELO, float OP_rating) {

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
        setPassword(pass);
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
    private String getPassword() {
        return this.password;
    }

}
