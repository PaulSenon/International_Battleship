package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tools.Coord;

public class MineTest {
	
	@Before
	public void setUp() throws Exception{

	}
	
	@Test
	public void testVisibleCoordsMine(){
		Coord coord = new Coord(10, 10);
		
		//Test radius à 0
		Mine mine = new Mine(coord,0,0,0);
		List<Coord> coords = mine.getVisibleCoords();
		assertEquals(1,coords.size());
		assertTrue(coords.contains(new Coord(10,10)));

		//Test radius à 1
		mine = new Mine(coord,0,0,1); //test radius à 1
		coords = mine.getVisibleCoords();
		assertEquals(5,coords.size());
		assertTrue(coords.contains(new Coord(10,10)));
		assertTrue(coords.contains(new Coord(11,10)));
		assertTrue(coords.contains(new Coord(9,10)));
		assertTrue(coords.contains(new Coord(10,11)));
		assertTrue(coords.contains(new Coord(10,9)));
		
		//Test radius à 2
		mine = new Mine(coord,0,0,2); //test radius à 1
		coords = mine.getVisibleCoords();
		coords = new ArrayList<Coord>(new HashSet<>(coords));
		//TODO modifier algo de Mine pour avoir bonne visibilté 
		/*
				  *	- *
				* - - - *
				- - x - -
				* - - - *
				  * - *
		*/
		assertEquals(21,coords.size());
		assertTrue(coords.contains(new Coord(10,10)));
		assertTrue(coords.contains(new Coord(11,10)));
		assertTrue(coords.contains(new Coord(9,10)));
		assertTrue(coords.contains(new Coord(10,11)));
		assertTrue(coords.contains(new Coord(10,9)));
		assertTrue(coords.contains(new Coord(9,9)));
		assertTrue(coords.contains(new Coord(11,11)));
		assertTrue(coords.contains(new Coord(9,11)));
		assertTrue(coords.contains(new Coord(11,9)));
		assertTrue(coords.contains(new Coord(10,8)));
		assertTrue(coords.contains(new Coord(8,10)));
		assertTrue(coords.contains(new Coord(10,12)));
		assertTrue(coords.contains(new Coord(12,10)));
		assertTrue(coords.contains(new Coord(9,8)));
		assertTrue(coords.contains(new Coord(12,11)));
		assertTrue(coords.contains(new Coord(8,9)));
		assertTrue(coords.contains(new Coord(11,12)));
		assertTrue(coords.contains(new Coord(8,11)));
		assertTrue(coords.contains(new Coord(9,12)));
		assertTrue(coords.contains(new Coord(11,8)));
		assertTrue(coords.contains(new Coord(12,9)));
	}
	
}
