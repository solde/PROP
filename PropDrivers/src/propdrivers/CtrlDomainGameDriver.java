package propdrivers;

/**
 *
 * @author Daniel Palomo
 */
import capaDomini.Board;
import capaDomini.CtrlDomainGame;
import capaDomini.GameAbs;
import java.util.Scanner;

public class CtrlDomainGameDriver {

    public static void main(String[] args) throws InterruptedException {
 
        display_menu();
        Scanner sc = new Scanner(System.in);
        CtrlDomainGame c = new CtrlDomainGame();
        int i = sc.nextInt();
        String fen = "r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R";
        GameAbs g;
        while (i <= 7) {
            switch (i) {
                case 1:
                    c = new CtrlDomainGame();
                    break;
                case 2:
                    c.loadProblemTest(fen, "test", 1, 5, "tardor", true, true);
                    if (c.getG().getP().getName().equals("test")) {
                        System.out.println("Problem set correctly");
                    } else {
                        System.out.println("Problem not set");
                    }
                    break;
                case 3:
                    c.authPlayer1Test("pepe", "pepe", 8, 0, 1564, 2500);
                    if (c.getG().getP1().getId().equals("pepe")) {
                        System.out.println("player set correctly");
                    } else {
                        System.out.println("player not set correctly");
                    }

                    break;
                case 4:
                    c.authPlayer2Test("pedro", "pepe", 8, 0, 1564, 2500);
                    if (c.getG().getP2().getId().equals("pedro")) {
                        System.out.println("player set correctly");
                    } else {
                        System.out.println("player not set correctly");
                    }
                    break;

                case 5:
                    Board b = new Board("R2qkb1r/1p2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R");
                    c.getG().setB(b);
                    c.moveWhitePiece(0, 0, 1, 0, 50);
                    if (c.getG().getB().getPieceAt(1, 0).getTypeOfPiece() == 5) {
                        System.out.println("Piece moved correctly");
                    } else {
                        System.out.println("Piece moved incorrectly");
                    }
                    break;
                case 6:
                    Board b1 = new Board("r2qkb1r/1p2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R");
                    c.getG().setB(b1);
                    c.moveBlackPiece(0, 0, 1, 0, 50);
                    if (c.getG().getB().getPieceAt(1, 0).getTypeOfPiece() == 5) {
                        System.out.println("Piece moved correctly");
                    } else {
                        System.out.println("Piece moved incorrectly");
                    }
                    break;
                case 7:
                   // c.initGame(); ACABAR
                    break;
                default:
                    break;

            }
            i = sc.nextInt();
        }
        
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: Controler Constuctor Creator");
        System.out.println("Test 2 :loadProblemTest() ");
        System.out.println("Test 3: authPlayer1Test");
        System.out.println("Test 4: authPlayer2Test");
        System.out.println("Test 5: moveWhitePiece  ");
        System.out.println("Test 6: moveBlackPiece  ");
        System.out.println("Test 7: initGame  ");/*
        System.out.println("Test 8: Get/set Name");
        System.out.println("Test 9: Get/set FirstTurn");
        System.out.println("Test 10: Get/set Theme");
        System.out.println("Test 11: Get/set Fen code");
        System.out.println("Test 12: Get/set N");
        System.out.println("Test 13: Get/set atk");
         */
        System.out.println("Para salir presione 8");

        System.out.println("Inserte opcion: ");

    }
}
