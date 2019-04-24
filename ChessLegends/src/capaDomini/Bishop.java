package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Bishop extends Piece {

    private int max;

    public Bishop() {
        super(3, 3, 0, 0, true);
        this.max = 10;
    }

    public Bishop(int x, int y, boolean color) {
        super(3, 3, x, y, color);
        this.max = 10;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return this.max;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> get_poss_mov(Board b) {
        ArrayList<Pair<Integer, Integer>> mov;
        mov = new ArrayList<>();
        Pair<Integer, Integer> tmp;
        int x_temp = getX();
        int y_temp = getY();

        for (int i = 0; i < 8; i++) {
            tmp = new Pair<>(x_temp - i, y_temp - i);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                if (pos_killable(tmp, b, isColor())) {
                    mov.add(tmp);
                }
                break;
            }
        }

        for (int i = 0; i < 8; i++) {
            tmp = new Pair<>(x_temp + i, y_temp + i);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                if (pos_killable(tmp, b, isColor())) {
                    mov.add(tmp);
                }
                break;
            }
        }

        for (int i = 0; i < 8; i++) {
            tmp = new Pair<>(x_temp - i, y_temp + i);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                if (pos_killable(tmp, b, isColor())) {
                    mov.add(tmp);
                }
                break;
            }
        }

        for (int i = 0; i < 8; i++) {
            tmp = new Pair<>(x_temp + i, y_temp - i);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            } else {
                if (pos_killable(tmp, b, isColor())) {
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
