/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;
import java.util.*; 
import javafx.util.Pair;

/**
 *
 * @author Arnau Santos
 */
public class Board {

    //private
    private String FEN_code;
    private final String Default_FEN_code;
    
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
    public Board(String FEN_code) throws chessException {  
        this.Default_FEN_code = "8/8/8/8/8/8/8/8";
        chessBoard = new Piece[8][8];
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                chessBoard[i][j] = new NullPiece(i, j, false);
            }
        }
        this.FEN_code = FEN_code;
        processFEN();
    }
    
    public Board(Board b) throws chessException {  
        chessBoard = new Piece[8][8];
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                chessBoard[i][j] = new NullPiece(i, j, true);
            }
        }
        this.Default_FEN_code = "8/8/8/8/8/8/8/8"; 
        this.FEN_code = b.getFEN_code();
        processFEN();
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
        if(dY == sY && dX == sX){
            throw new chessException("You are moving the piece at the same cell");
        }
        if(chessBoard[sX][sY].getTypeOfPiece() == -1){
            throw new chessException("No piece at Soruce");
        }
        if(chessBoard[sX][sY].isColor() != player){
            throw new chessException("Not your piece");
        } 
        if(chessBoard[dX][dY].getTypeOfPiece() != -1 && (chessBoard[dX][dY].isColor() == chessBoard[sX][sY].isColor())){
            throw new chessException("Color: " + player + " " + chessBoard[dX][dY].isColor() + " Trying to move a piece of type " + chessBoard[sX][sY].getTypeOfPiece() + " to a cell with a piece " + chessBoard[dX][dY].getTypeOfPiece());
        }
        switch(chessBoard[sX][sY].getTypeOfPiece()){
            case 0:
                chessBoard[dX][dY] = new King( chessBoard[sX][sY].getX(), chessBoard[sX][sY].getY(), chessBoard[sX][sY].isColor());
                break;
            case 1:
                chessBoard[dX][dY] = new Pawn( chessBoard[sX][sY].getX(), chessBoard[sX][sY].getY(), chessBoard[sX][sY].isColor());
                break;
            case 2:
                chessBoard[dX][dY] = new King( chessBoard[sX][sY].getX(), chessBoard[sX][sY].getY(), chessBoard[sX][sY].isColor());
                break;
            case 3:
                chessBoard[dX][dY] = new Bishop( chessBoard[sX][sY].getX(), chessBoard[sX][sY].getY(), chessBoard[sX][sY].isColor());
                break;
            case 4:
                chessBoard[dX][dY] = new Knight( chessBoard[sX][sY].getX(), chessBoard[sX][sY].getY(), chessBoard[sX][sY].isColor());
                break;
            case 5:
                chessBoard[dX][dY] = new Rock( chessBoard[sX][sY].getX(), chessBoard[sX][sY].getY(), chessBoard[sX][sY].isColor());
                break;
            case 7:
                chessBoard[dX][dY] = new Queen( chessBoard[sX][sY].getX(), chessBoard[sX][sY].getY(), chessBoard[sX][sY].isColor());
                break;

        }
        chessBoard[sX][sY] = new NullPiece(sX, sY, true);
        this.FEN_code = this.fenToString();
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

    private void processFEN() throws chessException{
        int i = 0, j = 0;
        int verifyer = 0;
        int[] piecesOfWhite;
        piecesOfWhite = new int[6];
        int[] piecesOfBlack;
        piecesOfBlack = new int[6];
        for(int x = 0; x < 6; ++x){
            piecesOfWhite[x] = 0;
            piecesOfBlack[x] = 0;
        }
        while(i < 8){
            int cont = 0;
            while(j < FEN_code.length()){
                if(FEN_code.charAt(j) == '/'){
                    if(verifyer != 8) throw new chessException("Invalid FEN code");
                    ++j;
                    verifyer = 0;
                    break;
                }
                if(FEN_code.charAt(j) >= '0' && FEN_code.charAt(j) <= '9'){
                    cont = cont + Character.getNumericValue(FEN_code.charAt(j));
                    verifyer = verifyer + Character.getNumericValue(FEN_code.charAt(j));
                }
                else{
                    verifyer += 1;
                    switch(FEN_code.charAt(j)){
                        case 'Q': 
                            piecesOfWhite[0] += 1;
                            chessBoard[i][cont] = new Queen(i, cont, true);
                            break;
                        case 'K': 
                            piecesOfWhite[1] += 1;
                            chessBoard[i][cont] = new King(i, cont, true);
                            break;
                        case 'B':
                            piecesOfWhite[2] += 1;
                            chessBoard[i][cont] = new Bishop(i, cont, true);
                            break;
                        case 'R': 
                            piecesOfWhite[3] += 1;
                            chessBoard[i][cont] = new Rock(i, cont, true);
                            break;
                        case 'N': 
                            piecesOfWhite[4] += 1;
                            chessBoard[i][cont] = new Knight(i, cont, true);
                            break;
                        case 'P': 
                            piecesOfWhite[5] += 1;
                            chessBoard[i][cont] = new Pawn(i, cont, true);
                            break;
                        case 'q': 
                            piecesOfBlack[0] += 1;
                            chessBoard[i][cont] = new Queen(i, cont, false);
                            break;
                        case 'k': 
                            piecesOfBlack[1] += 1;
                            chessBoard[i][cont] = new King(i, cont, false);
                            break;
                        case 'b': 
                            piecesOfBlack[2] += 1;
                            chessBoard[i][cont] = new Bishop(i, cont, false);
                            break;
                        case 'r': 
                            piecesOfBlack[3] += 1;
                            chessBoard[i][cont] = new Rock(i, cont, false);
                            break;
                        case 'n':
                            piecesOfBlack[4] += 1;
                            chessBoard[i][cont] = new Knight(i, cont, false);
                            break;
                        case 'p': 
                            piecesOfBlack[5] += 1;
                            chessBoard[i][cont] = new Pawn(i, cont, false);
                            break;
                        default: chessBoard[i][cont] = new NullPiece(i, cont, true);
                    }
                    ++cont;
                }
                ++j;
            }
            ++i;
        }
        boolean need_throw = false;
        for(int x = 0; x < 6; ++x){
            switch(x){
                case 0:
                    Queen q = new Queen();
                    if(piecesOfBlack[x] >= q.getMax() || piecesOfWhite[x] >= q.getMax()) need_throw = true;
                    
                    break;
                    
                case 1:
                    King k = new King();
                    if(piecesOfBlack[x] != k.getMax() || piecesOfWhite[x] != k.getMax()) need_throw = true;
                    break;
                    
                case 2:
                    Bishop b = new Bishop();
                    if(piecesOfBlack[x] >= b.getMax() || piecesOfWhite[x] >= b.getMax()) need_throw = true;
                    break;
                    
                case 3:
                    Rock r = new Rock();
                    if(piecesOfBlack[x] >= r.getMax() || piecesOfWhite[x] >= r.getMax()) need_throw = true;
                    break;
                    
                case 4:
                    Knight n = new Knight();
                    if(piecesOfBlack[x] >= n.getMax() || piecesOfWhite[x] >= n.getMax()) need_throw = true;
                    break;
                    
                case 5:
                    Pawn p = new Pawn();
                    if(piecesOfBlack[x] >= p.getMax() || piecesOfWhite[x] >= p.getMax()) need_throw = true;
                    break;
            }
        }
        if(need_throw) throw new chessException("Invalid FEN code");
    }

    /**
     * @Pre: FEN_code contains a correct fen code
     * @Post: The board contains the piece distribution encoded at FEN_code
     * @param FEN_code 
     */
    public void setFEN_code(String FEN_code) throws chessException {
        this.FEN_code = FEN_code;
        processFEN();
    }
    
    /**
     * @param color
     * @throws Exception.chessException
     * @Pre True
     * @Post Returns if player color is in checkmate
     * @return if player color is doing a checkmate to the other player
     */
    public boolean isCheckMate(boolean color) throws chessException{
        Piece p = new NullPiece(0, 0, true);
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                if(chessBoard[i][j].getTypeOfPiece() == 0 && chessBoard[i][j].isColor() == color){
                    p = chessBoard[i][j];
                }
            }
        }
        ArrayList<Pair<Integer, Integer>> pos_movs = p.get_poss_mov(this);
        for(int i = 0; i < pos_movs.size(); ++i){
            Board newb = new Board(this);
            newb.movePiece(p.getX(), p.getY(), pos_movs.get(i).getKey(), pos_movs.get(i).getValue(), color);
            Problem P = new Problem();
            P.setFenCode(newb.getFEN_code());
            P.setN_mov(1);
            P.setATK(!color);
            P.setFirstTurn(!color);
            if(!P.verify()){
                return false;
            }
        }
        return true;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Piece> getWhitePiecesOnBoard(){
        ArrayList<Piece> ret = new ArrayList<>();
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                if(chessBoard[i][j].getTypeOfPiece() != -1 && chessBoard[i][j].isColor()){
                    ret.add(chessBoard[i][j]);
                }
            }
        }
        return ret;
    }

    public ArrayList<Piece> getBlackPiecesOnBoard(){
        ArrayList<Piece> ret = new ArrayList<>();
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                if(chessBoard[i][j].getTypeOfPiece() != -1 && !chessBoard[i][j].isColor()){
                    ret.add(chessBoard[i][j]);
                }
            }
        }
        return ret;
    }
    
    public void addPieceToBoard(int x, int y, int value, boolean color){
        Piece p;
        switch(value){
            case 0: 
                p = new King(x, y, color);
                break;
            case 1: 
                p = new Pawn(x, y, color);
                break;
            case 3: 
                p = new Bishop(x, y, color);
                break;
            case 4: 
                p = new Knight(x, y, color);
                break;
            case 5: 
                p = new Rock(x, y, color);
                break;
            case 7: 
                p = new Queen(x, y, color);
                break;
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
                else if(cont == 8) line = line.concat(Integer.toString(cont));
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
                            default: throw new chessException("Unexpected chess white Error");
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
                            default: throw new chessException("Unexpected chess black Error");
                        }
                    }
                }
            }
            //if(cont != 0) line = line.concat(Integer.toString(cont));
            if(cont != 0){
                line = line.concat(Integer.toString(cont));
                cont = 0;
            }
            if(i != 7){
                line = line.concat("/");
            }
            str = str.concat(line);
        }
        return str;
    }
    
    public Piece getPieceAt(int x, int y){
        return chessBoard[x][y];
    }
}