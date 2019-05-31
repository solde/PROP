/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacio;

import capaDomini.CtrlDomainCreator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mainChessLegends {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //CtrlDomainCreator cc;
                try {
                    //cc = new CtrlDomainCreator();
                    CtrlPresentacio cp = new CtrlPresentacio();
                    cp.view();

                } catch (IOException ex) {
                    Logger.getLogger(mainChessLegends.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }
}
