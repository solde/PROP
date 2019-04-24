package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Pawn extends Piece {

    private int max;

    public Pawn() {
        super(1, 1, 0, 0, true);
        this.max = 16;
    }

    public Pawn(int x, int y, boolean color) {
        super(1, 1, x, y, color);
        this.max = 16;
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

        if (isColor()) {
            tmp = new Pair<>(x_temp - 1, y_temp);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            }
            if (x_temp == 6) {
                tmp = new Pair<>(x_temp - 2, y_temp);
                if (!pos_taken(tmp, b)) {
                    mov.add(tmp);
                }
            }
        }
        
        if (!isColor()) {
            tmp = new Pair<>(x_temp + 1, y_temp);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            }
            if (x_temp == 1) {
                tmp = new Pair<>(x_temp + 2, y_temp);
                if (!pos_taken(tmp, b)) {
                    mov.add(tmp);
                }
            }
        }

        return mov;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public int getTypeOfPiece() {
        return 1;
    }

}
