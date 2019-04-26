/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import Exception.chessException;
import capaDomini.AICompetition;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Arnau Santos
 */
public class AICompetitionDriver {

    /**
     * @param args the command line arguments
     * @throws Exception.chessException
     */
    public static void main(String[] args) throws chessException {
        
        Scanner sc = new Scanner(System.in);
        
        
        AICompetition G = new AICompetition();
        G.setPlayer1("IA1", 0, 0, 0, 0);
        G.setPlayer2("IA1", 0, 0, 0, 0);
        
        System.out.println("Please set");
     
            
        System.out.println("FEN code for board:");
        String FENc = sc.nextLine();

        System.out.println("The name:");
        String na = sc.nextLine();

        System.out.println("The difficulty:");
        int dif = sc.nextInt();

        System.out.println("N turns to checkmate:");
        int turns = sc.nextInt();

        //System.out.println("The theme:");
        //String them = sc.nextLine();

        System.out.println("Who attacks: (true/false)");
        Boolean attack = sc.nextBoolean();

        System.out.println("Games will play");
        int probnum = sc.nextInt();



        G.setProblem(FENc, na, dif, turns ,"test makes checkmate", attack, attack);
        G.setN(probnum);
        G.setTurn(attack);
        
        
        //G.setProblem("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B", "Test", 34, 2 ,"Blanques fan mat en 2", true, true);
        
       
        
        G.playMatch();
        
        
        
    }
}
