/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;
import Exception.chessException;
import capaDades.CtrlDades;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author David Soldevila
 */
public class CtrlDomainGame {
    private GameAbs G;
    private int movCounter;
    
    private CtrlDades CD;
        
    /**
     * Basic constructor
     */
    public CtrlDomainGame() {
        movCounter = 0;
    }

    /**
     * Set the problem with the desired parameters 
     *
     * @param fenCode
     * @param Name
     * @param diff
     * @param N_mov
     * @param Theme
     * @param atk
     * @param first_turn
     * @throws chessException
     */
    public void loadProblemTest(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn, boolean verified) throws chessException{
        //(fenCode, Name, diff, N_mov, Theme, atk)
        G.setProblem(fenCode, Name, diff, N_mov, Theme, atk, first_turn, verified);
    }
    
    public void loadProblem(String id) throws IOException, FileNotFoundException, chessException{
        String problemInfo = CD.getProblem(id);
        String aProblemInfo[] = problemInfo.split(" ");
        if(aProblemInfo.length != 8) throw new chessException("Unexpected error, failed while loading problem");

        String fenCode = aProblemInfo[1];
        String Name = aProblemInfo[0];
        String Theme = aProblemInfo[2].replace("_", " ");
        int diff = Integer.parseInt(aProblemInfo[3]);
        int N_mov = Integer.parseInt(aProblemInfo[4]);
        boolean atk = Boolean.parseBoolean(aProblemInfo[5]);
        boolean first_trun = Boolean.parseBoolean(aProblemInfo[6]);
        boolean verified = Boolean.parseBoolean(aProblemInfo[7]);
        
        G.setProblem(fenCode, Name, diff, N_mov, Theme, atk, first_trun, verified);
        
        String rankingInfo = CD.getStatistics(id);
        String aRankingInfo[] = rankingInfo.split(" ");
        ArrayList<Pair<Long, String>> newRanking;
        newRanking = new ArrayList<Pair<Long, String>>();
        for(int i = 1; i < aRankingInfo.length; i+=2){
            Pair<Long, String> aux = new Pair<Long, String>(Long.parseLong(aRankingInfo[i+1]), aRankingInfo[i]);
            newRanking.add(aux);
        }
        G.setRanking(newRanking);
    }
    
    public ArrayList<Pair<Long, String>> getRanking(){
        return G.getRanking();
    }

    /**
     * Set the player 1 to play a game with the desired parameters
     *
     * @param username
     * @param password
     * @return 
     * @throws java.io.FileNotFoundException
     * @throws Exception.chessException
     */
    public boolean authPlayer1(String username, String password) throws IOException, FileNotFoundException, chessException{
        String playerInfo = CD.getPlayer(username, password);
        if(playerInfo.length() == 0) return false;
        String aPlayerInfo[] = playerInfo.split(" ");
        if(aPlayerInfo.length != 5) throw new chessException("Unexpected error, failed while authenticating player 1");
        String id = aPlayerInfo[0];
        int wins = Integer.parseInt(aPlayerInfo[1]);
        int loses = Integer.parseInt(aPlayerInfo[2]);
        double ELO = Double.parseDouble(aPlayerInfo[3]);
        double OP_rating = Double.parseDouble(aPlayerInfo[4]);
        
        G.setPlayer1(playerInfo, wins, loses, loses, wins);
        return true;
    }
    
    /**
     * Set the player 1 to play a game with the desired parameters
     *
     * @param username
     * @param password
     * @return 
     * @throws java.io.FileNotFoundException
     * @throws Exception.chessException
     */
    public boolean authPlayer2(String username, String password) throws IOException, FileNotFoundException, chessException{
        String playerInfo = CD.getPlayer(username, password);
        if(playerInfo.length() == 0) return false;
        String aPlayerInfo[] = playerInfo.split(" ");
        if(aPlayerInfo.length != 5) throw new chessException("Unexpected error, failed while authenticating player 1");
        String id = aPlayerInfo[0];
        int wins = Integer.parseInt(aPlayerInfo[1]);
        int loses = Integer.parseInt(aPlayerInfo[2]);
        double ELO = Double.parseDouble(aPlayerInfo[3]);
        double OP_rating = Double.parseDouble(aPlayerInfo[4]);
        
        G.setPlayer2(playerInfo, wins, loses, loses, wins);
        return true;
    }
    
    /**
     * Function to move a piece, the move must be from result of getPossibleMovments
     * @param sX
     * @param sY
     * @param dX
     * @param dY
     * @param color
     * @param time
     */
    public void movePiece(int sX, int sY, int dX, int dY, boolean color ,long time){
        try{
            G.movePiece(sX, sY, dX, dY, color, time);
            G.setTurn(!color);
            this.movCounter += 1;
        }
        catch(chessException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Returns a list with all the possible movements of all pieces of a color given
     *
     * @param color
     * @return ArrayList<>
     */
    public ArrayList<int[]> getPossibleMovements(boolean color){
        return G.possibleMovements(color);
    }
    
    /**
     * Set the game in the initial conditions
     */
    public void initGame(){
        G = new Game();
        G.resetTimers();
    }
    
    /**
     * Set the game of IAvIA in the initial conditions
     *
     */
    public void initAIComp(){
        G = new AICompetition();
    }
    
    /**
     * Set the players to be two IA
     *
     */
    public void initCompetition(){
        G.setPlayer1("AI1", 0, 0, 1000, 1000);
        G.setPlayer2("AI1", 0, 0, 1000, 1000);
        //Fer coses d'estadistiques
    }
    
    /**
     * Set a human player
     * 
     * @param id
     * @param wins
     * @param loses
     * @param elo
     * @param op_r
     */
    public void initPlayer(String id, int wins, int loses, int elo, int op_r){
        G.setPlayer1(id, wins, loses, elo, op_r);
    }
    
    /**
     *  Return the "uncompressed" board, without the FEN code
     * @return char[][]
     * @throws chessException
     */
    public char[][] getBoardInfo() throws chessException{
        char[][] ret = G.getBoard();
        return G.getBoard();
    }
    
    /**
     *  Set the quantity of games IA will play
     * @param N
     * @throws chessException
     */
    public void setGames(int N) throws chessException{
        G.setN(N);
    }
    
    /**
     * Start the IAvIA set of games
     * @param color
     * @throws chessException
     */
    public void AIplay(Boolean color) throws chessException{
        G.playMatch(color);
    }
    
    /**
     * Start the HumanvIA game
     * @param color
     * @throws chessException
     */
    public void letsPlay(Boolean color) throws chessException{
        G.playMatch(color);
    }
    
    /*public void updatePlayers(long time, boolean color){
        boolean winner = G.getWinner();
        String pl1, pl2;
        pl2 = G.getPlayer1Info();
        pl1 = G.getPlayer2Info();
        String aPl[] = pl.split(" ");
        
    }*/
}
