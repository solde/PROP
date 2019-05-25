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
import Exception.chessException;
import capaDomini.Bishop;
import capaDomini.King;
import capaDomini.Knight;
import capaDomini.NullPiece;
import capaDomini.Pawn;
import capaDomini.Queen;
import capaDomini.Rock;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

public class BoardUI extends JFrame implements MouseListener, MouseMotionListener {

    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    private CtrlPresentacio p;
    private BaseUI b;
    Timer time;
    JLabel timeLabel;
    double milis;
    String fen = ("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");

    public BoardUI() {
        initComp();
    }

    public BoardUI(CtrlPresentacio p, BaseUI b) {
        this.b = b;
        this.p = p;
        initComp();
    }

    /*Aquesta Funcio crea els components de board*/
    private void initComp() {
        Dimension boardSize = new Dimension(600, 600); //chessboard dimension
        Dimension boardSize2 = new Dimension(600, 630); //chessboaurd UI dimension
        //LayeredPane per poder afegir peçes com jlabels a sobre de jpanels
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize2);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Exit button
        JButton exit = new JButton("Exit");
        exit.addActionListener(this::exit);
        exit.setBounds(529, 0, 70, 24);
        layeredPane.add(exit, JLayeredPane.DEFAULT_LAYER);

        //Logout button
        JButton logout = new JButton("Logout");
        logout.addActionListener(this::logout);
        logout.setBounds(445, 0, 80, 24);
        layeredPane.add(logout, JLayeredPane.DEFAULT_LAYER);

        //Resign button
        JButton resign = new JButton("Resign");
        resign.addActionListener(this::resign);
        resign.setBounds(0, 0, 80, 24);
        layeredPane.add(resign, JLayeredPane.DEFAULT_LAYER);

        //Clear button
        JButton restart = new JButton("Restart");
        restart.addActionListener(this::restart);

        restart.setBounds(81, 0, 70, 24);
        layeredPane.add(restart, JLayeredPane.DEFAULT_LAYER);

        //Name Problem
        JLabel nameP = new JLabel("NameSample");
        nameP.setBounds(250, 0, 80, 20);
        layeredPane.add(nameP);
        nameP.setText(b.getProblemName());

        //Timer
        time = new Timer(1, (ActionEvent e) -> {
            actTime();
        });
        time.start();

        timeLabel = new JLabel("0");
        timeLabel.setBounds(330, 0, 100, 20);
        layeredPane.add(timeLabel);

        //Acabem afegint el panel al layeradPane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 25, boardSize.width, boardSize.height);

        //Put and paint the board
        for (int i = 0; i < 64; i++) {
            JPanel bp = new JPanel(new BorderLayout());
            chessBoard.add(bp);

            int row = (i / 8) % 2;
            if (row == 0) {
                bp.setBackground(i % 2 == 0 ? Color.black : Color.white);
            } else {
                bp.setBackground(i % 2 == 0 ? Color.white : Color.black);
            }
        }
        setPieces(this.fen);

        /*
        //Add a few pieces to the board
        JLabel piece = new JLabel(new ImageIcon("/home/vinod/amarexamples/chess.jpg"));
        JPanel panel = (JPanel) chessBoard.getComponent(0);
        panel.add(piece);
        piece = new JLabel(new ImageIcon("/pieces/BK.png"));
        panel = (JPanel) chessBoard.getComponent(15);
        panel.add(piece);
        piece = new JLabel(new ImageIcon("/home/vinod/amarexamples/king.jpg"));
        panel = (JPanel) chessBoard.getComponent(16);
        panel.add(piece);
        piece = new JLabel(new ImageIcon("/home/vinod/amarexamples/camel.jpg"));
        panel = (JPanel) chessBoard.getComponent(20);
        panel.add(piece);*/
    }

    private void exit(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void restart(java.awt.event.ActionEvent evt) {

    }

    private void resign(java.awt.event.ActionEvent evt) {

    }

    private void logout(java.awt.event.ActionEvent evt) {
        b.changeLog();
        time.stop();
        
        // System.exit(0);
    }

    private void actTime() {

        timeLabel.setText(String.format("Time: %02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) milis),
                TimeUnit.MILLISECONDS.toSeconds((long) milis)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) milis))
        ));
        milis += 5.2;
        /* int sec = (int) (milis / 1000) % 60;
        int min= (int) ((milis / (1000 * 60)) % 60);
        milis+=5.5;
        String stime= (min+":"+ sec+":"+milis);
        timeLabel.setText(stime);*/
    }

    @Override
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel) {
            ((JPanel) c).setBorder(BorderFactory.createLineBorder(Color.green, 4));

            // c.setBackground(Color.yellow);
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

    public void see() {
        JFrame frame = new BoardUI(this.p, this.b);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setPieces(String FEN_code) {
        int i = 0, j = 0;
        int verifyer = 0;
        int cont = 0;
        while (i < 8) {
            while (j < FEN_code.length()) {
                if (FEN_code.charAt(j) >= '0' && FEN_code.charAt(j) <= '9') {
                    cont = cont + Character.getNumericValue(FEN_code.charAt(j));
                    verifyer = verifyer + Character.getNumericValue(FEN_code.charAt(j));
                } else {
                    verifyer += 1;
                    int k;
                    k = (i * 7) + cont;
                    if (k > 63) {
                        k = 63;
                    }
                    switch (FEN_code.charAt(j)) {
                        case 'Q':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(0 * 64, 1 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e1) {
                                e1.printStackTrace();
                                System.exit(1);
                            }

                            break;
                        case 'K':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(1 * 64, 1 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e2) {
                                e2.printStackTrace();
                                System.exit(1);
                            }

                            break;
                        case 'B':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(4 * 64, 1 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e3) {
                                e3.printStackTrace();
                                System.exit(1);
                            }

                            // chessBoard[i][cont] = new Bishop(i, cont, true);
                            break;
                        case 'R':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(2 * 64, 1 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e4) {
                                e4.printStackTrace();
                                System.exit(1);
                            }

                            // chessBoard[i][cont] = new Rock(i, cont, true);
                            break;
                        case 'N':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(3 * 64, 1 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e5) {
                                e5.printStackTrace();
                                System.exit(1);
                            }

                            // chessBoard[i][cont] = new Knight(i, cont, true);
                            break;
                        case 'P':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(5 * 64, 1 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e6) {
                                e6.printStackTrace();
                                System.exit(1);
                            }

                            //chessBoard[i][cont] = new Pawn(i, cont, true);
                            break;
                        case 'q':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(0 * 64, 0 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e7) {
                                e7.printStackTrace();
                                System.exit(1);
                            }

                            // chessBoard[i][cont] = new Queen(i, cont, false);
                            break;
                        case 'k':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(1 * 64, 0 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e8) {
                                e8.printStackTrace();
                                System.exit(1);
                            }

                            //chessBoard[i][cont] = new King(i, cont, false);
                            break;
                        case 'b':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(4 * 64, 0 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e9) {
                                e9.printStackTrace();
                                System.exit(1);
                            }

                            // chessBoard[i][cont] = new Bishop(i, cont, false);
                            break;
                        case 'r':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(2 * 64, 0 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e10) {
                                e10.printStackTrace();
                                System.exit(1);
                            }

                            // chessBoard[i][cont] = new Rock(i, cont, false);
                            break;
                        case 'n':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(3 * 64, 0 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e11) {
                                e11.printStackTrace();
                                System.exit(1);
                            }

                            // chessBoard[i][cont] = new Knight(i, cont, false);
                            break;
                        case 'p':
                            try {
                                URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                BufferedImage bi = ImageIO.read(url);

                                JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(5 * 64, 0 * 64, 64, 64)));
                                JPanel panel = (JPanel) chessBoard.getComponent(k);
                                panel.add(piece);

                            } catch (Exception e12) {
                                e12.printStackTrace();
                                System.exit(1);
                            }

                            // chessBoard[i][cont] = new Pawn(i, cont, false);
                            break;
                        default:
                        // chessBoard[i][cont] = new NullPiece(i, cont, true);
                    }
                    ++cont;
                }

                ++j;
            }
            cont = 0;
            ++i;
        }
    }
}
