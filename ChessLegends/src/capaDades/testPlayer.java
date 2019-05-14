/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDades;

import Exception.chessException;
import java.io.IOException;

/**
 *
 * @author David Soldevila
 */
public class testPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, chessException {
        PlayerManager a = new PlayerManager();
        String id = new String();
        for(int i = 0; i < 3; ++i){
            id = "User";
            for(int j = 0; j<i; ++j) {
                id = id.concat("r");
            }
            String info = id + " " + 0 + " " + 0 + " " + "1000" + " " + 0 + " " + "psw" + " ";
            //System.out.println(info);
            a.storePlayer(info);
        }
        id = "User";
        a.erasePlayer(id);
        id = "User";
        System.out.println(a.loadPlayer(id, "psw"));
    }
    
}
