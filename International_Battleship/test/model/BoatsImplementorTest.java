package model;

import org.junit.Before;
import org.junit.Test;
import testTools.BaseTests;
import testTools.Reflection;
import tools.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoatsImplementorTest extends BaseTests {

    private BoatsImplementor boatsImplementor;
    private PlayerInterface player;
    private MineImplementorInterface mineImplementor;
    private PortImplementorInterface portImplementor;

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
                0,
                0,
                0,
                new Color(0,0,0,0),new Color(0,0,0,0),new Color(0,0,0,0),new Color(0,0,0,0)
        );
        ProcessedPropsManager.getInstance();
        List<PlayerInterface> players = new ArrayList<>();
        List<BoatType> boatTypes = new ArrayList<>();
        this.player = new Player(1, "", "");
        this.mineImplementor = new MineImplementor();
        this.boatsImplementor = new BoatsImplementor(players, this.mineImplementor);
        this.portImplementor = new PortImplementor(players);
        this.boatsImplementor.setPortImplementor(this.portImplementor);
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
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(100,25); // move EAST but boat(5) on (55,25)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should stop before
        assertEquals(new Coord(50, 25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(10,25); // move WEST (while facing EAST)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // WEST
        boat.setFacingDirection(Direction.WEST);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,26); // move SOUTH (facing WEST)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(10,25);  // move WEST (facing WEST)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(0,25); // move WEST but boat(5) on (5,25)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should block boat
        assertEquals(new Coord(10, 25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(50,25); // move EAST (facing WEST)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // SOUTH
        boat.setFacingDirection(Direction.SOUTH);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(26,25); // move WEST (facing SOUTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,50); // move SOUTH (facing SOUTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,100); // move SOUTH but boat(5) on (25,55)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should block boat
        assertEquals(new Coord(25, 50), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,10); // move NORTH (facing SOUTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // NORTH
        boat.setFacingDirection(Direction.NORTH);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(26,25); // move EST (facing NORTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,10); // move NORTH (facing NORTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,0); // move NORTH but boat(5) on (25,5)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should stop boat
        assertEquals(new Coord(25, 10), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,50); // move SOUTH (facing NORTH)
        boat.moveAutorization();
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
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(100,25); // move EAST but boat(5) on (55,25)
        boat.moveAutorization();
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
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(0,25); // move WEST but boat(5) on (5,25)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should block boat
        assertEquals(new Coord(10, 25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(50,25); // move EAST (facing WEST)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // SOUTH
        boat.setFacingDirection(Direction.SOUTH);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(26,25); // move WEST (facing SOUTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,50); // move SOUTH (facing SOUTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,100); // move SOUTH but boat(5) on (25,55)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should block boat
        assertEquals(new Coord(25, 50), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,10); // move NORTH (facing SOUTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        // NORTH
        boat.setFacingDirection(Direction.NORTH);
        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,25); // move same place
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(26,25); // move EST (facing NORTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,10); // move NORTH (facing NORTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should be OK
        assertEquals(coord, boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,0); // move NORTH but boat(5) on (25,5)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should stop boat
        assertEquals(new Coord(25, 10), boat.getCoord());

        boat.moveHard(new Coord(25,25));
        coord = new Coord(25,50); // move SOUTH (facing NORTH)
        boat.moveAutorization();
        this.boatsImplementor.moveBoat(this.player, boat, coord); // should fail
        assertEquals(new Coord(25,25), boat.getCoord());
    }
    
    @Test
    public void testCreateMine(){
        // get the private list of mines of the MineImplementor
        List<Mine> mines = (List<Mine>)Reflection.getFieldByReflection(mineImplementor, "mines");

    	//Check for boat of size 5
    	AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(10, 10), 5, Direction.EAST);
    	this.boatsImplementor.createMine(this.player, boat);
    	assertTrue(this.mineImplementor.isMined(new Coord(7,10)));
        assertEquals(1,mines.size());

    	//Check for boat of size 3
    	boat = this.objGenerator.generateTestBoat(new Coord(10, 10), 3, Direction.SOUTH);
    	this.boatsImplementor.createMine(this.player, boat);
    	assertTrue(this.mineImplementor.isMined(new Coord(10,8)));
        assertEquals(2,mines.size());

    	//Check impossible to create a mine because there is already a mine
    	this.boatsImplementor.createMine(this.player, boat);
        assertEquals(2,mines.size());

    	//Check impossible to create a mine because there is already a boat
        List<BoatInterface> boats = new ArrayList<>(); // create a boat on the map that block rotation
    	AbstractBoat boat1 = this.objGenerator.generateTestBoat(new Coord(5, 5), 1, Direction.NORTH);
    	AbstractBoat boat2 = this.objGenerator.generateTestBoat(new Coord(5, 6), 1, Direction.NORTH);
        boats.add(boat1);
        boats.add(boat2);
        Reflection.setFieldByReflection(this.boatsImplementor, "boats", boats);
    	this.boatsImplementor.createMine(this.player, boat1);
        assertEquals(2,mines.size());
    }
    
    @Test
    public void testTriggerMine(){
        // get the private list of mines of the MineImplementor
        List<Mine> mines = (List<Mine>)Reflection.getFieldByReflection(mineImplementor, "mines");

        List<ProcessedProps> processedProps = ProcessedPropsManager.flushQueue();
        assertEquals(0, processedProps.size());

        //Check if we have no meet a mine
    	AbstractBoat boat1 = this.objGenerator.generateTestBoat(new Coord(10, 10), 5, Direction.EAST);

        assertEquals(0,mines.size()); // test init state
    	this.boatsImplementor.createMine(this.player, boat1);
        ProcessedPropsManager.flushQueue(); // we want the queue to be empty

    	this.boatsImplementor.triggerMine(boat1);
        processedProps = ProcessedPropsManager.flushQueue();
        assertEquals(0, processedProps.size()); // no update expected
        assertTrue(mines.size() >= 1); // still a mine expected

    	//Check if we have meet a mine
    	AbstractBoat boat2 = this.objGenerator.generateTestBoat(new Coord(7, 10), 5, Direction.EAST);

        ProcessedPropsManager.flushQueue(); // we want the queue to be empty
    	this.boatsImplementor.triggerMine(boat2);
        processedProps = ProcessedPropsManager.flushQueue();
        assertTrue(processedProps.size() >= 1); // one update expected
        assertEquals(new Coord(7,10), processedProps.get(0).coord); // one update expected
        assertEquals(StateMine.DESTROY, processedProps.get(0).stateMine); // one update expected
        assertEquals(0,mines.size()); // expect empty mines list
    }

}