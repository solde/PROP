package capaDomini;
public class Queen extends Piece {
	
	private int max;

	value = 7.0;

	
	public Queen() {
		super();
		this.max=9;
	}
	
  public int getMax(){return this.max;}

    public void setMax(int max) {
        this.max = max;
    }
	
	
    @Override
    public int[] get_poss_mov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



