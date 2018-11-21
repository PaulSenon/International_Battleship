package model;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import testTools.BaseTests;
import testTools.Reflection;
import tools.GameConfig;
import tools.Coord;
import tools.Direction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameModelTest extends BaseTests {
    private GameModelInterface gameModel;
    private BoatsImplementor boatsImplementor;

    @Before
    public void setUp() throws Exception {
        // create a game config for game board bounds test
        GameConfig.newInstance(
                25, // gameGridWidth
                25 // gameGridHeight
        );

        // create boats implementor
        List<Player> players = new ArrayList<>();
        List<BoatName> boatNames = new ArrayList<>();
        this.boatsImplementor = new BoatsImplementor(players, boatNames);

        // add some test boats on the game board
        // TODO change values
        List<BoatInterface> boats = new ArrayList<>();
        boats.add(this.objGenerator.generateTestBoat(new Coord(10, 11), 5, Direction.EAST));

        Reflection.setFieldByReflection(this.boatsImplementor, "boats", boats);

        // create game model
        this.gameModel = new GameModel();
        Reflection.setFieldByReflection(this.gameModel, "battleshipImplementor", this.boatsImplementor);

        // add some mines on the game board
        // TODO mines does not exist yet !
    }

    @Test
    public void testRotateBoat(){
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
    public void testRotateBoatBlockedByAnotherShip(){
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
    public void testRotateBoatOutOfGameBoard(){
        // setup
            // create the boat
            AbstractBoat boat = this.objGenerator.generateTestBoat(new Coord(10, 1), 4, Direction.EAST);
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

}