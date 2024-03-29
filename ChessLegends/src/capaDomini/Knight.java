package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Knight extends Piece {

    private int max;

    /**
     * Basic constructor
     */
    public Knight() {  //Per defecte son whites
        super(4, 3, 0, 0, true);
        this.max = 10;
    }

    /**
     * Constructor with parameters
     *
     * @param x
     * @param y
     * @param color
     */
    public Knight(int x, int y, boolean color) {
        super(4, 3, x, y, color);
        this.max = 10;
    }

    /**
     * Sets the max variable for Knight
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Retruns the max variable for Kinght
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

        tmp = new Pair<Integer, Integer>(x_temp - 1, y_temp - 2);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);
        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<Integer, Integer>(x_temp - 1, y_temp + 2);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);

        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }
        tmp = new Pair<Integer, Integer>(x_temp + 1, y_temp - 2);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);

        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }
        tmp = new Pair<Integer, Integer>(x_temp + 1, y_temp + 2);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);

        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }

        tmp = new Pair<Integer, Integer>(x_temp - 2, y_temp - 1);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);

        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }
        tmp = new Pair<Integer, Integer>(x_temp + 2, y_temp - 1);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);

        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }
        tmp = new Pair<Integer, Integer>(x_temp - 2, y_temp + 1);
        if (!pos_Taken(tmp, b)) {
            mov.add(tmp);

        }
        if (pos_Killable(tmp, b)) {
            mov.add(tmp);
        }
        tmp = new Pair<Integer, Integer>(x_temp + 2, y_temp + 1);
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
        return 4;
    }

}
