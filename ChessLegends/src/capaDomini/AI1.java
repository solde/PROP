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
    
    public static void printBoard(Board B){
        System.out.println("   0  1  2  3  4  5  6  7");
        for(int x = 0; x < 8; ++x){
            System.out.print(x + "|");
            for(int y = 0; y < 8; ++y){
                if(B.getPieceAt(x, y).isColor() && B.getPieceAt(x, y).getTypeOfPiece() != -1)System.out.print(" " + B.getPieceAt(x, y).getTypeOfPiece() + "|");
                else if(!B.getPieceAt(x, y).isColor() && B.getPieceAt(x, y).getTypeOfPiece() != -1)System.out.print("-" + B.getPieceAt(x, y).getTypeOfPiece() + "|");
                else System.out.print("  " + "|");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
    
    
    public static Board makeMove(Board b, boolean color, int depth) throws chessException {   
        
		int[] bestMove; //keeps track of the best possible move AI has available
		int bestMoveScore; //score of that best move
		
		ArrayList<Board> possibleBoards = new ArrayList<>(); //keeps track of the possible boards (boards with the possible moves made on them)
		ArrayList<int[]> moves = new ArrayList<>(); //keeps track of all possible moves 
                ArrayList<Integer> moveScore = new ArrayList<>();
		
                for(int i = 0; i<8; i++){
			for(int j=0; j<8; j++){
                            Piece piece = b.getPieceAt(i,j);
                            if(piece.getTypeOfPiece() != -1 && (piece.isColor() == color)){

                                ArrayList<Pair<Integer, Integer>> possMovs = piece.get_poss_mov(b);
                                for(int x = 0; x < possMovs.size(); ++x){
                                    int[] mov = new int[4];
                                    mov[0] = piece.getX();
                                    mov[1] = piece.getY();
                                    mov[2] = possMovs.get(x).getKey();
                                    mov[3] = possMovs.get(x).getValue();


                                    moves.add(mov); //Adds the possible movement to a poss. movs. list
                                    Board altBoard = new Board(b, true); //initialices an alternative space to evaluate

                                    try{altBoard.movePiece(mov[0], mov[1], mov[2], mov[3], color); //moves piece on the alternative board
                                    }
                                    catch(chessException exc){
                                        System.out.println("Oops.");
                                        System.out.println("Source: "+ mov[0] + " " + mov[1]);
                                        System.out.println("Destination: "+ mov[2] + " " + mov[3]);
                                        printBoard(altBoard);
                                        throw new chessException(exc.getMessage());
                                    }

                                    possibleBoards.add(altBoard); //adds the alternative board to the possible boards list
                                }
                                    
                            }
                            else {
                            }
                        }
		}
		//initializes bestMoveScore to compare
                //System.out.println(possibleBoards.size());
                if (possibleBoards.size() == 0) printBoard(b);
                bestMoveScore = evaluatePosition(possibleBoards.get(0), Integer.MIN_VALUE, Integer.MAX_VALUE, depth, !color, true, color); //1 is the depth, explained in evaluate position "header"
		moveScore.add(bestMoveScore);
		//call evaluateposition on each move
		//keep track of the move with the best score
		
                //It's important to know that moveScore is aligned with move.
                
                for(int i = 1; i<possibleBoards.size(); i++){
  
                            int j = evaluatePosition(possibleBoards.get(i), Integer.MIN_VALUE, Integer.MAX_VALUE, depth, !color, true, color); 
                            moveScore.add(j);
                            if(j >= bestMoveScore){
                                    bestMoveScore = j;
                            }

		}
                ArrayList<int[]> bestMoves = new ArrayList<>(); //keeps track of all possible moves 
                
                for (int i = 0; i < moveScore.size(); ++i){
                    if (moveScore.get(i) == bestMoveScore) {
                        bestMoves.add(moves.get(i));
                        //System.out.println("move added");
                    }
                }
                
                Random generator = new Random();
                int index = generator.nextInt(bestMoves.size());
		bestMove = bestMoves.get(index);
                
                
                
		
		b.movePiece(bestMove[0], bestMove[1] , bestMove[2], bestMove[3], color); //theorically now is well implemented, based on the best move (random or not)
                return b;
	}
	
 
    private static ArrayList <int[]> deepEvaluate( Board b, boolean color){
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
        
	

        private static int evaluatePosition(Board bo, int alpha, int beta, int depth, boolean color, boolean especulativeTurn, boolean attackColor) throws chessException{ 

           if (depth == 0){
               int evaluation = evaluate(bo, attackColor);
               //System.out.println("Puntuació: "+ evaluation);
               return evaluation;
           }
           
           //especulativeTurn will always be true for beta, false otherwise
           if (especulativeTurn){ //enter the beta section
               ArrayList<int[]> moves = deepEvaluate(bo, color);  
               int newBeta = beta;
               for (int i = 0; i < moves.size(); ++i){
                    Board successorBoard = new Board(bo, true); //Copy of the "original" board
                    int[] aux= moves.get(i); //Get a possible movement
                    try{successorBoard.movePiece(aux[0], aux[1], aux[2], aux[3], color);}
                   
                    catch(chessException e){
                       
                        System.out.println("Ooops. Move tried:");
                        System.out.println(aux[0]+" "+ aux[1]);
                        System.out.println(aux[2]+" "+ aux[3]);
                        System.out.println("the board was:");
                        printBoard(successorBoard);
                        if (especulativeTurn) System.out.println("beta situation");
                        else System.out.println("alpha situation");
                        throw new chessException(e.getMessage());
                    }
                    newBeta = Math.min(newBeta, evaluatePosition(successorBoard, alpha, beta, depth -1, !color, !especulativeTurn, attackColor)); 
                    if(newBeta<= alpha) break;
                }
                return newBeta; //returns the highest score of the possible moves
            }
           else{ //alpha situation
               ArrayList<int[]> moves = deepEvaluate(bo, color);
               int newAlpha = alpha;
               for (int i = 0; i < moves.size(); ++i){
                   System.out.println(bo.getFEN_code());
                   Board successorBoard = new Board(bo, true);
                   int[] aux= moves.get(i);
                   try{successorBoard.movePiece(aux[0], aux[1], aux[2], aux[3], color);}
                   
                    catch(chessException e){
                        System.out.println("Ooops. Move tried:");
                        System.out.println(aux[0]+" "+ aux[1]);
                        System.out.println(aux[2]+" "+ aux[3]);
                        System.out.println("the board was:");
                        printBoard(successorBoard);
                        if (especulativeTurn) System.out.println("beta situation");
                        else System.out.println("alpha situation");
                        throw new chessException(e.getMessage());
                    }
                   newAlpha = Math.max(newAlpha, evaluatePosition(successorBoard, alpha, beta, depth -1, !color, !especulativeTurn, attackColor));
                   if(beta<= newAlpha) break;
               }
               return newAlpha;
           }
           }
   

	public static int evaluate(Board b, boolean color) throws chessException{
		int attackScore = 0;
		int defendScore = 0;
                
                        for(int i = 0; i<8; i++){
                            for(int j=0; j<8; j++){
                                    if(b.getPieceAt(i, j).getTypeOfPiece() != -1){
                                        Piece piece = b.getPieceAt(i,j);
                                            if(piece.isColor() == color){ //true es blanc perqu� som racistes
                                                    attackScore += piece.getValue();
                                            }

                                            else { //fals es negre, perque aixi hauria de ser la seva exist�ncia
                                                    defendScore += piece.getValue();
                                            }
                                    }
                            }
                    }
                    return attackScore-defendScore; //returns blackscore-whitescore, black player tries to maximize, white player tries to minimize
                }
	

}
