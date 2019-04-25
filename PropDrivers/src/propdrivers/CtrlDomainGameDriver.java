package propdrivers;

/**
 *
 * @author Daniel Palomo
 */
import Exception.chessException;
import capaDomini.Board;
import capaDomini.CtrlDomainGame;
import capaDomini.Game;
import capaDomini.GameAbs;
import java.util.Arrays;
import java.util.Scanner;

public class CtrlDomainGameDriver {
    
    public static void printBoard(char[][] board){
            System.out.println("   0  1  2  3  4  5  6  7");
            for(int x = 0; x < 8; ++x){
                System.out.print(x + "|");
                for(int y = 0; y < 8; ++y){
                    System.out.print(board[x][y] + "|");
                }
                System.out.println(" ");
            }
            System.out.println(" ");
    }

    public static void main(String[] args) throws InterruptedException, chessException {
        CtrlDomainGame ChessLegends = new CtrlDomainGame();
        int input;
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do:");
        System.out.println("1: you vs AI/human");
        System.out.println("2: AI competition");
        input = sc.nextInt();
        if(input == 1){
            ChessLegends.initGame();
            String fenCode, Name, Theme;
            int N, diff;
            boolean atk;
            
            System.out.println("Enter a fen code");
            fenCode = sc.next();
            
            System.out.println("Enter problem name");
            Name = sc.next();
            
            System.out.println("Enter the tehme for the problem");
            Theme = sc.next();
            
            System.out.println("Enter number of turns");
            N = sc.nextInt();
            
            System.out.println("Enter difficulty level (number)");
            diff = sc.nextInt();
            
            System.out.println("Enter de attacker (true for white, false for black)");
            atk = sc.nextBoolean();
            
            ChessLegends.loadProblemTest(fenCode, Name, N, diff, Theme, atk, atk);
            printBoard(ChessLegends.getBoardInfo());
            
        }
        else{
            
        }
    }
}
