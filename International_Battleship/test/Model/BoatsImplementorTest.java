package Model;

import org.junit.Before;
import org.junit.Test;
import testTools.BaseTests;
import testTools.Reflection;
import tools.Coord;
import tools.Direction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoatsImplementorTest extends BaseTests {

    private BoatsImplementor boatsImplementor;

    @Before
    public void setUp() throws Exception {
        List<Player> players = new ArrayList<>();
        List<BoatName> boatNames = new ArrayList<>();
        this.boatsImplementor = new BoatsImplementor(players, boatNames);
    }

    @Test
    @Deprecated
    public void testRotateAround() {
        Coord coord = new Coord(10,10);
        Coord pivot = new Coord(8,10);

        // -180°
        Coord tmpCoord = this.boatsImplementor.rotateAround(coord, pivot, (float)-Math.PI);
        assertEquals(new Coord(6,10), tmpCoord);

        // 180°
        tmpCoord = this.boatsImplementor.rotateAround(coord, pivot, (float)Math.PI);
        assertEquals(new Coord(6,10), tmpCoord);

        // -90°
        tmpCoord = this.boatsImplementor.rotateAround(coord, pivot, (float)-Math.PI/2);
        assertEquals(new Coord(8,8), tmpCoord);

        // 90°
        tmpCoord = this.boatsImplementor.rotateAround(coord, pivot, (float)Math.PI/2);
        assertEquals(new Coord(8,12), tmpCoord);
    }

    @Test
    public void testRotate() {
        // create a boat to rotate (and check if well initialised, just to be sure
        AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(10, 12), 5, Direction.EAST);
        assertEquals(0, boat.getDirection().rotation);
        // -----------------------------------------------------------------------

        // rotate clock wise
        this.boatsImplementor.rotateBoat(boat, true);
        assertEquals(Direction.SOUTH, boat.getDirection());
        assertEquals(90, boat.getDirection().rotation);
        this.boatsImplementor.rotateBoat(boat, true);
        assertEquals(Direction.WEST, boat.getDirection());
        assertEquals(180, boat.getDirection().rotation);
        this.boatsImplementor.rotateBoat(boat, true);
        assertEquals(Direction.NORTH, boat.getDirection());
        assertEquals(-90, boat.getDirection().rotation);
        this.boatsImplementor.rotateBoat(boat, true);
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);

        // rotate counter clock wise
        this.boatsImplementor.rotateBoat(boat, false);
        assertEquals(Direction.NORTH, boat.getDirection());
        assertEquals(-90, boat.getDirection().rotation);
        this.boatsImplementor.rotateBoat(boat, false);
        assertEquals(Direction.WEST, boat.getDirection());
        assertEquals(180, boat.getDirection().rotation);
        this.boatsImplementor.rotateBoat(boat, false);
        assertEquals(Direction.SOUTH, boat.getDirection());
        assertEquals(90, boat.getDirection().rotation);
        this.boatsImplementor.rotateBoat(boat, false);
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);


        // create a boat on the map that block rotation
            List<Boat> boats = new ArrayList<>();
            boats.add(this.objGenerator.generateTestBoat(new Coord(10, 12), 5, Direction.EAST));

            Reflection.setFieldByReflection(this.boatsImplementor, "boats", boats);
        // --------------------------------------------


        // Verify that rotation is canceled
            this.boatsImplementor.rotateBoat(boat, true);
            assertEquals(Direction.EAST, boat.getDirection());
            assertEquals(0, boat.getDirection().rotation);

            this.boatsImplementor.rotateBoat(boat, false);
            assertEquals(Direction.EAST, boat.getDirection());
            assertEquals(0, boat.getDirection().rotation);
        // --------------------------------
    }

}