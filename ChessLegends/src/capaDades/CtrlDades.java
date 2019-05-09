/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDades;

import Exception.chessException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Daniel Palomo
 */

//--THIS WHOLE CLASS IS WORK IN PROGRESS AND IS NOT EXPECTED TO WORK OR BE FINAL--

public class CtrlDades {
    private final ProblemManager PM;
    private final PlayerManager PlM;
    private final StatisticsManager SM;
    
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
        if(infoArray.length > 6) throw new chessException("Too much info");
        for(int i = 0; i < infoArray.length; ++i){
           switch(i){
                case 0:
                   if(infoArray[i].length() <= 3) throw new chessException("Username mush has more than 3 character");
                   break;
                case 1:
                    try{
                        Integer.parseInt(infoArray[i]);
                    }
                    catch(Exception e){
                        throw new chessException("Wins must be a number");
                    }
                    break;
                case 2:
                    try{
                        Integer.parseInt(infoArray[i]);
                    }
                    catch(Exception e){
                        throw new chessException("Loses must be a number");
                    }
                    break;
                case 3:
                    try{
                        Integer.parseInt(infoArray[i]);
                    }
                    catch(Exception e){
                        throw new chessException("ELO must be a number");
                    }
                    break;
                case 4:
                    try{
                        Integer.parseInt(infoArray[i]);
                    }
                    catch(Exception e){
                        throw new chessException("OP_rating must be a number");
                    }
                case 5:
                    if(infoArray[i].length() <= 3) throw new chessException("Username mush has more than 3 character");
                    break;
                default:
                    throw new chessException("Too much info");
           }
        }
    }
    public void createPlayer(String id, String password){
        if()
        
    }
}
