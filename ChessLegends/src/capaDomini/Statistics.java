
package capaDomini;

import Exception.chessException;
//This class should manage the statistics from player and game, it may be usless when the controlers are implemented

public class Statistics {

    public double time;
    public boolean WL;
    public boolean OpRating;
    
    public Statistics(){}
    
    public void showStatsPlayer(Player p) throws chessException {
        double elo;
        try {
            elo = p.getELO();
            } catch (Exception ObjectNotFoundException) {
            throw new chessException("Player not found");
        }
            System.out.print("--");
            System.out.print(p.getId());
            System.out.println("--  \n");
            System.out.print("ELO:");
            System.out.println(elo);
            System.out.print("Rank: ");
            if (elo >= 1000.0 && elo <= 1599) {
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
  
            System.out.print("Wins: ");
            System.out.println(p.getWins());


        
    }
    
    public void showStatsGame(Game g){
        //to implement
        
    }

}
