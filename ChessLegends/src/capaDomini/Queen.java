package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Queen extends Piece {

    private int max;

    public Queen() {
        super(7, 0, 0, true);
        this.max = 9;
    }

    public Queen(int x, int y, boolean color) {
        super(7, x, y, color);
        this.max = 9;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public List<Pair> get_poss_mov(Board b) {
        List<Pair> mov = new ArrayList<>();
        Pair tmp;
        int x_temp = getX();
        int y_temp = getY();

        for (int i = 0; i < 8; i++) {
            tmp = new Pair(x_temp - i, y_temp);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            tmp = new Pair(x_temp + i, y_temp);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            tmp = new Pair(x_temp, y_temp + i);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            tmp = new Pair(x_temp, y_temp - i);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                break;
            }
        }
        for (int i = 0; i < 8; i++) {
            tmp = new Pair(x_temp - i, y_temp - i);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                break;
            }
        }

        for (int i = 0; i < 8; i++) {
            tmp = new Pair(x_temp + i, y_temp + i);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                break;
            }
        }

        for (int i = 0; i < 8; i++) {
            tmp = new Pair(x_temp - i, y_temp + i);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                break;
            }
        }

        for (int i = 0; i < 8; i++) {
            tmp = new Pair(x_temp + i, y_temp - i);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                break;
            }
        }

        return mov;
    }
}
