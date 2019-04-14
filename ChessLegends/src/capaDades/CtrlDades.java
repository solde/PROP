/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDades;

import Exception.chessException;
import capaDomini.Human;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.in;

/**
 *
 * @author Familia
 */
public class CtrlDades {
    //Move this to ctrl ficheros

    public int autenticateFICH(String pass, Human h) throws chessException, FileNotFoundException, IOException {
        try {
            FileInputStream fs = new FileInputStream("Passwords.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for (int i = 0; i <= h.getLineID(); ++i) { //Read all the lines untill the correct line is readed.
                br.readLine();
            }
            String truePass = br.readLine();
            in.close();
            if (pass.isEmpty()) {
                throw new chessException("No password introduced");
            } else if (!pass.equals(truePass)) {
                throw new chessException("Bad password");
            }
        } catch (FileNotFoundException e) {
            throw new chessException("Fichero De Contraseñas No Encontrado");
        }
        return 0;

    }

    public void setPasswordFICH(String pass, Human h) throws FileNotFoundException, chessException, IOException {

        int linenum = h.getLineID();
        BufferedReader br = null;
        FileReader reader = null;
        try {
            reader = new FileReader("password.txt");
            br = new BufferedReader(reader);
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("passtemp.txt")));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (linenum == i) {
                    line = pass;
                }
                writer.println(line);
            }
            File password = new File("password.txt");
            password.delete(); // remove the old file
            new File("passtemp.txt").renameTo(password); // Rename temp file
        } catch (FileNotFoundException e) {
            throw new chessException("Fichero De Contraseñas No Encontrado");

        }
    }
}
