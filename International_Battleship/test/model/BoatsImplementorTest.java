package model;

import org.junit.Before;
import org.junit.Test;
import testTools.BaseTests;
import testTools.Reflection;
import tools.Coord;
import tools.Direction;
import tools.GameConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoatsImplementorTest extends BaseTests {

    private BoatsImplementor boatsImplementor;
    private PlayerInterface player;

    @Before
    public void setUp() throws Exception {
        GameConfig.forceNewInstance(
                100, // gameGridWidth
                100, // gameGridHeight
                20, // maxActionPoint
                7, // portSize
                4, // nbMaxPlayer
                new String[]{
                        "José",
                        "Théodule",
                        "Yvonne",
                        "Titouan"
                },
                new BoatType[]{
                        BoatType.Cruiser,
                        BoatType.Submarine,
                        BoatType.AircraftCarrier,
                        BoatType.Sentinel,
                        BoatType.TorpedoBoat
                },
                0
        );
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
        boats.add(this.objGenerator.generateTestBoat(new Coord(55, 25), 5, Direction.EAST));
        boats.add(this.objGenerator.generateTestBoat(new Coord(5, 25), 5, Direction.WEST));
        boats.add(this.objGenerator.generateTestBoat(new Coord(25, 55), 5, Direction.SOUTH));
        boats.add(this.objGenerator.generateTestBoat(new Coord(25, 5), 5, Direction.NORTH));

        Reflection.setFieldByReflection(this.boatsImplementor, "boats", boats);
        // --------------------------------------------

        Coord coord;


        // EAST
        boat.setFacingDirection(Direction.EAST);
        boat.moveHard(new Coord(25,25)); // moveHard is for reset boat pos
        coord = new Coord(25,25); // move same place
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,26); // move SOUTH (while facing EAST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(50,25); // move EAST (while facing EAST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(100,25); // move EAST but boat(5) on (55,25)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should stop before
        assertEquals(new Coord(50, 25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(10,25); // move WEST (while facing EAST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // WEST
        boat.setFacingDirection(Direction.WEST);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,26); // move SOUTH (facing WEST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(10,25);  // move WEST (facing WEST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(0,25); // move WEST but boat(5) on (5,25)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should block boat
        assertEquals(new Coord(10, 25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(50,25); // move EAST (facing WEST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // SOUTH
        boat.setFacingDirection(Direction.SOUTH);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(26,25); // move WEST (facing SOUTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,50); // move SOUTH (facing SOUTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,100); // move SOUTH but boat(5) on (25,55)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should block boat
        assertEquals(new Coord(25, 50), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,10); // move NORTH (facing SOUTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // NORTH
        boat.setFacingDirection(Direction.NORTH);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(26,25); // move EST (facing NORTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,10); // move NORTH (facing NORTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,0); // move NORTH but boat(5) on (25,5)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should stop boat
        assertEquals(new Coord(25, 10), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,50); // move SOUTH (facing NORTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());
    }

    @Test
    public void testMoveEven(){
        AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(25,25), 4, Direction.EAST);

        // create a boat on the map that block move
        List<BoatInterface> boats = new ArrayList<>();
        boats.add(boat);
        boats.add(this.objGenerator.generateTestBoat(new Coord(55, 25), 5, Direction.EAST));
        boats.add(this.objGenerator.generateTestBoat(new Coord(5, 25), 5, Direction.WEST));
        boats.add(this.objGenerator.generateTestBoat(new Coord(25, 55), 5, Direction.SOUTH));
        boats.add(this.objGenerator.generateTestBoat(new Coord(25, 5), 5, Direction.NORTH));

        Reflection.setFieldByReflection(this.boatsImplementor, "boats", boats);
        // --------------------------------------------

        Coord coord;


        // EAST
        boat.setFacingDirection(Direction.EAST);
        boat.moveHard(new Coord(25,25)); // moveHard is for reset boat pos
        coord = new Coord(25,25); // move same place
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,26); // move SOUTH (while facing EAST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(50,25); // move EAST (while facing EAST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(100,25); // move EAST but boat(5) on (55,25)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should stop before
        assertEquals(new Coord(50, 25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(10,25); // move WEST (while facing EAST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // WEST
        boat.setFacingDirection(Direction.WEST);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,26); // move SOUTH (facing WEST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(10,25);  // move WEST (facing WEST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(0,25); // move WEST but boat(5) on (5,25)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should block boat
        assertEquals(new Coord(10, 25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(50,25); // move EAST (facing WEST)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // SOUTH
        boat.setFacingDirection(Direction.SOUTH);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(26,25); // move WEST (facing SOUTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,50); // move SOUTH (facing SOUTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,100); // move SOUTH but boat(5) on (25,55)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should block boat
        assertEquals(new Coord(25, 50), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,10); // move NORTH (facing SOUTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // NORTH
        boat.setFacingDirection(Direction.NORTH);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(26,25); // move EST (facing NORTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,10); // move NORTH (facing NORTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,0); // move NORTH but boat(5) on (25,5)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should stop boat
        assertEquals(new Coord(25, 10), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,50); // move SOUTH (facing NORTH)
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());
    }

}