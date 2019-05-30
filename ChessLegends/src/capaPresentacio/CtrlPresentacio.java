package capaPresentacio;

import Exception.chessException;
import capaDomini.CtrlDomainCreator;
import capaDomini.CtrlDomainGame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

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

    public CtrlPresentacio() throws IOException {
        bui = new BaseUI(this);
        this.cc = new CtrlDomainCreator();
        this.cg = new CtrlDomainGame();
    }

    public CtrlPresentacio(CtrlDomainCreator cdc) {
        this.cc = cdc;
        bui = new BaseUI(this);
    }

    public void createPlayer(String id, String pass) throws chessException, IOException {
        cc.createNewPlayer(id, pass);
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

    public void loadProblem(String id) throws IOException, FileNotFoundException, chessException {
        cg.loadProblem(id);
    }

    public void createProblem(String fen, String name, String theme, int mov, boolean color, boolean turn) throws IOException, chessException {
        saveProblem(fen, name, theme, mov, color, color, false);
    }

    public boolean verify(String fen, String name, String theme, int mov, boolean turn, boolean color) throws chessException {
        boolean ret = cc.verifyProblem(fen, name, theme, mov, turn, color);
        return ret;
    }

    public ArrayList<String> getProblemList() throws IOException {
        ArrayList<String> a = new ArrayList();
        a = cc.getProblemList();
        return a;
    }

    public boolean verifyFEN(String fen) {
        return cc.verifyFen(fen);
    }

    void saveProblem(String fen, String id, String theme, int movs, boolean turn, boolean first, boolean v) throws IOException, chessException {
        cc.createNewProblem(fen, id, theme, movs, turn, first, v);
    }

    boolean authenticatePlayer(String Username, String Password) throws IOException, FileNotFoundException, chessException {
        boolean ret = cg.authPlayer1(Username, Password);
        System.out.println(ret);
        return ret;
    }

    String getProblemInfo(String id) throws IOException, FileNotFoundException, chessException {
        return cc.getProblemInfo(id);
    }

    void makeMove(int x, int y, int xnew, int ynew, boolean turn) {
        cg.movePiece(x, y, xnew, ynew, turn, 0);
    }

    public String updateBoard() throws chessException {
        //return ("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        return cc.getFenCodeOfBoard();
    }

    public boolean canMove(int x, int y, int xnew, int ynew, boolean turn) {
        return cg.canMov(x, y, xnew, ynew);
    }

    public void setPlayer(int i, int type) {
        cg.setPlayer(i, type);
    }

    public void initGame() throws chessException {
        cg.initGame();
    }

    public ArrayList<Pair<Long, String>> getTop3(String user) {
        ArrayList<Pair<Long, String>> full = cg.getRanking();
        ArrayList<Pair<Long, String>> ret = new ArrayList<Pair<Long, String>>();
        for (int i = 0; i < 4 && i < full.size(); ++i) {
            ret.add(full.get(i));
        }
        for (int i = 0; i < full.size(); ++i) {
            if (full.get(i).getValue() == user) {
                ret.add(full.get(i));
            }
        }
        return ret;
    }

    public void deleteProblem(String id) throws IOException, chessException {
        cc.deleteProblem(id);
    }

    public boolean getTurn() {
        return cc.getFirstTurn();
    }

    boolean isVerified() {
        return cg.isVerified();
    }

    public void start() {
        try {
            cg.initGame();
        } catch (chessException ex) {
            Logger.getLogger(CtrlPresentacio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean playAIgame() throws chessException{
        cg.initAIComp();
        return cg.letsPlay();
    }
}
