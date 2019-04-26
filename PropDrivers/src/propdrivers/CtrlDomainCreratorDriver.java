/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import Exception.chessException;
import capaDomini.CtrlDomainCreator;
import java.util.Scanner;

/**
 *
 * @author Daniel Palomo
 */
public class CtrlDomainCreratorDriver {
    
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


    public static void main(String[] args) throws InterruptedException, chessException/*, Exception.chessException */{
        // TODO code application logic here
        CtrlDomainCreator ChessLegends = new CtrlDomainCreator();
        Scanner sc = new Scanner(System.in);
        int sel;
        System.out.println("1: Create new player");
        System.out.println("2: Create new problem");
        System.out.println("3: Modify a problem");
        sel = sc.nextInt();
        
        if(sel == 1){
            String Name, Password;
            System.out.print("Name ");
            System.out.println("");
            Name = sc.next();
            System.out.println("Password ");
            Password = sc.next();
            
            System.out.println("*" + Name + "* *" + Password+ "*");
            ChessLegends.createNewPlayerTest(Name, Password);
            //String info = ChessLegends.getPlayerInfo();
            System.out.println("Created user:");
            //System.out.println(ChessLegends.getPlayerInfo());
            //System.out.println(info);
            
        }
        else if(sel == 2){ //falta calcular la diff
            ChessLegends.createNewCustomBoard();
            int x = 0, y = 0;
            char t;
            boolean color;
            while(x != -1 || y != -1){
                System.out.println("Assign -1 to x or y to exit");
                System.out.println("X:");
                x = sc.nextInt();
                if(x == -1) break;
                System.out.println("Y:");
                if(y == -1) break;
                y = sc.nextInt();
                System.out.println("Enter type (nower case)");
                t = sc.next().charAt(0);
                System.out.println("Enter color (true->white|false->black)");
                color = sc.nextBoolean();
                ChessLegends.addPieceAt(x, y, t, color);

                printBoard(ChessLegends.getBoardInfo());
            }
            String fen = ChessLegends.getFenCodeOfBoard();
            if(!ChessLegends.verifyFen(fen)){
                System.out.println("Your fen code is wrong");
                System.out.println(fen);
            }
            else{
                String name, theme;
                int N;
                boolean atk;
                System.out.println("Enter a name");
                name = sc.next();
                System.out.println("Enter a theme");
                theme = sc.next();
                System.out.println("Enter number of movements");
                N = sc.nextInt();
                System.out.println("Enter atk (true->white|false->black)");
                atk = sc.nextBoolean();
                ChessLegends.createNewProblemTest(fen, name, N, theme, atk, atk);
                System.out.println("Problem created");
                System.out.println(ChessLegends.getProblemInfo());
                System.out.println("Do yo want to verify? (true->yes|false->no)");
                boolean b;
                b = sc.nextBoolean();
                if(b){
                    System.out.println(ChessLegends.verifyProblem());
                }
                System.out.println(ChessLegends.getProblemInfo());
            }
        }
        else if(sel == 3){ //falta el modificador
            String fenCode, Name, Theme;
            int N, diff;
            boolean atk = false;
            System.out.println("Enter fen code");
            fenCode = sc.next();
            for(int i = 0; i < 5; ++i){
                String aux = sc.next();
                if(i == 0 && aux == "w") atk = true;
            }
            System.out.println("Enter name");
            Name = sc.next();
            System.out.println("Enter Theme");
            Theme = sc.next();
            System.out.println("Enter number mov.");
            N = sc.nextInt();
            System.out.println("Enter diff.");
            diff = sc.nextInt();
            ChessLegends.loadProblemTest(fenCode, Name, N, diff, Theme, atk, atk);
            int x = 0, y = 0;
            char t;
            boolean color;
            System.out.println("Assign -1 to x or y to exit");
            while(x != -1 || y != -1){
                printBoard(ChessLegends.getBoardInfo());
                System.out.println("X:");
                x = sc.nextInt();
                if(x == -1) break;
                System.out.println("Y:");
                if(y == -1) break;
                y = sc.nextInt();
                System.out.println("Enter type (lower case)");
                System.out.println("q -> queen | r -> rook | p -> pawn | n -> Knight | k -> King | d -> delete");
                t = sc.next().charAt(0);
                System.out.println("Enter color (true->white|false->black)");
                color = sc.nextBoolean();
                ChessLegends.addPieceAt(x, y, t, color);
            }
            String fen = ChessLegends.getFenCodeOfBoard();
            if(!ChessLegends.verifyFen(fen)){
                System.out.println("Your fen code is wrong");
                System.out.println(fen);
            }
            else{
                String name, theme;
                System.out.println("Enter a name");
                name = sc.next();
                System.out.println("Enter a theme");
                theme = sc.next();
                System.out.println("Enter number of movements");
                N = sc.nextInt();
                System.out.println("Enter atk (true->white|false->black)");
                atk = sc.nextBoolean();
                ChessLegends.createNewProblemTest(fen, name, N, theme, atk, atk);
                System.out.println("Problem created");
                System.out.println(ChessLegends.getProblemInfo());
                System.out.println("Do yo want to verify? (true->yes|false->no)");
                boolean b;
                b = sc.nextBoolean();
                if(b){
                    System.out.println(ChessLegends.verifyProblem());
                }
                System.out.println(ChessLegends.getProblemInfo());
            }
        }       
    }
}
