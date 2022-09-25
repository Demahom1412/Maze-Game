package mazeGame.util.character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import mazeGame.util.clue.Jewel;
import mazeGame.util.clue.Parchment;
import mazeGame.util.maze.Cell;

public class PlayerTest {
	
	private Player p1;
	private Cell c1;
	private Jewel j1;
	private Parchment py;
	private Cell c2;
	private List<Object> condition;
	private List<Object> newcondition;
	@Before
	public void Before() throws Exception {
		this.c1 = new Cell(0,0);
		this.p1= new Player(c1);
		this.j1= new Jewel(c1, 2, 5);
		this.py= new Parchment(c1);
		this.c2 = new Cell(0,1);
		this.condition = new ArrayList<>();
		this.newcondition = new ArrayList<>();
		this.condition.add(c1);
		this.newcondition.add(c2);
		
	}

	@Test
	public void theInventoryOfThePlayerIsEmptyAtTheStart() {
		assertTrue(this.p1.getItem("Jewel").isEmpty());
		assertTrue(this.p1.getItem("Parchment").isEmpty());
	}
	
	@Test
	public void thePlayerDoesntKnowTheDestinationCellAtTheStart() {
		assertFalse(this.p1.know());
	}
	
	@Test
	public void addingAndRemovingItemTest() {
		assertTrue(this.p1.getItem("Jewel").isEmpty());
		assertTrue(this.p1.getItem("Parchment").isEmpty());
		this.p1.addItems("Jewel", j1);
		this.p1.addItems("Parchment", py);
		assertFalse(this.p1.getItem("Jewel").isEmpty());
		assertFalse(this.p1.getItem("Parchment").isEmpty());
		this.p1.removeItem("Jewel", j1);
		this.p1.removeItem("Parchment", py);
		assertTrue(this.p1.getItem("Jewel").isEmpty());
		assertTrue(this.p1.getItem("Parchment").isEmpty());
	}
	
	@Test
	public void changingTheCellOfThePlayerTest() {
		assertEquals(this.c1,this.p1.getCurrentCell());
		this.p1.changeCell(c2);
		assertEquals(this.c2,this.p1.getCurrentCell());
	}
	
	@Test
	public void addingAndRemovingGoldTest() {
		assertSame(0,this.p1.getGold());
		this.p1.addGold(5);
		assertSame(5,this.p1.getGold());
		this.p1.removeGold(2);
		assertSame(3,this.p1.getGold());
	}
	
	@Test
	public void theConditionListOfThePlayerTest() {
		assertEquals(this.p1.getConditions(),this.condition);
		this.p1.updateCondition(0, c2);
		assertNotEquals(this.p1.getConditions(),this.condition);
		assertEquals(this.p1.getConditions(),this.newcondition);
	}

}
