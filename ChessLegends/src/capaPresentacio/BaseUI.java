/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacio;

import Exception.chessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Daniel Palomo
 */
public class BaseUI extends javax.swing.JFrame {

    private CtrlPresentacio p;
    public String name = "Pipo"; //this will indicate the name of the player, setting is public for easier code
    public String pName = "Memeverso"; //same, but for problem name

    /**
     * Creates new form BaseUI
     */
    public BaseUI() {
        initComponents();
    }

    public BaseUI(CtrlPresentacio p) {
        this.p = p;
        initComponents();
    }

    public void view() {
        log = new LoginUI(this);
        jScrollPane1.setViewportView(log);
        this.revalidate();
        this.setVisible(true);

    }

    public CtrlPresentacio getP() {
        return p;
    }

    public void setP(CtrlPresentacio p) {
        this.p = p;
    }

    public void changeLog() {

        log = new LoginUI(this);
        jScrollPane1.setViewportView(log);
    }

    public void changeProblem() throws IOException {
        psui = new ProblemSelectUI(this);
        psui.setname(name);
        jScrollPane1.setViewportView(psui);
    }

    public void close() {
        System.exit(0);
    }

    public void loadProblem(String id) throws IOException, FileNotFoundException, chessException {
        p.loadProblem(id);
    }

    public void modifyProblem(String id) {
        npui = new NewProblemUI(this, id);
        npui.setname(name);
        jScrollPane1.setViewportView(npui);
    }

    public void changeNewProb() {
        npui = new NewProblemUI(this);
        npui.setname(name);
        jScrollPane1.setViewportView(npui);
    }

    public void changeBoardUI() {
        brui = new BoardUI(p, this);
        // jScrollPane1.setViewportView(brui);
        brui.see();
        // close();
    }

    public void changeModifyUI() {
        modifUI = new ModifierUI(p, this);
        // jScrollPane1.setViewportView(brui);
        modifUI.see();
        //close();
    }

    public void changeLoadUI() {
        loadUI = new LoadingUI(this);
        jScrollPane1.setViewportView(loadUI);
    }

    public void changeSettingsUI() {
        gcui = new GameConfigUI(this);
        gcui.setName(name);
        jScrollPane1.setViewportView(gcui);
    }
    
    public void changeAIcompUI() {
        aiUI = new AIcompUI(this);
        aiUI.setName(name);
        jScrollPane1.setViewportView(aiUI);
    }

    public boolean verify(String fen, String name, String theme, int mov, boolean first, boolean turn) throws chessException, IOException {
        return p.verify(fen, name, theme, mov, turn, turn);
    }

    public boolean verifyFEN(String fen) {
        return p.verifyFEN(fen);
    }

    public void saveProblem(String fen, String id, String theme, int movs, boolean first, boolean turn, boolean v) throws IOException, chessException {
        p.saveProblem(fen, id, theme, movs, turn, first, v);
    }

    void createPlayer(String id, String pass1) throws chessException, IOException {
        System.out.println(id + " " + pass1);
        p.createPlayer(id, pass1);
    }

    boolean passwordsMatch(String pass1, String pass2) {
        return p.passwordsMatch(pass2, pass1);
    }

    ArrayList<String> getProblemList() throws IOException {
        return p.getProblemList();
    }

    boolean autenticatePlayer(String username, String Password) throws IOException, FileNotFoundException, chessException {
        return p.authenticatePlayer(username, Password);
    }

    public String getProblemName() {
        return this.pName;
    }

    public String getPlayerName() {
        return this.name;
    }

    public String getProblemBriefInfo(String id) throws IOException, FileNotFoundException, chessException {
        String info = p.getProblemInfo(id);
        String aInfo[] = info.split(" ");
        try{
            System.out.println(aInfo[3]);
            String ret = aInfo[3];
            ret = ret.concat("#");
            ret = ret.concat(aInfo[2]);
            ret = ret.concat("#");
            ret = ret.concat(aInfo[4]);
            return ret;
        }
        catch(Exception e){}
        return "";
    }

    public String getProblemFen(String id) throws IOException, FileNotFoundException, chessException {
        String info = p.getProblemInfo(id);
        String aInfo[] = info.split(" ");
        return aInfo[1];

    }

    public void setPlayer1(int index) {
        p.setPlayer(0, index);
    }

    public void setPlayer2(int index) {
        p.setPlayer(1, index);
    }

    public void initGame() throws chessException {
        p.initGame();
    }
    
    public ArrayList<Pair<Long, String>> getTop3(){
        return p.getTop3(this.name);
    }

    public boolean isVerified() {
        return p.isVerified();
    }

    public void deleteProblem(String id) throws IOException, chessException {
        p.deleteProblem(id);
    }
    
    public boolean playAIgame() throws chessException{
        return p.playAIgame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(520, 420));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(455, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private LoginUI log;
    private ProblemSelectUI psui;
    private NewProblemUI npui;
    private BoardUI brui;
    private GameConfigUI gcui;
    private LoadingUI loadUI;
    private ModifierUI modifUI;
    private AIcompUI aiUI;
}
