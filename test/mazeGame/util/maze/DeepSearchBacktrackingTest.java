package mazeGame.util.maze;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeepSearchBacktrackingTest {

	private DeepSearchBacktracking maze;

    @Before
    public void before(){
        this.maze = new DeepSearchBacktracking(2, 2);
    }

    @Test
    public void theFirstCellOfTheMazeIsVisited(){
        assertTrue(this.maze.getCell(0,0).isVisited());
    }

    @Test
    public void theMethodIndexReturnTheDesiredCell(){
        assertEquals(this.maze.getCell(0,0),this.maze.index(0,0));
        assertNull(this.maze.index(0,-1));
    }

    @Test
    public void checkNeighborsGiveTheRightNeighbor(){
        this.maze.getCell(1,0).setVisited();
        Cell c1 =this.maze.checkNeighbors(this.maze.getCell(0,0));
        assertEquals(c1,this.maze.getCell(0,1));
        this.maze.getCell(0,1).setVisited();
        assertNull(this.maze.checkNeighbors(this.maze.getCell(0,0)));
    }

}
