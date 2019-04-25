/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;
import static capaDomini.AI1.evaluatePosition;
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

    public Problem() {
        this. fenCode = "8/8/8/8/8/8/8/8";
        Ranking = new ArrayList<Pair<Long, String>>();
    }

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
        Ranking = new ArrayList<Pair<Long, String>>();
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

        return deep_verify(b, 2*(this.N_mov), this.first_turn);
    }
    
    public static void printBoard(Board B){
        System.out.println("   0  1  2  3  4  5  6  7");
        for(int x = 0; x < 8; ++x){
            System.out.print(x + "|");
            for(int y = 0; y < 8; ++y){
                if(B.getPieceAt(x, y).isColor() && B.getPieceAt(x, y).getTypeOfPiece() != -1)System.out.print(" " + B.getPieceAt(x, y).getTypeOfPiece() + "|");
                else if(!B.getPieceAt(x, y).isColor() && B.getPieceAt(x, y).getTypeOfPiece() != -1)System.out.print("-" + B.getPieceAt(x, y).getTypeOfPiece() + "|");
                else System.out.print("  " + "|");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    
    private boolean deep_verify(Board b, int n, boolean color) throws chessException{
        //System.out.println(color);
        ArrayList<Board> possibleBoards = new ArrayList<>(); //keeps track of the possible boards (boards with the possible moves made on them)
        boolean can_solve = false;
        
        if(n == 0){
            //printBoard(b);
            for(int i = 0; i < 8; ++i){
                for(int j = 0; j < 8; ++j){
                    if(b.getPieceAt(i, j).getTypeOfPiece() == 0 && !(b.getPieceAt(i, j).isColor()^color)) return false;
                }
            }
            return true;
        }

        for(int i = 0; i<8; i++){
            for(int j=0; j<8; j++){
                Piece piece = b.getPieceAt(i,j);
                if(piece.getTypeOfPiece() != -1 && !(piece.isColor() ^ color)){
                    ArrayList<Pair<Integer, Integer>> possMovs = piece.get_poss_mov(b);
                    int[] mov = new int[4];
                    mov[0] = piece.getX();
                    mov[1] = piece.getY();
                    for(int x = 0; x < possMovs.size(); ++x){
                        //System.out.println(x + " " + piece.getTypeOfPiece() + " " + Arrays.toString(piece.getXY()));
                        //printBoard(b);
                        mov[2] = possMovs.get(x).getKey();
                        mov[3] = possMovs.get(x).getValue();
                        //printBoard(b);
                        Board altBoard = new Board(b); //initialices an alternative space to evaluate
                        altBoard.movePiece(mov[0], mov[1], mov[2], mov[3], color); //moves piece on the alternative board
                        can_solve = deep_verify(altBoard, n-1, !color);
                        if(can_solve) return true;
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
    
    private boolean comp(Pair<Long, String> a, Pair<Long, String>b){
        return true;
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
    
    public Pair<Long, String> getRankingPossition(int index) throws chessException{
        try{
            return Ranking.get(index);
        }
        catch(Exception e){
            throw new chessException("Out of range");
        }
    }
}
