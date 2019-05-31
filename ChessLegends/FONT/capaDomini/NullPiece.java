package capaDomini;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
/**
 *
 * @author Daniel Palomo
 *
 */
public class NullPiece extends Piece {

    private int max;

    /**
     * Basic constructor
     */
    public NullPiece() {
        super(-1, -1, 0, 0, true);
        this.max = 64;
    }

    /**
     * Constructor with parameters
     *
     * @param x
     * @param y
     * @param color
     */
    public NullPiece(int x, int y, boolean color) {
        super(-1, -1, x, y, color);
        this.max = 64;
    }

    /**
     * Sets the variable max for nullPiece
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Retruns the max variabe for nullPiece
     *
     * @return int
     */
    public int getMax() {
        return this.max;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> get_poss_mov(Board b) {
        ArrayList<Pair<Integer, Integer>> mov = new ArrayList<>();
        return mov;
    }

    @Override
    public int getTypeOfPiece() {
        return -1;
    }

}
