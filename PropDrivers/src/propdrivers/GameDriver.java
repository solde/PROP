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
                    
                    break;
                    
                case 2: // Problem(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn)

                    break;

                case 3: //Creator with string with DL info

                    break;       
                
                case 4: //verify

                    break;       
                
                case 5: //numberPiecesOf

                    break;       
                
                case 6: //calculateDiff/getDiff

                    break;
                
                case 7: //getProblemInfo

                    break;
                
                case 8: //Get/set Name

                    break;       
            }
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: Empty game");
        System.out.println("Test 2 :set/isTurn");
        System.out.println("Test 3: resetTimers/addTimerX/GetTimerX");
        System.out.println("Test 4: setProblem");
        System.out.println("Test 5: setPlayers");
        System.out.println("Test 6: reset/add/getTimerX");
        System.out.println("Test 7: movePiece");
        System.out.println("Test 8: possibleMovements");

        System.out.println("Para salir presione 14");

        System.out.println("Inserte opcion: ");

    }
}
