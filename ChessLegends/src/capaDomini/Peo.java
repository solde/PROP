package capaDomini;


public class Peo extends Piece {
	
	private int max;



	
	public Peo() {
		super();
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






