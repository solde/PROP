/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini;

/**
 *
 * @author Daniel Palomo
 */
public class AI1 extends Player {

    private static int AI1_ID = 2;
    
    public AI1(){
        System.out.println("Not implemented");
    }

    // minmax funciton: it's used to calculate the next movment based on the
    // minmax algorithm. currentDepth is the depth of the actual node.
    // nodePointer is a pointer to a node. maxTurn defines if you
    // want the highes score or the lowest. score[] is contains the scores of 
    // every node. targetDepth is the maximum depth to search.
    /*
    private int minmax(int currentDepth, int nodeId, boolean maxTurn, int score[], int targetDepth) {
        if (currentDepth == targetDepth) {
            return scores[nodeIndex];
        }
        if (isMax) {
            int piecesID[] = playerController.getPiecesID(AI2_ID);
            for (int i = 0; i < piecesID.length(); ++i) {
                int piecesMovments[] = pieceController.getPossibleMovments(piecesID[i])
                for (int j = 0; j < piecesMOvments[j]; ++j) {

                }
            }
        }
    }*/
    //return Math.max(minimax(depth+1, nodeIndex*2, false, scores, h), minimax(depth+1, nodeIndex*2 + 1, false, scores, h)); 

    private void calculate_mov() {
        System.out.println("Calculant el moviment");
    }

}
