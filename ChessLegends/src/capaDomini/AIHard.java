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
 * @author Arnau Santos
 */
public class AIHard extends AI {

    private static int AI1_ID = 2;
    private static final long serialVersionUID = 1L;
    
    public AIHard(){
        super.id = "AIHard";
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
        //System.out.println("Puntuació: "+ evaluation);
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

                 System.out.println("Ooops. Move tried:");
                 System.out.println(aux[0]+" "+ aux[1]);
                 System.out.println(aux[2]+" "+ aux[3]);
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
            //System.out.println(bo.getFEN_code());
            Board successorBoard = new Board(bo, true);
            int[] aux= moves.get(i);
            try{successorBoard.movePiece(aux[0], aux[1], aux[2], aux[3], color);}

             catch(chessException e){
                 System.out.println("Ooops. Move tried:");
                 System.out.println(aux[0]+" "+ aux[1]);
                 System.out.println(aux[2]+" "+ aux[3]);
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
   
    
}