package model;

import org.junit.Before;
import org.junit.Test;
import testTools.BaseTests;
import testTools.Reflection;
import tools.Coord;
import tools.Direction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoatsImplementorTest extends BaseTests {

    private BoatsImplementor boatsImplementor;
    private PlayerInterface player;

    @Before
    public void setUp() throws Exception {
        List<PlayerInterface> players = new ArrayList<>();
        List<BoatType> boatTypes = new ArrayList<>();
        this.player = new Player(1, "", "");
        this.boatsImplementor = new BoatsImplementor(players);
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
        this.boatsImplementor.rotateBoat(this.player, boat, true);
        assertEquals(Direction.SOUTH, boat.getDirection());
        assertEquals(90, boat.getDirection().rotation);
        assertEquals(Direction.EAST, Reflection.getFieldByReflection2(AbstractBoat.class, boat, "lastDirection"));

        this.boatsImplementor.rotateBoat(this.player, boat, true);
        assertEquals(Direction.WEST, boat.getDirection());
        assertEquals(180, boat.getDirection().rotation);
        assertEquals(Direction.SOUTH, Reflection.getFieldByReflection2(AbstractBoat.class, boat, "lastDirection"));

        this.boatsImplementor.rotateBoat(this.player, boat, true);
        assertEquals(Direction.NORTH, boat.getDirection());
        assertEquals(-90, boat.getDirection().rotation);
        assertEquals(Direction.WEST, Reflection.getFieldByReflection2(AbstractBoat.class, boat, "lastDirection"));

        this.boatsImplementor.rotateBoat(this.player, boat, true);
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);
        assertEquals(Direction.NORTH, Reflection.getFieldByReflection2(AbstractBoat.class, boat, "lastDirection"));


        // rotate counter clock wise
        this.boatsImplementor.rotateBoat(this.player, boat, false);
        assertEquals(Direction.NORTH, boat.getDirection());
        assertEquals(-90, boat.getDirection().rotation);
        assertEquals(Direction.EAST, Reflection.getFieldByReflection2(AbstractBoat.class, boat, "lastDirection"));

        this.boatsImplementor.rotateBoat(this.player, boat, false);
        assertEquals(Direction.WEST, boat.getDirection());
        assertEquals(180, boat.getDirection().rotation);
        assertEquals(Direction.NORTH, Reflection.getFieldByReflection2(AbstractBoat.class, boat, "lastDirection"));

        this.boatsImplementor.rotateBoat(this.player, boat, false);
        assertEquals(Direction.SOUTH, boat.getDirection());
        assertEquals(90, boat.getDirection().rotation);
        assertEquals(Direction.WEST, Reflection.getFieldByReflection2(AbstractBoat.class, boat, "lastDirection"));

        this.boatsImplementor.rotateBoat(this.player, boat, false);
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);
        assertEquals(Direction.SOUTH, Reflection.getFieldByReflection2(AbstractBoat.class, boat, "lastDirection"));


        // create a boat on the map that block rotation
            List<BoatInterface> boats = new ArrayList<>();
            boats.add(this.objGenerator.generateTestBoat(new Coord(10, 12), 5, Direction.EAST));

            Reflection.setFieldByReflection(this.boatsImplementor, "boats", boats);
        // --------------------------------------------


        // Verify that rotation is canceled
            this.boatsImplementor.rotateBoat(this.player, boat, true);
            assertEquals(Direction.EAST, boat.getDirection());
            assertEquals(0, boat.getDirection().rotation);

            this.boatsImplementor.rotateBoat(this.player, boat, false);
            assertEquals(Direction.EAST, boat.getDirection());
            assertEquals(0, boat.getDirection().rotation);
        // --------------------------------
    }

    @Test
    public void testMoveOdd(){
        AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(10,10), 5, Direction.EAST);

        // create a boat on the map that block rotation
        List<BoatInterface> boats = new ArrayList<>();
        boats.add(boat);
        boats.add(this.objGenerator.generateTestBoat(new Coord(100, 10), 5, Direction.EAST));
        boats.add(this.objGenerator.generateTestBoat(new Coord(-100, 10), 5, Direction.WEST));
        boats.add(this.objGenerator.generateTestBoat(new Coord(10, 100), 5, Direction.SOUTH));
        boats.add(this.objGenerator.generateTestBoat(new Coord(10, -100), 5, Direction.NORTH));

        Reflection.setFieldByReflection(this.boatsImplementor, "boats", boats);
        // --------------------------------------------

        Coord coord;


        // EAST
        boat.setFacingDirection(Direction.EAST);
            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(coord, boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,11);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10,10), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(50,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(coord, boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(1000,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(95, 10), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(-50,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10,10), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(-1000,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10, 10), boat.getCoord());

        // WEST
        boat.setFacingDirection(Direction.WEST);
            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(coord, boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,11);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10,10), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(-50,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(coord, boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(-1000,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(-95, 10), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(50,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10,10), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(1000,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10, 10), boat.getCoord());

        // SOUTH
        boat.setFacingDirection(Direction.SOUTH);
            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(coord, boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(11,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10,10), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,50);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(coord, boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,100);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10, 95), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,-50);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10,10), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,-100);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10, 10), boat.getCoord());

        // NORTH
        boat.setFacingDirection(Direction.NORTH);
            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(coord, boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(11,10);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10,10), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,-50);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(coord, boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,-100);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10, -95), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,50);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10,10), boat.getCoord());

            boat.moveHard(new Coord(10,10));
            coord = new Coord(10,100);
            this.boatsImplementor.moveBoat(this.player, boat, coord);
            assertEquals(new Coord(10, 10), boat.getCoord());
    }

    @Test
    public void testMoveEven(){
        AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(10,10), 4, Direction.EAST);

        // create a boat on the map that block rotation
        List<BoatInterface> boats = new ArrayList<>();
        boats.add(boat);
        boats.add(this.objGenerator.generateTestBoat(new Coord(100, 10), 5, Direction.EAST));
        boats.add(this.objGenerator.generateTestBoat(new Coord(-100, 10), 5, Direction.WEST));
        boats.add(this.objGenerator.generateTestBoat(new Coord(10, 100), 5, Direction.SOUTH));
        boats.add(this.objGenerator.generateTestBoat(new Coord(10, -100), 5, Direction.NORTH));

        Reflection.setFieldByReflection(this.boatsImplementor, "boats", boats);
        // --------------------------------------------

        Coord coord;


        // EAST
        boat.setFacingDirection(Direction.EAST);
        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,11);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10,10), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(50,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(1000,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(96, 10), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(-50,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10,10), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(-1000,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10, 10), boat.getCoord());

        // WEST
        boat.setFacingDirection(Direction.WEST);
        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,11);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10,10), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(-50,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(-1000,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(-96, 10), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(50,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10,10), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(1000,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10, 10), boat.getCoord());

        // SOUTH
        boat.setFacingDirection(Direction.SOUTH);
        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(11,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10,10), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,50);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,100);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10, 96), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,-50);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10,10), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,-100);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10, 10), boat.getCoord());

        // NORTH
        boat.setFacingDirection(Direction.NORTH);
        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(11,10);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10,10), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,-50);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,-100);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10, -96), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,50);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10,10), boat.getCoord());

        boat.moveHard(new Coord(10,10));
        coord = new Coord(10,100);
        this.boatsImplementor.moveBoat(this.player, boat, coord);
        assertEquals(new Coord(10, 10), boat.getCoord());
    }

}