package mazeGame.util.character;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import mazeGame.util.maze.Cell;

public class MobileNPCTest {

	private Player p1;
	private ArrayList<Cell> thepath = new ArrayList<>();
	private MobileNPC mch1;
	private Cell c1;
	private Cell c2;
	private Cell c3;
	@Before
	public void Before() throws Exception {
		this.c1 = new Cell(0,0);
		this.mch1 = new MobileNPC(c1,1);
		this.c1.getMobileNPC().add(this.mch1);
		this.c2 = new Cell(0,1);
		this.c3 = new Cell(1,0);
		this.p1= new Player(c1);
		
		this.thepath.add(this.c1);
		this.thepath.add(this.c2);
		this.thepath.add(this.c3);
		this.mch1.setPaths(this.thepath);
		
	}
	
	@Test
	public void theNPCMoveCorrectyInBothWay() {
		assertFalse(this.c1.getMobileNPC().isEmpty());
		assertEquals(this.c1,this.mch1.getCell());
		this.mch1.move();
		assertEquals(this.c2,this.mch1.getCell());
		assertTrue(this.c1.getMobileNPC().isEmpty());
		this.mch1.move();
		assertEquals(this.c3,this.mch1.getCell());
		assertTrue(this.c2.getMobileNPC().isEmpty());
		this.mch1.move();
		assertEquals(this.c2,this.mch1.getCell());
		assertTrue(this.c3.getMobileNPC().isEmpty());
		this.mch1.move();
		assertEquals(this.c1,this.mch1.getCell());
		assertTrue(this.c2.getMobileNPC().isEmpty());
		this.mch1.move();
		assertEquals(this.c2,this.mch1.getCell());
		assertTrue(this.c1.getMobileNPC().isEmpty());
		
	}
}
