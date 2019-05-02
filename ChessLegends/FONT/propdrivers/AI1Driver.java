/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import Exception.chessException;
import capaDomini.AI1;
import capaDomini.Board;
import java.util.Scanner;

/**
 *
 * @author Arnau Santos
 */
public class AI1Driver {
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
    public static void main(String[] args) throws chessException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a fern code");
        String FENi = sc.next(); 
        for(int x = 0; x < 5; ++x){
            String s = sc.next();
        }
        System.out.println(FENi);
        Board b = new Board(FENi);
        printBoard(b);


        b = new Board(AI1.makeMove(b, true, 3), false); //1: Board, 2: fitxa, 3: depth
        printBoard(b);
        b = new Board(AI1.makeMove(b, false, 3), false);
        printBoard(b);
        b = new Board(AI1.makeMove(b, true, 3), false);
        printBoard(b);
        String FENf = b.fenToString();
        System.out.println(FENf);
        if (FENi != FENf) System.out.println("Ha mogut.");
    }
}

