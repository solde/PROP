package capaDomini;

import java.util.List;
import javafx.util.Pair;

public abstract class Piece {

    private double value;
    private int x;
    private int y;

    public Piece() {
        this.value = 0;
    }

    public Piece(double val, int x, int y) {
        this.value = val;
    }

    public int getX() {
        return x;
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
    
    public int[] getXY(){
        int a[]=new int[2];
        a[0]=this.x;
        a[1]=this.y;
        return a;
    }
    
    public boolean equalXY(int X, int Y){
        return (X == this.x && Y == this.y);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

//This method will always be overwritten
    public Pair get_poss_mov(Board b) { //CHANGE IN UML
        Pair a= new Pair(5,4);
        return a;
    }
}
