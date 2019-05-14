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

    public String getPlayer(String id, String password) throws IOException, FileNotFoundException, chessException{
        return PlM.loadPlayer(id, password);
    }
    
    private void checkPlayerInfo(String info) throws chessException{
        int cont = 0, word_counter = 0;
        String infoArray[] = info.split(" ");
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
    public void createPlayer(String id, String password) throws IOException, chessException{
        String info = id + " " + 0 + " " + 0 + " " + "1000.0" + " " + 0 + " " + password + " ";
        checkPlayerInfo(info);
        PlM.storePlayer(info);
    }
    
    public void updatePassword(String id, String oldPassword, String newPassword) throws IOException, FileNotFoundException, chessException{
        PlM.updatePassword(id, oldPassword, newPassword);
    }
    
    public void deletePlayer(String id) throws IOException, chessException{
        if(PlM.existPlayer(id)){
            PlM.erasePlayer(id);
        }
        else{
            throw new chessException("Player doesn't exists");
        }
    }
    
    public void updatePlayer(String id, String password, int wins, int loses, double ELO, double OP_rating) throws IOException, FileNotFoundException, chessException{
        String info = PlM.loadPlayer(id, password);
        String aInfo[] = info.split(" ");
        aInfo[1] = String.valueOf(wins);
        aInfo[2] = String.valueOf(loses);
        aInfo[3] = String.valueOf(ELO);
        aInfo[4] = String.valueOf(OP_rating);
        info = new String();
        info = info.concat(id + " ");
        for(int i = 1; i < aInfo.length; ++i){
            info = info.concat(aInfo[i] + " ");
        }
        PlM.erasePlayer(id);
        PlM.storePlayer(info);
    }
        
    public String getProblem(String id) throws IOException, FileNotFoundException, chessException{
        return PM.loadProblem(id);
    }
    
    public void createProblem(String fenCode, String Name, String Theme, int diff, int N_mov, boolean atk, boolean first_turn, boolean verified) throws IOException, chessException, chessException, chessException, chessException, chessException, chessException, chessException{
        String p = new String();
        p = p.concat(Name + " ");
        p = p.concat(fenCode + " ");
        p = p.concat(Theme + " ");
        p = p.concat(String.valueOf(diff) + " ");
        p = p.concat(String.valueOf(N_mov) + " ");
        p = p.concat(String.valueOf(atk) + " ");
        p = p.concat(String.valueOf(first_turn) + " ");
        p = p.concat(String.valueOf(verified));
        PM.storeProblem(p);
    }
    
    public void eraseProblem(String Name) throws IOException, chessException, chessException, chessException{
        PM.eraseProblem(Name);
        SM.eraseStatistics(Name);
    }
    
    public String getStatistics(String id) throws IOException, FileNotFoundException, chessException{
        return SM.loadStatistics(id);
    }
    
    public void eraseStatistics(String id) throws IOException, chessException{
        SM.eraseStatistics(id);
    }
    
    public void addRank(String Name, String id, long time) throws IOException, FileNotFoundException, chessException{
        SM.addRangTo(id, Name, String.valueOf(time));
    }
}
