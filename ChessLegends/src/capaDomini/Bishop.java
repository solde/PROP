package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Bishop extends Piece {

    private int max;

    public Bishop() {
        super(3, 0, 0,true);
        this.max = 10;
    }

    public Bishop(int x, int y,boolean color) {
        super(3, x, y,color);
        this.max = 10;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return this.max;
    }

    @Override
    public List<Pair> get_poss_mov(Board b) {
        List<Pair> mov = new ArrayList<>();
        Pair tmp;
        int x_temp = getX();
        int y_temp = getY();

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
