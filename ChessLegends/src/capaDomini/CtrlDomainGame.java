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
    private Human P;
    private Problem Prbl;
    private int movCounter;

    private CtrlDades CD;

    /**
     * Basic constructor
     *
     * @throws java.io.IOException
     */
    public CtrlDomainGame() throws IOException {
        movCounter = 0;
        CD = new CtrlDades();
        P = new Human();
        Prbl = new Problem();
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
    public void loadProblemTest(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn, boolean verified) throws chessException {
        //(fenCode, Name, diff, N_mov, Theme, atk)
        G.setProblem(fenCode, Name, diff, N_mov, Theme, atk, first_turn, verified);
    }

    public void loadProblem(String id) throws IOException, FileNotFoundException, chessException {
        String problemInfo = CD.getProblem(id);
        String aProblemInfo[] = problemInfo.split(" ");
        if (aProblemInfo.length != 8) {
            throw new chessException("Unexpected error, failed while loading problem");
        }

        String fenCode = aProblemInfo[1];
        String Name = aProblemInfo[0];
        String Theme = aProblemInfo[2].replace("_", " ");
        int diff = Integer.parseInt(aProblemInfo[3]);
        int N_mov = Integer.parseInt(aProblemInfo[4]);
        boolean atk = Boolean.parseBoolean(aProblemInfo[5]);
        boolean first_turn = Boolean.parseBoolean(aProblemInfo[6]);
        boolean verified = Boolean.parseBoolean(aProblemInfo[7]);
        System.out.println(verified);

        //Prbl = new Problem();
        Prbl.setFenCode(fenCode);
        Prbl.setName(Name);
        Prbl.setTheme(Theme);
        Prbl.calculateDiff();
        Prbl.setN_mov(N_mov);
        Prbl.setATK(atk);
        Prbl.setFirstTurn(first_turn);
        Prbl.setVerified(verified);

        //G.setProblem(fenCode, Name, diff, N_mov, Theme, atk, first_turn, verified);
        String rankingInfo = CD.getStatistics(id);
        String aRankingInfo[] = rankingInfo.split(" ");
        ArrayList<Pair<Long, String>> newRanking;
        newRanking = new ArrayList<Pair<Long, String>>();
        for (int i = 1; i < aRankingInfo.length; i += 2) {
            Pair<Long, String> aux = new Pair<Long, String>(Long.parseLong(aRankingInfo[i]), aRankingInfo[i + 1]);
            newRanking.add(aux);
        }
        Prbl.setRanking(newRanking);
    }

    public ArrayList<Pair<Long, String>> getRanking() {
        return Prbl.getRanking();
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
    public boolean authPlayer1(String username, String password) throws IOException, FileNotFoundException, chessException {
        String playerInfo = CD.getPlayer(username, password);
        if (playerInfo.length() == 0) {
            return false;
        } else {
            String aPlayerInfo[] = playerInfo.split(" ");
            if (aPlayerInfo.length != 5) {
                throw new chessException("Unexpected error, failed while authenticating player 1");
            }
            String id = aPlayerInfo[0];
            int wins = Integer.parseInt(aPlayerInfo[1]);
            int loses = Integer.parseInt(aPlayerInfo[2]);
            double ELO = Double.parseDouble(aPlayerInfo[3]);
            double OP_rating = Double.parseDouble(aPlayerInfo[4]);
            P.setId(id);
            P.setWins(wins);
            P.setLoses(loses);
        }
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
    public boolean authPlayer2(String username, String password) throws IOException, FileNotFoundException, chessException {
        String playerInfo = CD.getPlayer(username, password);
        if (playerInfo.length() == 0) {
            return false;
        } else {
            String aPlayerInfo[] = playerInfo.split(" ");
            if (aPlayerInfo.length != 5) {
                throw new chessException("Unexpected error, failed while authenticating player 1");
            }
            String id = aPlayerInfo[0];
            int wins = Integer.parseInt(aPlayerInfo[1]);
            int loses = Integer.parseInt(aPlayerInfo[2]);
            double ELO = Double.parseDouble(aPlayerInfo[3]);
            double OP_rating = Double.parseDouble(aPlayerInfo[4]);
            P.setId(id);
            P.setWins(wins);
            P.setLoses(loses);
        }
        return true;
    }

    public void setPlayer(int i, int AI) {
        if (AI == 0) {
            if (i == 0) {
                G.setP1(P);
            } else if (i == 1) {
                G.setP2(P);
            }
        } else if (AI == 1) {
            if (i == 0) {
                G.setP1(new AIEasy());
            }
            if (i == 1) {
                G.setP2(new AIEasy());
            }
        } else if (AI == 2) {
            if (i == 0) {
                G.setP1(new AIHard());
            }
            if (i == 1) {
                G.setP2(new AIHard());
            }
        }
    }

    /**
     * Function to move a piece, the move must be from result of
     * getPossibleMovments
     *
     * @param sX
     * @param sY
     * @param dX
     * @param dY
     * @param color
     * @param time
     */
    public void movePiece(int sX, int sY, int dX, int dY, boolean color, long time) {
        try {
            G.movePiece(sX, sY, dX, dY, color, time);
            G.setTurn(!color);
            this.movCounter += 1;
        } catch (chessException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a list with all the possible movements of all pieces of a color
     * given
     *
     * @param color
     * @return ArrayList<>
     */
    public ArrayList<int[]> getPossibleMovements(boolean color) {
        return G.possibleMovements(color);
    }

    /**
     * Set the game in the initial conditions
     */
    public void initGame() throws chessException, chessException {
        G = new Game();
        G.resetTimers();
        G.setProblem(Prbl);
        //  G.setB(new Board(Prbl.getFenCode()));
    }

    /**
     * Set the game of IAvIA in the initial conditions
     *
     * @throws Exception.chessException
     */
    public void initAIComp(int n) throws chessException {
        G = new AICompetition(1);
        G.setProblem(Prbl);
    }

    /**
     * Return the FEN code of a board
     *
     * @return String
     * @throws chessException
     */
    public String getFenCodeOfBoard() throws chessException {
        return G.getB().fenToString();
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
    public void initPlayer(String id, int wins, int loses, int elo, int op_r) {
        G.setPlayer1(id, wins, loses, elo, op_r);
    }

    /**
     * Return the "uncompressed" board, without the FEN code
     *
     * @return char[][]
     * @throws chessException
     */
    public char[][] getBoardInfo() throws chessException {
        char[][] ret = G.getBoard();
        return G.getBoard();
    }

    /**
     * Set the quantity of games IA will play
     *
     * @param N
     * @throws chessException
     */
    public void setGames(int N) throws chessException {
        G.setN(N);
    }

    /**
     * Start the IAvIA set of games
     *
     * @param color
     * @throws chessException
     */
    public void AIplay(Boolean color) throws chessException {
        G.playMatch(color);
    }

    /**
     * Start the HumanvIA game
     *
     * @param color
     * @throws chessException
     */
    public boolean letsPlay() throws chessException {
        boolean ret = G.playMatch(Prbl.getFirstTurn());
        return ret;
    }

    public boolean isVerified() {
        System.out.println(Prbl.isVerified());
        return Prbl.isVerified();
    }

    /*public void updatePlayers(long time, boolean color){
        boolean winner = G.getWinner();
        String pl1, pl2;
        pl2 = G.getPlayer1Info();
        pl1 = G.getPlayer2Info();
        String aPl[] = pl.split(" ");
        
    }*/
    public boolean canMov(int x, int y, int xnew, int ynew, boolean turn) {
        Piece p = G.getB().getPieceAt(y, x);
        Pair<Integer, Integer> c = new Pair(ynew, xnew);
        System.out.println("pieza" + p.getX() + " " + p.getY());
        boolean ret = false;
        System.out.println(turn + " " + G.turn);
        if (turn == G.turn) {
            System.out.println("HELOOOOOOOOOOOOO");
            ArrayList<Pair<Integer, Integer>> llista = p.get_poss_mov(G.getB());
            for (Pair<Integer, Integer> mov : llista) {
                System.out.println(p.getTypeOfPiece());
                System.out.println(mov.getKey() + " " + mov.getValue());
                System.out.println("A comprovar con:" + xnew + " " + ynew);
                if (mov.getKey() == c.getKey() && mov.getValue() == c.getValue()) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public void restartB() {
        G.getB().restartBoard();
    }

    public boolean colorPiece(int x, int y) {
        return G.getB().getPieceAt(x, y).isColor();
    }

    public boolean getTurn() {
        return G.turn;
    }

    public String getFen() {
        return Prbl.getFenCode();
    }

    public void muevetePuta(boolean turn) throws chessException {
        G.playMatch(turn);
    }
}
