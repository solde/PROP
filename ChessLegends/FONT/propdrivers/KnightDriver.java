/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

;

import Exception.chessException;
import capaDomini.Board;
import java.util.Scanner;
import capaDomini.Knight;
import capaDomini.Piece;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javafx.util.Pair;
import capaDomini.Board;
import java.util.Scanner;
import capaDomini.Knight;
import capaDomini.Piece;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javafx.util.Pair;

/**
 *
 * @author Daniel Palomo
 */


public class KnightDriver {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, chessException {
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        Knight h = new Knight();
        int i = sc.nextInt();
        while (i <= 6) { // ( o el que sea
            switch (i) {

                case 1: {
                    System.out.println("Testing getMax");
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
                        System.out.println("Setter doesn't work");
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
                        System.out.println("Getter doesn't work");
                    } else {
                        System.out.println("Passed");
                    }
                    TimeUnit.SECONDS.sleep(3);

                    display_menu();
                    h.setMax(2); // reinstaurar el estado anterior para multiples tests
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
                    System.out.println("Piece on X:0 Y:7 (Empty Board)");
                    //Board b = new Board("7K/8/k1P5/7p/8/8/8/8");
                    Board b = new Board();
                    Piece p = new Knight(0, 7, true);
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
                    System.out.println("Piece on X:3 Y:4 (Empty Board)");
                    p = new Knight(3, 4, true);
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
                    System.out.println("Piece on X:3 Y:4  (Board not empty)");
                    b = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
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
