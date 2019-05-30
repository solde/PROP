/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaPresentacio;

/**
 *
 * @author Daniel Palomo
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
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    // char realChessBoard[][];
    Timer time;
    JLabel timeLabel;
    double milis;
    int posX, posY, newX, newY, tx, ty;
    boolean playing;
    String fen = ("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    String fen_original;
    boolean turn;
    boolean mov;

    public BoardUI() {
        initComp();
    }

    public BoardUI(CtrlPresentacio p, BaseUI b) {
        this.b = b;
        this.p = p;
        initComp();
    }

     /**
     * @Pre: 
     * @Post: 
     * This function sets the board
     */
    private void initComp() {
        Dimension boardSize = new Dimension(600, 600); //chessboard dimension
        Dimension boardSize2 = new Dimension(600, 630); //chessboard UI dimension
        //LayeredPane per poder afegir peçes com jlabels a sobre de jpanels
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize2);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Exit button
        JButton exit = new JButton("Exit");
        exit.addActionListener(this::exit);
        exit.setBounds(528, 0, 70, 24);
        layeredPane.add(exit, JLayeredPane.DEFAULT_LAYER);

        //Logout button
        JButton logout = new JButton("Logout");
        logout.addActionListener(this::logout);
        logout.setBounds(445, 0, 80, 24);
        layeredPane.add(logout, JLayeredPane.DEFAULT_LAYER);

        //Confirm play button, needed for possible interactive errors
        JButton resign = new JButton("Confirm");
        resign.addActionListener(this::Confirm);
        resign.setBounds(0, 0, 80, 24);
        layeredPane.add(resign, JLayeredPane.DEFAULT_LAYER);

        //Restart button
        JButton restart = new JButton("Restart");
        restart.addActionListener(this::restart);
        restart.setBounds(81, 0, 80, 24);
        layeredPane.add(restart, JLayeredPane.DEFAULT_LAYER);

        //play button
        JButton play = new JButton("Start Game");
        play.addActionListener(this::play);
        play.setBounds(163, 0, 100, 24);
        layeredPane.add(play, JLayeredPane.DEFAULT_LAYER);

        //Name Problem
        JLabel nameP = new JLabel("NameSample");
        nameP.setBounds(270, 0, 80, 20);
        layeredPane.add(nameP);
        nameP.setText(b.getProblemName());

        //Timer
        time = new Timer(10, (ActionEvent e) -> {
            actTime();
        });
        timeLabel = new JLabel("Time: 00:00");
        timeLabel.setBounds(350, 0, 100, 20);
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

    }

    public void setFEN(String fen){
        this.fen=fen;
    }
    private void exit(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void restart(java.awt.event.ActionEvent evt) {
        for (int i = 0; i < 64; i++) {
            if (chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel) {
                chessBoard.getComponent(i).getComponentAt(30, 30).setVisible(false);
                chessBoard.getComponent(i).getComponentAt(30, 30).getParent().remove(0);
            }
        }
        setPieces(fen_original);
        milis = 0;
        mov = false;
        turn = p.getTurn();
    }

    /**
     * @Pre: button Confirm is pressed, there has been a movement
     * @Post: move is translated to the logical board. 
     * This function move the piece in the logical board as done in the interactive one
     */
    private void Confirm(java.awt.event.ActionEvent evt) {
        if (posX == newX && posY == newY) {
            JOptionPane.showMessageDialog(null, "You didn't move any piece");
            return;
        }
        p.makeMove(posX / 75, posY / 75, newX / 75, newY / 75, turn);
        try {
            fen = p.updateBoard();
        } catch (chessException ex) {
            Logger.getLogger(BoardUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        setPieces(fen);
        mov = false;
        // clearBoard();

    }

    /**
     * @Pre: button logout is pressed
     * @Post:game ends, board closes This function allows to quit the game and
     * login again
     */
    private void logout(java.awt.event.ActionEvent evt) {
        b.changeLog();
        time.stop();
        this.setVisible(false);

        // System.exit(0);
    }

    /**
     * @Pre: button Start play is pressed
     * @Post: game has started This function starts the game
     */
    private void play(java.awt.event.ActionEvent evt) {

        if (playing) {
            return;
        }
        playing = true;
        p.start();
        turn = p.getTurn();
        mov = false;
        time.start();
        fen_original = fen;
        try {
            setPieces(p.updateBoard());
        } catch (chessException ex) {
            Logger.getLogger(BoardUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        setPieces(this.fen);

    }

    /**
     * @Pre:
     * @Post: This function manages the timer aspect of the board
     */
    private void actTime() {

        timeLabel.setText(String.format("Time: %02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes((long) milis),
                TimeUnit.MILLISECONDS.toSeconds((long) milis)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) milis))
        ));
        milis += 10;
    }

    /**
     * @Pre:
     * @Post: This function allows to the jlabel to be pressed
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (mov) {
            if (0 == JOptionPane.showConfirmDialog(null, "You have already made a move, do you want to reverse it?")) {
                chessPiece.setVisible(false);
                Component d = chessBoard.findComponentAt(tx, ty);
                Container parent = (Container) d;
                parent.add(chessPiece);
                mov = false;
                chessPiece.setVisible(true);
                return;

            } else {
                return;
            }
        }
        chessPiece = null;
        //fixes ocasional nullpointer
        if (e.getY() < 0 || e.getY() > 600) {
            return;
        }
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        posX = e.getX();
        posY = e.getY();
        if (c instanceof JPanel) {
            // ((JPanel) c).setBorder(BorderFactory.createLineBorder(Color.green, 4));

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

    /**
     * @Pre:Something is pressed
     * @Post:Something can be moved through the board
     * @param e This function moves the chesspiece around
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
    }

    /**
     * This functions computes the drop of the piece to see if the final
     * location is allowed
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }
        chessPiece.setVisible(false);

        Component c;

        //Boundary Limits makes you return to the original panel
        if (e.getX() < 0 || e.getX() > 600) {
            c = chessBoard.findComponentAt(posX, posY);
            newX = posX;
            newY = posY;
        } else if (e.getY() < 0 || e.getY() > 600) {
            c = chessBoard.findComponentAt(posX, posY);
            newX = posX;
            newY = posY;

        } else {
            c = chessBoard.findComponentAt(e.getX(), e.getY());
            newX = e.getX();
            newY = e.getY();
        }
        if (c instanceof JLabel) {
            if (!p.canMove(posX / 75, posY / 75, e.getX() / 75, e.getY() / 75, turn)) {
                Component d = chessBoard.findComponentAt(posX, posY);
                Container parent = (Container) d;
                parent.add(chessPiece);
                JOptionPane.showMessageDialog(null, "This movement is not allowed");
            } else {
                Container p = c.getParent();
                p.remove(0);
                p.add(chessPiece);
                tx = posX;
                ty = posY;
                mov = true;
            }
        } else {
            if (!p.canMove(posX / 75, posY / 75, e.getX() / 75, e.getY() / 75, turn)) {
                Component d = chessBoard.findComponentAt(posX, posY);
                Container parent = (Container) d;
                parent.add(chessPiece);
                JOptionPane.showMessageDialog(null, "This movement is not allowed");
            } else {
                Container parent = (Container) c;
                parent.add(chessPiece);
                tx = posX;
                ty = posY;
                mov = true;
            }
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

    /**
     * This function allows the Jframe to be displayed
     */
    public void see() {
        JFrame frame = new BoardUI(this.p, this.b);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * This function puts Jlabels on the chessboard acording to the FEN code
     */
    private void setPieces(String FEN_code) {
        int i = 0, j = 0, k = 0;
        int t = 0;
        int y = 0;
        boolean nope = false;

        while (i < 8) {
            while (j < 8) {

                if (FEN_code.length() > t && FEN_code.charAt(t) != '/') {
                    if (FEN_code.charAt(t) >= '0' && FEN_code.charAt(t) <= '9') {
                        j += Character.getNumericValue(FEN_code.charAt(t)) - 1;
                        t++;
                    } else {
                        k = (i * 8) + j;
                        switch (FEN_code.charAt(t)) {
                            case 'Q':
                                try {
                                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                    BufferedImage bi = ImageIO.read(url);

                                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(0 * 64, 1 * 64, 64, 64)));
                                    JPanel panel = (JPanel) chessBoard.getComponent(k);
                                    panel.add(piece);
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
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
                                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
                                } catch (Exception e12) {
                                    e12.printStackTrace();
                                    System.exit(1);
                                }

                                // chessBoard[i][cont] = new Pawn(i, cont, false);
                                break;
                            default:
                            // chessBoard[i][cont] = new NullPiece(i, cont, true);
                        }
                        //-System.out.print(FEN_code.charAt(t));
                        t++;
                    }
                    j++;

                } else {
                    nope = true;
                    t++;
                    j += 10;
                }
                //-System.out.print(k + " ");
            }
            if (!nope) {
                i++;
            }
            nope = false;
            j = 0;
            //-System.out.print("i:" + i + " ");
        }

    }
}
