/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package capaDomini;

import Exception.chessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.util.Pair;
import java.math.*;

/**
 *
 * @author Arnau Santos
 */
public class AI extends Player {
    
    private static int AI_ID = 2;
    private static final long serialVersionUID = 1L;
    
    public AI(){
        
    }
    
    
    /**
     * makeMove: moves the ideal piece on a given board 
     *
     * @param b
     * @param color
     * @param depth
     * @return
     * @throws chessException
     */
    @Override
    public Board makeMove(Board b, boolean color, int depth) throws Exception {   
        
		System.out.println("hellowo here is the problemo-san");
		ArrayList<Board> possibleBoards = new ArrayList<>(); //keeps track of the possible boards (boards with the possible moves made on them)
                ArrayList<int[]> moves = new ArrayList<>();
                
                
                moves = deepEvaluate(b, color);
                
                for (int[] mov : moves) {
                    Board altBoard = new Board(b, true);
                    try{altBoard.movePiece(mov[0], mov[1], mov[2], mov[3], color); //moves piece on the alternative board
                    }
                    catch(chessException exc){
                        System.out.println("Oops.");
                        System.out.println("Source: "+ mov[0] + " " + mov[1]);
                        System.out.println("Destination: "+ mov[2] + " " + mov[3]);
                        throw new chessException(exc.getMessage());
                    }

                    possibleBoards.add(altBoard);
                }
                
                 
		int bestMoveScore; //score of that best move
		ArrayList<Integer> moveScore = new ArrayList<>();
                
		//initializes bestMoveScore to compare
                
                bestMoveScore = evaluatePosition(possibleBoards.get(0), Integer.MIN_VALUE, Integer.MAX_VALUE, depth, !color, true, color); //1 is the depth, explained in evaluate position "header"
		moveScore.add(bestMoveScore);
                
		//call evaluateposition on each move
		//keep track of the move with the best score
		
                //It's important to know that moveScore is aligned with move.
                
                for(int i = 1; i<possibleBoards.size(); i++){
  
                    int j = evaluatePosition(possibleBoards.get(i), Integer.MIN_VALUE, Integer.MAX_VALUE, depth, !color, true, color); 
                    moveScore.add(j);
                    bestMoveScore = Math.max(j, bestMoveScore);

		}
                
                
                ArrayList<int[]> bestMoves = new ArrayList<>(); //keeps track of all possible moves 
                
                for (int i = 0; i < moveScore.size(); ++i){
                    if (moveScore.get(i) == bestMoveScore) {
                        bestMoves.add(moves.get(i));
                        
                    }
                }
                
                
                int[] bestMove; //We choose a move randomly from the best moves pool
                
                Random generator = new Random();
                int index = generator.nextInt(bestMoves.size());
		bestMove = bestMoves.get(index);
                
                
                
		
		b.movePiece(bestMove[0], bestMove[1] , bestMove[2], bestMove[3], color); //theorically now is well implemented, based on the best move (random or not)
                return b;
	}

    /**
     *
     * Evaluate position: returns best score of any board given
     * 
     * @param bo
     * @param alpha
     * @param beta
     * @param depth
     * @param color
     * @param especulativeTurn
     * @param attackColor
     * @return int
     * @throws chessException
     */
    protected int evaluatePosition(Board bo, int alpha, int beta, int depth, boolean color, boolean especulativeTurn, boolean attackColor) throws chessException{ 
       return 0; 
    
    }
   
    /**
     *evaluate: heuristic function: calculates score of a board based on pieces' position
     * 
     * @param b
     * @param color
     * @return
     * @throws chessException
     */
    protected static int evaluate(Board b, boolean color) throws chessException{
	if (b.isCheckMate(!color)) return 100000; //King's Value
        else {
                int attackScore = 0;
		int defendScore = 0;
                
                        for(int i = 0; i<8; i++){
                            for(int j=0; j<8; j++){
                                    if(b.getPieceAt(i, j).getTypeOfPiece() != -1){
                                        Piece piece = b.getPieceAt(i,j);
                                            if(piece.isColor() == color){ //true es blanc
                                                    attackScore += piece.getValue();
                                            }

                                            else { //fals es negre
                                                    defendScore += piece.getValue();
                                            }
                                    }
                            }
                    }
                    return attackScore-defendScore;
    
    
        }
    }
	



/**
     *
     * deepEvaluate: calculates all possible movements of an any board given
     * 
     * @param bo
     * @param color
     * @return ArrayList<>
     */
    protected static ArrayList <int[]> deepEvaluate( Board b, boolean color){
            ArrayList<int[]> moves = new ArrayList<>();
            for(int i = 0; i<8; i++){
                    for(int j=0; j<8; j++){
                            if(b.getPieceAt(i,j).getTypeOfPiece() != -1){ 
                                if(b.getPieceAt(i,j).isColor() == color){
                                    Piece piece = b.getPieceAt(i,j);
                                    ArrayList<Pair<Integer, Integer>> llista;
                                    llista = piece.get_poss_mov(b);
                                    
                                    for (int n = 0; n < llista.size(); ++n){
                                        int[] mov = new int[4];
                                        mov[0] = piece.getX();
                                        mov[1] = piece.getY();
                                        mov[2] = llista.get(n).getKey();
                                        mov[3] = llista.get(n).getValue();
                                        moves.add(mov); 
                                       

                                    }
                                }
                            }
                    }
            }
            return moves;
        }
    
    /**
     *
     * @return
     * 
     */
    @Override
    public String getPassword(){
        String s = null;
        return s;
    }
}