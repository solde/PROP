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
    int xAdjustment;
    int yAdjustment;
    private CtrlPresentacio p;
    private BaseUI b;
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
        
        //Clear Board button
        JButton clear = new JButton("Clear Board");
        clear.addActionListener(this::clear);
        clear.setBounds(600, 0, 125, 24);
        layeredPane.add(clear,JLayeredPane.DEFAULT_LAYER);  
        
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
        
        
        init_fen = b.fenCode;
        setPieces(this.fen);
        setLateralPieces();

    }
   
    private String updateFen(){
        String fen = "";
        int aux = 0;
        for (int i = 1; i <= 64; i++) {
            if (chessBoard.getComponent(i-1).getComponentAt(0,0) instanceof JLabel) {
                if( aux != 0){
                    fen = fen +(Integer.toString(aux));
                }
                fen = fen + chessBoard.getComponent(i-1).getComponentAt(35, 35).getName();
                aux = 0;
            }
            else aux++;
            if (i%8 == 0){
                if( aux != 0){
                    fen = fen +(Integer.toString(aux));
                }
                if (i != 64){
                    fen = fen + ("/");
                aux = 0;
                }

            }
           
        }
        //System.out.println(fen);
        return fen;
    }
    
    private void exit(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    
    private void loadDefault(java.awt.event.ActionEvent evt) {
        //System.out.println(init_fen);
        this.fen = init_fen;
        setPieces(this.fen);
    }
    
    private void save(java.awt.event.ActionEvent evt){
        this.fen = updateFen();
        b.fenCode = this.fen;
        dispose();
        
    }
    
    private void clear(java.awt.event.ActionEvent evt){
        this.fen = "8/8/8/8/8/8/8/8";
        setPieces(this.fen);
    }
    
    private void logout(java.awt.event.ActionEvent evt) {
        b.changeLog();
        this.setVisible(false);
        //dispose tanca la pantalla amb logout
        dispose();
    }
    
    private void wP(ActionEvent e){
        for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(5 * 64, 1 * 64, 64, 64)));
                    piece.setName("P");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    
                    
                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
        
    }
    
    private void wR(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(2 * 64, 1 * 64, 64, 64)));
                    piece.setName("R");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    
                    
                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        };
    }
    
    private void wN(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(3 * 64, 1 * 64, 64, 64)));
                    piece.setName("N");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    
                    
                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
    }
    
    private void wB(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(4 * 64, 1 * 64, 64, 64)));
                    piece.setName("B");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    
                    
                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
    }
    
    private void wQ(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(0 * 64, 1 * 64, 64, 64)));
                    piece.setName("Q");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    
                    
                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
    }
    
    private void wK(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(1 * 64, 1 * 64, 64, 64)));
                    piece.setName("K");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    
                    
                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
    }
    
    private void bP(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(5 * 64, 0 * 64, 64, 64)));
                    piece.setName("p");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    
                    
                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
    }
    
    private void bR(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(2 * 64, 0 * 64, 64, 64)));
                    piece.setName("r");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    
                    
                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
    }
    
    private void bN(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(3 * 64, 0 * 64, 64, 64)));
                    piece.setName("n");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    
                    
                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
    }
    private void bB(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(4 * 64, 0 * 64, 64, 64)));
                    piece.setName("b");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    

                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
    }
    
    private void bQ(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(0 * 64, 0 * 64, 64, 64)));
                    piece.setName("q");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    

                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
    }
    
    private void bK(ActionEvent e){
         for (int i = 0; i < 64; i++){
            if (!(chessBoard.getComponent(i).getComponentAt(30, 30) instanceof JLabel)) {
                try {
                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                    BufferedImage bi = ImageIO.read(url);

                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(1 * 64, 0 * 64, 64, 64)));
                    piece.setName("k");
                    piece.setVisible(true);
                    piece.setSize(75,75);
                    JPanel panel = (JPanel) chessBoard.getComponent(i);
                    panel.add(piece);
                    
                    
                    this.fen = updateFen();
                    setPieces(this.fen);
                    

                } catch (Exception e6) {
                    e6.printStackTrace();
                    System.exit(1);
                }
             break;   
            }
        }
    }
    

    @Override
    public void mousePressed(MouseEvent me) {
        
        chessPiece = null;
        
        posX = me.getX();
        posY = me.getY();
        
        
        if (chessBoard.getBounds().contains(new Point(me.getX(), me.getY()))){
            
            Component c = chessBoard.findComponentAt(me.getX(), me.getY()); 
            
            if (c instanceof JPanel){
                
                return;
            }
            else {
               
                Point parentLocation = c.getParent().getLocation();
                xAdjustment = parentLocation.x - me.getX();
                yAdjustment = parentLocation.y - me.getY();
                chessPiece = (JLabel) c;
                chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
                chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
                layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);       
            }
            
        }
        else return;

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

        Component c;
        boolean skip = false;
        //Boundary Limits makes you return to the original panel
        if (chessBoard.getBounds().contains(new Point(e.getX(), e.getY()))) {
            c = chessBoard.findComponentAt(e.getX(), e.getY());
        }
        else if (deleteZone.getBounds().contains(new Point(e.getX(), e.getY()))){
            skip = true;
            c = chessBoard.findComponentAt(posX, posY);
            chessPiece = null;
        }
 
        else {
            c = chessBoard.findComponentAt(posX, posY);
            posX = e.getX();
            posY = e.getY();
            skip = true;
        }
        
        if (c instanceof JLabel && chessPiece != null) {
                Container p = c.getParent();
                p.remove(0);
                p.add(chessPiece);
        }
        else {
            //pass the interactive movement  to the logical board
            if (!skip) {

            }
            if (chessPiece != null){
                Container parent = (Container) c;
                parent.add(chessPiece);
            }
            
        }

        if (chessPiece != null) chessPiece.setVisible(true);
        this.fen = updateFen();
        setPieces(this.fen);
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
        chessPiece = new JLabel();
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
                                    
                                    piece.setVisible(true);
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
                                    piece.setName("K");
                                    
                                    piece.setVisible(true);
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
                                    piece.setName("B");
                                    
                                    piece.setVisible(true);
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
                                    piece.setName("R");
                                    
                                    piece.setVisible(true);
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
                                    piece.setName("N");
                                    
                                    piece.setVisible(true);
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
                                    piece.setName("P");
                                    
                                    piece.setVisible(true);
                                    JPanel panel = (JPanel) chessBoard.getComponent(k);
                                    panel.add(piece);
                                } catch (Exception e6) {
                                    e6.printStackTrace();
                                    System.exit(1);
                                }


                                break;
                            case 'q':
                                try {
                                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                    BufferedImage bi = ImageIO.read(url);

                                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(0 * 64, 0 * 64, 64, 64)));
                                    piece.setName("q");
                                    
                                    piece.setVisible(true);
                                    JPanel panel = (JPanel) chessBoard.getComponent(k);
                                    panel.add(piece);
                                } catch (Exception e7) {
                                    e7.printStackTrace();
                                    System.exit(1);
                                }

                                break;
                            case 'k':
                                try {
                                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                    BufferedImage bi = ImageIO.read(url);

                                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(1 * 64, 0 * 64, 64, 64)));
                                    piece.setName("k");
                                    
                                    piece.setVisible(true);
                                    JPanel panel = (JPanel) chessBoard.getComponent(k);
                                    panel.add(piece);
                                } catch (Exception e8) {
                                    e8.printStackTrace();
                                    System.exit(1);
                                }

                                break;
                            case 'b':
                                try {
                                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                    BufferedImage bi = ImageIO.read(url);

                                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(4 * 64, 0 * 64, 64, 64)));
                                    piece.setName("b");
                                    
                                    piece.setVisible(true);
                                    JPanel panel = (JPanel) chessBoard.getComponent(k);
                                    panel.add(piece);
                                } catch (Exception e9) {
                                    e9.printStackTrace();
                                    System.exit(1);
                                }

                                break;
                            case 'r':
                                try {
                                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                    BufferedImage bi = ImageIO.read(url);

                                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(2 * 64, 0 * 64, 64, 64)));
                                    piece.setName("r");
                                    
                                    piece.setVisible(true);
                                    JPanel panel = (JPanel) chessBoard.getComponent(k);
                                    panel.add(piece);
                                } catch (Exception e10) {
                                    e10.printStackTrace();
                                    System.exit(1);
                                }

                                break;
                            case 'n':
                                try {
                                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                    BufferedImage bi = ImageIO.read(url);

                                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(3 * 64, 0 * 64, 64, 64)));
                                    piece.setName("n");
                                    
                                    piece.setVisible(true);
                                    JPanel panel = (JPanel) chessBoard.getComponent(k);
                                    panel.add(piece);
                                } catch (Exception e11) {
                                    e11.printStackTrace();
                                    System.exit(1);
                                }

                                
                                break;
                            case 'p':
                                try {
                                    URL url = new URL("http://i.stack.imgur.com/memI0.png");
                                    BufferedImage bi = ImageIO.read(url);

                                    JLabel piece = new JLabel(new ImageIcon(bi.getSubimage(5 * 64, 0 * 64, 64, 64)));
                                    piece.setName("p");
                                    
                                    piece.setVisible(true);
                                    JPanel panel = (JPanel) chessBoard.getComponent(k);
                                    panel.add(piece);;
                                } catch (Exception e12) {
                                    e12.printStackTrace();
                                    System.exit(1);
                                }


                                break;
                            default:

                        }

                        t++;
                    }
                    j++;

                } else {
                    nope = true;
                    t++;
                    j += 10;
                }

            }
            if (!nope) {
                i++;
            }
            nope = false;
            j = 0;

        }
    }

    private void setLateralPieces(){
        
        //White Pawn button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //White Rook button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //White Knight button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
         //White Bishop button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //White Queen button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //White King button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //Black Pawn button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //Black Rook button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //Black Knight button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //Black Bishop button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //Black Queen button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
        
        //Black King button
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
        } catch (Exception e6) {
            e6.printStackTrace();
            System.exit(1);
        }
       
    }

}
