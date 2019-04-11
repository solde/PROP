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
    /**
     * Pre: True
     * Post: Return a conquetenation of WhitePiecesOnBoard and 
     * BlackPiecesOnBoard.
     * @return 
     */
    public Vector<Piece> get_pieces() {
	Vector<Piece> temp = WhitePiecesOnBoard;
        temp.addAll(BlackPiecesOnBoard);
        return temp;
    }

    public Vector<Piece> getWhitePiecesOnBoard() {
        return WhitePiecesOnBoard;
    }

    public Vector<Piece> getBlackPiecesOnBoard() {
        return BlackPiecesOnBoard;
    }
	
    /**
     * @Pre Exist a piece at source and it can move to destiantion.
     * @Post Piece has de new location.
     * 
     * @brief Function to move a piece. toDo is a string with two board cord. (ex: A3),
     * source and destination. If cord are in low case the piece to move is
     * black, otherwise it is a white piece.
     * 
     * @param toDo 
     */
    public void movePiece(int sX, int sY, int dX, int dY, boolean player) {
        boolean moved = false;
        if(player){
            for(int i = 0; i < WhitePiecesOnBoard.size(); ++i){
                if(WhitePiecesOnBoard.elementAt(i).equalXY(sX, sY)){
                    for(int j = 0; j < WhitePiecesOnBoard.size(); ++j){
                        if(WhitePiecesOnBoard.elementAt(j).equalXY(dX, dY)){
                            throw new ArithmeticException("Can't move - You have one piece at destiny");
                        }
                    }
                }
                if(BlackPiecesOnBoard.elementAt(i).equalXY(sX, sY)){
                    for(int j = 0; j < BlackPiecesOnBoard.size(); ++j){
                        if(BlackPiecesOnBoard.elementAt(j).equalXY(dX, dY)){
                            killPieceAt(dX, dY);
                        }
                    }
                }
                WhitePiecesOnBoard.elementAt(i).setX(dX);
                WhitePiecesOnBoard.elementAt(i).setY(dY);
                moved = true;
            }
        }
        else{
            for(int i = 0; i < WhitePiecesOnBoard.size(); ++i){
                if(BlackPiecesOnBoard.elementAt(i).equalXY(sX, sY)){
                    for(int j = 0; j < BlackPiecesOnBoard.size(); ++j){
                        if(BlackPiecesOnBoard.elementAt(j).equalXY(dX, dY)){
                            throw new ArithmeticException("Can't move - You have one piece at destiny");
                        }
                    }
                }
                if(WhitePiecesOnBoard.elementAt(i).equalXY(sX, sY)){
                    for(int j = 0; j < WhitePiecesOnBoard.size(); ++j){
                        if(WhitePiecesOnBoard.elementAt(j).equalXY(dX, dY)){
                            killPieceAt(dX, dY);
                        }
                    }
                }
                BlackPiecesOnBoard.elementAt(i).setX(dX);
                BlackPiecesOnBoard.elementAt(i).setY(dY);
                moved = true;
            }
        }
        if(!moved){
            throw new ArithmeticExcetpion("Can't move -  You dont have any piece at source")
        }
    }

    //public constructors & get/set
    /**
     * @Pre True
     * @Post Create a empty board (fen code = 8/8/8/8/8/8/8/8)
     */
    public Board() { this.Default_FEN_code = "8/8/8/8/8/8/8/8";
        this.FEN_code = Default_FEN_code;
    }

    /**
     * @Pre FEN_code contains a correct fen code
     * @Post Create a board with fen code equals to FEN_code
     * @param FEN_code 
     */
    public Board(String FEN_code) {  
        this.Default_FEN_code = "8/8/8/8/8/8/8/8";
        this.FEN_code = FEN_code;
        processFEN();
    }
    
    /**
     * @Pre True
     * @Post Return the original FEN_code
     * @return 
     */
    public String getFEN_code() {
        return FEN_code;
    }

    /**
     * @Pre True
     * @Post Vectors WhitePiecesOnBoard and BlackPiecesOnBoard are initialized 
     * with the pieces acording with the fen code
     */
    private void processFEN(){
        int i = 0, j = 0;
        while(i < 7){
            while(FEN_code.charAt(j) != '/'){
                int cont = 0;
                if(FEN_code.charAt(j) > '0' and FEN_code.charAt(j) <= '9'){
                    cont += Character.getNumericValue(FEN_code.charAt(j));
                }
                else if( Character.isLowerCase(FEN_code.charAt(j)) ){
                    Piece newPiece;
                    switch(FEN_code.charAt(j)){
                        case 'P': newPiece = Pawn(i, cont);
                        case 'Q': newPiece = Queen(i, cont);
                        case 'K': newPiece = King(i, cont);
                        case 'B': newPiece = Bishop(i, cont);
                        case 'R': newPiece = Rock(i, cont);
                        case 'N': newPiece = Knight(i, cont);
                    }
                    WhitePiecesOnBoard.add(newPiece);
                }
                else if( Character.isUpperCase(FEN_code.charAt(j)) ){
                    Piece newPiece;
                    switch(FEN_code.charAt(j)){
                        case 'p': newPiece = Pawn(i, cont);
                        case 'q': newPiece = Queen(i, cont);
                        case 'k': newPiece = King(i, cont);
                        case 'b': newPiece = Bishop(i, cont);
                        case 'r': newPiece = Rock(i, cont);
                        case 'n': newPiece = Knight(i, cont);
                    }
                    BlackPiecesOnBoard.add(newPiece);
                }
                ++j;
            }
            ++i;
        }
    }

    /**
     * @Pre: FEN_code contains a correct fen code
     * @Post: The board contains the piece distribution encoded at FEN_code
     * @param FEN_code 
     */
    public void setFEN_code(String FEN_code) {
        this.FEN_code = FEN_code;
        processFEN();
    }
    
    /**
     * @Pre True
     * @Post Returns if any of the kings is in check mate
     * @return 
     */
    public boolean isCheckMate(){
        return false;
    }
}