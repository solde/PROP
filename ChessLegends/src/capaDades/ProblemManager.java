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
    
    public void storeProblem(String info) throws IOException{
        writer.append(info);
        writer.close();
    }
    
    private boolean userIs(String str, String toFind){
        int cont = 0;
        String result = new String();
        while(str.charAt(cont) != ' '){
            result = result.concat(String.valueOf(str.charAt(cont)));
        }
        return result.equals(toFind);
    }
    
    public String loadProblem(String id) throws FileNotFoundException, IOException, chessException{
        BufferedReader br;
        
        
        br = new BufferedReader(fr);
        boolean find = false;
        String line;
        while ((line = br.readLine()) != null) {
                System.out.println(line);
                if(userIs(line, id)){
                    find = true;
                    break;
                }
        }
        br.close();
        
        if(!find) throw new chessException("No problem with id: " + id);
        return line;
    }
    
}
