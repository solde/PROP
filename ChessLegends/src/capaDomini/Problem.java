/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;
import static capaDomini.AI1.evaluatePosition;
import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author David Soldevila
 */
public class Problem {
    private String fenCode;
    private String Name;
    private String Theme;
    private int diff;
    private int N_mov;
    private boolean atk; //True = white, False = black
    private boolean first_turn;

    public Problem() {
        this. fenCode = "8/8/8/8/8/8/8/8";
    }

    public Problem(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn) {
        this.fenCode = fenCode;
        this.Name = Name;
        this.diff = diff;
        this.N_mov = N_mov;
        this.Theme = Theme;
        this.atk = atk;
        this.first_turn = first_turn;
    }
    
    public Problem(String fenCode, String Name, int N_mov, String Theme, boolean atk, boolean first_turn) {
        this.fenCode = fenCode;
        this.Name = Name;
        this.N_mov = N_mov;
        this.Theme = Theme;
        this.atk = atk;
        this.first_turn = first_turn;
        calculateDiff();
    }
    
    /**
     *
     * @param info
     */
    public Problem(String info) {
        int i = 0;
        fenCode = "";
        while(info.charAt(i) != ' ' && i < info.length()){
            this.fenCode = fenCode.concat(Character.toString(info.charAt(i)));
        }
        
        Name = "";
        while(info.charAt(i) != ' ' && i < info.length()){
            this.Name = Name.concat(Character.toString(info.charAt(i)));
        }
        
        String aux = "";
        while(info.charAt(i) != ' ' && i < info.length()){
            aux = aux.concat(Character.toString(info.charAt(i)));
        }
        N_mov = Integer.getInteger(aux);
        
        this.Theme = "";
        while(info.charAt(i) != ' ' && i < info.length()){
            this.Theme = Theme.concat(Character.toString(info.charAt(i)));
        }
        
        aux = "";
        while(info.charAt(i) != ' ' && i < info.length()){
            aux = aux.concat(Character.toString(info.charAt(i)));
        }
        atk = Boolean.valueOf(aux);
        
        aux = "";
        while(info.charAt(i) != ' ' && i < info.length()){
            aux = aux.concat(Character.toString(info.charAt(i)));
        }
        first_turn = Boolean.valueOf(aux);
    }

    public boolean getFirstTurn(){
        return this.first_turn;
    }
    
    public String getFenCode() {
        return fenCode;
    }

    public String getName() {
        return Name;
    }

    public int getDiff() {
        return this.diff;
    }

    public int getN_mov() {
        return N_mov;
    }
    
    public String getTheme(){
        return this.Theme;
    }
    
    public void setTheme(String Theme){
        this.Theme = Theme;
    }

    public void setFenCode(String fenCode) {
        this.fenCode = fenCode;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setN_mov(int N_mov) {
        this.N_mov = N_mov;
    }
    
    public void setFirstTurn(boolean firstTurn){
        this.first_turn = firstTurn;
    }
        
    public boolean verify() throws chessException{
        Board b = new Board(this.getFenCode());
        boolean color = this.first_turn;

        return deep_verify(b, this.N_mov, this.first_turn);
    }
    
    private boolean deep_verify(Board b, int n, boolean color) throws chessException{
        ArrayList<Board> possibleBoards = new ArrayList<>(); //keeps track of the possible boards (boards with the possible moves made on them)
        boolean can_solve = false;
        
        if(n == 0){
            return b.isCheckMate(this.atk);
        }

        for(int i = 0; i<8; i++){
            for(int j=0; j<8; j++){
                Piece piece = b.getPieceAt(i,j);
                if(piece.getTypeOfPiece() != -1 && !(piece.isColor() ^ color)){
                    for(int k=0; k<8; k++){
                        for(int l=0; l<8; l++){
                            ArrayList<Pair<Integer, Integer>> possMovs = piece.get_poss_mov(b);
                            for(int x = 0; x < possMovs.size(); ++x){
                                int[] mov = new int[4];
                                mov[0] = piece.getX();
                                mov[1] = piece.getY();
                                mov[2] = possMovs.get(x).getKey();
                                mov[3] = possMovs.get(x).getValue();

                                Board altBoard = new Board(b); //initialices an alternative space to evaluate
                                altBoard.movePiece(mov[0], mov[1], mov[2], mov[3], color); //moves piece on the alternative board
                                can_solve = deep_verify(altBoard, n-1, !color);
                                if(can_solve) return true;
                            }
                        }
                    }      
                }
            }
        }
        return can_solve;
    }
    
    public boolean getATK(){
        return this.atk;
    }
    
    public void setATK(boolean atk){
        this.atk = atk;
    }
    
    public int numberPiecesOf(boolean bw){
        char i, e;
        int cont = 0;
        if(bw){
            i = 'A';
            e = 'Z';
        }
        else{
            i = 'a';
            e = 'z'; 
        }
        for(int j = 0; j < fenCode.length(); ++j){
            if( fenCode.charAt(j) >= i && fenCode.charAt(j) <= e ){
                ++cont;
            }
        }
        return cont;
    }
    
    /**
     * @Pre true
     * @Post diff get the value of the difficulty of the problem.
     */
    public void calculateDiff(){
        int x = numberPiecesOf(atk);
        this.diff = (5*(16-x))*N_mov;
    }
    
    /**
     *
     * @return
     */
    public String getProblemInfo(){
        String ret = "";
        ret = ret.concat(fenCode);
        ret = ret.concat(" ");
        ret = ret.concat(Name);
        ret = ret.concat(" ");
        ret = ret.concat(Theme);
        ret = ret.concat(" ");
        ret = ret.concat(Integer.toString(diff));
        ret = ret.concat(" ");
        ret = ret.concat(Integer.toString(N_mov));
        ret = ret.concat(" ");
        ret = ret.concat(Boolean.toString(atk));
        ret = ret.concat(" ");
        ret = ret.concat(Boolean.toString(first_turn));
        return ret;
    }
}
