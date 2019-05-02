package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Daniel Palomo
 */
public class King extends Piece {

    private int max;

    /**
     * Basic constructors
     */
    public King() {
        super(0, 100000, 0, 0, true);
        this.max = 1;
    }

    /**
     * Constructor with parameters
     *
     * @param x
     * @param y
     * @param color
     */
    public King(int x, int y, boolean color) {
        super(3, 100000, x, y, color);
        this.max = 1;
    }

    /**
     * Returns the max variable for King
     *
     * @return int
     */
    public int getMax() {
        return this.max;
    }

    /**
     * Sets the max variable for King
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *
     * @param b
     * @return
     */
    @Override
    public ArrayList<Pair<Integer, Integer>> get_poss_mov(Board b) {
        ArrayList<Pair<Integer, Integer>> mov;
        mov = new ArrayList<>();
        Pair<Integer, Integer> tmp;
        int x_temp = getX();
        int y_temp = getY();

        tmp = new Pair<>(x_temp - 1, y_temp - 1);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);
        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<>(x_temp + 1, y_temp - 1);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);
        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<>(x_temp - 1, y_temp + 1);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);
        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<>(x_temp + 1, y_temp + 1);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);
        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }
        tmp = new Pair<>(x_temp, y_temp - 1);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);
        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }
        tmp = new Pair<>(x_temp, y_temp + 1);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);
        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }
        tmp = new Pair<>(x_temp + 1, y_temp);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);
        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }
        tmp = new Pair<>(x_temp - 1, y_temp);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);
        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }
        return mov;
    }

    @Override
    public int getTypeOfPiece() {
        return 0;
    }

}
