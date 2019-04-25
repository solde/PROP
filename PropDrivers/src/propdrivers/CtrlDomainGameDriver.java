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
        CtrlDomainGame ChessLegends = new CtrlDomainGame();
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do:");
        System.out.println("1: you vs AI/human");
        System.out.println("2: AI competition");
        
    }
}
