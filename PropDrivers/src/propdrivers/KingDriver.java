/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

;

import capaDomini.Board;
import java.util.Scanner;
import capaDomini.King;
import capaDomini.Piece;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javafx.util.Pair;

/**
 *
 * @author Daniel Palomo
 */


public class KingDriver {

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
                    if (p != 2) {
                        System.out.println("Getter doesn't work");
                    } else {
                        System.out.println("Passed");
                    }
                    TimeUnit.SECONDS.sleep(3);
                    display_menu(); 
                    break;
                }
                case 2: {
                    System.out.println("Testing setMax");
                    h.setMax(7);
                    int k = h.getMax();
                    if (k != 7) {
                        System.out.println("Setter no funciona");
                    } else {
                        System.out.println("Passed");
                    }
                    TimeUnit.SECONDS.sleep(3);

                    display_menu(); 
                    h.setMax(2); // reinstaurar el estado anterior para multiples tests
                    break;
                }
                case 3: {
                    System.out.println("Testing getValue");
                    h.setValue(7);
                    double k = h.getValue();
                    if (k != 7) {
                        System.out.println("Getter no funciona");
                    } else {
                        System.out.println("Passed");
                    }
                    TimeUnit.SECONDS.sleep(3);

                    display_menu(); 
                    h.setValue(3); // reinstaurar el estado anterior para multiples tests
                    break;
                }
                case 4: {
                    System.out.println("Testing setValue");
                    h.setValue(7);
                    double k = h.getValue();
                    if (k != 7) {
                        System.out.println("Setter doesn't work");
                    } else {
                        System.out.println("Passed");
                    }
                    TimeUnit.SECONDS.sleep(3);

                    display_menu(); 
                    h.setValue(3); // reinstaurar el estado anterior para multiples tests
                    break;
                }

                case 5: {
                    System.out.println("Probando get_poss_mov");
                    System.out.println("Piece on X:0 Y:7:");
                    //Board b = new Board("7K/8/k1P5/7p/8/8/8/8");
                    Board b = new Board();
                    Piece p = new King(0, 7, true);
                    ArrayList<Pair<Integer, Integer>> mov;
                    mov = p.get_poss_mov(b);
                    if (mov.isEmpty()) {
                        System.out.println("There aren't any possible movements");
                        break;
                    }
                    for (Iterator<Pair<Integer, Integer>> it = mov.iterator(); it.hasNext();) {
                        Pair movl = it.next();
                        System.out.print("X: ");
                        System.out.print(movl.getKey());
                        System.out.print(" Y: ");
                        System.out.println(movl.getValue());
                    }
                    System.out.println("Piece on X:0 Y:4");
                    p = new King(0, 4, false);
                    mov = p.get_poss_mov(b);
                    if (mov.isEmpty()) {
                        System.out.println("There aren't any possible movements");
                        break;
                    }
                    for (Iterator<Pair<Integer, Integer>> it = mov.iterator(); it.hasNext();) {
                        Pair movl = it.next();
                        System.out.print("X: ");
                        System.out.print(movl.getKey());
                        System.out.print(" Y: ");
                        System.out.println(movl.getValue());
                    }
                                      System.out.println("Piece on X:3 Y:4 con board no vacio");
                     b = new Board("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R");
                     mov=p.get_poss_mov(b);
                      if (mov.isEmpty()) {
                        System.out.println("There aren't any possible movements");
                        break;
                    }
                    for (Iterator<Pair<Integer, Integer>> it = mov.iterator(); it.hasNext();) {
                        Pair movl = it.next();
                        System.out.print("X: ");
                        System.out.print(movl.getKey());
                        System.out.print(" Y: ");
                        System.out.println(movl.getValue());
                    }
                    TimeUnit.SECONDS.sleep(3);
                    display_menu(); 
                    break;
                }

                default:
                    break;

            }
            i = sc.nextInt();
        }
    }

   /**
     * This function shows the set of options avalible in this driver
     */
    public static void display_menu() {
        System.out.println("Option menu:");
        System.out.println("Test 1: getMax  ");
        System.out.println("Test 2: setMax   ");
        System.out.println("Test 3: getValue  ");
        System.out.println("Test 4: setValue ");
        System.out.println("Test 5: get_poss_mov  ");/*
          System.out.println("Test 6:   ");
          System.out.println("Test 7:   ");
          System.out.println("Test 8:   ");
         */
        System.out.println("For exit press 6");

        System.out.println("Insert option: ");

    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
