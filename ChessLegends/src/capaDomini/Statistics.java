/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;
//This class should manage the statistics from player and game, it may be usless when the controlers are implemented

/**
 *
 * @author David Soldevila
 */
public class Statistics {

    public double time;
    public boolean WL;
    public boolean OpRating;

    public void showStatsPlayer(Player p) throws chessException {
        try {
            float elo = p.getELO();
            System.out.println("--");
            System.out.println(p.getId());
            System.out.println("--  \n");
            System.out.println("ELO:");
            System.out.println(elo);
            System.out.println(" Rank: ");
            if (elo > 1000.0 && elo <= 1599) {
                System.out.println("Noob");
            }
            if (elo >= 1600 && elo <= 2000) {
                System.out.println("Experimented");
            }
            if (elo >= 2001 && elo <= 2300) {
                System.out.println("Hardo-bolido");
            }
            if (elo >= 2301 && elo <= 2700) {
                System.out.println("Master Mutenroshi");
            }
            if (elo >= 2700 && elo <= 3000) {
                System.out.println("Poke Super Mega Hyper Master League Digimon Champion");
            }
            if (elo >= 3000) {
                System.out.println("Cheater, please report");
            }
            System.out.println("\n");
            System.out.println("Wins: ");
            System.out.println(p.getWins());
            System.out.println("\n");
            System.out.println("--");
            System.out.println("--");
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("\n");

        } catch (Exception IllegalArgumentException) {
            throw new chessException("Player not found");
        }
    }
    
    public void showStatsGame(Game g){
        
        
    }

}
