package mazeGame.util.character;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import mazeGame.util.clue.Clue;
import mazeGame.util.clue.Quest;
import mazeGame.util.maze.Cell;

public class CharactersTest {

	private Player p1;
	private ArrayList<Clue> theclues = new ArrayList<>();
	private Characters ch1;
	private Cell c1;
	private Cell c2;
	private Clue clue;
	@Before
	public void Before() throws Exception {
		this.c1 = new Cell(0,0);
		this.c2 = new Cell(0,1);
		this.p1= new Player(c1);
		this.ch1 = new Characters(c1,1);
		this.clue= new Clue(c2);
		this.theclues.add(this.clue);
		
	}
	
	@Test
	public void theCharacterNeverTalkedToThePlayerAtTheStart() {
		assertSame(0,this.ch1.getTalk());
	}
	
	@Test
	public void theMethodClueReturnTheCorrectBoolean() {
		assertFalse(this.ch1.clue(this.clue, "Nord", 5));
	}
	
	@Test
	public void theActionsMethodWithNormalClueTest() {
		assertSame(0,this.ch1.getTalk());
		assertFalse(this.ch1.actions(this.p1, this.theclues));
		assertSame(1,this.ch1.getTalk());
		assertFalse(this.ch1.actions(this.p1, this.theclues));
	}
}
