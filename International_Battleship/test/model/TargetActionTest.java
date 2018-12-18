package model;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import tools.Coord;
import tools.Direction;

public class TargetActionTest {
    private TargetAction mySpecialAction;

    @Before
    public void setUp() throws Exception {
    	//TODO
    }
    
    @Test
    public void doActionTest(){
    	//TODO
    	
    	/*final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        
        //Aircraft
    	try {
            BoatInterface aircraft = new AircraftCarrier(1, new Coord(10,10), 10);
            aircraft.actionSpecial(new Coord(5, 5));
            assertEquals("Action spécial porte avion" + System.getProperty("line.separator"), outContent.toString());       
        } catch (Exception e) {
        	fail();
        }
    	
    	//Cruiser
    	try {
            BoatInterface cruiser = new Cruiser(2, new Coord(11,11), 11);
            cruiser.actionSpecial(new Coord(6, 6));
            assertEquals("Action spéciale croiseur" + System.getProperty("line.separator"), outContent.toString());       
        } catch (Exception e) {
        	fail();
        } 	
    	
    	//Sentinel
    	try {
            BoatInterface sentinel = new Sentinel(3, new Coord(12,12), 12);
            sentinel.actionSpecial(new Coord(7, 7));
            assertEquals("Action spéciale sentinel" + System.getProperty("line.separator"), outContent.toString());       
        } catch (Exception e) {
        	fail();
        }*/
    }
}
