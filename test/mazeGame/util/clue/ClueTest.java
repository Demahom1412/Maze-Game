package mazeGame.util.clue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazeGame.util.maze.Cell;

public class ClueTest {
	
	private Clue clue;
	private Cell c1;
	
	@Before
	public void setUp() throws Exception {
		this.c1= new Cell(0,0);
		this.clue = new Clue(this.c1);
	}

	@Test
	public void theClueIsNotAQuest() {
		assertFalse(this.clue.isQuest());
		
	}

}
