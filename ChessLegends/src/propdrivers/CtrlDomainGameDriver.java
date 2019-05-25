package propdrivers;

/**
 *
 * @author Daniel Palomo
 */
import Exception.chessException;
import capaDomini.AICompetition;
import capaDomini.Board;
import capaDomini.CtrlDomainGame;
import capaDomini.Game;
import capaDomini.GameAbs;
import java.io.IOException;
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

    public static void main(String[] args) throws InterruptedException, chessException, IOException {
        CtrlDomainGame ChessLegends = new CtrlDomainGame();
        int input;
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do:");
        System.out.println("1: you vs AI/human");
        System.out.println("2: AI competition");
        input = sc.nextInt();
        String fenCode, Name, Theme;
        int N, diff;
        boolean atk = false;
        if(input == 1){
            ChessLegends.initGame();
            System.out.println("Enter your player data in this order:");
            
            System.out.println("id1");
            String id;
            id = sc.next();
           
            System.out.println("wins:");
            int wins;
            wins = sc.nextInt();
            
            System.out.println("loses:");
            int loses;
            loses = sc.nextInt();
            
            System.out.println("ELO:");
            int ELO;
            ELO = sc.nextInt();
            
            System.out.println("OP_rating");
            int OPR;
            OPR = sc.nextInt();
            
            System.out.println("color: this will define your color. true = white, false = black Ai will be the other");
            Boolean color;
            color = sc.nextBoolean();
            
            ChessLegends.initPlayer(id, wins, loses, ELO, OPR);
            
            System.out.println("Enter a fen code");
            fenCode = sc.next();
            for(int i = 0; i < 5; ++i){
                String aux = sc.next();
                if(i == 0 && aux == "w") atk = true;
                
            }
            
            System.out.println("Enter problem name");
            Name = sc.next();
            
            System.out.println("Enter the tehme for the problem");
            Theme = sc.next();
            
            System.out.println("Enter number of turns");
            N = sc.nextInt();
            
            System.out.println("Enter difficulty level (number)");
            diff = sc.nextInt();

            //ChessLegends.loadProblemTest(fenCode, Name, N, diff, Theme, atk, atk);
            ChessLegends.letsPlay(color);
            
        
        }
        else if (input == 2){
            ChessLegends.initAIComp();
            ChessLegends.initCompetition();
            
            System.out.println("Enter a fen code");
            fenCode = sc.next();
            for(int i = 0; i < 5; ++i){
                String aux = sc.next();
                if(i == 0 && aux == "w") atk = true;
            }
            
            System.out.println("Enter problem name");
            Name = sc.next();
            
            System.out.println("Enter the tehme for the problem");
            Theme = sc.next();
            
            System.out.println("Enter number of turns");
            N = sc.nextInt();
            
            System.out.println("Enter difficulty level (number)");
            diff = sc.nextInt();
            
            int games;
            System.out.println("Enter which qtt of games do you wanna play");
            games = sc.nextInt();
            
            //ChessLegends.loadProblemTest(fenCode, Name, diff, N, Theme, atk, atk);
            ChessLegends.setGames(games);
            
            ChessLegends.AIplay(atk); //atk is useless here.
        }
    }
}
