/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
    
    private ArrayList<Pair<Long, String>> Ranking;

    /**
     *  Basic constructor
     */
    public Problem() {
        this. fenCode = "8/8/8/8/8/8/8/8";
        Ranking = new ArrayList<Pair<Long, String>>();
    }

    /**
     * Constructor with parameters
     *
     * @param fenCode
     * @param Name
     * @param diff
     * @param N_mov
     * @param Theme
     * @param atk
     * @param first_turn
     */
    public Problem(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn) {
        Ranking = new ArrayList<Pair<Long, String>>();
        this.fenCode = fenCode;
        this.Name = Name;
        this.diff = diff;
        this.N_mov = N_mov;
        this.Theme = Theme;
        this.atk = atk;
        this.first_turn = first_turn;
    }
    
    /**
     * Constructor with parameters
     *
     * @param fenCode
     * @param Name
     * @param N_mov
     * @param Theme
     * @param atk
     * @param first_turn
     */
    public Problem(String fenCode, String Name, int N_mov, String Theme, boolean atk, boolean first_turn) {
        Ranking = new ArrayList<Pair<Long, String>>();
        this.fenCode = fenCode;
        this.Name = Name;
        this.N_mov = N_mov;
        this.Theme = Theme;
        this.atk = atk;
        this.first_turn = first_turn;
        calculateDiff();
    }

    /**
     * Check who starts 
     *
     * @return boolean
     */
    public boolean getFirstTurn(){
        return this.first_turn;
    }
    
    /**
     * Returns the FEN of a board
     *
     * @return String
     */
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
        
    /**
     * Check if a problem has solution
     *
     * @return boolean
     * @throws chessException
     */
    public boolean verify() throws chessException{
        Board b = new Board(this.getFenCode());
        boolean color = this.first_turn;

        return deep_verify(b, 2*(this.N_mov), this.first_turn);
    }
    
    private boolean deep_verify(Board b, int n, boolean color) throws chessException{
        //System.out.println(n);
        boolean can_solve = false;
        if(n == -1){
            for(int i = 0; i < 8; ++i){
                for(int j = 0; j < 8; ++j){
                    if(b.getPieceAt(i, j).getTypeOfPiece() == 0 && (b.getPieceAt(i, j).isColor() != this.atk)) return false;
                }
            }
            return true;
        }
        else{
            for(int i = 0; i < 8; ++i){
                for(int j = 0; j < 8; ++j){
                    if(b.getPieceAt(i, j).getTypeOfPiece() != -1 && b.getPieceAt(i, j).isColor() == color){
                        Piece p = b.getPieceAt(i, j);
                        int[] mov;
                        mov = new int[4];
                        mov[0] = i;
                        mov[1] = j;
                        ArrayList<Pair<Integer, Integer>> movs = p.get_poss_mov(b);
                        for(int x = 0; x < movs.size(); ++x){
                            mov[2] = movs.get(x).getKey();
                            mov[3] = movs.get(x).getValue();
                            Board altBoard = new Board(b, true);
                            altBoard.movePiece(mov[0], mov[1], mov[2], mov[3], color);
                            can_solve = deep_verify(altBoard, n-1, !color);
                            if(can_solve) return can_solve;
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
    
    /**
     * Return the quantity of pieces on a board of the color given
     *
     * @param bw
     * @return
     */
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
    
    private boolean comp(Pair<Long, String> a, Pair<Long, String>b){
        return true;
    }
    
    /**
     * Returns information of the problem
     *
     * @return String
     */
    public String getProblemInfo(){
        String ret = "";
        ret = ret.concat(fenCode);
        if(atk) ret = ret.concat(" w");
        else ret = ret.concat(" b");
        ret = ret.concat(" - - 0 1");
        ret = ret.concat(" ");
        ret = ret.concat(Name);
        ret = ret.concat(" ");
        ret = ret.concat(Theme);
        ret = ret.concat(" ");
        ret = ret.concat(Integer.toString(diff));
        ret = ret.concat(" ");
        ret = ret.concat(Integer.toString(N_mov));
        ret = ret.concat(" ");
        return ret;
    }
    
    /**
     * Add a time of a player (and the player) to a problem ranking
     *
     * @param PlayerName
     * @param time
     */
    public void addToRanking(String PlayerName, long time){
        Pair<Long, String> e;
        e = new Pair<>(time, PlayerName);
        System.out.println(e);
        Ranking.add(e);
        Ranking.sort(new Comparator<Pair<Long, String>>() {
            @Override
            public int compare(Pair<Long, String> o1, Pair<Long, String> o2){
                return o1.getKey().compareTo(o2.getKey());
            }
        });
    }
    
    /**
     * Get the ranking of a problem
     *
     * @param index
     * @return
     * @throws chessException
     */
    public Pair<Long, String> getRankingPossition(int index) throws chessException{
        try{
            return Ranking.get(index);
        }
        catch(Exception e){
            throw new chessException("Out of range");
        }
    }
}