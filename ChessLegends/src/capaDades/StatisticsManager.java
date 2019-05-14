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
public class StatisticsManager {
    private BufferedWriter writer;
    private FileReader fr;
    
    public StatisticsManager() throws IOException{
    }

    private boolean isId(String str, String toFind){
        String result[] = str.split(" ");
        return result[0].equals(toFind);
    }
    
    public String loadStatistics(String id) throws FileNotFoundException, IOException, chessException{
        BufferedReader br;
        fr = new FileReader("Stats.txt");
        br = new BufferedReader(fr);
        boolean find = false;
        String line;
        while ((line = br.readLine()) != null) {
                if(isId(line, id)){
                    find = true;
                    break;
                }
        }        
        if(!find) throw new chessException("No stats with that id");
        br.close();
        fr.close();
        return line;
    }
    
    public boolean existStatistics(String id) throws IOException{
        BufferedReader br;
        fr = new FileReader("Stats.txt");
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
    
    public void addRangTo(String id, String playerName, String time) throws IOException, FileNotFoundException, chessException{
        String info = new String();
        try{
            info = loadStatistics(id);
        }
        catch(chessException e){
            if(e.getMessage() != "No stats with that id"){
                throw e;
            }
            else{
                info = id + " ";
            }
        }
        String aInfo[] = info.split(" ");
        //Afegir o crear (falta)
    }
    
    protected void createStatistics(String id) throws IOException{
        writer.append(id + " ");
    }
    
    public void eraseStatistics(String id) throws IOException, chessException{
        BufferedWriter writerAux;
        writerAux = new BufferedWriter(new FileWriter("StatsAux.txt", true));
        BufferedReader br;
        fr = new FileReader("Stats.txt");
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
        File file = new File("./Stats.txt");
        if(!file.delete()) throw new chessException("WTF");
        writer = new BufferedWriter(new FileWriter("Stats.txt", true));
        fr = new FileReader("StatsAux.txt");
        br = new BufferedReader(fr);
        while((line = br.readLine()) != null){
            writer.append(line+'\n');
        }
        file = new File("./StatsAux.txt");
        if(!file.delete()) throw new chessException("WTF");
        br.close();
        fr.close();
        writer.close();
    }
}
