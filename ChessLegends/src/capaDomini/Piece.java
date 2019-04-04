package capaDomini;
public abstract class Piece {

private double value;
public Piece(){
	this.value=0;
	}
public Piece(double val){
	this.value=val;
	}
public double getValue(){
	return value;
}

    public void setValue(double value) {
        this.value = value;
    }


public abstract int[] get_poss_mov();
    
    
}





