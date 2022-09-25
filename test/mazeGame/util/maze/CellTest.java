package mazeGame.util.maze;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazeGame.util.character.Characters;
import mazeGame.util.character.Player;
import mazeGame.util.clue.Item;

public class CellTest {

	private Cell c1;
	private Cell parentC1;
	private Player p1;
	private Characters ch1;
	private Item i1;
    @Before
    public void before(){
        this.c1 = new Cell(0,0);
        this.parentC1=new Cell(1,0);
        this.p1 = new Player(c1);
        this.p1.changeCell(this.c1);
        this.ch1 = new Characters(c1,1);
        this.i1 = new Item(c1);
    }
    @Test
    public void allTheWallsOfTheCellArePresentAtCreation(){
        assertTrue(this.c1.north());
        assertTrue(this.c1.west());
        assertTrue(this.c1.south());
        assertTrue(this.c1.east());
    }
    @Test
    public void theCellIsNotVisitedAtCreation(){
        assertFalse(this.c1.isVisited());
        assertFalse(this.c1.isVisitedByPathFinder());
    }
    @Test
    public void theCorrectWallIsDestroyed(){
        this.c1.destroyWall("N");
        assertFalse(this.c1.north());
        this.c1.destroyWall("S");
        assertFalse(this.c1.south());
    }
    @Test
    public void noOneIsOnTheCellAtCreation() {
    	assertNull(this.c1.getCharacter());
    	assertTrue(this.c1.getMobileNPC().isEmpty());
    	assertNull(this.c1.getPlayer());
    }
    @Test
    public void theCellHasNoItemAtCreation() {
    	assertNull(this.c1.getItem());
    }
    @Test
    public void theCellHasNoParentAtCreation() {
    	assertNull(this.c1.getParent());
    }
    @Test
    public void theCellIsCorrectlyVisited() {
    	assertFalse(this.c1.isVisited());
        assertFalse(this.c1.isVisitedByPathFinder());
        this.c1.setVisited();
        this.c1.setVisitedByPathFinder(true);
        assertTrue(this.c1.isVisited());
        assertTrue(this.c1.isVisitedByPathFinder());
    }
    @Test
    public void aPlayerIsCorreclyPutOnTheCell() {
    	assertNull(this.c1.getPlayer());
    	this.c1.setPlayer(this.p1);
    	assertEquals(this.p1,this.c1.getPlayer());
    }
    
    @Test
    public void aCharacterIsCorrectylPutOnTheCell() {
    	assertNull(this.c1.getCharacter());
    	this.c1.setCharacter(this.ch1);
    	assertEquals(this.ch1,this.c1.getCharacter());
    	
    }
    @Test
    public void anItemIsCorretlyPutOnTheCell() {
    	assertNull(this.c1.getItem());
    	this.c1.setItem(this.i1);
    	assertEquals(this.i1,this.c1.getItem());
    }
    @Test
    public void theParentCellIsCorretlySet() {
    	assertNull(this.c1.getParent());
    	this.c1.setParent(this.parentC1);
    	assertEquals(this.parentC1,this.c1.getParent());
    }
    @Test
    public void anItemIsCorretlyRemovedFromTheCell() {
    	assertNull(this.c1.getItem());
    	this.c1.setItem(this.i1);
    	assertEquals(this.i1,this.c1.getItem());
    	this.c1.removeitem();
    	assertNull(this.c1.getItem());
    }
    @Test
    public void aCharacterIsCorrectyRemovedFromTheCell() {
    	assertNull(this.c1.getCharacter());
    	this.c1.setCharacter(this.ch1);
    	assertEquals(this.ch1,this.c1.getCharacter());
    	this.c1.removeCharacter();
    	assertNull(this.c1.getCharacter());
    }
    
}
