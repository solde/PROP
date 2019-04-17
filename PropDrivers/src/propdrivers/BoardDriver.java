/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import Exception.chessException;
import capaDomini.Board;
import capaDomini.King;
import capaDomini.Piece;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author David Soldevila
 */
public class BoardDriver {
    public static void main(String[] args) throws InterruptedException, chessException {
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        Board B;
        Piece p;
        int i = sc.nextInt();
        while (i <= 11) { // ( o el que sea
            switch (i) {
                case 1:
                    B = new Board();
                    if(B.fenToString().equals("8/8/8/8/8/8/8/8")){
                        System.out.println("Board is created with no pieces");
                    }
                    else{
                        System.out.println("Board is created with pieces, the fen code of the actual board is:");
                        System.out.println(B.getFEN_code());
                    }
                    break;
                    
                case 2:
                    String newFen = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B";
                    B = new Board(newFen);
                    System.out.println(B.getFEN_code());
                    if(B.getFEN_code().equals(newFen)){
                        System.out.println("TEST 2: OK");
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
                    String Fen_test;
                    Fen_test = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B";
                    B = new Board(Fen_test);
                    if(B.getFEN_code().equals(Fen_test)){
                        System.out.println("Board is created with the ");
                    }
                    else{
                        System.out.println("Board is created with diferent piece distribution, the fen code of the acutal board is:");
                        System.out.println(B.getFEN_code());
                    }                    
                    break;       
                
                case 5: //getWhitePiecesOnBoard
                    B = new Board();
                    B.addPieceToBoard(0, 0, 0, true);
                    B.addPieceToBoard(4, 7, 1, true);
                    B.addPieceToBoard(2, 7, 1, true);
                    B.addPieceToBoard(2, 6, 1, false);
                    ArrayList<Piece> WPOB;
                    WPOB = B.getWhitePiecesOnBoard();  
                    for(int j = 0; j < WPOB.size(); ++j){
                        System.out.println("You have a piece at:");
                        System.out.println(Integer.toString(WPOB.get(j).getX()));
                        System.out.println(Integer.toString(WPOB.get(j).getY()));
                    }
                    break;       
                
                case 6: //getBlackPiecesOnBoard
                    B = new Board();
                    B.addPieceToBoard(0, 0, 0, false);
                    B.addPieceToBoard(4, 7, 1, false);
                    B.addPieceToBoard(2, 7, 1, false);
                    B.addPieceToBoard(2, 6, 1, true);
                    ArrayList<Piece> BPOB;
                    BPOB = B.getBlackPiecesOnBoard();  
                    for(int j = 0; j < BPOB.size(); ++j){
                        System.out.println("You have a piece at:");
                        System.out.println(Integer.toString(BPOB.get(j).getX()));
                        System.out.println(Integer.toString(BPOB.get(j).getY()));
                    }
                    break;
                
                case 7: //getPieceAt
                    B = new Board();
                    B.addPieceToBoard(5, 5, 0, true);
                    p = new King(5, 5, true);
                    System.out.println(B.getPieceAt(5, 5).getTypeOfPiece());
                    System.out.println(B.getPieceAt(5, 5).getX());
                    System.out.print(B.getPieceAt(5, 5).getY());
                    break;
                
                case 8://movePiece
                    B = new Board();
                    B.addPieceToBoard(0, 0, 0, true);
                    B.movePiece(0, 0, 1, 0, true);
                    System.out.println(B.getPieceAt(0, 0));
                    System.out.println(B.getPieceAt(1, 0));
                    break;       
                
                case 9://setFEN_code
                    B = new Board();
                    String new_fen = sc.nextLine();
                    B.setFEN_code(new_fen);
                    if(new_fen.equals(B.getFEN_code())){
                        System.out.println("TEST 9: OK");
                    }
                    else{
                        System.out.println("Tah pocho el setter");
                    }
                    break;       
                
                case 10: //addPieceToBoard
                    B = new Board();
                    B.addPieceToBoard(5, 5, 0, true);
                    p = new King(5, 5, true);
                    System.out.println(B.getPieceAt(5, 5));
                    break;       
                
                case 11: //fenToString
                    String fen_to_compare;
                    fen_to_compare = sc.nextLine();
                    B = new Board(fen_to_compare);
                    String ret_fen = null;
                    try{
                        ret_fen = B.fenToString();
                    }
                    catch(chessException e){
                        System.out.println(e.getMessage());
                    }
                    if(fen_to_compare != null && fen_to_compare.equals(ret_fen)){
                        System.out.println("Convertion to fenCode is correct");
                    }
                    else{
                        System.out.println("Convertion to fenCode is not correct");
                    }
                    break;


            }
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: create empty board    ");
        System.out.println("Test 2: create board with a fenCode  2  ");
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