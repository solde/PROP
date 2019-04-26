/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import capaDomini.Game;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author David Soldevila
 */
public class GameDriver {
    public static void main(String[] args){
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        Game G;
        int i = sc.nextInt();
        while (i <= 9) {
            switch (i) {
                case 1: //Empty game
                    G = new Game();
                    System.out.println(G.isTurn());
                    break;
                    
                case 2: //Game with turn
                    System.out.println("Enter ''true'' for white or 'false'' for black");
                    boolean t = sc.nextBoolean();
                    G = new Game(t);
                    System.out.println(G.isTurn());
                    break;

                case 3: //set/isTurn
                    G = new Game();
                    System.out.println("Enter ''true'' for white or 'false'' for black");
                    boolean t2 = sc.nextBoolean();
                    G.setTurn(t2);
                    System.out.println(G.isTurn());
                    break;       
                
                case 4: //resetTimers/addTimerX/GetTimerX
                    G = new Game();
                    G.addTimeB(5);
                    G.addTimeW(5);
                    System.out.println(G.getTimerW());
                    System.out.println(G.getTimerB());
                    G.resetTimers();
                    System.out.println(G.getTimerW());
                    System.out.println(G.getTimerB());
                    break;       
                
                case 5: //setProblem
                    //System.out.println("Inset a problem description");
                    String problemInfo = "4k3/4B3/4K3/8/4N3/8/8/3q4 problem_test test 3 1 true true";
                    G = new Game();
                    //G.setProblem(problemInfo);
                    System.out.println(G.getProblemInfo());
                    break;       
                
                case 6: //setPlayers
                    //String playerId, int wins, int loses, int ELO, int OP_rating
                    G = new Game();
                    System.out.println("Enter player 1 anme");
                    String player1Id = sc.nextLine();
                    System.out.println("Enter number of wins for player 1");
                    int wins = sc.nextInt();
                    System.out.println("Enter number of loses for player 1");
                    int loses = sc.nextInt();
                    System.out.println("Enter ELO for player 1");
                    int ELO = sc.nextInt();
                    System.out.println("Enter OP rating for player 1 (OP rating is the sum of ELO of all oponents)");
                    int OP_rating = sc.nextInt();
                    G.setPlayer1(player1Id, wins, loses, ELO, OP_rating);
                    
                    System.out.println(G.getPlayer1Info());
                    
                    System.out.println("Enter player 2 anme");
                    player1Id = sc.nextLine();
                    System.out.println("Enter number of wins for player 2");
                    wins = sc.nextInt();
                    System.out.println("Enter number of loses for player 2");
                    loses = sc.nextInt();
                    System.out.println("Enter ELO for player 2");
                    ELO = sc.nextInt();
                    System.out.println("Enter OP rating for player 2 (OP rating is the sum of ELO of all oponents)");
                    OP_rating = sc.nextInt();
                    G.setPlayer2(player1Id, wins, loses, ELO, OP_rating);
                    
                    System.out.println(G.getPlayer2Info());
                    break;
                
                case 7: //reset/add/getTimerX
                    G = new Game();
                    System.out.println("Enter a tiem to add");
                    long time = sc.nextLong();
                    G.addTimeB(time);
                    G.addTimeW(time);
                    System.out.println(G.getTimerB() + " " + G.getTimerW());
                    G.resetTimers();
                    System.out.println(G.getTimerB() + " " + G.getTimerW());
                    break;
                
                case 8: //movePiece
                    System.out.println("Directly calls to board");
                    break;   
                    
                case 9: //possibleMovements
                    G = new Game();
                    System.out.print("Enter problem info");
                    String prob = sc.nextLine();
                    //G.setProblem(prob);
                    System.out.println("Enter your color (true for white, false for black)");
                    boolean c = sc.nextBoolean();
                    ArrayList<int[]> result = G.possibleMovements(c);
                    System.out.println(result);
                    break;   
            }
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: Empty game");
        System.out.println("Test 2: Game with turn");
        System.out.println("Test 3: set/isTurn");
        System.out.println("Test 4: resetTimers/addTimerX/GetTimerX");
        System.out.println("Test 5: setProblem");
        System.out.println("Test 6: setPlayers");
        System.out.println("Test 7: reset/add/getTimerX");
        System.out.println("Test 8: movePiece");
        System.out.println("Test 9: possibleMovements");

        System.out.println("Para salir presione 10");

        System.out.println("Inserte opcion: ");

    }
}
