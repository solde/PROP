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
        while (i <= 13) {
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
                
                case 9: //Get/set FirstTurn

                    break;
                
                case 10: //Get/set Theme

                    break;       
                
                case 11: //Get/set Fen code

                    break;      
                
                case 12: //Get/set N

                    break;      
                
                case 13: //Get/set atk

                    break;


            }
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: Problem Constuctor Creator");
        System.out.println("Test 2 :Problem Constuctor Loader Test");
        System.out.println("Test 3: Problem Constuctor Loader String Info");
        System.out.println("Test 4: verify ");
        System.out.println("Test 5: numberPiecesOf  ");
        System.out.println("Test 6: calculateDiff/getDiff  ");
        System.out.println("Test 7: getProblemInfo  ");
        System.out.println("Test 8: Get/set Name");
        System.out.println("Test 9: Get/set FirstTurn");
        System.out.println("Test 10: Get/set Theme");
        System.out.println("Test 11: Get/set Fen code");
        System.out.println("Test 12: Get/set N");
        System.out.println("Test 13: Get/set atk");

        System.out.println("Para salir presione 14");

        System.out.println("Inserte opcion: ");

    }
}
