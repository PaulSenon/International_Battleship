package model;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import testTools.BaseTests;
import testTools.Reflection;
import tools.Coord;
import tools.Direction;
import tools.GameConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameModelTest extends BaseTests {
    private GameModel gameModel;
    private BoatsImplementor boatsImplementor;
	private PortImplementor portImplementor;

    @Before
    public void setUp() throws Exception {
        // setup game config :
        GameConfig.forceNewInstance(
                25, // gameGridWidth
                25, // gameGridHeight
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
                0
        );

        // create boats implementor
        List<PlayerInterface> players = new ArrayList<>();
        List<BoatType> boatTypes = new ArrayList<>();
        this.boatsImplementor = new BoatsImplementor(players);
        this.portImplementor = new PortImplementor(players);
        this.boatsImplementor.setPortImplementor(this.portImplementor);

        // add some test boats on the game board
        // TODO change values
        List<BoatInterface> boats = new ArrayList<>();
        boats.add(this.objGenerator.generateTestBoat(new Coord(10, 11), 5, Direction.EAST));

        Reflection.setFieldByReflection(this.boatsImplementor, "boats", boats);

        // create game model
        this.gameModel = new GameModel();
        Reflection.setFieldByReflection(this.gameModel, "battleshipImplementor", this.boatsImplementor);

        // create player
        PlayerInterface player = new Player(1, "", "");
        Reflection.setFieldByReflection(this.gameModel, "currentPlayer", player);

        // add some mines on the game board
        // TODO mines does not exist yet !
    }

    @Test
    public void testRotateBoat() {
        // setup
            // create the boat
            AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(15, 15), 4, Direction.EAST);
            // add it to implementor
            List<BoatInterface> boats = (List<BoatInterface>) Reflection.getFieldByReflection(this.boatsImplementor, "boats");
            boats.add(boat);
            Reflection.setFieldByReflection(this.gameModel, "selectedBoat", boat);

        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);
        this.gameModel.rotateBoatClockWise();
        assertEquals(Direction.SOUTH, boat.getDirection());
        assertEquals(90, boat.getDirection().rotation);
        this.gameModel.rotateBoatClockWise();
        assertEquals(Direction.WEST, boat.getDirection());
        assertEquals(180, boat.getDirection().rotation);
        this.gameModel.rotateBoatClockWise();
        assertEquals(Direction.NORTH, boat.getDirection());
        assertEquals(-90, boat.getDirection().rotation);
        this.gameModel.rotateBoatClockWise();
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);

        this.gameModel.rotateBoatCounterClockWise();
        assertEquals(Direction.NORTH, boat.getDirection());
        assertEquals(-90, boat.getDirection().rotation);
        this.gameModel.rotateBoatCounterClockWise();
        assertEquals(Direction.WEST, boat.getDirection());
        assertEquals(180, boat.getDirection().rotation);
        this.gameModel.rotateBoatCounterClockWise();
        assertEquals(Direction.SOUTH, boat.getDirection());
        assertEquals(90, boat.getDirection().rotation);
        this.gameModel.rotateBoatCounterClockWise();
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);
    }

    @Test
    public void testRotateBoatBlockedByAnotherShip() {
        // setup
            // create the boat
            AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(10, 10), 4, Direction.EAST);
            // add it to implementor
            List<BoatInterface> boats = (List<BoatInterface>) Reflection.getFieldByReflection(this.boatsImplementor, "boats");
            boats.add(boat);
            Reflection.setFieldByReflection(this.gameModel, "selectedBoat", boat);

        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);
        this.gameModel.rotateBoatClockWise();
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);
        this.gameModel.rotateBoatCounterClockWise();
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);
    }

    @Test
    public void testRotateBoatOutOfGameBoard() {
        // setup
            // create the boat
            AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(10, 23), 4, Direction.EAST);
            // add it to implementor
            List<BoatInterface> boats = (List<BoatInterface>) Reflection.getFieldByReflection(this.boatsImplementor, "boats");
            boats.add(boat);
            Reflection.setFieldByReflection(this.gameModel, "selectedBoat", boat);

        // try one way
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);

        this.gameModel.rotateBoatCounterClockWise();
        assertEquals(Direction.NORTH, boat.getDirection());
        assertEquals(-90, boat.getDirection().rotation);

        this.gameModel.rotateBoatCounterClockWise();
        assertEquals(Direction.WEST, boat.getDirection());
        assertEquals(180, boat.getDirection().rotation);

        // go out of bounds (should block)
        this.gameModel.rotateBoatCounterClockWise();
        assertEquals(Direction.WEST, boat.getDirection());
        assertEquals(180, boat.getDirection().rotation);

        // try the other way
        this.gameModel.rotateBoatClockWise();
        assertEquals(Direction.NORTH, boat.getDirection());
        assertEquals(-90, boat.getDirection().rotation);

        this.gameModel.rotateBoatClockWise();
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);

        // go out of bounds (should block)
        this.gameModel.rotateBoatClockWise();
        assertEquals(Direction.EAST, boat.getDirection());
        assertEquals(0, boat.getDirection().rotation);

    }

    @Test
    @Ignore
    public void testRotateBoatOnMine(){
        // TODO
    }

    @Test
    public void testCreatePlayer(){

        // TODO fixme
//        //Test the creation of Player1 when setting of a GameModel
//        GameModel gameModel = new GameModel();
//        List<PlayerInterface> players = gameModel.getPlayers();
//        assertEquals(1,players.size());
//        assertEquals("Player1",players.get(0).getType());
//        assertEquals("Port1",players.get(0).getPortName());
//        //Adding a player to the model
//        try {
//            gameModel.createPlayer("Player2","Port2");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        assertEquals(2,players.size());
//        assertEquals("Player2",players.get(1).getType());
//        assertEquals("Port2",players.get(1).getPortName());
//        //Test the creation of a player already existing
//        try {
//            gameModel.createPlayer("Player2","Port2");
//            fail("Should have thrown an exception");
//        } catch (Exception e) {
//            String expectedMessage = "Player or Port already existing";
//            assertEquals(expectedMessage,e.getMessage());
//        }
//        assertEquals(2,players.size());
    }

    @Test
    public void testDeletePlayer(){
        // TODO fixme
//        GameModel gameModel = new GameModel();
//        //Trying to delete a non-existing player
//        try {
//            gameModel.deletePlayer("Player2");
//            fail();
//        } catch (Exception e) {
//            assertEquals("The player you want to delete isn't existing",e.getMessage());
//        }
//        try {
//            gameModel.deletePlayer("Player1");
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail();
//        }
//        List<PlayerInterface> players = gameModel.getPlayers();
//        assertEquals(0,players.size());
    }

    @Test
    public void testAddBoat() {
        // TODO fixme
//        //Trying to add a boat to the fleet of a player
//        //Uncomment the tests commented when the debug to see boat in GUI is removed in addBoat function in GameModel class
//        GameModel gameModel = new GameModel();
//        List<PlayerInterface> players = gameModel.getPlayers();
//        Player player1 = (Player) players.get(0);
//        List<BoatInterface> fleet = player1.getFleet();
//        //assertEquals(0,fleet.size());
//        gameModel.addBoat(player1,BoatType.Submarine, new Coord(0,0));
//        //assertEquals(1,fleet.size());
//
//        //Testing that the boat is correctly added
//        BoatInterface boat = fleet.get(0);
//        //assertEquals(new Coord(0,0),boat.getPivot());
    }
    
    @Test
public void testMoveBoatBlockedByAnotherShip() {
        // setup
            // create the boat
            AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(20, 11), 5, Direction.WEST);
            // add it to implementor
            List<BoatInterface> boats = (List<BoatInterface>) Reflection.getFieldByReflection(this.boatsImplementor, "boats");
            boats.add(boat);
            Reflection.setFieldByReflection(this.gameModel, "selectedBoat", boat);

        this.gameModel.moveBoat(17, 11);
        assertEquals(new Coord(17, 11),boat.getPivot());
        boat.moveAutorization();
        this.gameModel.moveBoat(5, 11);
        assertEquals(new Coord(15, 11),boat.getPivot());
    }

    @Test
    public void testMoveBoatOutOfGameBoard() {
        // setup
        // create the boat
        AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(10, 10), 5, Direction.EAST);
        // add it to implementor
        List<BoatInterface> boats = (List<BoatInterface>) Reflection.getFieldByReflection(this.boatsImplementor, "boats");
        boats.add(boat);
        Reflection.setFieldByReflection(this.gameModel, "selectedBoat", boat);

        this.gameModel.moveBoat(15, 10);
        assertEquals(new Coord(15, 10),boat.getPivot());
        boat.moveAutorization();
        this.gameModel.moveBoat(500, 10);
        assertEquals(new Coord(22, 10),boat.getPivot());
    }

    @Test
    @Ignore
    public void testMoveBoatOnMine(){
        // TODO !!
    }
    
    @Test
    public void testEndActionsPlayer(){
    	//changer déclaration gameModel
    	int initTurn = this.gameModel.getTurn();
    	this.gameModel.endTurn();
    	assertEquals(initTurn, this.gameModel.getTurn());
    	
    	int initDay = this.gameModel.getDay();
    	this.gameModel.endDay();
    	assertEquals(initDay, this.gameModel.getDay());
     	
    	int nextDay = this.gameModel.getDay();
    	this.gameModel.initDay();
    	assertEquals(nextDay, this.gameModel.getDay()-1);
        	
    	int nextTurn = this.gameModel.getTurn();
    	this.gameModel.initTurn();
    	assertEquals(nextTurn, this.gameModel.getTurn()-1);
    }
     
    
    public void testNextPlayer(){
    	PlayerInterface nextPlayer = new Player(1,"nextName", "nextPort");
    	this.gameModel.getPlayersImplementor().getPlayers().add(nextPlayer);
    	this.gameModel.nextPlayer();
    	assertEquals(this.gameModel.getCurrentPlayer(), nextPlayer);
    }

}