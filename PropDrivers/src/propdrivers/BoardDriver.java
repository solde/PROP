/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import capaDomini.Board;
import java.util.Scanner;

/**
 *
 * @author David Soldevila
 */
public class BoardDriver {
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        Board B;
        int i = sc.nextInt();
        while (i <= 7) { // ( o el que sea
            switch (i) {
                case 1:
                   Board B = new Board();
                    if(B.fenToString().equals("8/8/8/8/8/8/8/8")){
                        System.out.println("Board is created with no pieces");
                    }
                    else{
                        System.out.println("Board is created with pieces, the fen code of the actual board is:");
                        System.out.println(B.getFEN_code());
                    }
                    break;
                    
                case 2:
                    String newFen = sc.nextLine();
                    B = new Board(newFen);
                    if(B.getFEN_code().equals(newFen)){
                        System.out.println("Board is created with the ");
                    }
                    else{
                        System.out.println("Board is created with diferent piece distribution, the fen code of the acutal board is:");
                        System.out.println(B.getFEN_code());
                    }
                    break;

                 case 3:
                    B = new Board();
                    Board B2 = new Board(B);
                    
                    if(B.getFEN_code().equals(B2.getFEN_code())){
                        System.out.println("Boards are equal");
                    }
                    else{
                        System.out.println("Boards are different");
                    }
                    break;       
                
                 case 4:
                    String newFen = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B";
                    if(B.getFEN_code().equals(newFen)){
                        System.out.println("Board is created with the ");
                    }
                    else{
                        System.out.println("Board is created with diferent piece distribution, the fen code of the acutal board is:");
                        System.out.println(B.getFEN_code());
                    }                    
                    break;       
                
                 case 5:

                    break;       
                
                 case 6:

                    break;       
                
                 case 7:

                    break;       
                
                 case 8:

                    break;       
                
                 case 9:

                    break;       
                
                 case 10:

                    break;       
                
                 case 11:

                    break;
            }
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: create empty board    ");
        System.out.println("Test 2: create board with a fenCode   ");
        System.out.println("Test 3: create a copy of a board  ");
        System.out.println("Test 4: getFEN_code ");
        System.out.println("Test 5: getWhitePiecesOnBoard  ");
        System.out.println("Test 6: getBlackPiecesOnBoard  ");
        System.out.println("Test 7: getPieceAt  ");
        System.out.println("Test 8: movePiece   ");
        System.out.println("Test 9: setFEN_code   ");
        System.out.println("Test 10: addPieceToBoard   ");
        System.out.println("Test 11: fenToString   ");

        System.out.println("Para salir presione 8");

        System.out.println("Inserte opcion: ");

    }
}
