/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDades;

import Exception.chessException;
import capaDomini.Human;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.in;

/**
 *
 * @author Daniel Palomo
 */

//--THIS WHOLE CLASS IS WORK IN PROGRESS AND IS NOT EXPECTED TO WORK OR BE FINAL--

public class CtrlDades {
    private ProblemManager PM;
    private PlayerManager PlM;
    private StatisticsManager SM;
    
    public CtrlDades() throws IOException{
        PM = new ProblemManager();
        PlM = new PlayerManager();
        SM = new StatisticsManager();
    }
    
    public void destructor() throws IOException{
        PM.destructor();
        PlM.destructor();
        SM.destructor();
    }
    
    public String getProblem(String id) throws IOException, FileNotFoundException, chessException{
        return PM.loadProblem(id);
    }
    
    public String getPlayer(String id, String password) throws IOException, FileNotFoundException, chessException{
        return PlM.loadPlayer(id, password);
    }
    
    public String getStatistics(String id) throws IOException, FileNotFoundException, chessException{
        return SM.loadStatistics(id);
    }
    
    public void storePlayer(String info) throws chessException{
        int cont = 0, word_counter = 0;
        String infoArray[] = info.split(" ");
        if(infoArray.length > 7) throw new chessException("Too much info");
        for(int i = 0; i < infoArray.length; ++i){
           switch(i){
                case 0:
                   if(infoArray[i].length() < 4) throw new chessException("Username mush has more than 3 character");
                   break;
                case 1:
                    if(Integer.ge)
           }
        }
    }
}
