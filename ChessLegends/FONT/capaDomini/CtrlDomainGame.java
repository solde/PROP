/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;
import Exception.chessException;
import java.util.ArrayList;

/**
 *
 * @author David Soldevila
 */
public class CtrlDomainGame {
    private GameAbs G;
    private int movCounter;
        
    /**
     * Basic constructor
     */
    public CtrlDomainGame() {
        G = new Game();
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
    public void loadProblemTest(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn) throws chessException{
        //(fenCode, Name, diff, N_mov, Theme, atk)
        G.setProblem(fenCode, Name, diff, N_mov, Theme, atk, first_turn);
    }

    /**
     * Set the player 1 to play a game with the desired parameters
     *
     * @param playerId
     * @param playerPassword
     * @param wins
     * @param loses
     * @param ELO
     * @param OP_rating
     */
    public void authPlayer1Test(String playerId, String playerPassword, int wins, int loses, int ELO, int OP_rating){
        G.setPlayer1(playerId, wins, loses, ELO, OP_rating);
    }
    
    /**
     * Set the player 2 to play a game with the desired parameters
     *
     * @param playerId
     * @param playerPassword
     * @param wins
     * @param loses
     * @param ELO
     * @param OP_rating
     */
    public void authPlayer2Test(String playerId, String playerPassword, int wins, int loses, int ELO, int OP_rating){
        G.setPlayer2(playerId, wins, loses, ELO, OP_rating);
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
    /*public int leftTurns(){
        return G.leftTurn();
    }*/
}
