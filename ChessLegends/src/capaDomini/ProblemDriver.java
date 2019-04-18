/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author David Soldevila
 */
public class ProblemDriver {
 public static void main(String[] args) throws InterruptedException, chessException {
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        Board B;
        Piece p;
        int i = sc.nextInt();
        while (i <= 11) { // ( o el que sea
            switch (i) {
                case 1: //

                    break;
                    
                case 2: //

                    break;

                case 3: //

                    break;       
                
                case 4: //
                    
                    break;       
                
                case 5: //

                    break;       
                
                case 6: //

                    break;
                
                case 7: //

                    break;
                
                case 8://

                    break;       
                
                case 9://

                    break;       
                
                case 10: //

                    break;       
                
                case 11: //

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
        System.out.println("Test 8: ");
        System.out.println("Test 9: ");
        System.out.println("Test 10: ");
        System.out.println("Test 11: ");

        System.out.println("Para salir presione 8");

        System.out.println("Inserte opcion: ");

    }
}
