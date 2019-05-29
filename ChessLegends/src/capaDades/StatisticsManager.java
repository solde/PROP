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
    
    public StatisticsManager(){
    }

    private boolean isId(String str, String toFind){
        String result[] = str.split(" ");
        System.out.println("*"+result[0]+"*");
        System.out.println("*"+toFind+"*");
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
    
    public void addRankTo(String id, String playerName, String time) throws IOException, FileNotFoundException, chessException{
        String info = new String();
        info = loadStatistics(id);
        String aInfo[] = info.split(" ");
        String aux = new String();
        aux = aux.concat(id + " ");
        boolean added = false;
        //Afegir o crear (falta)
        if(aInfo.length == 1){
            aux = aux.concat(time + " " + playerName);
        }
        else{
            for(int i = 1; i < aInfo.length; i += 2){
                if(!added && (Integer.parseInt(time) >= Integer.parseInt(aInfo[i+1]))){
                    aux = aux.concat(playerName + " ");
                    aux = aux.concat(time + " ");
                    added = true;
                }
                aux = aux.concat(aInfo[i]);
                aux = aux.concat(aInfo[i+1]);
            }
        }
        eraseStatistics(id);
        writer = new BufferedWriter(new FileWriter("Stats.txt", true));
        writer.append(aux+'\n');
        writer.close();
    }
    
    protected void createStatistics(String id) throws IOException{
        writer = new BufferedWriter(new FileWriter("Stats.txt", true));
        writer.append(id + " ");
        writer.append('\n');
        writer.close();
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
        if(!file.delete()) throw new chessException("WTF1");
        writer = new BufferedWriter(new FileWriter("Stats.txt", true));
        fr = new FileReader("StatsAux.txt");
        br = new BufferedReader(fr);
        while((line = br.readLine()) != null){
            writer.append(line+'\n');
        }
        file = new File("./StatsAux.txt");
        br.close();
        fr.close();
        writer.close();
        if(!file.delete()) throw new chessException("WTF2");
    }
}
