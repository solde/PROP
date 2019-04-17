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
import javafx.util.Pair;

/**
 *
 * @author Arnau Santos
 */
public class Board {

    //private
    private String FEN_code;
    private String Default_FEN_code;
    
    private Piece[][] chessBoard;
    
    /**
     * @Pre True
     * @Post Create a empty board (fen code = 8/8/8/8/8/8/8/8)
     */
    public Board() { 
        this.Default_FEN_code = "8/8/8/8/8/8/8/8";
        this.FEN_code = Default_FEN_code;
        chessBoard = new Piece[8][8];
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                chessBoard[i][j] = new NullPiece(i, j, false);
            }
        }
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
    
    public Board(Board b) {  
        this.Default_FEN_code = "8/8/8/8/8/8/8/8";          
        this.FEN_code = b.getFEN_code();
        this.chessBoard = b.getchessBoard();
    }

    /*Board() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    //public methods	
    /**
     * @throws Exception.chessException
     * @Pre Movment follows de rules for the piece.
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
    public void movePiece(int sX, int sY, int dX, int dY, boolean player) throws chessException {
        boolean moved = false;
        if(chessBoard[sX][sY].getTypeOfPiece() == -1){
            throw new chessException("No piece at Soruce");
        }
        else{
            if(chessBoard[sX][sY].isColor() ^ player){
                throw new chessException("Not your piece");
            } 
            else if(chessBoard[dX][dY].getTypeOfPiece() != -1 && !(chessBoard[dX][dY].isColor() ^ player)){
                throw new chessException("Cannot move to dest");
            }
            else{
                chessBoard[dX][dY] = chessBoard[sX][sX];
                chessBoard[dX][dY].setX(dX);
                chessBoard[dX][dY].setY(dY);
                chessBoard[sX][sY] = new NullPiece(sX, sY, true);
            }
        }
    }

    protected Piece[][] getchessBoard(){
        return this.chessBoard;
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

    private void processFEN(){
        int i = 0, j = 0;
        while(i < 7){
            while(FEN_code.charAt(j) != '/' && j < FEN_code.length()){
                int cont = 0;
                if(FEN_code.charAt(j) > '0' && FEN_code.charAt(j) <= '9'){
                    cont = cont + Character.getNumericValue(FEN_code.charAt(j));
                }
                else{
                    switch(FEN_code.charAt(j)){
                        case 'Q': chessBoard[i][cont] = new Queen(i, cont, true);
                        case 'K': chessBoard[i][cont] = new King(i, cont, true);
                        case 'B': chessBoard[i][cont] = new Bishop(i, cont, true);
                        case 'R': chessBoard[i][cont] = new Rock(i, cont, true);
                        case 'N': chessBoard[i][cont] = new Knight(i, cont, true);
                        case 'P': chessBoard[i][cont] = new Pawn(i, cont, true);
                        case 'q': chessBoard[i][cont] = new Queen(i, cont, false);
                        case 'k': chessBoard[i][cont] = new King(i, cont, false);
                        case 'b': chessBoard[i][cont] = new Bishop(i, cont, false);
                        case 'r': chessBoard[i][cont] = new Rock(i, cont, false);
                        case 'n': chessBoard[i][cont] = new Knight(i, cont, false);
                        case 'p': chessBoard[i][cont] = new Pawn(i, cont, false);
                        default: chessBoard[i][cont] = new NullPiece(i, cont, true);
                    }
                    ++cont;
                }
            }
            ++j;
        }
        ++i;
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
     * @param color
     * @Pre True
     * @Post Returns if any of the kings is in check mate
     * @return 
     */
    public boolean isCheckMate(boolean color){
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                if(chessBoard[i][j].getTypeOfPiece() == 0 && !(color ^ chessBoard[i][j].isColor())){
                    List<Pair> pos_movs = chessBoard[i][j].get_poss_mov(this);
                    if(pos_movs.size() > 0) return false;
                }
            }
        }
        return true;
    }
    
    /**
     *
     * @return
     */
    public List<Piece> getWhitePiecesOnBoard(){
        List<Piece> ret = new ArrayList<>();
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                if(chessBoard[i][j].getTypeOfPiece() != -1 && chessBoard[i][j].isColor()){
                    ret.add(chessBoard[i][j]);
                }
            }
        }
        return ret;
    }

    public List<Piece> getBlackPiecesOnBoard(){
        List<Piece> ret = new ArrayList<>();
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                if(!chessBoard[i][j].isColor()){
                    ret.add(chessBoard[i][j]);
                }
            }
        }
        return ret;
    }
    
    public void addPieceToBoard(int x, int y, int value, boolean color){
        Piece p;
        switch(value){
            case 0: p = new King(x, y, color);
            case 1: p = new Pawn(x, y, color);
            case 3: p = new Bishop(x, y, color);
            case 4: p = new Knight(x, y, color);
            case 5: p = new Rock(x, y, color);
            case 7: p = new Queen(x, y, color);
            default: p = new NullPiece(x, y, true);
        }
        chessBoard[x][y] = p;
    }
    
    /**
     *
     * @return
     * @throws Exception.chessException
     */
    public String fenToString() throws chessException {
        String str = new String();
        for(int i = 0; i < 8; ++i){
            String line = new String();
            int cont = 0;
            for(int j = 0; j < 8; ++j){
                if(chessBoard[i][j].getTypeOfPiece() == -1){
                    cont++;
                }
                else{
                    if(cont != 0){
                        line = line.concat(Integer.toString(cont));
                        cont = 0;
                    }
                    if(chessBoard[i][j].isColor()){
                        switch(chessBoard[i][j].getTypeOfPiece()){
                            case 0: 
                                line = line.concat("K");
                                break;
                            case 1: 
                                line = line.concat("P");
                                break;
                            case 3: 
                                line = line.concat("B");
                                break;
                            case 4: 
                                line = line.concat("N");
                                break;
                            case 5: 
                                line = line.concat("R");
                                break;
                            case 7: 
                                line = line.concat("Q");
                                break;
                            default: throw new chessException("Unexpected Error");
                        }
                    }
                    else{
                        switch(chessBoard[i][j].getTypeOfPiece()){
                            case 0: 
                                line = line.concat("k");
                                break;
                            case 1: 
                                line = line.concat("p");
                                break;
                            case 3: 
                                line = line.concat("b");
                                break;
                            case 4: 
                                line = line.concat("n");
                                break;
                            case 5: 
                                line = line.concat("r");
                                break;
                            case 7: 
                                line = line.concat("q");
                                break;
                            default: throw new chessException("Unexpected Error");
                        }
                    }
                }
            }
            str = str.concat(line);
            if(i != 7){
                str = str.concat("/");
            }
        }
        return str;
    }
    
    public Piece getPieceAt(int x, int y){
        return chessBoard[x][y];
    }
}