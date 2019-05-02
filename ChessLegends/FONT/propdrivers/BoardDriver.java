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
    
    public static void printBoard(Board B){
        System.out.println("   0  1  2  3  4  5  6  7");
        for(int x = 0; x < 8; ++x){
            System.out.print(x + "|");
            for(int y = 0; y < 8; ++y){
                if(B.getPieceAt(x, y).isColor() && B.getPieceAt(x, y).getTypeOfPiece() != -1)System.out.print(" " + B.getPieceAt(x, y).getTypeOfPiece() + "|");
                else if(!B.getPieceAt(x, y).isColor() && B.getPieceAt(x, y).getTypeOfPiece() != -1)System.out.print("-" + B.getPieceAt(x, y).getTypeOfPiece() + "|");
                else System.out.print("  " + "|");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    
    public static void main(String[] args) throws InterruptedException, chessException {
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        Board B = new Board();
        Piece p;
        int i = sc.nextInt();
        while (i <= 12) { // ( o el que sea
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
                    String newFen = sc.next();
                    for(int x = 0; x < 5; ++x){
                        String s = sc.next();
                    }
                    B = new Board(newFen);
                    System.out.println(newFen);
                    printBoard(B);
                    break;

                case 3:
                    String newFen3 = sc.next();
                    for(int x = 0; x < 5; ++x){
                        String s = sc.next();
                    }
                    B = new Board(newFen3);
                    printBoard(B);
                    Board B2 = new Board(B, false);
                    
                    printBoard(B2);
                    break;
                
                case 4:
                    System.out.println("Enter fen code");
                    String Fen_test = sc.next();
                    for(int x = 0; x < 5; ++x){
                        String s = sc.next();
                    };
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
                    System.out.println("Enter pieces with possition and type");
                    System.out.println("0->King, 1->Pawn, 3->Bishop, 4->Knight, 5->Rook, 7->Queen");
                    int x = 0, y = 0, t = 0;
                    boolean color = true;
                    while(t != -1){
                        System.out.println("X:");
                        x = sc.nextInt();
                        System.out.println("Y:");
                        y = sc.nextInt();
                        System.out.println("Type:");
                        t = sc.nextInt();
                        System.out.println("color");
                        color = sc.nextBoolean();
                        B.addPieceToBoard(x, y, t, color);
                        if(t == -1) break;
                    }
                    
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
                    System.out.println("Enter pieces with possition and type");
                    System.out.println("0->King, 1->Pawn, 2-> ");
                    int X = 0, Y = 0, T = 0;
                    boolean Color = true;
                    while(T != -1){
                        System.out.println("X:");
                        X = sc.nextInt();
                        System.out.println("Y:");
                        Y = sc.nextInt();
                        System.out.println("Type:");
                        T = sc.nextInt();
                        System.out.println("color");
                        Color = sc.nextBoolean();
                        B.addPieceToBoard(X, Y, T, Color);
                        if(T == -1) break;
                    }
                    
                    ArrayList<Piece> BPOB;
                    BPOB = B.getBlackPiecesOnBoard();  
                    for(int j = 0; j < BPOB.size(); ++j){
                        System.out.println("You have a piece at:");
                        System.out.println(Integer.toString(BPOB.get(j).getX()));
                        System.out.println(Integer.toString(BPOB.get(j).getY()));
                    };
                
                case 7: //getPieceAt
                    B = new Board();
                    int sx = sc.nextInt();
                    int sy = sc.nextInt();
                    int st = sc.nextInt();
                    boolean scolor = sc.nextBoolean();
                    B.addPieceToBoard(sx, sy, st, scolor);
                    printBoard(B);
                    break;
                
                case 8://movePiece
                    B = new Board();
                    System.out.println("Original Board");
                    B.addPieceToBoard(1, 5, 0, true);
                    printBoard(B);
                    System.out.print(B.fenToString());
                    System.out.println("Moved board");
                    B.movePiece(1, 5, 2, 5, true);
                    printBoard(B);
                    System.out.print(B.fenToString());
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
                
                case 11: //fenToString
                    String fen_to_compare;
                    fen_to_compare = sc.nextLine();
                    B = new Board(fen_to_compare);
                    String ret_fen = null;
                    ret_fen = B.fenToString();
                    System.out.println(fen_to_compare);
                    System.out.println(ret_fen);
                    break;
                    
                case 10:
                    String mate_fen = "7k/R7/6Q1/8/8/8/8/3K4";
                    B = new Board(mate_fen);
                    printBoard(B);
                    if(B.isCheckMate(true)) System.out.println("C'murio");
                    else System.out.println("Es dios y no muere");
                    break;
                    
                case 0:
                    printBoard(B);
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
        System.out.println("Test 12: isCheckMate   ");
        
        System.out.println("0-Debug: print actual board");

        System.out.println("Para salir presione 8");

        System.out.println("Inserte opcion: ");

    }
}
