package capaDomini;

import java.util.List;
import javafx.util.Pair;

public abstract class Piece {

    private int value;
    private int x;
    private int y;
    private boolean color; //TRUE WHITE FALSE BLACK

    public Piece() {
        this.value = 0;
        
    }
    
    public Piece(int val, int x, int y, boolean color) {
        this.value = val;
        this.color=color;
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
    

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getXY() {
        int a[] = new int[2];
        a[0] = this.x;
        a[1] = this.y;
        return a;
    }

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
     *
     * @param p
     * @param b
     * @return
     */
    public boolean pos_taken(Pair p, Board b) {  //FALSE = LLIURE  TRUE = OCUPADA
        boolean ret = false;
        int x_temp = (int) p.getKey();
        int y_temp = (int) p.getValue();
        List<Piece> whites= b.getWhitePiecesOnBoard();
        List<Piece> blacks= b.getBlackPiecesOnBoard();
        
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
        }
        else{
            return true;
        }
        return ret;
    }

//This method will always be overwritten

    /**
     *
     * @param b
     * @return
     */
    public abstract List<Pair> get_poss_mov(Board b); //CHANGE IN UML
}
