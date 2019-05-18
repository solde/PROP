package capaPresentacio;

import Exception.chessException;
import capaDomini.CtrlDomainCreator;
import capaDomini.CtrlDomainGame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Palomo
 */
public class CtrlPresentacio {

    private CtrlDomainGame cg;
    private CtrlDomainCreator cc;

    private BaseUI bui;

    public CtrlPresentacio(CtrlDomainGame g) {
        this.cg = g;

        bui = new BaseUI(this);
    }

    public CtrlPresentacio() {
        bui = new BaseUI(this);
    }

    public CtrlPresentacio(CtrlDomainCreator cdc) {
        this.cc = cdc;
        bui = new BaseUI(this);
    }

    public void createPlayer(String id, String pass) {
        cc.createNewPlayerTest(id, pass);
        try {
            cc.storeNewPlayer();
        } catch (IOException | chessException ex) {
            Logger.getLogger(CtrlPresentacio.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Player with name: " + id + " created.  Password: " + pass);

    }

    public void view() {
        bui.view();
    }

    public boolean passwordsMatch(String p, String x) {
        return p.equals(x);
    }

    public boolean IDExists(String name) {
        //storeplayer(name)
        return false;
    }

    public void loadProblem(String id) {
        //NOT DONE
    }

    public void createProblem(String fen, String name, String theme, int mov, boolean color, boolean turn) {
        //       cc.createNewProblemTest(fen,name,mov,theme,color,turn);
    }

    public boolean verify() throws chessException {
        //return cc.verifyProblem();
        return false;
    }

    public ArrayList<String> getProblemList() {
        ArrayList<String> a = new ArrayList();
        return a;
    }

    public boolean verifyFEN(String fen) {
        return cc.verifyFen(fen);
    }

    void save(String fen, String id, String theme, int movs, boolean turn, boolean first) {
       //not done
    }
}