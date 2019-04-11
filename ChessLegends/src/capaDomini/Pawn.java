package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Pawn extends Piece {

    private int max;

    public Pawn() {
        super(1, 0, 0, true);
        this.max = 16;
    }

    public Pawn(int x, int y, boolean color) {
        super(1, x, y, color);
        this.max = 16;
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

        if (isColor()) {
            tmp = new Pair(x_temp, y_temp + 1);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            }
            if (y_temp == 1) {
                tmp = new Pair(x_temp, y_temp + 2);
                if (!pos_taken(tmp, b)) {
                    mov.add(tmp);
                }
            }
        }
        
        if (!isColor()) {
            tmp = new Pair(x_temp, y_temp - 1);
            if (!pos_taken(tmp, b)) {
                mov.add(tmp);
            }
            if (y_temp == 6) {
                tmp = new Pair(x_temp, y_temp - 2);
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

}
