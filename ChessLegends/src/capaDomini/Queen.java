package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Queen extends Piece {

    private int max;

    /**
     * constructor without parameters
     */
    public Queen() {
        super(7, 9, 0, 0, true);
        this.max = 9;
    }

    /**
     * Costructor with parameters
     *
     * @param x
     * @param y
     * @param color
     */
    public Queen(int x, int y, boolean color) {
        super(7, 9, x, y, color);
        this.max = 9;
    }

    /**
     * Retruns the max variable for Queen
     *
     * @return int
     */
    public int getMax() {
        return this.max;
    }

    /**
     * Sets the max variable for Queen
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> get_poss_mov(Board b) {
        ArrayList<Pair<Integer, Integer>> mov = new ArrayList<>();
        Pair<Integer, Integer> tmp;
        int x_temp = getX();
        int y_temp = getY();

        for (int i = 1; i < 8; i++) {
            tmp = new Pair<>(x_temp - i, y_temp);
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
            tmp = new Pair<>(x_temp + i, y_temp);
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
            tmp = new Pair<>(x_temp, y_temp + i);
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
            tmp = new Pair<>(x_temp, y_temp - i);
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
            tmp = new Pair<>(x_temp + i, y_temp + i);
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
            tmp = new Pair<>(x_temp + i, y_temp - i);
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
        return 7;
    }
}
