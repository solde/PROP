/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import Exception.chessException;
import java.util.Scanner;
import capaDomini.Bishop;
import capaDomini.Board;
import capaDomini.Piece;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javafx.util.Pair;

/**
 *
 * @author Daniel Palomo
 */
public class BishopDriver {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, chessException {
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        Bishop h = new Bishop();
        int i = sc.nextInt();
        while (i <= 6) {
            switch (i) {
                case 1:
                    System.out.println("Testing getMax");
                    int p = h.getMax();
                    if (p != 2) {
                        System.out.println("getter doesen't work");
                    } else {
                        System.out.println("Passed");
                    }
                    TimeUnit.SECONDS.sleep(2);
                    display_menu();
                    break;

                case 2:
                    System.out.println("Testing setMax");
                    h.setMax(7);
                    int k = h.getMax();
                    if (k != 7) {
                        System.out.println("Setter doesen`t work");
                    } else {
                        System.out.println("Passed");
                    }
                    TimeUnit.SECONDS.sleep(2);
                    display_menu();
                    h.setMax(2); // reinstaurar el estado anterior para multiples tests
                    break;
                     
                    
                    
                case 3:
                    System.out.println("testnig getValue");
                    int value;
                    value = h.getValue();
                    if (value == 7) {
                        System.out.println("Passed");
                    } else {
                        System.out.println("Getter doesen't work");
                    }
                    TimeUnit.SECONDS.sleep(2);
                    display_menu();
                    h.setMax(2); // reinstaurar el estado anterior para multiples tests
                    break;
                    

                case 4:
                    System.out.println("Testing get_poss_mov...");
                    System.out.println("Piece en X:0 Y:7 (Empty Board)");
                    Board b = new Board();
                    Piece p1 = new Bishop(0, 7, true);
                    ArrayList<Pair<Integer, Integer>> mov;
                    mov = p1.get_poss_mov(b);
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
                    p1 = new Bishop(3, 4, true);
                    mov = p1.get_poss_mov(b);
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
                    System.out.println("Piece on X:3 Y:4 (Board not Empty)");
                    b = new Board("7K/PPPPKKKK/k1P5/7p/8/8/kkppkkpp/8");
                    mov = p1.get_poss_mov(b);
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
                    TimeUnit.SECONDS.sleep(2);
                    display_menu();
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
        System.out.println("Test 4: get_poss_mov  ");/*
          System.out.println("Test 6:   ");
          System.out.println("Test 7:   ");
          System.out.println("Test 8:   ");
         */
        System.out.println("For exit press 6");

        System.out.println("Insert option: ");

    }
}
