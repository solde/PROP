/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

;

import java.util.Scanner;
import Exception.chessException;
import capaDomini.King;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Daniel Palomo
 */


public class kingDriver {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        King h = new King();
        int i = sc.nextInt();
        while (i <= 7) { // ( o el que sea
            switch (i) {

                case 1: {
                    System.out.println("Provando getMax");
                    int p = h.getMax();
                    if (p!=2) {
                        System.out.println("getter no funciona");
                    } else {
                        System.out.println("passed");
                    }
                    TimeUnit.SECONDS.sleep(3);
                    display_menu();//Para poder leer la salida mejor
                }
                case 2: {
                    System.out.println("provando setMax");
                    h.setMax(7);
                    int k = h.getMax();
                    if (k!=7) {
                        System.out.println("Setter no funciona");
                    } else {
                        System.out.println("passed");
                    }
                    TimeUnit.SECONDS.sleep(3);

                    display_menu();//Para poder leer la salida mejor
                    h.setMax(2); // reinstaurar el estado anterior para multiples tests
                }

                default:
                    break;

            }
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: getMax  ");
        System.out.println("Test 2: setMax   ");
     //   System.out.println("Test 3: setPassword  ");
        /*System.out.println("Test 4:   ");
          System.out.println("Test 5:   ");
          System.out.println("Test 6:   ");
          System.out.println("Test 7:   ");
          System.out.println("Test 8:   ");
         */
        System.out.println("Para salir presione 8");

        System.out.println("Inserte opcion: ");

    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
