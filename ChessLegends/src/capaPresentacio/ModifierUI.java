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
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

public class ModifierUI extends JFrame implements MouseListener, MouseMotionListener {

    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    JPanel deleteZone;
    //JPanel lateralPieceZone;
    int xAdjustment;
    int yAdjustment;
    private CtrlPresentacio p;
    private BaseUI b;
    // char realChessBoard[][];
    Timer time;
    JLabel timeLabel;
    double milis;
    int posX, posY;
    boolean playing;
    String init_fen;
    String fen;
    
    //private Pair<Integer, Integer> lateralPiecesPos[2][6];

    public ModifierUI() {
        initComp();
    }

    public ModifierUI(CtrlPresentacio p, BaseUI b, String fen) {
        this.b = b;
        this.p = p;
        this.fen = fen;
        initComp();
    }

    /*Aquesta Funcio crea els components de board*/
    private void initComp() {
        Dimension boardSize = new Dimension(600, 600); //chessboard dimension
        Dimension boardSize2 = new Dimension(800, 630); //chessboard UI dimension
        Dimension deletez = new Dimension (150,150); //delete zone dimension
        
        //LayeredPane per poder afegir peçes com jlabels a sobre de jpanels
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize2);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Exit button
        JButton exit = new JButton("Exit");
        exit.addActionListener(this::exit);
        exit.setBounds(455, 0, 125, 24);
        layeredPane.add(exit, JLayeredPane.DEFAULT_LAYER);

        //Logout button
        JButton logout = new JButton("Logout");
        logout.addActionListener(this::logout);
        logout.setBounds(310, 0, 125, 24);
        layeredPane.add(logout, JLayeredPane.DEFAULT_LAYER);

        //Load Default button
        JButton loadDefault = new JButton("Load Default");
        loadDefault.addActionListener(this::loadDefault);
        loadDefault.setBounds(165, 0, 125, 24);
        layeredPane.add(loadDefault, JLayeredPane.DEFAULT_LAYER);
        
        //Save button
        JButton save = new JButton("Save&Exit");
        save.addActionListener(this::save);
        save.setBounds(20, 0, 125, 24);
        layeredPane.add(save, JLayeredPane.DEFAULT_LAYER);
        
        
        
        

        

        //Acabem afegint el panel al layeredPane
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
        
        //Setting up the delete zone for the pieces
        deleteZone = new JPanel();
        layeredPane.add(deleteZone, JLayeredPane.DEFAULT_LAYER);
        deleteZone.setLayout(new BorderLayout());
        deleteZone.setPreferredSize(deletez);
        deleteZone.setBounds(625,425, deletez.width, deletez.height);
        deleteZone.setBackground(Color.red);

        
        //Delete zone marker
        JLabel nameP = new JLabel("NameSample");
        nameP.setBounds(625, 580, 175, 20);
        layeredPane.add(nameP);
        nameP.setText("Drag&Release here to delete.");
        
        
        init_fen = fen;
        setPieces(this.fen);
        setLateralPieces();

    }
   
    private void updateFen(){
        for (int i = 0; i < 64; i++) {
            if (chessBoard.getComponent(i).getComponentAt(35,35) instanceof JLabel) {
                System.out.print(chessBoard.getComponent(i).getComponentAt(35, 35).getName());
                
            }
            if (i%7 == 0) System.out.println("");
        }
        
    }
    
    private void exit(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    
    private void loadDefault(java.awt.event.ActionEvent evt) {
        this.fen = init_fen;
        setPieces(this.fen);
    }
    
    private void save(java.awt.event.ActionEvent evt){
        updateFen();
        b.fenCode = this.fen;
        dispose();
        
    }
       
    
    private void logout(java.awt.event.ActionEvent evt) {
        b.changeLog();
        this.setVisible(false);
        //dispose tanca la pantalla amb logout
        dispose();
        //System.exit(0);
    }
    
    private void wP(ActionEvent e){
        for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(5 * 64, 1 * 64, 64, 64)));
                    piece.setName("P");
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    piece.setVisible(true);
                    panel.add(piece);
                    
                    //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
        
    }
    
    private void wR(ActionEvent e){
        System.out.println("Hola");
    }
    
    private void wN(ActionEvent e){
        System.out.println("Hola");
    }
    
    private void wB(ActionEvent e){
        System.out.println("Hola");
    }
    
    private void wQ(ActionEvent e){
        System.out.println("Hola");
    }
    
    private void wK(ActionEvent e){
        System.out.println("Hola");
    }
    
    private void bP(ActionEvent e){
        System.out.println("Hola");
    }
    
    private void bR(ActionEvent e){
        System.out.println("Hola");
    }
    
    private void bN(ActionEvent e){
        System.out.println("Hola");
    }
    private void bB(ActionEvent e){
        System.out.println("Hola");
    }
    
    private void bQ(ActionEvent e){
        System.out.println("Hola");
    }
    
    private void bK(ActionEvent e){
        System.out.println("Hola");
    }
    

    @Override
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        posX = e.getX();
        posY = e.getY();
        Point po = new Point(e.getX(), e.getY());
        
        if (chessBoard.getBounds().contains(new Point(e.getX(), e.getY()))){
            
            Component c = chessBoard.findComponentAt(e.getX(), e.getY()); 
            
            if (c instanceof JPanel){
                
                return;
            }
            else {
               
                Point parentLocation = c.getParent().getLocation();
                xAdjustment = parentLocation.x - e.getX();
                yAdjustment = parentLocation.y - e.getY();
                chessPiece = (JLabel) c;
                chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
                chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
                layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);       
            }
            
        }
        /*else if (lateralPieceZone.getBounds().contains(new Point(e.getX(), e.getY()))){
            
            
            Component c = lateralPieceZone.findComponentAt(e.getX(), e.getY());

            if (c instanceof JPanel){
                
                return;
            }
            else {
               
                //Point parentLocation = c.getParent().getLocation();
                //xAdjustment = parentLocation.x - e.getX();
                //yAdjustment = parentLocation.y - e.getY();
                chessPiece = (JLabel) c;
                chessPiece.setLocation(e.getX(), e.getY()); // + xAdjustment  + yAdjustment
                chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
                layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);       
            }
            /*Component c = lateralPieceZone.findComponentAt(po);
            chessPiece = (JLabel) c;
            
            /*if (chessPiece instanceof JPanel){
                System.out.println("Estas clicando el panel");   
                return;
            }
           if  (c instanceof JLabel){
                //System.out.println("Estas clicando el label");
                chessPiece = (JLabel) c;
                chessPiece.setLocation(e.getX(), e.getY());
                chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
                layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);  
            }
            else if (c != null){
                System.out.println("no es null");
            }
            else{
                System.out.println("es null");
                return;
            }
            
        } */
                
        
        else if (deleteZone.getBounds().contains(new Point(e.getX(), e.getY()))){
            System.out.println("Zona de deletear amigo");
        }
        else    System.out.println("Cuidado donde clicas amigo");

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
        //-System.out.println("-------------");
        //-ç.println(e.getX());
        //-System.out.println(e.getY());
        Component c;
        boolean skip = false;
        //Boundary Limits makes you return to the original panel
        if (e.getX() < 0 || e.getX() > 600) {
            c = chessBoard.findComponentAt(posX, posY);
            skip = true;

        } else if (e.getY() < 0 || e.getY() > 600) {
            skip = true;
            posX = e.getX();
            posY = e.getY();
            c = chessBoard.findComponentAt(posX, posY);

        } else {
            c = chessBoard.findComponentAt(e.getX(), e.getY());
        }
        if (c instanceof JLabel) {
                Container p = c.getParent();
                p.remove(0);
                p.add(chessPiece);
        }
         else {
            //pass the interactive movement  to the logical board
            if (!skip) {
               // p.makeMove(posX / 64, posY / 64, e.getX() / 64, e.getY() / 64);
            }
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

    public void see(JFrame frame) {
        //JFrame frame = new ModifierUI(this.p, this.b, fen);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void cleanBoard(){
         for (int i = 0; i < 64; i++) {
            if (chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel) {
                chessBoard.getComponent(i).getComponentAt(30, 30).setVisible(false);
                chessBoard.getComponent(i).getComponentAt(30, 30).getParent().remove(0);
            }
        }
    }
    
     private void setPieces(String FEN_code) {
        cleanBoard();
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
                                    piece.setName("Q");
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
                                    piece.setName("K");
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
                                    piece.setName("B");
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
                                    piece.setName("R");
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
                                    piece.setName("N");
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
                                    piece.setName("P");
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
                                    piece.setName("q");
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
                                    piece.setName("k");
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
                                    piece.setName("b");
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
                                    piece.setName("r");
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
                                    piece.setName("n");
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
                                    piece.setName("p");
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

    private void setLateralPieces(){
        JButton whitePawn = new JButton();
        whitePawn.addActionListener(this::wP);
        whitePawn.setBounds(605, 30, 95,64);
        whitePawn.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(whitePawn, JLayeredPane.DEFAULT_LAYER);
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(5 * 64, 1 * 64, 64, 64)));
            piece.setName("P");
            whitePawn.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton whiteRook = new JButton();
        whiteRook.addActionListener(this::wR);
        whiteRook.setBounds(605, 94, 95,64);
        whiteRook.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(whiteRook, JLayeredPane.DEFAULT_LAYER);
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(2 * 64, 1 * 64, 64, 64)));
            piece.setName("R");
            whiteRook.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton whiteKnight = new JButton();
        whiteKnight.addActionListener(this::wN);
        whiteKnight.setBounds(605, 158, 95,64);
        whiteKnight.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(whiteKnight, JLayeredPane.DEFAULT_LAYER);
         try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(3 * 64, 1 * 64, 64, 64)));
            piece.setName("N");
            whiteKnight.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton whiteBishop = new JButton();
        whiteBishop.addActionListener(this::wB);
        whiteBishop.setBounds(605, 222, 95,64);
        whiteBishop.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(whiteBishop, JLayeredPane.DEFAULT_LAYER);
         try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(4 * 64, 1 * 64, 64, 64)));
            piece.setName("B");
            whiteBishop.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton whiteQueen = new JButton();
        whiteQueen.addActionListener(this::wQ);
        whiteQueen.setBounds(605, 286, 95,64);
        whiteQueen.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(whiteQueen, JLayeredPane.DEFAULT_LAYER);
         try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(0 * 64, 1 * 64, 64, 64)));
            piece.setName("Q");
            whiteQueen.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton whiteKing = new JButton();
        whiteKing.addActionListener(this::wK);
        whiteKing.setBounds(605, 350, 95,64);
        whiteKing.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(whiteKing, JLayeredPane.DEFAULT_LAYER);
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(1 * 64, 1 * 64, 64, 64)));
            piece.setName("K");
            whiteKing.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton blackPawn = new JButton();
        blackPawn.addActionListener(this::bP);
        blackPawn.setBounds(705, 30, 95,64);
        blackPawn.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(blackPawn, JLayeredPane.DEFAULT_LAYER);
         try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(5 * 64, 0 * 64, 64, 64)));
            piece.setName("p");
            blackPawn.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton blackRook = new JButton();
        blackRook.addActionListener(this::bR);
        blackRook.setBounds(705, 94, 95,64);
        blackRook.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(blackRook, JLayeredPane.DEFAULT_LAYER);
         try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(2 * 64, 0 * 64, 64, 64)));
            piece.setName("r");
            blackRook.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton blackKnight = new JButton();
        blackKnight.addActionListener(this::bN);
        blackKnight.setBounds(705, 158, 95,64);
        blackKnight.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(blackKnight, JLayeredPane.DEFAULT_LAYER);
         try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(3 * 64, 0 * 64, 64, 64)));
            piece.setName("n");
            blackKnight.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton blackBishop = new JButton();
        blackBishop.addActionListener(this::bB);
        blackBishop.setBounds(705, 222, 95,64);
        blackBishop.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(blackBishop, JLayeredPane.DEFAULT_LAYER);
         try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(4 * 64, 0 * 64, 64, 64)));
            piece.setName("b");
            blackBishop.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton blackQueen = new JButton();
        blackQueen.addActionListener(this::bQ);
        blackQueen.setBounds(705, 286, 95,64);
        blackQueen.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(blackQueen, JLayeredPane.DEFAULT_LAYER);
         try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(0 * 64, 0 * 64, 64, 64)));
            piece.setName("q");
            blackQueen.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        JButton blackKing = new JButton();
        blackKing.addActionListener(this::bK);
        blackKing.setBounds(705, 350, 95,64);
        blackKing.setBackground(Color.LIGHT_GRAY);
        layeredPane.add(blackKing, JLayeredPane.DEFAULT_LAYER);
         try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);

            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(1 * 64, 0 * 64, 64, 64)));
            piece.setName("k");
            blackKing.add(piece);
            //-System.out.println("pos:" + k + "case:" + FEN_code.charAt(j));
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        
        /*lateralPieceZone = new JPanel();
        layeredPane.add(lateralPieceZone, JLayeredPane.DEFAULT_LAYER);
        
        lateralPieceZone.setName("lateralpiece");
        
        lateralPieceZone.setLayout(new GridLayout(6,2));
        Dimension latpiece = new Dimension(128, 384); //lateral piece zone dimension
        lateralPieceZone.setPreferredSize(latpiece);
        lateralPieceZone.setBounds(636,30, latpiece.width, latpiece.height);

        
        for (int i = 0; i < 12; ++i){
            JPanel bp = new JPanel(new BorderLayout());
            bp.setBackground(Color.LIGHT_GRAY);
            bp.setSize(64,64);
            bp.setName("panel " + (i+1));
            lateralPieceZone.add(bp);
             
        }
        //White pawn
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JButton logout = new JButton("Logout");
            logout.addActionListener(this::logout);
            logout.setBounds(310, 0, 125, 24);
            layeredPane.add(logout, JLayeredPane.DEFAULT_LAYER);

            /*JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(5 * 64, 1 * 64, 64, 64)));
            piece.setBounds(636, 30, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(0);
            panel.add(piece);

            
            
        } catch (Exception e1) {
            e1.printStackTrace();
            System.exit(1);
        }
        //White rook
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(2 * 64, 1 * 64, 64, 64)));
            piece.setBounds(700, 30, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(1);
            panel.add(piece);
            
            
        } catch (Exception e2) {
            e2.printStackTrace();
            System.exit(1);
        }
        
        //White knight
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(3 * 64, 1 * 64, 64, 64)));
            piece.setBounds(636, 94, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(2);
            panel.add(piece);
            
            
        } catch (Exception e3) {
            e3.printStackTrace();
            System.exit(1);
        }
        
        //White Bishop
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(4 * 64, 1 * 64, 64, 64)));
            piece.setBounds(700, 94, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(3);
            panel.add(piece);
            
            
        } catch (Exception e4) {
            e4.printStackTrace();
            System.exit(1);
        }
        
        //White Queen
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(0 * 64, 1 * 64, 64, 64)));
            piece.setBounds(636, 158, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(4);
            panel.add(piece);
            
            
        } catch (Exception e5) {
            e5.printStackTrace();
            System.exit(1);
        }
        
        //White King
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(1 * 64, 1 * 64, 64, 64)));
            piece.setBounds(700, 158, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(5);
            panel.add(piece);
            
            
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //Black Pawn
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(5 * 64, 0 * 64, 64, 64)));
            piece.setBounds(636, 222, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(6);
            panel.add(piece);
            
            
        } catch (Exception e7) {
            e7.printStackTrace();
            System.exit(1);
        }
        
        //Black Rook
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(2 * 64, 0 * 64, 64, 64)));
            piece.setBounds(700, 222, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(7);
            panel.add(piece);
            
            
        } catch (Exception e8) {
            e8.printStackTrace();
            System.exit(1);
        }
        
        //Black Knight
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(3 * 64, 0 * 64, 64, 64)));
            piece.setBounds(636, 286, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(8);
            panel.add(piece);
            
            
        } catch (Exception e9) {
            e9.printStackTrace();
            System.exit(1);
        }
        
        //Black Bishop
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(4 * 64, 0 * 64, 64, 64)));
            piece.setBounds(700, 286, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(9);
            panel.add(piece);
            
            
        } catch (Exception e10) {
            e10.printStackTrace();
            System.exit(1);
        }
        
        //Black Queen
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(0 * 64, 0 * 64, 64, 64)));
            piece.setBounds(636, 350, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(10);
            panel.add(piece);
            
            
        } catch (Exception e11) {
            e11.printStackTrace();
            System.exit(1);
        }
        
        //Black King
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            
            JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(1 * 64, 0 * 64, 64, 64)));
            piece.setBounds(700, 350, 64, 64);
            JPanel panel = (JPanel) lateralPieceZone.getComponent(11);
            panel.add(piece);
            
            
        } catch (Exception e12) {
            e12.printStackTrace();
            System.exit(1);
        }
        */
        
    }
    
   
}
