package mazeGame.util.clue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazeGame.util.maze.Cell;

public class ParchmentTest {
	
	private Clue clue;
	private Parchment p1;
	private Cell c1;
	@Before
	public void setUp() throws Exception {
		this.c1= new Cell(0,0);
		this.clue = new Clue(this.c1);
		this.p1= new Parchment(c1);
	}

	@Test
	public void theClueFunctionReturnTrue() {
		assertTrue(this.p1.clue(this.clue));
	}
	
	@Test
	public void theTypeIsCorrcet() {
		assertEquals(this.p1.getType(),"Parchment");
	}

}
