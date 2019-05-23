/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacio;

/**
 *
 * @author Internet
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class testBoard extends JFrame implements MouseListener, MouseMotionListener {

    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    private CtrlPresentacio p;
    private BaseUI b;

    public testBoard() {
        initComp();
    }

    public testBoard(CtrlPresentacio p, BaseUI b) {
        this.b = b;
        this.p = p;
        initComp();
    }

    /*Aquesta Funcio crea els components de board*/
    private void initComp() {
        Dimension boardSize = new Dimension(600, 600); //board dimension
        Dimension boardSize2 = new Dimension(600, 630); //App dimension
        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize2);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Exit button
        JButton exit = new JButton("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit(evt);
            }
        });
        exit.setBounds(529, 0, 70, 24);
        layeredPane.add(exit, JLayeredPane.DEFAULT_LAYER);

        //Logout button
        JButton logout = new JButton("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout(evt);
            }
        });
        logout.setBounds(445, 0, 80, 24);
        layeredPane.add(logout, JLayeredPane.DEFAULT_LAYER);
      
        //Resign button
        JButton resign = new JButton("Resign");
        resign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resign(evt);
            }
        });
        resign.setBounds(0, 0, 80, 24);
        layeredPane.add(resign, JLayeredPane.DEFAULT_LAYER);
     
        //Clear button
        JButton clear = new JButton("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear(evt);
            }
        });

        clear.setBounds(81, 0, 70, 24);
        layeredPane.add(clear, JLayeredPane.DEFAULT_LAYER);
                
        //Name Problem
        
        JLabel nameP= new JLabel("NameSample");
        nameP.setBounds(200,0,80,20);
        layeredPane.add(nameP);
        
        
        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 25, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.black : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.black);
            }
        }

        //Add a few pieces to the board
        JLabel piece = new JLabel(new ImageIcon("/home/vinod/amarexamples/chess.jpg"));
        JPanel panel = (JPanel) chessBoard.getComponent(0);
        panel.add(piece);
        piece = new JLabel(new ImageIcon("/home/vinod/amarexamples/chess1.jpg"));
        panel = (JPanel) chessBoard.getComponent(15);
        panel.add(piece);
        piece = new JLabel(new ImageIcon("/home/vinod/amarexamples/king.jpg"));
        panel = (JPanel) chessBoard.getComponent(16);
        panel.add(piece);
        piece = new JLabel(new ImageIcon("/home/vinod/amarexamples/camel.jpg"));
        panel = (JPanel) chessBoard.getComponent(20);
        panel.add(piece);
    }

    private void exit(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void clear(java.awt.event.ActionEvent evt) {

    }

    private void resign(java.awt.event.ActionEvent evt) {

    }

    private void logout(java.awt.event.ActionEvent evt) {
        b.changeLog();
        System.exit(0);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel) {
            c.setBackground(Color.yellow);
            return;
        }

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    //Move the chess piece around
    @Override
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) {
            return;
        }
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    //Drop the chess piece back onto the chess board
    @Override
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }

        chessPiece.setVisible(false);
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JLabel) {
            Container parent = c.getParent();
            parent.remove(0);
            parent.add(chessPiece);
        } else {
            Container parent = (Container) c;
            parent.add(chessPiece);
        }

        chessPiece.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        JFrame frame = new testBoard();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
