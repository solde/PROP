/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import Exception.chessException;
import capaDomini.Problem;
import java.util.Scanner;

/**
 *
 * @author David Soldevila
 */
public class ProblemDriver {
 public static void main(String[] args) throws InterruptedException, chessException {
        // TODO code application logic here
        display_menu();
        Scanner sc = new Scanner(System.in);
        Problem P;
        int i = sc.nextInt();
        while (i <= 14) {
            switch (i) {
                case 1: // Problem()
                    P = new Problem();
                    break;
                    
                case 2: // Problem(String fenCode, String Name, int diff, int N_mov, String Theme, boolean atk, boolean first_turn)
                    String fc2 = sc.nextLine();
                    String n2 = sc.nextLine();
                    int d2 = sc.nextInt();
                    int nm2 = sc.nextInt();
                    String t2 = sc.nextLine();
                    boolean a2 = sc.nextBoolean();
                    boolean ft2 = sc.nextBoolean();
                    P = new Problem(fc2, n2, d2, nm2, t2, a2, ft2);
                    break;

                case 3: //Creator with string with DL info
                    String info = sc.nextLine();
                    P = new Problem(info);
                    break;       
                
                case 4: //verify
                    System.out.println("Enter a fen Code");
                    //String fc3 = sc.nextLine();
                    String fc3 = "r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R";
                    System.out.println("Enter a name");
                    String n3 = "problem_test";
                    System.out.println("Enter a difficulty");
                    int d3 = 3;
                    System.out.println("Enter a number of movs");
                    //int nm3 = sc.nextInt();
                    int nm3 = 2;
                    System.out.println("Enter a theme");
                    String t3 = "test";
                    System.out.println("Enter the player who atacks");
                    boolean a3 = sc.nextBoolean();
                    System.out.println("Enter a the player who starts the game");
                    boolean ft3 = sc.nextBoolean();
                    P = new Problem(fc3, n3, d3, nm3, t3, a3, ft3);
                    //System.out.println(P.getProblemInfo());
                    System.out.println(P.verify());
                    break;       
                
                case 5: //numberPiecesOf
                    P = new Problem();
                    P.setFenCode("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B");
                    System.out.println(P.numberPiecesOf(true));
                    System.out.println(P.numberPiecesOf(false));
                    break;       
                
                case 6: //calculateDiff/getDiff
                    P = new Problem();
                    P.setFenCode("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B");
                    P.setATK(true);
                    P.setN_mov(3);
                    P.calculateDiff();
                    System.out.println(P.getDiff());
                    break;
                
                case 7: //getProblemInfo
                    String i7 = sc.nextLine();
                    P = new Problem(i7);
                    System.out.println(P.getProblemInfo());
                    break;
                
                case 8: //Get/set Name
                    P = new Problem();
                    String n8 = sc.nextLine();
                    P.setName(n8);
                    System.out.println(P.getName());
                    break;       
                
                case 9: //Get/set FirstTurn
                    P = new Problem();
                    boolean ft9 = sc.nextBoolean();
                    P.setFirstTurn(ft9);
                    System.out.println(P.getFirstTurn());
                    break;
                
                case 10: //Get/set Theme
                    P = new Problem();
                    String t10 = sc.nextLine();
                    P.setTheme(t10);
                    System.out.println(P.getTheme());
                    break;       
                
                case 11: //Get/set Fen code
                    P = new Problem();
                    String fen11 = sc.nextLine();
                    P.setFenCode(fen11);
                    System.out.println(P.getFenCode());
                    break;      
                
                case 12: //Get/set N
                    P = new Problem();
                    int N12 = sc.nextInt();
                    P.setN_mov(N12);
                    System.out.println(P.getN_mov());
                    break;      
                
                case 13: //Get/set atk
                    P = new Problem();
                    boolean a13 = sc.nextBoolean();
                    P.setATK(a13);
                    System.out.println(P.getATK());
                    break;

                case 14: // get/set stats
                    P = new Problem();
                    P.addToRanking("Pipo", 1);
                    P.addToRanking("Pipo3", 3);
                    P.addToRanking("Pipo2", 2);
                    System.out.print(P.getRankingPossition(2));
            }
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: Problem Constuctor Creator");
        System.out.println("Test 2 :Problem Constuctor Loader Test");
        System.out.println("Test 3: Problem Constuctor Loader String Info");
        System.out.println("Test 4: verify ");
        System.out.println("Test 5: numberPiecesOf  ");
        System.out.println("Test 6: calculateDiff/getDiff  ");
        System.out.println("Test 7: getProblemInfo  ");
        System.out.println("Test 8: Get/set Name");
        System.out.println("Test 9: Get/set FirstTurn");
        System.out.println("Test 10: Get/set Theme");
        System.out.println("Test 11: Get/set Fen code");
        System.out.println("Test 12: Get/set N");
        System.out.println("Test 13: Get/set atk");
        System.out.println("Test 14: Get/set ranking");

        System.out.println("Para salir presione 14");

        System.out.println("Inserte opcion: ");

    }
}
