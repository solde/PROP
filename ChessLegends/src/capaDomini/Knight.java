package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class Knight extends Piece {

    private int max;

    public Knight() {  //Per defecte son whites
        super(4, 0, 0, true);
        this.max = 10;
    }

    public Knight(int x, int y, boolean color) {
        super(4, x, y, color);
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

        tmp = new Pair(x_temp - 1, y_temp - 2);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);

        }
        tmp = new Pair(x_temp - 1, y_temp + 2);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);

        }
        tmp = new Pair(x_temp + 1, y_temp - 2);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);

        }
        tmp = new Pair(x_temp + 1, y_temp + 2);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);

        }

        tmp = new Pair(x_temp - 2, y_temp - 1);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);

        }
        tmp = new Pair(x_temp + 2, y_temp - 1);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);

        }
        tmp = new Pair(x_temp - 2, y_temp + 1);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);

        }
        tmp = new Pair(x_temp + 2, y_temp + 1);
        if (!pos_taken(tmp, b)) {
            mov.add(tmp);

        }

        return mov;
    }

}
