/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import Exception.chessException;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.util.Pair;

/**
 *
 * @author David Soldevila 
 */
public class Game extends GameAbs {

    private long timerW;
    private long timerB;
    private int movCounter;

    /**
     * Basic constructor
     */
    public Game() {
        timerW = 0;
        timerB = 0;
        B = new Board();
        turn = true;
        movCounter = 0;
    }
    
    /**
     * Constructor with parameter
     *
     * @param initialTurn
     */
    public Game(boolean initialTurn) {
        turn = initialTurn;
        B = new Board();
        timerW = 0;
        timerB = 0;
    }


    /**
     *
     */
    @Override
    public void resetTimers() {
        timerW = 0;
        timerB = 0;
    }

    /**
     * Add time to the white time
     * @param t
     */
    public void addTimeW(long t) {
        timerW += t;
    }
    
    /**
     * Add time to the black time
     *
     * @param t
     */
    public void addTimeB(long t) {
        timerB += t;
    }

    /**
     * Get the white time
     *
     * @return double
     */
    public double getTimerW() {
        return timerW;
    }

    /**
     * Get the black time
     *
     * @return double
     */
    public double getTimerB() {
        return timerB;
    }

    /**
     * Set the white timer
     *
     * @param timerW
     */
    public void setTimerW(long timerW) {
        this.timerW = timerW;
    }
    
    /**
     * Set the black timer
     *
     * @param timerB
     */
    public void setTimerB(long timerB) {
        this.timerB = timerB;
    }

    /**
     * Funciton to move a piece at board.
     * 
     * @param sX X index of source possition
     * @param sY Y index of source possition
     * @param dX X index of destination possition
     * @param dY y index of destination possition
     * @param color color of the player that moves (treu = white, false = black)
     * @param time Time of the desicion
     * @throws chessException when you are not de owner of the piece or when the piece cannot be moved.
     */
    @Override
    public void movePiece(int sX, int sY, int dX, int dY, boolean color, long time) throws chessException {
        if (color != this.turn) {
            throw new chessException("You are not the owner of the piece");
        } else {
            try {
                B.movePiece(sX, sY, dX, dY, color);
                movCounter += 1;
            } catch (chessException e) {
                throw new chessException("Can't move");
            }
            turn = !turn;
        }
    }
    
    @Override
    public ArrayList<int[]> possibleMovements(boolean color){
        ArrayList<int[]> moves = new ArrayList<>();

        for(int i = 0; i<8; i++){
                for(int j=0; j<8; j++){
                    Piece piece = B.getPieceAt(i,j);
                    if(piece.getTypeOfPiece() != -1 && (piece.isColor() == color)){

                        ArrayList<Pair<Integer, Integer>> possMovs = piece.get_poss_mov(B);
                        for(int x = 0; x < possMovs.size(); ++x){
                            int[] mov = new int[4];
                            mov[0] = piece.getX();
                            mov[1] = piece.getY();
                            mov[2] = possMovs.get(x).getKey();
                            mov[3] = possMovs.get(x).getValue();


                            moves.add(mov); //Adds the possible movement to a poss. movs. list
                        }
                    }
                            
                }
        }
        return moves;
    }
    
    /**
     * Get information of the desired problem
     *
     * @return String
     */
    public String getProblemInfo(){
        return P.getProblemInfo();
    }
    
    @Override
    public char[][] getBoard(){
        char[][] chessBoard = new char[8][8];
        for(int i = 0; i < 8; ++i){
            for(int j = 0; j < 8; ++j){
                switch(B.getPieceAt(i, j).getTypeOfPiece()){
                    case 0:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'K';
                        else chessBoard[i][j] = 'k';
                        break;
                    case 1:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'P';
                        else chessBoard[i][j] = 'p';
                        break;
                    case 5:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'R';
                        else chessBoard[i][j] = 'r';
                        break;
                    case 3:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'B';
                        else chessBoard[i][j] = 'b';
                        break;
                    case 4:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'N';
                        else chessBoard[i][j] = 'n';
                        break;
                    case 7:
                        if(B.getPieceAt(i, j).isColor()) chessBoard[i][j] = 'Q';
                        else chessBoard[i][j] = 'q';
                        break;
                    case -1:
                        chessBoard[i][j] = ' ';
                        break;
                }
            }
        }
        return chessBoard;
    }
    
    /**
     * Check how turns are missing to finish the problem
     *
     * @return
     */
    public int leftTurn(){
        return ((2*P.getN_mov()) - movCounter)/2;
    }

    @Override
    public void playMatch(Boolean color) throws chessException {
        Scanner sc = new Scanner(System.in);
        int turn_cont = this.P.getN_mov();
        Boolean turn = this.isTurn();
        while(turn_cont > 0){
            if (turn == color){
               
                
                ArrayList<int[]> moves = new ArrayList<>();
                moves = possibleMovements(color);
                for (int i = 0; i < moves.size(); ++i){
                    int[] aux = moves.get(i);
                    System.out.println(aux[0] + " " + aux[1]);
                    System.out.println(aux[2] + " " + aux[3]);
                     System.out.println("");
                }
                
                printBoard(this.B);
                
                System.out.println("Enter your x source");
                int sX = sc.nextInt();
                
                System.out.println("Enter your y source");
                int sY = sc.nextInt();
                
                System.out.println("Enter your x destination");
                int dX = sc.nextInt();
                
                System.out.println("Enter your y destination");
                int dY = sc.nextInt();

                
                movePiece(sX, sY, dX, dY, color, 0); //0 now unimplemented
                this.B = new Board(AI1.makeMove(this.B, !turn, 2), false);
            }
           
            else{
                this.B = new Board(AI1.makeMove(this.B, turn, 2), false);
               
                
                ArrayList<int[]> moves = new ArrayList<>();
                moves = possibleMovements(color);
                for (int i = 0; i < moves.size(); ++i){
                    int[] aux = moves.get(i);
                    System.out.println(aux[0] + " " + aux[1]);
                    System.out.println(aux[2] + " " + aux[3]);
                }
                
                printBoard(this.B);
                
                System.out.println("Enter your x source");
                int sX = sc.nextInt();
                
                System.out.println("Enter your y source");
                int sY = sc.nextInt();
                
                System.out.println("Enter your x destination");
                int dX = sc.nextInt();
                
                System.out.println("Enter your y destination");
                int dY = sc.nextInt();

                
                movePiece(sX, sY, dX, dY, color, 0); //0 now unimplemented
                
            }
            

            this.setTurn(!this.isTurn());
            turn_cont -= 1;

        }
        if(B.isCheckMate(!P.getATK())){

            if (P.getATK()){
                System.out.println("WhiteWins");
            }
            else{
                System.out.println("BlackWins");
            }

        }
        
        
        else if(P.getATK()) {
            System.out.println("BlackWins");
        }
        else {
            System.out.println("WhiteWins");
        }
        

    }
    
    
}
