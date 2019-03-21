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
public class Human extends Player {

    //private
    private String password; //TEMP, ja millorarem la seguritat

    //public methods
    public int autenticate(String pass) {

        if (!pass.equals(this.password)) {
            return 1;
        } else if (pass.isEmpty()) {
            return 2;
        }

        //Es poden posar mes
        return 0;

    }


    //public constructors & get/set
    public Human() { //basic constructor
        this.id = null;
        this.wins = 0; 
        this.loses = 0;
        this.ELO = 1000;
        this.OP_rating=0;
        setPassword(null);

        password = null;
    }

    public Human(String id, String pass, int wins, int loses, float ELO, float OP_rating) {  //constructor amb parametres
        //Create a human player by entering the stats
        this.id = id;
        this.wins = wins; 
        this.loses = loses;
        this.ELO = ELO;
        this.OP_rating= OP_rating;
        setPassword(pass);

    }
    public Human(String id,String pass){
        // Create a new human player
        this.id = id;
        this.wins = 0; 
        this.loses = 0;
        this.ELO = DEFAULT_ELO;
        this.OP_rating=0;
        setPassword(pass);        
        
    }
    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

}