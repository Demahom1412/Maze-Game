package mazeGame.util.clue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazeGame.util.maze.Cell;

public class JewelTest {
	
	private Jewel j1;
	private Cell c1;
	@Before
	public void before() throws Exception {
		this.c1= new Cell(0,0);
		this.j1= new Jewel(c1, 2, 5);
	}
	

	@Test
	public void theTypeIsCorrect() {
		assertEquals("Jewel",this.j1.getType());
	}

}
