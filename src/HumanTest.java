/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDomini; //cambiar al package pertinent

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Familia
 */
public class HumanTest {
    
    public HumanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of autenticate method, of class Human.
     */
    @Test
    public void testAutenticate() {
        System.out.println("autenticate");
        String pass = "yoyo";
        Human instance = new Human("pepe","contraseña");
        int expResult = 0;
        int result = instance.autenticate(pass);
 
        if(result == expResult){
       // TODO review the generated test code and remove the default call to fail.
        fail("Matches when it shouldn't");
        }else if(result==2){
                
        fail("Test doesn·t work");
                }
        else return;
    }

    /**
     * Test of play_turn method, of class Human.
     */
    @Test
    public void testPlay_turn() {
        System.out.println("play_turn");
        Human instance = new Human();
        instance.play_turn();
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class Human.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Human instance = new Human("pepe","ya");
        String expResult = "ya";
        String result = instance.getPassword();
        if(result.equals(expResult)){ return;
        }else  fail("The test case is a prototype.");
        
        // TODO review the generated test code and remove the default call to fail.
        //
    }
    
}