package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class King extends Piece {

    private int max;

    /**
     *
     */
    public King() {
        super(0, 1000, 0, 0,true);
        this.max = 2;
    }

    /**
     *
     * @param x
     * @param y
     * @param color
     */
    public King(int x, int y, boolean color) {
        super(3, 1000, x, y,color);
        this.max = 2;
    }

    /**
     *
     * @return
     */
    public int getMax() {
        return this.max;
    }

    /**
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
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<>(x_temp + 1, y_temp - 1);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<>(x_temp - 1, y_temp + 1);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<>(x_temp + 1, y_temp + 1);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<Integer, Integer>(x_temp, y_temp - 1);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<Integer, Integer>(x_temp, y_temp + 1);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<Integer, Integer>(x_temp + 1, y_temp);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<>(x_temp - 1, y_temp);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);
        }

        return mov;
    }

    @Override
    public int getTypeOfPiece() {
        return 0;
    }

}
