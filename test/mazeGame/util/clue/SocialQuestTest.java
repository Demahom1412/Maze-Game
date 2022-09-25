package mazeGame.util.clue;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import java.lang.Integer;
import mazeGame.util.maze.Cell;

public class SocialQuestTest {
	
	private SocialQuest sq1;
	private Cell c1;
	private Cell c2;
	private List<Object> condition;
	private List<Object> falsecondition;
	@Before
	public void setUp() throws Exception {
		this.c1= new Cell(0,0);
		this.c2= new Cell(1,0);
		this.sq1 = new SocialQuest(c1,2);
		this.condition = new ArrayList<>();
		this.condition.add(c1);
		this.condition.add(Integer.valueOf(2));
		this.falsecondition = new ArrayList<>();
		this.falsecondition.add(c2);
		this.falsecondition.add(Integer.valueOf(0));
	}

	@Test
	public void theConditionHaveBeenMet() {
		assertTrue(this.sq1.checkConditions(this.condition));
	}
	@Test
	public void theConditionHaveNotBeenMet() {
		assertFalse(this.sq1.checkConditions(this.falsecondition));
	}

}
