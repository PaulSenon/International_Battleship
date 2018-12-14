package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import tools.Coord;

public class InstantActionTest {
    private InstantAction mySpecialAction;

    @Before
    public void setUp() throws Exception {
    	//TODO
    }
    
    @Test
    public void doActionTest(){
        //TODO
    	/*final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        //Submarin
    	try {
            BoatInterface submarin = new Submarin(new Coord(10,10), 10);
            submarin.actionSpecial(new Coord(5, 5));
            assertEquals("Je suis instant action" + System.getProperty("line.separator"), outContent.toString());       
        } catch (Exception e) {
        	fail();
        }
    	
    	//TorpedoBoat
    	try {
            BoatInterface torpedoboat = new TorpedoBoat(new Coord(11,11), 11);
            torpedoboat.actionSpecial(new Coord(6, 6));
            assertEquals("Je suis instant action" + System.getProperty("line.separator"), outContent.toString());       
        } catch (Exception e) {
        	fail();
        }*/
    }
}
