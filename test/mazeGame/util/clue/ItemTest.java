package mazeGame.util.clue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazeGame.util.maze.Cell;

public class ItemTest {
	
	private Item i1;
	private Clue clue;
	private Cell c1;
	@Before
	public void before() throws Exception {
		this.c1= new Cell(0,0);
		this.clue = new Clue(c1);
		this.i1= new Item(c1);
	}

	@Test
	public void theItemReturnNoClue() {
		assertFalse(this.i1.clue(this.clue));
	}
	@Test
	public void theValueOfTheItemIsZero() {
		assertSame(0,this.i1.getValueItem());
	}
	@Test
	public void theTypeIsCorrcet() {
		assertEquals(this.i1.getType(),"Item");
	}

}
