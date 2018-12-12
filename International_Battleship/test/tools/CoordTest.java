package tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordTest {

    @Test
    public void testGetDistanceTo(){
        Coord coord = new Coord(2,1);
        int distance = coord.getDistanceTo(new Coord(2,10));
        assertEquals(9, distance);

        coord = new Coord(2,1);
        distance = coord.getDistanceTo(new Coord(2,-10));
        assertEquals(-11, distance);

        coord = new Coord(2,1);
        distance = coord.getDistanceTo(new Coord(10,10));
        assertEquals(17, distance);

        // TODO ...
    }

}