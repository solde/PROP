package capaDomini;


public class Bishop extends Piece {
	
	private int max;
	
	public Bishop() {
		super(3);
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





