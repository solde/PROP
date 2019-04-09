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
    private int diff;
    private int N_mov;

    public Problem() {
    }

    public Problem(String fenCode, String Name) {
        this.fenCode = fenCode;
        this.Name = Name;
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
    
    public void calculateDiff(){
        diff = 0;
    }
    
    
}
