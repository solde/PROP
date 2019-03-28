/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

;
import capaDomini.Human;
import java.util.Scanner;
import Exception.chessException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Daniel Palomo
 */


public class HumanDriver {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        Human h = new Human("pepe", "loco");
        int i = sc.nextInt();
        while (i <= 7) { // ( o el que sea
            switch (i) {
                case 1: {
                    String pass = "yah";
                    System.out.println("Provando autenticate");
                    try {
                        System.out.println("Provando contraseña incorrecta");
                        h.autenticate(pass);
                    } catch (chessException e) {
                        String p = e.getMessage();
                        System.out.println(p);

                    }
                    try {
                        pass = "";
                        System.out.println("Provando contraseña vacia");
                        h.autenticate(pass);
                    } catch (chessException a) {
                        String p = a.getMessage();
                        System.out.println(p);
                    }
                    TimeUnit.SECONDS.sleep(3); //Para poder leer la salida mejor
                    display_menu();

                }
                break;
                case 2: {
                    System.out.println("Provando getPassword");
                    String p = h.getPassword();
                    if (!p.equals("loco")) {
                        System.out.println("getter no funciona");
                    } else {
                        System.out.println("passed");
                    }
                    TimeUnit.SECONDS.sleep(3);
                    display_menu();//Para poder leer la salida mejor
                }
                case 3: {
                    System.out.println("provando setPassword");
                    h.setPassword("yaya");
                    String k = h.getPassword();
                    if (k.equals("loco")) {
                        System.out.println("Setter no funciona");
                    } else {
                        System.out.println("passed");
                    }
                    TimeUnit.SECONDS.sleep(3);

                    display_menu();//Para poder leer la salida mejor
                    h.setPassword("loco"); // reinstaurar el estado anterior para multiples tests
                }

                default:
                    break;

            }
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: Authenticate  ");
        System.out.println("Test 2: getPassword   ");
        System.out.println("Test 3: setPassword  ");
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
