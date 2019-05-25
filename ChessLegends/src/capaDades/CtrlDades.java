/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDades;

import Exception.chessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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

    public String getPlayer(String id, String password) throws IOException, FileNotFoundException{
        String playerInfo;
        try{
            playerInfo = PlM.loadPlayer(id, password);
        }
        catch(chessException e){
            return "";
        }
        playerInfo = playerInfo.replaceFirst(password + " ", "");
        return playerInfo;
    }
    
    public void createPlayer(String id, String password) throws IOException, chessException{
        String info = id + " " + 0 + " " + 0 + " " + "1000.0" + " " + 0 + " " + password + " ";
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
        Theme.replace(' ', '_');
        p = p.concat(Theme + " ");
        p = p.concat(String.valueOf(diff) + " ");
        p = p.concat(String.valueOf(N_mov) + " ");
        p = p.concat(String.valueOf(atk) + " ");
        p = p.concat(String.valueOf(first_turn) + " ");
        p = p.concat(String.valueOf(verified));
        PM.storeProblem(p);
        SM.createStatistics(Name);
    }
    
    public void eraseProblem(String Name) throws IOException, chessException, chessException, chessException{
        PM.eraseProblem(Name);
        SM.eraseStatistics(Name);
    }
    
    public String getStatistics(String id) throws IOException, FileNotFoundException, chessException{
        return SM.loadStatistics(id);
    }
    
    public ArrayList<String> listProblmes() throws IOException{
        ArrayList<String> LP = PM.listProblems();
        System.out.println(LP.size());
        
        return LP;
    }
    
    public void eraseStatistics(String id) throws IOException, chessException{
        SM.eraseStatistics(id);
    }
    
    public void addRank(String Name, String id, long time) throws IOException, FileNotFoundException, chessException{
        SM.addRangTo(id, Name, String.valueOf(time));
    }
}
