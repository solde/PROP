/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import Exception.chessException;
import capaDades.CtrlDades;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author David Soldevila
 */
public class CDdriver {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws Exception.chessException
     */
    public static void main(String[] args) throws IOException, chessException {
        display_menu();
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        CtrlDades CD = new CtrlDades();
        while (i <= 14) { // ( o el que sea
            switch (i) {          
                case 1:
                    String un1 = sc.next();
                    String psw1 = sc.next();
                    System.out.println(CD.getPlayer(un1, psw1));
                    break;
                case 3:
                    String un2 = sc.next();
                    String psw2 = sc.next();
                    CD.createPlayer(un2, psw2);
                    break;
                case 4:
                    String us3 = sc.next();
                    String psw3 = sc.next();
                    String npsw3 = sc.next();
                    CD.updatePassword(us3, psw3, npsw3);
                    System.out.println(CD.getPlayer(us3, npsw3));
                    break;
                case 5:
                    String us4 = sc.next();
                    String psw4 = sc.next();
                    CD.deletePlayer(us4);
                    CD.getPlayer(us4, psw4);
                    break;
                case 6:
                    System.out.println("Username:");
                    String us5 = sc.next();
                    System.out.println("Password:");
                    String psw5 = sc.next();
                    System.out.println("Wins:");
                    int wins = sc.nextInt();
                    System.out.println("Loses:");
                    int loses = sc.nextInt();
                    System.out.println("ELO:");
                    double ELO = sc.nextDouble();
                    System.out.println("OP_rating:");
                    double OP_rating = sc.nextDouble();
                    CD.updatePlayer(us5, psw5, wins, loses, ELO, OP_rating);
                    System.out.println(CD.getPlayer(us5, psw5));
                    break;
                case 7:
                    String id = sc.next();
                    System.out.println(CD.getProblem(id));
                    break;
                case 8:
                    System.out.println("fen:");
                    String fenCode = sc.next();
                    System.out.println("name:");
                    String Name = sc.next();
                    System.out.println("theme:");
                    String Theme = sc.next();
                    System.out.println("diff:");
                    int diff = sc.nextInt();
                    System.out.println("n_mov:");
                    int n_mov = sc.nextInt();
                    System.out.println("atk:");
                    boolean atk = sc.nextBoolean();
                    System.out.println("first_turn:");
                    boolean first_turn = sc.nextBoolean();
                    System.out.println("verified:");
                    boolean v = sc.nextBoolean();
                    CD.createProblem(fenCode, Name, Theme, diff, n_mov, atk, first_turn, v);
                    System.out.println(CD.getProblem(Name));
                    break;
                case 9:
                    String id8 = sc.next();
                    CD.eraseProblem(id8);
                    try{
                        CD.getProblem(id8);
                    }
                    catch(chessException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    String s10 = sc.next();
                    System.out.println(CD.getStatistics(s10));
                    break;
                case 12:
                    System.out.println("Problem id:");
                    String p12 = sc.next();
                    System.out.println("player id:");
                    String pl12 = sc.next();
                    System.out.println("player time:");
                    long l12 = sc.nextLong();
                    
                    CD.addRank(p12, pl12, l12);
                    
                    break;
                case 13:
                    ArrayList<String> AS = CD.listProblmes();
                    System.out.println(AS);
                    break;
                    
                case 14:
                    CD.createProblem("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B", "a", "a", 34, 2, true, true, true);
            }
            display_menu();
            i = sc.nextInt();
        }
    }

    public static void display_menu() {
        System.out.println("Menu de opciones:");
        System.out.println("Test 1: getPlayer");
        //System.out.println("Test 2: checkPlayerInfo");
        System.out.println("Test 3: createPlayer");
        System.out.println("Test 4: updatePassword");
        System.out.println("Test 5: deletePlayer");
        System.out.println("Test 6: updatePlayer");
        System.out.println("Test 7: getProblem");
        System.out.println("Test 8: createProblem");
        System.out.println("Test 9: eraseProblem");
        System.out.println("Test 10: getStatistics");
        //System.out.println("Test 11: eraseStatistics");
        System.out.println("Test 12: addRank");
        System.out.println("Para salir presione 13");

        System.out.println("Inserte opcion: ");

    }
    
}
