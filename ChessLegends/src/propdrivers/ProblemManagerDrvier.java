/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import Exception.chessException;
import capaDades.ProblemManager;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author David Soldevila
 */
public class ProblemManagerDrvier {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws Exception.chessException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, chessException {
        ProblemManager PM;
        PM = new ProblemManager();
        PM.storeProblem("pipo");
        String test = PM.loadProblem("piop");
        System.out.println(test);
    }
    
}
