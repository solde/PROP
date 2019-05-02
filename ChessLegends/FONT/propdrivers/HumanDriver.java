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
import java.io.IOException;
import java.util.concurrent.TimeUnit;
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
    public static void main(String[] args) throws InterruptedException, IOException {
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        Human h = new Human("pepe", "loco");
        int i = sc.nextInt();
        while (i <= 7) { // ( o el que sea
            switch (i) {
                case 0:
                    Human p = new Human();
                    Human p2 = new Human("nom", "password");
                    Human p3 = new Human("nom", "pass", 2, 3, 1520, 8965);
                    System.out.println(p);
                    break;

                case 1: {
                    String pass = "yah";
                    System.out.println("Testing autenticate");
                    try {
                        System.out.println("Testing incorrect password");
                        h.autenticate(pass);
                    } catch (chessException e) {
                        String p4 = e.getMessage();
                        System.out.println(p4);

                    }
                    try {
                        pass = "";
                        System.out.println("Testing null password");
                        h.autenticate(pass);
                    } catch (chessException a) {
                        String o = a.getMessage();
                        System.out.println(o);

                    }
                    String r = "Passed";
                    System.out.println(r);
                    TimeUnit.SECONDS.sleep(2); //Para poder leer la salida mejor
                    display_menu();
                    break;
                }

                case 2:
                    System.out.println("Testing getPassword");
                    String w = h.getPassword();
                    if (!w.equals("loco")) {
                        System.out.println("Getter doesn't work");
                        break;
                    } else {
                        System.out.println("Passed");
                    }
                    TimeUnit.SECONDS.sleep(2);
                    display_menu();//Para poder leer la salida mejor
                    break;

                case 3: {
                    System.out.println("Testing setPassword");
                    h.setPassword("yaya");
                    String k = h.getPassword();
                    if (k.equals("loco")) {
                        System.out.println("Setter doesn't work");
                        break;
                    } else {
                        System.out.println("Passed");
                    }
                    TimeUnit.SECONDS.sleep(2);

                    display_menu();//Para poder leer la salida mejor
                    h.setPassword("loco"); // reinstaurar el estado anterior para multiples tests
                    break;
                }

                default:
                    break;

            }
            i = sc.nextInt();
        }  
    }

    public static void display_menu() {
        System.out.println("Option menu:");
        System.out.println("Test 0: Creadora ");
        System.out.println("Test 1: Authenticate  ");
        System.out.println("Test 2: getPassword   ");
        System.out.println("Test 3: setPassword  ");
        /*
          System.out.println("Test 5:   ");
          System.out.println("Test 6:   ");
          System.out.println("Test 7:   ");
          System.out.println("Test 8:   ");
         */
        System.out.println("For exit press 6");

        System.out.println("Insert option: ");

    }
}
