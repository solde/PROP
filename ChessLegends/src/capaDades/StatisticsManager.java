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
public class StatisticsManager {
    private BufferedWriter writer;
    private FileReader fr;
    
    public StatisticsManager() throws IOException{
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
    
    private boolean isId(String str, String toFind){
        String result[] = str.split(" ");
        return result[0].equals(toFind);
    }
    
    public String loadStatistics(String id) throws FileNotFoundException, IOException, chessException{
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
        if(!find) throw new chessException("Problem " + id + "doesn't exist");
        br.close();        
        return line;
    }
    
    public boolean existStatistics(String id) throws IOException{
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
    
    public void addRangTo(String id, String playerName, String time) throws IOException, FileNotFoundException, chessException{
        BufferedReader br;
        br = new BufferedReader(fr);
        boolean find = false;
        String line;
        int offset = 0;
        while ((line = br.readLine()) != null) {
                System.out.println(line);
                offset += line.length();
                if(isId(line, id)){
                    find = true;
                    break;
                }
        }
        if(!find) throw new chessException("Problem " + id + "doesn't exist");
        String toAdd = id + " " + time + " ";
        writer.write(toAdd, offset, toAdd.length());
    }
    
    protected void createStatistics(String id) throws IOException{
        writer.append(id + " ");
    }
}
