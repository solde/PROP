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
import java.util.ArrayList;

/**
 *
 * @author David Soldevila
 */
public class ProblemManager {
    
    private BufferedWriter writer;
    private FileReader fr;
    
    public ProblemManager() throws IOException{

    }
    
    /*
        PARKE NO HO HA DESTRUCTORES PUTO LLENGATGE DE PROGRAMACIÓ FET PER
        DISMINUITS MENTALS QUE NO SABEN NI FER OOP *DEGENERATS TOTS*
    */     
    public void destructor() throws IOException{
        fr.close();
    }
    
    public void storeProblem(String info) throws IOException, chessException{
        writer = new BufferedWriter(new FileWriter("Problems.txt", true));
        String infoArray[] = info.split(" ");
        if(existProblem(infoArray[0])) throw new chessException("Problem already exists");
        writer.append(info);
        writer.append('\n');
        writer.close();
    }
    
    private boolean isId(String str, String toFind){
        int cont = 0;
        String result[] = str.split(" ");
        return result[0].equals(toFind);
    }
    
    public String loadProblem(String id) throws FileNotFoundException, IOException, chessException{
        BufferedReader br;
        fr = new FileReader("Problems.txt");
        br = new BufferedReader(fr);
        boolean find = false;
        String line;
        while ((line = br.readLine()) != null) {
                if(isId(line, id)){
                    find = true;
                    break;
                }
        }        
        if(!find) throw new chessException("No problem with user name: " + id);
        br.close();
        fr.close();
        return line;
    }
    
    public boolean existProblem(String id) throws IOException{
        BufferedReader br;
        fr = new FileReader("Problems.txt");
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
    
    public void eraseProblem(String id) throws IOException, chessException{
        BufferedWriter writerAux;
        writerAux = new BufferedWriter(new FileWriter("ProblemAux.txt", true));
        BufferedReader br;
        fr = new FileReader("Problems.txt");
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
        File file = new File("./Problems.txt");
        if(!file.delete()) throw new chessException("WTF");
        writer = new BufferedWriter(new FileWriter("Problems.txt", true));
        fr = new FileReader("ProblemAux.txt");
        br = new BufferedReader(fr);
        while((line = br.readLine()) != null){
            writer.append(line+'\n');
        }
        file = new File("./ProblemAux.txt");
        br.close();
        fr.close();
        writer.close();
        if(!file.delete()) throw new chessException("WTF");
    }
    
    public ArrayList<String> listProblems() throws FileNotFoundException, IOException{
        BufferedReader br;
        fr = new FileReader("Problems.txt");
        br = new BufferedReader(fr);
        boolean find = false;
        ArrayList<String> ret = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null) {
            String aLine[] = line.split(" ");
            ret.add(aLine[0]);
        }        
        br.close();
        fr.close();
        return ret; 
    }
}
