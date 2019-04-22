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

/**
 *
 * @author Arnau Santos
 */
public class AI1 extends Player {

    private static int AI1_ID = 2;
    private static final long serialVersionUID = 1L;
    
    public AI1(){
        System.out.println("Not implemented");
    }

    public static void makeMove(Board b, boolean color, int N, int depth) throws chessException {   //now incomplete. Needs to associate moves w/ possible boards.
                                                                                                    //nowadays possi
        
		int[] bestMove; //keeps track of the best possible move AI has available
		int bestMoveScore; //score of that best move
		
		ArrayList<Board> possibleBoards = new ArrayList<>(); //keeps track of the possible boards (boards with the possible moves made on them)
		ArrayList<int[]> moves = new ArrayList<>(); //keeps track of all possible moves 
		
		/*
		 * iterates through board, generates all possible moves and saves them in moves
                	 */
                for(int i = 0; i<8; i++){
			for(int j=0; j<8; j++){
                            Piece piece = b.getPieceAt(i,j);
                            if(piece.getTypeOfPiece() != -1 && !(piece.isColor() ^ color)){
                                for(int k=0; k<8; k++){
                                    for(int l=0; l<8; l++){
                                        ArrayList<Pair<Integer, Integer>> possMovs = piece.get_poss_mov(b);
                                        for(int x = 0; x < possMovs.size(); ++x){
                                            int[] mov = new int[4];
                                            mov[0] = piece.getX();
                                            mov[1] = piece.getY();
                                            mov[2] = possMovs.get(x).getKey();
                                            mov[3] = possMovs.get(x).getValue();
                                            
                                            moves.add(mov); //Adds the possible movement to a poss. movs. list
                                            Board altBoard = new Board(b); //initialices an alternative space to evaluate
                                            altBoard.movePiece(mov[0], mov[1], mov[2], mov[3], color); //moves piece on the alternative board
                                            possibleBoards.add(altBoard); //adds the alternative board to the possible boards list
                                        }
                                    }
                                }      
                            }
                            else {
                            }
                        }
		}
		//initializes bestMove to the first move in the 
		bestMove = moves.get(0);
		bestMoveScore = evaluatePosition(possibleBoards.get(0), Integer.MIN_VALUE, Integer.MAX_VALUE, depth, false); //1 is the depth, explained in evaluate position "header"
		
		//call evaluateposition on each move
		//keep track of the move with the best score
		if(N > 0){
			for(int i = 1; i<possibleBoards.size(); i++){
				//System.out.println("Evaluating move: " + moves.get(i).toString()); this is made to have a visual control
				/*
				 * calls evaluatePosition on each possible board and if the score is higher than previous,
				 * reset the bestMove
				 */
				int j = evaluatePosition(possibleBoards.get(i), Integer.MIN_VALUE, Integer.MAX_VALUE, depth, false); 
				if(j >= bestMoveScore){
					bestMove = moves.get(i);
					bestMoveScore = j;
				}
	
			}
		}else{
			Random generator = new Random();
			int randnum = generator.nextInt(moves.size());
			bestMove = moves.get(randnum);
		}
		//System.out.println(bestMove.toString()); same as in line ~66, to have a visual control
		N++; //Change the turn to extend deep in search
		b.movePiece(bestMove[0], bestMove[1] , bestMove[2], bestMove[3], true); //theorically now is well implemented, based on the best move (random or not)
	}
	
    /**
     *
     * @param b
     * @param color
     * @return
     */
    public static ArrayList <int[]> deepEvaluate( Board b, boolean color){
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
                                        mov[0] = i;
                                        mov[1] = j;
                                        mov[3] = llista.get(n).getKey();
                                        mov[4] = llista.get(n).getValue();
                                        moves.add(mov); 
                                    }
                                }
                            }
                    }
            }
            return moves;
        }
        
	/**
	 * The evaluatePosition function takes a board, initial alpha, initial beta, depth, and color as parameters
	 * and The function is 
 recursive, and every time it evaluates itself it decreases the depth by 1.coWhen the depth reaches 0, the
 function returns the result of running the evaluate function on the board.  If the depth is not 0, the function
 generates all possible moves from that position for the color specified, and then runs evaluatePosition for 
 each of the boards generated by each possible move. runs evaluatePosition for 
	 * each of the boards generated by each possible move. 
	 * @param b
	 * @param alpha
	 * @param beta 
	 * @param depth
	 * @param color
	 * @return an int giving a score of how good a particular board is, with higher numbers corresponding to better boards for the AI
         * @throws Exception.chessException 
	 */
	public static int evaluatePosition(Board b, int alpha, int beta, int depth, boolean color) throws chessException{ 
		System.out.println("Begin evaluating position: depth-" + depth + "for- "+ color);
		/*
		 * Base case: when depth is decremented to 0, evaluatePosition simply returns the result
		 * of the evaluate function
		 */
		if(depth == 0){
			int evaluation = evaluate(b);
			System.out.println("Evaluated to: " + evaluation);
			return evaluation;
		}
		
		if(!color){ //minimizing player--sequence of events that occurs
			ArrayList <int[]> moves = deepEvaluate(b, color);
			
                        /*
			 * Iterate through the board, collect all possible moves of the minimizing player
			 */

			/*
			 * This for loop goes through all possible moves and calls evaluatePosition on them,
			 * changing the color.  Alpha-beta pruning is used here to remove obviously poor moves.
			 * These are determined by the variables alpha and beta.  All moves where the beta,
			 * which is the score of the minimizing (in this case white player) is less than or
			 * equal to alpha are discarded.  
			 */
			
                        int newBeta = beta;
			for(int i = 0; i < moves.size(); ++i){
				//System.out.println("Move to be evaluated: " + move.toString());
				Board successorBoard = new Board(b);
                                int[] aux= moves.get(i);
				b.movePiece(aux[0], aux[1], aux[2], aux[3], color);
                            
				newBeta = Math.min(newBeta, evaluatePosition(successorBoard, alpha, beta, depth -1, !color)); //think about how to change moves
				if(newBeta<= alpha) break;
			}
			return newBeta; //returns the highest score of the possible moves
		}else{ //maximizing player--this is the course of action determined if this is the maximizing player, or black
			/*
			 * These for loops iterate through the board and add all possible pieces to the ArrayList of
			 * moves.  
			 */
                        //AQUI VA LA DEEP_EVALUATE
                       List<int[]> moves = deepEvaluate(b, color);
			
		/*
		 * This for loop cycles through all possible moves and 
		 * calculates a new alpha if the successor board evaluates
		 * to a higher number than what is currently the highest score,
		 * which is stored in alpha.  
		 */
		int newAlpha = alpha;
		for(int i = 0; i < moves.size(); ++i){
			//System.out.println("Move to be evaluated: " + move.toString());
			Board successorBoard = new Board(b); 
                        int[] aux = moves.get(i);
			b.movePiece(aux[0], aux[1], aux[2], aux[3], color);
			newAlpha = Math.max(newAlpha, evaluatePosition(successorBoard, alpha, beta, depth -1, !color)); //think about how to change moves
			if(beta<= newAlpha) break;
		}
		return newAlpha; //returns the highest score of the possible moves
		}
	}
	
	/**
	 * The evaluate(Board b) function is an evaluation function that returns a number based on
	 * how advantageous a board is for the maximizing, black in this case, player. This function
	 * simply iterates through the whole board and gives a weighted number to each piece on the board,
	 * kings naturally yielding the highest number, queens the second, and so on.  The total white score
	 * is subtracted from the total black score to give a full picture of how advantageous the board is 
	 * for a black player.  
	 * @param b
	 * @return int that represents how advantageous a board is
	 */
	public static int evaluate(Board b){
		int ws = 0;
		int bs = 0;

		/*
		 * Iterates through entire board.   
		 */
		for(int i = 0; i<8; i++){
			for(int j=0; j<8; j++){
				if(b.getPieceAt(i, j).getTypeOfPiece() != -1){
                                    Piece piece = b.getPieceAt(i,j);
					if(piece.isColor()){ //true es blanc perqu� som racistes
                                                ws += piece.getValue();
                                        }
					
                                        else { //fals es negre, perque aixi hauria de ser la seva exist�ncia
                                                bs += piece.getValue();
					}
				}
			}
		}
		return bs-ws; //returns blackscore-whitescore, black player tries to maximize, white player tries to minimize
	}

}
