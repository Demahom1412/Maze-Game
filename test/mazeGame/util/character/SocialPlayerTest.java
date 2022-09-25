package mazeGame.util.character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import mazeGame.util.maze.Cell;

public class SocialPlayerTest {

	private SocialPlayer SP;
	private Cell c1;
	private Cell c2;
	private List<Object> condition;
	private List<Object> newcondition;
	@Before
	public void before() {
		this.c1 = new Cell(0,0);
		this.SP = new SocialPlayer(c1);
		this.SP.changeCell(c1);
		this.c2 = new Cell(0,1);
		this.condition = new ArrayList<>();
		this.newcondition = new ArrayList<>();
		this.condition.add(c1);
		this.condition.add(1);
		this.newcondition.add(c2);
		this.newcondition.add(1);
		
		
	}

	@Test
	public void getNPCAndAddNPCTest() {
		assertEquals(0,this.SP.getNPC());//The number of NPC is 0 because there is none at creation
		this.SP.addNPC(5);
		assertEquals(5,this.SP.getNPC());
	}
	
	@Test
	public void theConditionListOfThePlayerTest() {
		assertNotEquals(this.SP.getConditions(),this.condition);
		this.SP.updateCondition(1, c1);
		assertEquals(this.SP.getConditions(),this.condition);
		this.SP.updateCondition(0, c2);
		assertEquals(this.SP.getConditions(),this.newcondition);
	}
	
}
