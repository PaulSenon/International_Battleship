package model;

import org.junit.Before;
import org.junit.Test;

import testTools.BaseTests;
import tools.*;

import java.util.*;

import static org.junit.Assert.*;

public class AbstractBoatTest extends BaseTests {

    private BoatInterface boat;

    @Before
    public void setUp() throws Exception {
        MessageManager.getInstance();
        this.boat = this.objGenerator.generateTestBoat(new Coord(10,10), 5, Direction.EAST);
    }

    @Test
    public void getCoords() {
        // EAST -> odd
        // ooOo>
        ((AbstractBoat) this.boat).setSize(5);
        ((AbstractBoat) this.boat).setFacingDirection(Direction.EAST);
        List<Coord> coords = this.boat.getCoords();

        assertEquals(5, coords.size());
        assertEquals(new Coord(8,10), coords.get(0));
        assertEquals(new Coord(9,10), coords.get(1));
        assertEquals(new Coord(10,10), coords.get(2)); // pivot
        assertEquals(new Coord(11,10), coords.get(3));
        assertEquals(new Coord(12,10), coords.get(4)); // head

        // EAST -> even
        // oooOo>
        ((AbstractBoat) this.boat).setSize(6);
        ((AbstractBoat) this.boat).setFacingDirection(Direction.EAST);
        coords = this.boat.getCoords();

        assertEquals(6, coords.size());
        assertEquals(new Coord(8,10), coords.get(0));
        assertEquals(new Coord(9,10), coords.get(1));
        assertEquals(new Coord(10,10), coords.get(2));  // pivot
        assertEquals(new Coord(11,10), coords.get(3));
        assertEquals(new Coord(12,10), coords.get(4));
        assertEquals(new Coord(13,10), coords.get(5)); // head

        // WEST -> odd
        // <oOoo
        ((AbstractBoat) this.boat).setSize(5);
        ((AbstractBoat) this.boat).setFacingDirection(Direction.WEST);
        coords = this.boat.getCoords();

        assertEquals(5, coords.size());
        assertEquals(new Coord(12,10), coords.get(0));
        assertEquals(new Coord(11,10), coords.get(1));
        assertEquals(new Coord(10,10), coords.get(2)); // pivot
        assertEquals(new Coord(9,10), coords.get(3));
        assertEquals(new Coord(8,10), coords.get(4)); // head

        // WEST -> even
        // <oOooo
        ((AbstractBoat) this.boat).setSize(6);
        ((AbstractBoat) this.boat).setFacingDirection(Direction.WEST);
        coords = this.boat.getCoords();

        assertEquals(6, coords.size());
        assertEquals(new Coord(12,10), coords.get(0));
        assertEquals(new Coord(11,10), coords.get(1));
        assertEquals(new Coord(10,10), coords.get(2)); // pivot
        assertEquals(new Coord(9,10), coords.get(3));
        assertEquals(new Coord(8,10), coords.get(4));
        assertEquals(new Coord(7,10), coords.get(5)); // head

        // NORTH -> odd
        // ^
        // O
        // o
        ((AbstractBoat) this.boat).setSize(3);
        ((AbstractBoat) this.boat).setFacingDirection(Direction.NORTH);
        coords = this.boat.getCoords();

        assertEquals(3, coords.size());
        assertEquals(new Coord(10,11), coords.get(0));
        assertEquals(new Coord(10,10), coords.get(1)); // pivot
        assertEquals(new Coord(10,9), coords.get(2)); // head

        // NORTH -> even
        // ^
        // O
        // o
        // o
        ((AbstractBoat) this.boat).setSize(4);
        ((AbstractBoat) this.boat).setFacingDirection(Direction.NORTH);
        coords = this.boat.getCoords();

        assertEquals(4, coords.size());
        assertEquals(new Coord(10,11), coords.get(0));
        assertEquals(new Coord(10,10), coords.get(1)); // pivot
        assertEquals(new Coord(10,9), coords.get(2));
        assertEquals(new Coord(10,8), coords.get(3)); // head

        // SOUTH -> odd
        // o
        // O
        // v
        ((AbstractBoat) this.boat).setSize(3);
        ((AbstractBoat) this.boat).setFacingDirection(Direction.SOUTH);
        coords = this.boat.getCoords();

        assertEquals(3, coords.size());
        assertEquals(new Coord(10,9), coords.get(0));
        assertEquals(new Coord(10,10), coords.get(1)); // pivot
        assertEquals(new Coord(10,11), coords.get(2)); // head

        // SOUTH -> even
        // o
        // o
        // O
        // v
        ((AbstractBoat) this.boat).setSize(4);
        ((AbstractBoat) this.boat).setFacingDirection(Direction.SOUTH);
        coords = this.boat.getCoords();

        assertEquals(4, coords.size());
        assertEquals(new Coord(10,9), coords.get(0));
        assertEquals(new Coord(10,10), coords.get(1)); // pivot
        assertEquals(new Coord(10,11), coords.get(2));
        assertEquals(new Coord(10,12), coords.get(3)); // head
    }

    @Test
    public void testIsMoveOk(){
        // boat coord is 10;10
        boolean ok;

        // EAST
        ((AbstractBoat) this.boat).setFacingDirection(Direction.EAST);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,10));
            assertTrue(ok);

            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(11,10));
            assertTrue(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(123456789,10));
            assertTrue(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(9,10));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(-123456789,10));
            assertFalse(ok);

            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,9));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,-123456789));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,11));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,123456789));
            assertFalse(ok);

        // WEST
        ((AbstractBoat) this.boat).setFacingDirection(Direction.WEST);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,10));
            assertTrue(ok);

            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(9,10));
            assertTrue(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(-123456789,10));
            assertTrue(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(11,10));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(123456789,10));
            assertFalse(ok);

            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,9));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,-123456789));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,11));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,123456789));
            assertFalse(ok);

        // SOUTH
        ((AbstractBoat) this.boat).setFacingDirection(Direction.SOUTH);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,10));
            assertTrue(ok);

            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,11));
            assertTrue(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,123456789));
            assertTrue(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,9));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,-123456789));
            assertFalse(ok);

            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(9,10));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(-123456789,10));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(11,10));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(123456789,10));
            assertFalse(ok);

        // NORTH
        ((AbstractBoat) this.boat).setFacingDirection(Direction.NORTH);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,10));
            assertTrue(ok);

            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,9));
            assertTrue(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,-123456789));
            assertTrue(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,11));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(10,123456789));
            assertFalse(ok);

            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(9,10));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(-123456789,10));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(11,10));
            assertFalse(ok);
            ok = ((AbstractBoat) this.boat).isMoveOk(new Coord(123456789,10));
            assertFalse(ok);
    }

    @Test
    public void testNbParts(){
        BoatInterface boat = this.objGenerator.generateTestBoat(new Coord(10,10), 6, Direction.EAST);
        assertEquals(3, boat.getNbFrontParts());
        assertEquals(3, boat.getNbBackParts());

        boat = this.objGenerator.generateTestBoat(new Coord(10,10), 5, Direction.EAST);
        assertEquals(2, boat.getNbFrontParts());
        assertEquals(3, boat.getNbBackParts());

        boat = this.objGenerator.generateTestBoat(new Coord(10,10), 4, Direction.EAST);
        assertEquals(2, boat.getNbFrontParts());
        assertEquals(2, boat.getNbBackParts());

        boat = this.objGenerator.generateTestBoat(new Coord(10,10), 3, Direction.EAST);
        assertEquals(1, boat.getNbFrontParts());
        assertEquals(2, boat.getNbBackParts());
    }

    @Test
    public void testShoot(){
        Pair<ResultShoot, ProcessedPosition> result;
        try {
            result = boat.shoot(new Coord(12,10));
            assertEquals(ResultShoot.TOUCHED, result.getFirst());
            result = boat.shoot(new Coord(12,10));
            assertEquals(ResultShoot.ALREADY_TOUCHED, result.getFirst());
            result = boat.shoot(new Coord(11,10));
            assertEquals(ResultShoot.TOUCHED, result.getFirst());
            result = boat.shoot(new Coord(10,10));
            assertEquals(ResultShoot.TOUCHED, result.getFirst());
            result = boat.shoot(new Coord(9,10));
            assertEquals(ResultShoot.TOUCHED, result.getFirst());
            result = boat.shoot(new Coord(8,10));
            assertEquals(ResultShoot.DESTROYED, result.getFirst());
        } catch (Exception e) {
            fail();
        }
        try {
            result = boat.shoot(new Coord(180,10));
        } catch (Exception e) {
            assertTrue(e.getClass().getName().equals(Exception.class.getName()) );
        }

    }
    
    @Test
    public void testSetDammage(){
    	//Check boat is not destroy
    	assertEquals(false,boat.getDestroy());
    	
    	//Check for 1 dammage
    	assertEquals(0,boat.getTouchedFragmentIds().size());
    	try {
			boat.setDammage(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	assertEquals(1,boat.getTouchedFragmentIds().size());
    	
    	//Check for much dammage and destrucion boat
    	assertEquals(1,boat.getTouchedFragmentIds().size());
    	try {
			boat.setDammage(4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	assertEquals(5,boat.getTouchedFragmentIds().size());
    	
    	//Check boat is destroy
    	assertEquals(true,boat.getDestroy());
    	
    	//Check boat can't touched by mine because destroy
    	assertEquals(5,boat.getTouchedFragmentIds().size());
    	assertEquals(true,boat.getDestroy());
    	try {
			boat.setDammage(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	assertEquals(5,boat.getTouchedFragmentIds().size());
    	assertEquals(true,boat.getDestroy());
    }
}