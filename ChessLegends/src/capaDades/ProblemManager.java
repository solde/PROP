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
public class ProblemManager {
    
    private BufferedWriter writer;
    private FileReader fr;
    
    public ProblemManager() throws IOException{
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
    
    public void storeProblem(String info) throws IOException, chessException{
        String id = new String();
        int cont = 0;
        while(cont != ' '){
            id = id.concat(String.valueOf(info.charAt(cont)));
        }
        if(existProblem(id)) throw new chessException("Problem already exists");
        writer.append(info);
        StatisticsManager SM = new StatisticsManager();
        if(!SM.existStatistics(id)){
            SM.createStatistics(id);
        }
    }
    
    private boolean isId(String str, String toFind){
        int cont = 0;
        String result[] = str.split(" ");
        return result[0].equals(toFind);
    }
    
    public String loadProblem(String id) throws FileNotFoundException, IOException, chessException{
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
        if(!find) throw new chessException("No problem with id: " + id);
        return line;
    }
    
    public boolean existProblem(String id) throws IOException{
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
    
}
