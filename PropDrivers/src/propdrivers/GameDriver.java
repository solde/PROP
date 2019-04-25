/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import capaDomini.Game;
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
        while (i <= 8) {
            switch (i) {
                case 1: // Problem()
                    G = new Game();
                    System.out.println(G.isTurn());
                    break;
                    
                case 2: // Problem(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn)
                    System.out.println("Enter ''true'' for white or 'false'' for black");
                    boolean t = sc.nextBoolean();
                    G = new Game(t);
                    System.out.println(G.isTurn());
                    break;

                case 3: //Creator with string with DL info
                    G = new Game();
                    boolean t2 = sc.nextBoolean();
                    G.setTurn(t2);
                    System.out.println(G.isTurn());
                    break;       
                
                case 4: //verify
                    G = new Game();
                    G.addTimeB(5);
                    G.addTimeW(5);
                    System.out.println(G.getTimerW());
                    System.out.println(G.getTimerB());
                    G.resetTimers();
                    System.out.println(G.getTimerW());
                    System.out.println(G.getTimerB());
                    break;       
                
                case 5: //numberPiecesOf
                    G = new Game();
                    G.setProblem(fenCode, Name, i, i, Theme, t2, t2);
                    break;       
                
                case 6: //calculateDiff/getDiff

                    break;
                
                case 7: //getProblemInfo

                    break;
                
                case 8: //Get/set Name

                    break;   
                    
                case 9: //Get/set Name

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
