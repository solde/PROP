/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

import java.util.ArrayList;
import Exception.chessException;

/**
 *
 * @author Arnau Santos
 */
public class AIEasy extends AI{
    
    public AIEasy(){
        super.id = "AIEasy";
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
    @Override
    protected int evaluatePosition(Board bo, int alpha, int beta, int depth, boolean color, boolean especulativeTurn, boolean attackColor) throws chessException{ 

    if (depth == 0){
        int evaluation = evaluate(bo, attackColor);

        return evaluation;
    }

    //especulativeTurn will always be true for beta, false otherwise
    if (especulativeTurn){ //enter the beta section
        ArrayList<int[]> moves = super.deepEvaluate(bo, color);  
        int newBeta = beta;
        for (int i = 0; i < moves.size(); ++i){
             Board successorBoard = new Board(bo, true); //Copy of the "original" board
             int[] aux= moves.get(i); //Get a possible movement
             try{successorBoard.movePiece(aux[0], aux[1], aux[2], aux[3], color);}

             catch(chessException e){
                 throw new chessException(e.getMessage());
             }
             return evaluatePosition(successorBoard, alpha, beta, depth -1, !color, !especulativeTurn, attackColor); 
        } 
     }
    else{ //alpha situation
        ArrayList<int[]> moves = deepEvaluate(bo, color);
        int newAlpha = alpha;
        for (int i = 0; i < moves.size(); ++i){

            Board successorBoard = new Board(bo, true);
            int[] aux= moves.get(i);
            try{successorBoard.movePiece(aux[0], aux[1], aux[2], aux[3], color);}

             catch(chessException e){
                 throw new chessException(e.getMessage());
             }
            return evaluatePosition(successorBoard, alpha, beta, depth -1, !color, !especulativeTurn, attackColor);
            
        }
    }
    throw new chessException("No hauries d'estar aqui");
    }
}
    
    
