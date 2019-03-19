/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDgit omini;

/**
 *
 * @author Daniel Palomo
 */
public class Human extends AI1 {

    @Override
    public void play_turn() {
         System.out.println("Només per testejar");
        calculate_mov();
        
    }

    //public constructors & get/set
    public AI1() { //basic constructor
        loader l();
        T AI_info = l.get_player_info(AI1);
        this.id = AI_info.id;
        this.wins = AI_info.wins;
        this.loses = AI_info.loses;
        this.ELO = AI_info.ELO;
        this.OP_rating = AI_info.OP_rating;
    }

    private calculate_mov(){
        System.out.println("Calculant el moviment");
    }

}
