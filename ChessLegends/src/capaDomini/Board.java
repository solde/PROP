/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import java.util.Vector;

/**
 *
 * @author Arnau Santos
 */
public class Board {

    //private
    private String FEN_code;
    private String Default_FEN_code;

    //public methods
    public Vector<Vector> get_pos_piece() {
	Vector<Vector> temp =new Vector();	
            return temp;
    }
	
	public void move_piece() {
		
	}

    //public constructors & get/set
    public Board() { this.Default_FEN_code = "8/8/8/8/8/8/8/8";
//basic constructor
        this.FEN_code = Default_FEN_code;
    }

    public Board(String FEN_code) {  this.Default_FEN_code = "8/8/8/8/8/8/8/8";
//constructor amb parametres

        this.FEN_code = FEN_code;

    }
    
    
    public String getFEN_code() {
        return FEN_code;
    }

    private void setFEN_code(String FEN_code) {
        this.FEN_code = FEN_code;
    }

}
