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
        
        System.out.println("Welcome. Please set");
        
        System.out.println("FEN code for board:");
        String FENc = sc.nextLine();
        
        System.out.println("Who attacks: (true/false)");
        Boolean attack = sc.nextBoolean();
        
        System.out.println("Who starts: (true/false)");
        Boolean start = sc.nextBoolean();
        
        System.out.println("N turns to checkmate:");
        int en = sc.nextInt();
        
        System.out.println("The theme:");
        String them = sc.nextLine();
        
        System.out.println("The name:");
        String na = sc.nextLine();
        
        System.out.println("The difficulty:");
        int dif = sc.nextInt();
        
        
        G.setProblem(FENc, na, en, dif ,them, attack, start);
        
        G.setN(5);
        
        G.playMatch();
        
        
        
    }
}
