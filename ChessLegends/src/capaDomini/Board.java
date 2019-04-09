/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import java.util.Vector;
import java.util.*; 

/**
 *
 * @author Arnau Santos
 */
public class Board {

    //private
    private String FEN_code;
    private String Default_FEN_code;
    
    private Vector<Piece> WhitePiecesOnBoard;
    private Vector<Piece> BlackPiecesOnBoard;

    //public methods
    public Vector<Vector> get_pos_piece() {
	Vector<Vector> temp = new Vector();	
            return temp;
    }
	
    /**
     * Function to move a piece. toDo is a string with two board cord. (ex: A3),
     * source and destination. If cord are in low case the piece to move is
     * black, otherwise it is a white piece.
     * @param toDo 
     */
    public void move_piece(String toDo) {
        String source = toDo.substring(0, 1);
        String destination = toDo.substring(2, 3);
        if( Character.isLowerCase(toDo.charAt(0)) ){ //Black
            Iterator it = BlackPiecesOnBoard.iterator();
            while( it.hasNext() ){
                it.next().
            }
        }
        else{ //White
            
        }
    }

    //public constructors & get/set
    public Board() { this.Default_FEN_code = "8/8/8/8/8/8/8/8";
//basic constructor
        this.FEN_code = Default_FEN_code;
    }

    public Board(String FEN_code) {  this.Default_FEN_code = "8/8/8/8/8/8/8/8";
//constructor amb parametres

        this.FEN_code = FEN_code;

    }
    
    public String getFEN_code() {
        return FEN_code;
    }

    private void processFEN(){
        int i = 0, j = 0;
        while(i < 7){
            while(FEN_code.charAt(j) != '/'){
                if(FEN_code.charAt(j) > '0' and FEN_code.charAt(j) <= '9'){
                    contB += Character.getNumericValue(FEN_code.charAt(j));
                    contW += Character.getNumericValue(FEN_code.charAt(j));
                }
                else if( Character.isLowerCase(FEN_code.charAt(j)) ){
                    Piece newPiece;
                    switch(FEN_code.charAt(j)){
                        case 'P': newPiece = Pawn(i, j);
                        case 'Q': newPiece = Queen(i, j);
                        case 'K': newPiece = King(i, j);
                        case 'B': newPiece = Bishop(i, j);
                        case 'R': newPiece = Rock(i, j);
                        case 'N': newPiece = Knight(i, j);
                    }
                    WhitePiecesOnBoard.add(newPiece);
                }
                else if( Character.isUpperCase(FEN_code.charAt(j)) ){
                    Piece newPiece;
                    switch(FEN_code.charAt(j)){
                        case 'p': newPiece = Pawn(i, j);
                        case 'q': newPiece = Queen(i, j);
                        case 'k': newPiece = King(i, j);
                        case 'b': newPiece = Bishop(i, j);
                        case 'r': newPiece = Rock(i, j);
                        case 'n': newPiece = Knight(i, j);
                    }
                    BlackPiecesOnBoard.add(newPiece);
                }
                ++j;
            }
            ++i;
        }
    }

}

    public void setFEN_code(String FEN_code) {
        this.FEN_code = FEN_code;
        processFEN();
    }
    
    public boolean isCheckMate(){
        return false;
    }