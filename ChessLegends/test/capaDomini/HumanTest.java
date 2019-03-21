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
     * Test of autenticate method, of class  
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
     * Test of getPassword method, of class  
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
     
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Human instance = new Human("pepe","ya");
        String expResult = "pepe";
        String result = instance.getID();
        if(result.equals(expResult)){ return;
        }else  fail("The test case is a prototype.");
        
        // TODO review the generated test code and remove the default call to fail.
        //
    } 
     */
    @Test
    public void testGetWins() {
        System.out.println("getSetWins");
        Human instance = new Human("pepe","ya");
        instance.setWins(24);
        int expResult = 24;
        int result = instance.getWins();
        if(result.equals(expResult)){ 
            return;
        }else  fail("The test case is a prototype.");
        
        // TODO review the generated test code and remove the default call to fail.
        //
    }
     
     */
    @Test
    public void testGetSetLoses() {
        System.out.println("getSetLoses");
        Human instance = new Human("pepe","ya");
        instance.setLoses(345);
        int expResult = 345;
        int result = instance.getLoses();
        if(result.equals(expResult)){ return;
        }else  fail("The test case is a prototype.");
        
        // TODO review the generated test code and remove the default call to fail.
        //
    }
     
     */
    @Test
    public void testGetSetELO() {
        System.out.println("getSetELO");
        Human instance = new Human("pepe","ya");
        instance.setELO(1345);
        int expResult = 1345;
        int result = (int) instance.getELO();
        if(expResult == result)){ return;
        }else  fail("The test case is a prototype.");
        
        // TODO review the generated test code and remove the default call to fail.
        //
    }
    
    
}
