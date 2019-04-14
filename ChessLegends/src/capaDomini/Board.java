/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import java.util.Vector;
import java.util.*; 
import java.io.*;
import Exception.chessException;

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
    
    private Vector<Vector<Piece>> chessBoard;
    
    /**
     * @Pre True
     * @Post Create a empty board (fen code = 8/8/8/8/8/8/8/8)
     */
    /*public Board() { 
        this.Default_FEN_code = "8/8/8/8/8/8/8/8";
        this.FEN_code = Default_FEN_code;
        chessBoard = new Vector<Vector<Piece>>();
        for(int i = 0; i < 8; ++i){
            Vector<Piece> aux = new Vector<Piece>();
            for(int j = 0; j < 8; ++j){
                Piece newPiece = new Piece();
                aux.add(newPiece);
            }
            chessBoard.add(aux);
        }
    }*/

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

    Board() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //public methods
    /**
     * Pre: True
     * Post: Return a conquetenation of WhitePiecesOnBoard and 
     * BlackPiecesOnBoard.
     * @return 
     */
    public Vector<Piece> get_pieces() {
	Vector<Piece> temp;
        temp = WhitePiecesOnBoard;
        temp.addAll(BlackPiecesOnBoard);
        return temp;
    }

    /**
     *
     * @return
     */
    public Vector<Piece> getWhitePiecesOnBoard() {
        return WhitePiecesOnBoard;
    }

    /**
     *
     * @return
     */
    public Vector<Piece> getBlackPiecesOnBoard() {
        return BlackPiecesOnBoard;
    }
	
    /**
     * @throws Exception.chessException
     * @Pre Exist a piece at source and it can move to destiantion.
     * @Post Piece has de new location.
     * 
     * @brief Function to move a piece. toDo is a string with two board cord. (ex: A3),
     * source and destination. If cord are in low case the piece to move is
     * black, otherwise it is a white piece.
     * 
     * @param sX
     * @param sY
     * @param dX
     * @param dY
     * @param player 
     */
    
    /*public void movePiece(int sX, int sY, int dX, int dY, boolean player) throws chessException {
        boolean moved = false;
        if(chessBoard.elementAt(sX).elementAt(sY) != null){
            if(player && chessBoard.elementAt(sX).elementAt(dY).isColor()){
                if(chessBoard.elementAt(dX).elementAt(dY) == null || !chessBoard.elementAt(dX).elementAt(dY).isColor()){
                    chessBoard.elementAt(dX).elementAt(dY) = chessBoard.elementAt(sX).elementAt(sY);
                    chessBoard.elementAt(sX).elementAt(dY) = new Piece();
                }
                else throw chessEsception("You have a piece at destination");
                
            if(!player && )
            }
        }
        else throw new chessException("No piece at source");
    }*/
    
    public void movePiece(int sX, int sY, int dX, int dY, boolean player) throws chessException {
        boolean moved = false;
        if(player){
            for(int i = 0; i < WhitePiecesOnBoard.size(); ++i){
                if(WhitePiecesOnBoard.elementAt(i).equalXY(sX, sY)){
                    for(int j = 0; j < WhitePiecesOnBoard.size(); ++j){
                        if(WhitePiecesOnBoard.elementAt(j).equalXY(dX, dY)){
                            throw new chessException("Can't move - You have one piece at destiny");
                        }
                    }
                }
                if(BlackPiecesOnBoard.elementAt(i).equalXY(sX, sY)){
                    for(int j = 0; j < BlackPiecesOnBoard.size(); ++j){
                        if(BlackPiecesOnBoard.elementAt(j).equalXY(dX, dY)){
                            BlackPiecesOnBoard.remove(j);
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
                            throw new chessException("Can't move - You have one piece at destiny");
                        }
                    }
                }
                if(WhitePiecesOnBoard.elementAt(i).equalXY(sX, sY)){
                    for(int j = 0; j < WhitePiecesOnBoard.size(); ++j){
                        if(WhitePiecesOnBoard.elementAt(j).equalXY(dX, dY)){
                            WhitePiecesOnBoard.remove(j);
                        }
                    }
                }
                BlackPiecesOnBoard.elementAt(i).setX(dX);
                BlackPiecesOnBoard.elementAt(i).setY(dY);
                moved = true;
            }
        }
        if(!moved){ 
            throw new chessException("Can't move -  You dont have any piece at source");
        }
    }

    //public constructors & get/set

    
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
                if(FEN_code.charAt(j) > '0' && FEN_code.charAt(j) <= '9'){
                    cont += Character.getNumericValue(FEN_code.charAt(j));
                }
                else if( Character.isLowerCase(FEN_code.charAt(j)) ){
                        Piece newPiece;
                        switch(FEN_code.charAt(j)){
                            case 'Q': newPiece = new Queen(i, cont, true);
                            case 'K': newPiece = new King(i, cont, true);
                            case 'B': newPiece = new Bishop(i, cont, true);
                            case 'R': newPiece = new Rock(i, cont, true);
                            case 'N': newPiece = new Knight(i, cont, true);
                            default: newPiece = new Pawn(i, cont, true);
                        }
                        WhitePiecesOnBoard.add(newPiece);
                    }
                else if( Character.isUpperCase(FEN_code.charAt(j)) ){
                    Piece newPiece;
                    switch(FEN_code.charAt(j)){
                        case 'q': newPiece = new Queen(i, cont, false);
                        case 'k': newPiece = new King(i, cont, false);
                        case 'b': newPiece = new Bishop(i, cont, false);
                        case 'r': newPiece = new Rock(i, cont, false);
                        case 'n': newPiece = new Knight(i, cont, false);
                        default: newPiece = new Pawn(i, cont, false);
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
    
    /*public String toFEN(){
        for(int i = 0; i < 8; ++i){
            String line;
            for(int j = 0; j < 8; ++j){
                if()
            }
        }
    }*/
}