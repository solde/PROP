/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

/**
 *
 * @author Arnau Santos
 */
public class Board {

    //private
    private string FEN_code;
    private const string Default_FEN_code = "8/8/8/8/8/8/8/8";

    //public methods
    public array<array> get_pos_piece() {
		

    }
	
	public void move_piece() {
		
	}

    //public constructors & get/set
    public Board() { //basic constructor
        this.FEN_code = Default_FEN_code;
    }

    public Board(String FEN_code) {  //constructor amb parametres

        this.FEN_code = FEN_code;

    }
    
    
    public String getFEN_code() {
        return FEN_code;
    }

    private void setFEN_code(String FEN_code) {
        this.FEN_code = FEN_code;
    }

}
