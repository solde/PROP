/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import capaDomini.Human;
import capaDomini.Player;
import capaDomini.Statistics;
import java.util.Scanner;
import Exception.chessException;

/**
 *
 * @author Familia
 */
public class StatDriver {

    public static void main(String[] args) {

        Player h = new Human();
        Player h2 = new Human("joe", "pass", 90, 3, 2540, 1000);
        Statistics s = new Statistics();
        Scanner sc = new Scanner(System.in);
        display_menu();
        int i = sc.nextInt();
        while (i <= 7) { // ( o el que sea
            switch (i) {
                case 1: {
                    try {
                        s.showStatsPlayer(h);
                    } catch (chessException k) {
                        String p = k.getMessage();
                        System.out.println(p);

                    }
                    display_menu();
                    break;
                }
                case 2: {
                    try {
                        s.showStatsPlayer(h2);
                    } catch (chessException k) {
                        String p = k.getMessage();
                        System.out.println(p);

                    }
                    display_menu();
                    break;
                }
                default:
                    break;

            }
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 0: Creadora (Automatico) ");
        System.out.println("Test 1: Show Human stats (Default human)  ");
        System.out.println("Test 2: Show Human stats (Joe,2540 elo, wins 90, loses 3   ");
        // System.out.println("Test 3: setPassword  ");
        /*
          System.out.println("Test 5:   ");
          System.out.println("Test 6:   ");
          System.out.println("Test 7:   ");
          System.out.println("Test 8:   ");
         */
        System.out.println("Para salir presione 8");
        System.out.println("Inserte opcion: ");

    }
}
