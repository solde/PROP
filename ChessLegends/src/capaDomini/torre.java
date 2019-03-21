package capaDomini;
public class Torre extends Piece {
	
	private int max;



	
	public Torre() {
		super();
		this.max=8;
	}
	
  public int getMax(){return this.max;}
  
    @Override
    public int[] get_poss_mov() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}



