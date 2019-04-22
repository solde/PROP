/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import Exception.chessException;
import capaDomini.AI1;
import capaDomini.Board;

/**
 *
 * @author Arnau Santos
 */
public class AI1Driver {
    //De moment només teniu aquest bonic missatge UwU
    public static void main(String[] args) throws chessException {
        String FENi = "1r6/5kp1/5p1p/p2b4/PP4P1/2B3K1/5PP1/1R6" ; //extret de Kasparov vs Carlsen, 2004
        System.out.print(FENi);
        Board b = new Board(FENi);


        AI1.makeMove(b, false, 1, 1); //b de board, false mouen les negres, la ia funcionarà amb profunditat 1 del minmax, un torn.
        //For some reason, this doesn't work

        String FENf = b.fenToString();
        System.out.print(FENf);
        if (FENi != FENf) System.out.println("Ha mogut.");
    }
}

