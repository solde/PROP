/**
 *
 * @author Daniel Palomo
 */
package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Bishop extends Piece {

    private int max;

    /**
     * Basic constructor
     */
    public Bishop() {
        super(3, 3, 0, 0, true);
        this.max = 10;
    }

    /**
     * Constructor with parameters
     *
     * @param x
     * @param y
     * @param color
     */
    public Bishop(int x, int y, boolean color) {
        super(3, 3, x, y, color);
        this.max = 10;
    }

    /**
     * Sets the max variable for Bishop
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Retruns the max variable for Bishop
     *
     * @return int
     */
    public int getMax() {
        return this.max;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> get_poss_mov(Board b) {
        ArrayList<Pair<Integer, Integer>> mov;
        mov = new ArrayList<Pair<Integer, Integer>>();
        Pair<Integer, Integer> tmp;
        int x_temp = getX();
        int y_temp = getY();

        for (int i = 0; i < 8; i++) {
            tmp = new Pair<>(x_temp - i, y_temp - i);
            if (!pos_Taken(tmp, b)) {
                mov.add(tmp);
            } else {
                if (pos_Killable(tmp, b)) {
                    mov.add(tmp);
                }
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            tmp = new Pair<Integer, Integer>(x_temp + i, y_temp + i);
            if (!pos_Taken(tmp, b)) {
                mov.add(tmp);
            } else {
                if (pos_Killable(tmp, b)) {
                    mov.add(tmp);
                }
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            tmp = new Pair<>(x_temp - i, y_temp + i);

            if (!pos_Taken(tmp, b)) {
                mov.add(tmp);
            } else {
                if (pos_Killable(tmp, b)) {
                    mov.add(tmp);
                }
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            tmp = new Pair<Integer, Integer>(x_temp + i, y_temp - i);
            if (!pos_Taken(tmp, b)) {
                mov.add(tmp);
            } else {
                if (pos_Killable(tmp, b)) {
                    mov.add(tmp);
                }
                break;
            }
        }

        return mov;
    }

    @Override
    public int getTypeOfPiece() {
        return 3;
    }

}
