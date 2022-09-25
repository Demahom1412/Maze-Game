package mazeGame.util.character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazeGame.util.maze.Cell;

public class GoldPlayerTest {
	
	private GoldPlayer p1;
	private Cell c1;
	private Cell c2;
	private List<Object> condition;
	private List<Object> newcondition;
	@Before
	public void before() {
		this.c1 = new Cell(0,0);
		this.p1 = new GoldPlayer(c1);
		this.p1.changeCell(c1);
		this.c2 = new Cell(0,1);
		this.condition = new ArrayList<>();
		this.newcondition = new ArrayList<>();
		this.condition.add(c1);
		this.condition.add(5);
		this.newcondition.add(c2);
		this.newcondition.add(5);

	}
	
	@Test
	public void theConditionListOfThePlayerTest() {
		assertNotEquals(this.p1.getConditions(),this.condition);
		this.p1.addGold(5);
		assertEquals(this.p1.getConditions(),this.condition);
		this.p1.updateCondition(0, c2);
		assertEquals(this.p1.getConditions(),this.newcondition);
	}
}