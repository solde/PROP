package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Daniel Palomo
 *
 */
public abstract class Piece {

    private final int id;
    private int value;
    private int x;
    private int y;
    private boolean color; //TRUE WHITE FALSE BLACK

    /**
     * Basic Constructor
     */
    public Piece() {
        this.value = 0;
        this.id = -1;
    }

    /**
     * Constructor with parameters
     *
     * @param id
     * @param val
     * @param x
     * @param y
     * @param color
     */
    public Piece(int id, int val, int x, int y, boolean color) {
        this.id = id;
        this.value = val;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * Retruns X
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Changes x of the piece
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retruns color of the piece
     *
     * @return
     */
    public boolean isColor() {
        return color;
    }

    /**
     * Changes color of the piece
     *
     * @param color
     */
    public void setColor(boolean color) {
        this.color = color;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns a Pair with the position of the piece
     *
     * @return
     */
    public int[] getXY() {
        int a[] = new int[2];
        a[0] = this.x;
        a[1] = this.y;
        return a;
    }

    /**
     * Retruns if the (x,y) passed is equal with the position of the piece
     *
     * @param X
     * @param Y
     * @return
     */
    public boolean equalXY(int X, int Y) {
        return (X == this.x && Y == this.y);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Checks if the Pair p represents an empty space in the board.
     *
     * @param p
     * @param b
     * @return
     */
    public boolean pos_Taken(Pair p, Board b) {  //FALSE = LLIURE  TRUE = OCUPADA
        boolean ret = false;
        int x_temp = (int) p.getKey();
        int y_temp = (int) p.getValue();
        List<Piece> whites = b.getWhitePiecesOnBoard();
        List<Piece> blacks = b.getBlackPiecesOnBoard();
        if(x_temp==this.getX() && y_temp==this.getY()) return true;
        if (x_temp >= 0 && x_temp < 8 && y_temp >= 0 && y_temp < 8) {

            for (Piece white : whites) {
                int x_check = white.getX();
                int y_check = white.getY();
                if (x_check == x_temp && y_check == y_temp) {
                    ret = true;
                }
            }
            for (Piece black : blacks) {
                int x_check = black.getX();
                int y_check = black.getY();
                if (x_check == x_temp && y_check == y_temp) {
                    ret = true;
                }
            }
        } else {
            return true;
        }
        return ret;
    }

    /**
     * This Funciton detects if the position in Pair p represents a piece on
     * board that can be killed by the piece .
     *
     * @param p Pair of coords
     * @param b Board where the game is taking place
     * @param color Color of the piece
     * @return
     */
    public boolean pos_Killable(Pair p, Board b, boolean color) { //TRUE= KILLABLE  FALSE=FRIENDLY

        int x_temp = (int) p.getKey();
        int y_temp = (int) p.getValue();
        if (x_temp >= 0 && x_temp < 8 && y_temp >= 0 && y_temp < 8) {
            Piece k = b.getPieceAt(x_temp, y_temp);
            if (k.getTypeOfPiece() != -1 && !(color ^ k.isColor())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
        /*
        boolean ret = false;
        int x_temp = (int) p.getKey();
        int y_temp = (int) p.getValue();
        List<Piece> whites = b.getWhitePiecesOnBoard();
        List<Piece> blacks = b.getBlackPiecesOnBoard();

        if (x_temp >= 0 && x_temp < 8 && y_temp >= 0 && y_temp < 8) {

            for (Piece white : whites) {
                int x_check = white.getX();
                int y_check = white.getY();
                if (x_check == x_temp && y_check == y_temp && !color) {
                    ret = true;

                } 
            }
            for (Piece black : blacks) {
                int x_check = black.getX();
                int y_check = black.getY();
                if (x_check == x_temp && y_check == y_temp && color) {
                    ret = true;
                }
            }
        } else {
            return false;
        }
        return ret;*/
    }

    public boolean equals(Piece p) {
        boolean ret = this.color == p.color && this.value == p.value && this.equalXY(p.getX(), p.y) && this.id == p.getTypeOfPiece();
        return ret;
    }

//This method will always be overwritten
    /**
     *
     * @param b
     * @return
     */
    public abstract ArrayList<Pair<Integer, Integer>> get_poss_mov(Board b); //CHANGE IN UML

    public abstract int getTypeOfPiece();
}
