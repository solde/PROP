package capaDomini;

import java.util.List;
import javafx.util.Pair;

public class King extends Piece {

    private int max;

    public King() {
        super(0, 0, 0);
        this.max = 2;
    }

    public King(int x, int y) {
        super(0, x, y);
        this.max = 2;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public Pair get_poss_mov(Board b) {
        List<Pair> mov = new List; 
        Pair tmp = new Pair(0, 0);
        Piece whites[] = b.getWhitePiecesOnBoard();
        Piece blacks[] = b.getBlackPiecesOnBoard();
        int x_temp = getX();
        int y_temp = getY();

        for (int i = 0; i < whites.length; i++) {
            int x_check=whites[i].getX();
            int y_check=blacks[i].getY();
            
            if(x_check-1==x_temp && y_check-1==y_temp){
        }
        }

        return mov;
    }

}
