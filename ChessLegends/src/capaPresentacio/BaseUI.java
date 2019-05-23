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

/**
 *
 * @author Daniel Palomo
 */
public class BaseUI extends javax.swing.JFrame {

    private CtrlPresentacio p;
    String name = "popeye";

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

    public void loadProblem(String id) {
        p.loadProblem(id);
    }

    public void modifyProblem(String id) {
        //whatever
    }

    public void changeNewProb() {
        npui = new NewProblemUI(this);
        npui.setname(name);
        jScrollPane1.setViewportView(npui);
    }

    public void changeBoardUI() {
        brui = new BoardUI();
       // jScrollPane1.setViewportView(brui);
       brui.see();
      // close();
    }

    public boolean verify(String fen, String name, String theme, int mov, boolean first, boolean turn) throws chessException {
        p.createProblem(fen, name, theme, mov, turn, turn);
        return p.verify();
    }

    public boolean verifyFEN(String fen) {
        return p.verifyFEN(fen);
    }

    public void save(String fen, String id, String theme, int movs, boolean first, boolean turn) {
        p.save(fen, id, theme, movs, turn, first);
    }

    void createPlayer(String id, String pass1) {
        p.createPlayer(id, pass1);
    }

    boolean passwordsMatch(String pass1, String pass2) {
        return p.passwordsMatch(pass2, pass1);
    }
    
    ArrayList<String> getProblemList() throws IOException{
        return p.getProblemList();
    }
    
    boolean autenticatePlayer(String username, String Password) throws IOException, FileNotFoundException, chessException{
        return p.authenticatePlayer(username, Password);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
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
}
