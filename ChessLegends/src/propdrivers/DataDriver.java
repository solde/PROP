/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import Exception.chessException;
import capaDades.CtrlDades;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author David Soldevila
 */
public class DataDriver {
    private void checkPlayerInfo(String info) throws IOException, FileNotFoundException, chessException{
        display_menu();
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        CtrlDades CD = new CtrlDades();
        while (i <= 12) { // ( o el que sea
            switch (i) {          
                case 1:
                    String un1 = sc.next();
                    String psw1 = sc.next();
                    System.out.println(CD.getPlayer(un1, psw1));
                    break;
                case 3:
                    String un2 = sc.next();
                    String psw2 = sc.next();
                    CD.createPlayer(un2, psw2);
                    break;
                case 4:
                    String us3 = sc.next();
                    String psw3 = sc.next();
                    String npsw3 = sc.next();
                    CD.updatePassword(us3, psw3, npsw3);
            }
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: getPlayer");
        //System.out.println("Test 2: checkPlayerInfo");
        System.out.println("Test 3: createPlayer");
        System.out.println("Test 4: updatePassword");
        System.out.println("Test 5: deletePlayer");
        System.out.println("Test 6: updatePlayer");
        System.out.println("Test 7: getProblem");
        System.out.println("Test 8: createProblem");
        System.out.println("Test 9: eraseProblem");
        System.out.println("Test 10: getStatistics");
        System.out.println("Test 11: eraseStatistics");
        System.out.println("Test 12: addRank");
        System.out.println("Para salir presione 13");

        System.out.println("Inserte opcion: ");

    }
}
