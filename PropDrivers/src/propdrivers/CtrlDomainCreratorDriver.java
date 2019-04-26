/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propdrivers;

import capaDomini.CtrlDomainCreator;
import java.util.Scanner;

/**
 *
 * @author Daniel Palomo
 */
public class CtrlDomainCreratorDriver {

    public static void main(String[] args) throws InterruptedException/*, Exception.chessException */{
        // TODO code application logic here
        CtrlDomainCreator ChessLegends = new CtrlDomainCreator();
        Scanner sc = new Scanner(System.in);
        int sel;
        System.out.println("1: Create new player");
        System.out.println("2: Create new problem");
        sel = sc.nextInt();
        
        if(sel == 1){
            String Name, Password;
            System.out.print("Name ");
            System.out.println("");
            Name = sc.nextLine();
            System.out.println("Password ");
            Password = sc.next();
            
            System.out.println("*" + Name + "* *" + Password+ "*");
            ChessLegends.createNewPlayerTest(Name, Password);
            System.out.println("Created user:");
            System.out.println(ChessLegends.getPlayerInfo());
            
        }
        else if(sel == 2){
            
            
            ChessLegends.createNewCustomBoard();
        }
    }

}
