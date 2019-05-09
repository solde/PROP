/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacio;

//import capaDades.CtrlDades;
import capaDomini.CtrlDomainCreator;
import capaDomini.CtrlDomainGame;

/**
 *
 * @author Daniel Palomo
 */
public class CtrlPresentacio {

    private CtrlDomainGame cg;
    private CtrlDomainCreator cc;
    //private CtrlDades cd;
    private LoginUI lui;
    private ProblemSelectorUI psui;

    public CtrlPresentacio(CtrlDomainGame g) {
        this.cg = g;
        lui = new LoginUI(this);
        psui = new ProblemSelectorUI(this);
    }

    public CtrlPresentacio() {
        lui = new LoginUI(this);
        psui = new ProblemSelectorUI(this);
    }

    public void createPlayer(String id, String pass) {
        // cd.createPlayer(id, pass);
        System.out.println("Player with name: " + id + " created.  Password: " + pass);

    }

    public boolean passwordsMatch(String p, String x) {
        return p.equals(x);
    }

    void setVisiblePS(boolean b) {
        psui.setVisible(b);
    }

    void setVisibleLOG(boolean b) {
        lui.setVisible(b);
    }

}
