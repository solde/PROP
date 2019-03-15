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
    private String password; //temp

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
    @Override
    public void play_turn(){
        
    }

    //public constructors & get/set
    
    public Human() { //basic constructor
        super();
        password = null;
    }

    public Human(String id, String pass, int wins, int loses, float ELO) {  //constructor amb parametres
        super(id, wins, loses, ELO);
        setPassword(pass);

    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

}
