package mazeGame.util.clue;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



import mazeGame.util.maze.Cell;

public class QuestTest {
	
	private Quest q1;
	private Cell c1;
	private Cell c2;
	private List<Object> condition;
	private List<Object> falsecondition;
	@Before
	public void setUp() throws Exception {
		this.c1= new Cell(0,0);
		this.c2= new Cell(1,0);
		this.q1 = new Quest(c1);
		this.condition = new ArrayList<>();
		this.condition.add(c1);
		this.falsecondition = new ArrayList<>();
		this.falsecondition.add(c2);
	}

	@Test
	public void isQuestReturnTrue() {
		assertTrue(this.q1.isQuest());
	}
	
	@Test
	public void theConditionHaveBeenMet() {
		assertTrue(this.q1.checkConditions(this.condition));
	}
	@Test
	public void theConditionHaveNotBeenMet() {
		assertFalse(this.q1.checkConditions(this.falsecondition));
	}

}
