/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacio;

import Exception.chessException;
import java.awt.PopupMenu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia
 */
public final class AIcompUI extends javax.swing.JPanel {

    private BaseUI b;
    private int totalGames;
    private int Wwins;
    
    private int WP;
    private int BP;

    /**
     * Creates new form ProblemSelectUI
     */
    public AIcompUI() {
        initComponents();
    }
    
    public AIcompUI(BaseUI b, int WP, int BP) {
        initComponents();
        this.b = b;
        this.WP = WP;
        this.BP = BP;
        initListOfProblems();
    }

    public void setname(String Text) {
        jLabel1.setText(Text);
    }
    
    public void initListOfProblems(){
        ArrayList<String> problemList = new ArrayList<String>();
        try{
            problemList = b.getProblemList();
        }
        catch(Exception e){
            ;
        }
        for(int i = 0; i < problemList.size(); ++i){
            jProblemSelect.addItem(problemList.get(i));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        playButton = new javax.swing.JButton();
        jProblemSelect = new javax.swing.JComboBox<>();
        deleteButton = new javax.swing.JButton();
        winBar = new javax.swing.JProgressBar();
        Wlabel = new javax.swing.JLabel();
        Blabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        winnerLabel = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        jTextField1.setText("jTextField1");

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "AI Competiton", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        setPreferredSize(new java.awt.Dimension(455, 338));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Problem Selector", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel2.setText("Select a problem and click play to simulate.");

        playButton.setText("Play");
        playButton.setMaximumSize(new java.awt.Dimension(67, 23));
        playButton.setMinimumSize(new java.awt.Dimension(65, 23));
        playButton.setPreferredSize(new java.awt.Dimension(67, 23));
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        jProblemSelect.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jProblemSelectItemStateChanged(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(255, 0, 0));
        deleteButton.setText("Finish");
        deleteButton.setMaximumSize(new java.awt.Dimension(67, 23));
        deleteButton.setPreferredSize(new java.awt.Dimension(67, 23));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        winBar.setBackground(new java.awt.Color(51, 0, 51));
        winBar.setForeground(new java.awt.Color(255, 255, 255));
        winBar.setValue(50);

        Wlabel.setText("0");

        Blabel.setText("0");

        jLabel6.setText("White");

        jLabel7.setText("Black");

        winnerLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        winnerLabel.setText("--- Wins");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addComponent(winBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Wlabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Blabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jProblemSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(winnerLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProblemSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(winnerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(winBar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Wlabel)
                    .addComponent(Blabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setText("Logout");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft PhagsPa", 2, 12)); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel3.setText("User:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        //exit
        b.close();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //logout
        if (0 == JOptionPane.showConfirmDialog(null, "Do you want to logout?")) {
            b.changeLog();

        } else {
            return;
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        //load Problem
        int i = jProblemSelect.getSelectedIndex();
        System.out.println(i);
        String id = jProblemSelect.getItemAt(i);
        System.out.println(id);
        try {
            b.loadProblem(id);
        } catch (IOException ex) {
            Logger.getLogger(AIcompUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (chessException ex) {
            Logger.getLogger(AIcompUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!b.isVerified()){
            JOptionPane.showMessageDialog(null, "This problem is not verified");
            return;
        }
        boolean win;
        try {
            win = b.playAIgame(WP, BP);
            updateLabels(win);
        } catch (chessException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Cannot play the game");
        }
    }//GEN-LAST:event_playButtonActionPerformed

    private void jProblemSelectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jProblemSelectItemStateChanged
    }//GEN-LAST:event_jProblemSelectItemStateChanged

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (0 == JOptionPane.showConfirmDialog(null, "Do you want to finish?")) {
            try {
                b.changeProblem();
            } catch (IOException ex) {
                Logger.getLogger(AIcompUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            return;
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    void updateLabels(boolean win){
        this.totalGames += 1;
        winBar.setMaximum(this.totalGames);
        if(win){
            this.Wwins += 1;
            winnerLabel.setText("White wins");
            winBar.setValue(Wwins);
        }
        else{
            winnerLabel.setText("Black wins");
            winBar.setValue(Wwins);
        }
        double W = winBar.getPercentComplete();
        double B = 100-W;
        Wlabel.setText(Double.toString(W)+"%");
        Blabel.setText(Double.toString(B)+"%");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Blabel;
    private javax.swing.JLabel Wlabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> jProblemSelect;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton playButton;
    private javax.swing.JProgressBar winBar;
    private javax.swing.JLabel winnerLabel;
    // End of variables declaration//GEN-END:variables
}
