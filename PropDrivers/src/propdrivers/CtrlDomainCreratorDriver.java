/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import capaDomini.CtrlDomainCreator;
import capaDomini.Problem;
import java.util.Scanner;

/**
 *
 * @author Daniel Palomo
 */
public class CtrlDomainCreratorDriver {

    public static void main(String[] args) throws InterruptedException/*, Exception.chessException */{
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        CtrlDomainCreator c;
        int i = sc.nextInt();
        while (i <= 7) {
            switch (i) {
                case 1:
                    c = new CtrlDomainCreator();
                    
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;

            }
        }
        i = sc.nextInt();
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: Controler Constuctor Creator");
        System.out.println("Test 2 :createNewCustomBoard() ");
        System.out.println("Test 3: modifyBoardCell");
        System.out.println("Test 4: createNewProblemTest");
        System.out.println("Test 5: createNewPlayer  ");
        System.out.println("Test 6: modifyProblemTest  ");
        System.out.println("Test 7: saveProblemAsCopy  ");/*
        System.out.println("Test 8: Get/set Name");
        System.out.println("Test 9: Get/set FirstTurn");
        System.out.println("Test 10: Get/set Theme");
        System.out.println("Test 11: Get/set Fen code");
        System.out.println("Test 12: Get/set N");
        System.out.println("Test 13: Get/set atk");
         */
        System.out.println("Para salir presione 8");

        System.out.println("Inserte opcion: ");

    }
}
