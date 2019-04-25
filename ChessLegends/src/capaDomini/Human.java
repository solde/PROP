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
    private String password; //TEMP, ja millorarem la seguritat
    //Funció temporal per la primera entrega 

    /**
     * Checks if a the string pass is equal to the password in player.
     *
     * @param pass
     * @return int
     * @throws chessException
     * @throws FileNotFoundException
     * @throws IOException
     */
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
    public String getPassword() {
        return this.password;
    }

}
