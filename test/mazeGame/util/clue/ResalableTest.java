package mazeGame.util.clue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazeGame.util.maze.Cell;

public class ResalableTest {
	
	private Jewel j1;
	private Cell c1;
	@Before
	public void setUp() throws Exception {
		this.c1= new Cell(0,0);
		this.j1= new Jewel(c1, 2, 5);
	}

	@Test
	public void theGetterReturnGiveDesiredValue() {
		assertSame(2,this.j1.getSize());
		assertSame(5,this.j1.getValue());
	}
	@Test
	public void theValueOfTheResalableItemIsCorrect() {
		assertSame(10,this.j1.getValueItem());
	}

}
