package model;

import org.junit.Before;
import org.junit.Test;
import testTools.Reflection;
import tools.Coord;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MineImplementorTest {
	
	@Before
	public void setUp() throws Exception{

	}
	
	@Test
	public void testCreateMine(){
		//On teste qu'il n'y a pas de mine au départ
		MineImplementor mineImplementor = new MineImplementor();
		List<Mine> mines = (List<Mine>) Reflection.getFieldByReflection(mineImplementor, "mines");
		assertEquals(0,mines.size());
		
		//On teste la création d'une mine
		mineImplementor.createMine(new Coord(10,10), 0);
		mines = (List<Mine>) Reflection.getFieldByReflection(mineImplementor, "mines");
		assertEquals(1,mines.size());
		assertEquals(new Coord(10,10),mines.get(0).getCoord());	
	}
	
	public void testIsMined(){
		///On teste qu'une mine n'est pas placé à ces coords
		MineImplementor mineImplementor = new MineImplementor();
		Boolean res = mineImplementor.isMined(new Coord(10,10));
		assertEquals(false,res);
		
		//On teste qu'une mine est placé à ces coords
		mineImplementor.createMine(new Coord(10,10), 0);
		assertEquals(true,res);
		
		//On test si la non création d'une mine au même coordonnées
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
		mineImplementor.createMine(new Coord(10,10), 1);
		assertEquals("Attention une mine existe déjà à cet endroit", outContent.toString());
	}
	
	
}
