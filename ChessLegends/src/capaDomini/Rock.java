package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Rock extends Piece {

    private int max;

    public Rock() {
        super(5, 5, 0, 0, true);
        this.max = 10; //for each player
    }

    public Rock(int x, int y, boolean color) {
        super(5, 5, x, y, color);
        this.max = 10; //for each player
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return this.max;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> get_poss_mov(Board b) {
        ArrayList<Pair<Integer, Integer>> mov = new ArrayList<>();
        Pair<Integer, Integer> tmp;

        int x_temp = getX();
        int y_temp = getY();

        for (int i = 0; i < 8; i++) {
            tmp = new Pair<Integer, Integer>(x_temp - i, y_temp);
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
            tmp = new Pair<Integer, Integer>(x_temp + i, y_temp);
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
            tmp = new Pair<Integer, Integer>(x_temp, y_temp + i);
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
            tmp = new Pair<Integer, Integer>(x_temp, y_temp - i);
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
        return 5;
    }
}
