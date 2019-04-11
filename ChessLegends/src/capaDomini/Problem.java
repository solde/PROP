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
    private bloolean atk; //True = white, False = black

    public Problem() {
    }

    public Problem(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk) {
        this.fenCode = fenCode;
        this.Name = Name;
        this.diff = diff;
        this.N_mov = N_mov;
        this.Theme = Theme;
        this.atk = atk;
    }

    public String getFenCode() {
        return fenCode;
    }

    public String getName() {
        return Name;
    }

    public int getDiff() {
        return diff;
    }

    public int getN_mov() {
        return N_mov;
    }
    
    public String getTheme(){
        return this.Theme;
    }
    
    public void setThem(String Theme){
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
        
    public boolean verify(){
        return true;
    }
    
    private int numberPiecesOf(){
        char i, e;
        int cont = 0;
        if(atk){
            i = 'A';
            e = 'Z';
        }
        else{
            i = 'a';
            e = 'z'; 
        }
        for(int i = 0; i < fenCode.length(); ++i){
            if( fenCode.charAt(i) >= i && fenCode.charAt(i) <= e ){
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
        int x = numberPiecesOf();
        diff = 5*(16-x)*N_mov);
    }
    
}
