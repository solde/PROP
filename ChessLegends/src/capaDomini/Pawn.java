package capaDomini;

/**
 *
 * @author Daniel Palomo
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;

public class Pawn extends Piece {

    private int max;

    /**
     * Basic constructor
     */
    public Pawn() {
        super(1, 1, 0, 0, true);
        this.max = 8;
    }

    /**
     * Constrcutor with parameters
     *
     * @param x
     * @param y
     * @param color
     */
    public Pawn(int x, int y, boolean color) {
        super(1, 1, x, y, color);
        this.max = 8;
    }

    /**
     * Retruns max varialbe for Pawn
     *
     * @return int
     */
    public int getMax() {
        return this.max;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> get_poss_mov(Board b) {
        ArrayList<Pair<Integer, Integer>> mov = new ArrayList<>();
        Pair<Integer, Integer> tmp;
        int x_temp = getX();
        int y_temp = getY();

        if (isColor()) {
            tmp = new Pair<>(x_temp - 1, y_temp);
            if (!pos_Taken(tmp, b)) {
                mov.add(tmp);
            }
            tmp = new Pair<>(x_temp - 1, y_temp - 1);
            if (pos_Killable(tmp, b)) {
                mov.add(tmp);
            }
            tmp = new Pair<>(x_temp - 1, y_temp + 1);
            if (pos_Killable(tmp, b)) {
                mov.add(tmp);
            }

            if (x_temp == 6) {
                tmp = new Pair<>(x_temp - 2, y_temp);
                if (!pos_Taken(tmp, b)) {
                    mov.add(tmp);
                }
                /*tmp = new Pair<>(x_temp - 2, y_temp - 1);
                if (pos_Killable(tmp, b)) {
                    mov.add(tmp);
                }
                tmp = new Pair<>(x_temp - 2, y_temp + 1);
                if (pos_Killable(tmp, b)) {
                    mov.add(tmp);
                }*/
            }
        }

        if (!isColor()) {
            tmp = new Pair<>(x_temp + 1, y_temp);
            if (!pos_Taken(tmp, b)) {
                //   System.out.println(x_temp + " " + y_temp + " " + Arrays.toString(b.getPieceAt(tmp.getKey(), tmp.getValue()).getXY()));
                mov.add(tmp);
            }
            tmp = new Pair<>(x_temp + 1, y_temp + 1);
            if (pos_Killable(tmp, b)) {
                mov.add(tmp);
            }
            tmp = new Pair<>(x_temp + 1, y_temp - 1);
            if (pos_Killable(tmp, b)) {
                mov.add(tmp);
            }
            if (x_temp == 1) {
                tmp = new Pair<>(x_temp + 2, y_temp);
                if (!pos_Taken(tmp, b)) {
                    mov.add(tmp);
                }
                /*tmp = new Pair<>(x_temp + 2, y_temp - 1);
                if (pos_Killable(tmp, b)) {
                    mov.add(tmp);
                }
                tmp = new Pair<>(x_temp + 2, y_temp + 1);
                if (pos_Killable(tmp, b)) {
                    mov.add(tmp);
                }*/
            }
        }

        return mov;
    }

    /**
     * Sets the max variable for Pawn
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public int getTypeOfPiece() {
        return 1;
    }

}
