package capaDomini;


public class Pawn extends Piece {
	
	private int max;


	
	public Pawn() {
		super(1);
		this.max=16;
	}
	
  public int getMax(){return this.max;}
  
     @Override
    public int[] get_poss_mov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      public void setMax(int max) {
        this.max = max;
    }
  
}





