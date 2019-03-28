package capaDomini;


public class Cavall extends Piece {
	
	private int max;



	
	public Cavall() {
		super();
		this.max=8;
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





