package mazeGame.util.character;

import java.util.Arrays;
import java.util.List;

import mazeGame.util.maze.Cell;
/**
 * The GoldPlayer class that represent a player with gold condition.
 */
public class GoldPlayer extends Player {
	
	/**
     * Constructor of the GoldPlayer class
     * 
     * @param cell  the cell of the GoldPlayer
     */
    public GoldPlayer(Cell cell) {
        super(cell);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getConditions() {
        return Arrays.asList(this.getCurrentCell(), this.getGold());
    }
}
