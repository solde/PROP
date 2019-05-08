/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author David Soldevila
 */
public class testWrite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String str = "World\n";
        BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt", true));
        writer.append(str);     
        writer.close();
    }
    
}
