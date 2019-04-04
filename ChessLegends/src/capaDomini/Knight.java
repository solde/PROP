package capaDomini;


public class Knight extends Piece {
	
	private int max;

	value = 3.5;

	
	public Knight() {
		super();
		this.max=10;
	}

    public void setMax(int max) {
        this.max = max;
    }
	
  public int getMax(){return this.max;}
  
    @Override
    public int[] get_poss_mov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}





