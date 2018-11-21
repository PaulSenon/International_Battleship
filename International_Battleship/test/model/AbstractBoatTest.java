package model;

import org.junit.Before;
import org.junit.Test;
import testTools.BaseTests;
import tools.Coord;
import tools.Direction;

import java.util.List;

import static org.junit.Assert.*;

public class AbstractBoatTest extends BaseTests {

    private Boat boat;

    @Before
    public void setUp() throws Exception {
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
        assertEquals(new Coord(7,10), coords.get(0));
        assertEquals(new Coord(8,10), coords.get(1));
        assertEquals(new Coord(9,10), coords.get(2));
        assertEquals(new Coord(10,10), coords.get(3)); // pivot
        assertEquals(new Coord(11,10), coords.get(4));
        assertEquals(new Coord(12,10), coords.get(5)); // head

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
        assertEquals(new Coord(13,10), coords.get(0));
        assertEquals(new Coord(12,10), coords.get(1));
        assertEquals(new Coord(11,10), coords.get(2));
        assertEquals(new Coord(10,10), coords.get(3)); // pivot
        assertEquals(new Coord(9,10), coords.get(4));
        assertEquals(new Coord(8,10), coords.get(5)); // head

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
        assertEquals(new Coord(10,12), coords.get(0));
        assertEquals(new Coord(10,11), coords.get(1));
        assertEquals(new Coord(10,10), coords.get(2)); // pivot
        assertEquals(new Coord(10,9), coords.get(3)); // head

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
        assertEquals(new Coord(10,8), coords.get(0));
        assertEquals(new Coord(10,9), coords.get(1));
        assertEquals(new Coord(10,10), coords.get(2)); // pivot
        assertEquals(new Coord(10,11), coords.get(3)); // head
    }
}