/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDades;

import Exception.chessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
    }
    
    /*
        PARKE NO HO HA DESTRUCTORES PUTO LLENGATGE DE PROGRAMACIÓ FET PER
        DISMINUITS MENTALS QUE NO SABEN NI FER OOP *DEGENERATS TOTS*
    */     
    
    public void storePlayer(String info) throws IOException, chessException{
        writer = new BufferedWriter(new FileWriter("Players.txt", true));
        String infoArray[] = info.split(" ");
        if(existPlayer(infoArray[0])) throw new chessException("Username already exists");
        writer.append(info);
        writer.append('\n');
        writer.close();
    }
    
    private boolean isId(String str, String toFind){
        String info[] = str.split(" ");
        if(info[0].equals(toFind)) return true;
        return false;
    }
    
    private boolean checkPassword(String str, String password){
        // Password is the last word of the string, then we can use endsWith
        // funciton to check if password is in the entry.
        return str.endsWith(" " + password + " ");
    }
    
    public String loadPlayer(String id, String password) throws FileNotFoundException, IOException, chessException{
        BufferedReader br;
        fr = new FileReader("Players.txt");
        br = new BufferedReader(fr);
        boolean find = false;
        String line;
        while ((line = br.readLine()) != null) {
                if(isId(line, id)){
                    find = true;
                    break;
                }
        }        
        if(!find) throw new chessException("No player with user name: " + id);
        if(!checkPassword(line, password)) throw new chessException("Wrong password");
        br.close();
        fr.close();
        return line;
    }
    
    public boolean existPlayer(String id) throws IOException{
        BufferedReader br;
        fr = new FileReader("Players.txt");
        br = new BufferedReader(fr);
        boolean find = false;
        String line;
        while ((line = br.readLine()) != null) {
                if(isId(line, id)){
                    find = true;
                    break;
                }
        }
        br.close();
        fr.close();
        return find;
    }
    
    public void updatePassword(String id, String oldPassword, String newPassword) throws IOException, FileNotFoundException, chessException{
        String p = loadPlayer(id, oldPassword);
        erasePlayer(id);
        String paux[] = p.split(" ");
        p = new String();
        for(int i = 0; i < paux.length-1; ++i){
            p = p.concat(paux[i]+" ");
        }
        p = p.concat(newPassword + " ");
        storePlayer(p);
    }
    
    public void erasePlayer(String id) throws IOException, chessException{
        BufferedWriter writerAux;
        writerAux = new BufferedWriter(new FileWriter("PlayersAux.txt", true));
        BufferedReader br;
        fr = new FileReader("Players.txt");
        String line;
        br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            if(!isId(line, id)){
                writerAux.append(line);
                writerAux.append('\n');
            }
        }
        br.close();
        fr.close();
        writerAux.close();
        File file = new File("./Players.txt");
        if(!file.delete()) throw new chessException("WTF");
        writer = new BufferedWriter(new FileWriter("Players.txt", true));
        fr = new FileReader("PlayersAux.txt");
        br = new BufferedReader(fr);
        while((line = br.readLine()) != null){
            writer.append(line+'\n');
        }
        file = new File("./PlayersAux.txt");
        br.close();
        fr.close();
        writer.close();
        if(!file.delete()) throw new chessException("WTF");
    }
}
