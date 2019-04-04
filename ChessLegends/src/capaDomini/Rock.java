package capaDomini;


public class Rock extends Piece {
	
	private int max;

	value = 5.0;

	
	public Rock() {
		super();
		this.max=10; //for each player
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


