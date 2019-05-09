/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDades;

import Exception.chessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author David Soldevila
 */
public class PlayerManager {
        
    private BufferedWriter writer;
    private FileReader fr;
    
    public PlayerManager() throws IOException{
        writer = new BufferedWriter(new FileWriter("problemsP.txt", true));
        fr = new FileReader("problemsP.txt");
    }
    
    /*
        PARKE NO HO HA DESTRUCTORES PUTO LLENGATGE DE PROGRAMACIÓ FET PER
        DISMINUITS MENTALS QUE NO SABEN NI FER OOP *DEGENERATS TOTS*
    */     
    public void destructor() throws IOException{
        fr.close();
    }
    
    public void storePlayer(String info) throws IOException, chessException{
        String infoArray[] = info.split(" ");
        if(existPlayer(infoArray[0])) throw new chessException("Username already exists");
        writer.append(info);
    }
    
    private boolean isId(String str, String toFind){
        int cont = 0;
        String result = new String();
        while(str.charAt(cont) != ' '){
            result = result.concat(String.valueOf(str.charAt(cont)));
        }
        return result.equals(toFind);
    }
    
    private boolean checkPassword(String str, String password){
        // Password is the last word of the string, then we can use endsWith
        // funciton to check if password is in the entry.
        return str.endsWith(" " + password);
    }
    
    public String loadPlayer(String id, String password) throws FileNotFoundException, IOException, chessException{
        BufferedReader br;
        br = new BufferedReader(fr);
        boolean find = false;
        String line;
        while ((line = br.readLine()) != null) {
                System.out.println(line);
                if(isId(line, id)){
                    find = true;
                    break;
                }
        }
        br.close();
        if(!find) throw new chessException("No player with user name: " + id);
        if(!checkPassword(line, password)) throw new chessException("Wrong password");
        return line;
    }
    
    public boolean existPlayer(String id) throws IOException{
        BufferedReader br;
        br = new BufferedReader(fr);
        boolean find = false;
        String line;
        while ((line = br.readLine()) != null) {
                System.out.println(line);
                if(isId(line, id)){
                    find = true;
                    break;
                }
        }
        br.close();
        return find;
    }
    
    public void changePassword(String id, String oldPassword, String newPassword){
        
    }
    
    public void createPlayer(){
        
    }
}
