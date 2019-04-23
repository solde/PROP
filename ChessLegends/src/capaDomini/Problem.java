/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

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
        
    public boolean verify(){
        return true;
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
