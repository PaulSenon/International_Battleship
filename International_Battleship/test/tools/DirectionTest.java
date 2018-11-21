package tools;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    private Direction direction;

    @Before
    public void setUp() throws Exception {
        this.direction = Direction.DEFAULT();
    }

    @Test
    public void testNext() {
        assertEquals(0, this.direction.rotation);

        this.direction = this.direction.next(true);
        assertEquals(90, this.direction.rotation);
        this.direction = this.direction.next(true);
        assertEquals(180, this.direction.rotation);
        this.direction = this.direction.next(true);
        assertEquals(-90, this.direction.rotation);
        this.direction = this.direction.next(true);
        assertEquals(0, this.direction.rotation);
        this.direction = this.direction.next(true);
        assertEquals(90, this.direction.rotation);

        this.direction = this.direction.next(false);
        assertEquals(0, this.direction.rotation);
        this.direction = this.direction.next(false);
        assertEquals(-90, this.direction.rotation);
        this.direction = this.direction.next(false);
        assertEquals(180, this.direction.rotation);
        this.direction = this.direction.next(false);
        assertEquals(90, this.direction.rotation);
        this.direction = this.direction.next(false);
        assertEquals(0, this.direction.rotation);
    }
}