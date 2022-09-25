package mazeGame.util.clue;

import mazeGame.util.maze.Cell;

/**
 * The Class Clue, that cover all the objects that can be pointed by a clue in
 * the game.
 */
public class Clue {

	/** The current cell of the object in maze. */
	protected Cell currentCell;

	/**
	 * Constructor of a new clue.
	 *
	 * @param cell the cell of the clue
	 */
	public Clue(Cell cell) {
		this.currentCell = cell;
	}

	/**
	 * Gets the current cell.
	 *
	 * @return the current cell
	 */
	public Cell getCurrentCell() {
		return currentCell;
	}

	/**
	 * Sets the current cell.
	 *
	 * @param currentCell the new current cell
	 */
	public void setCurrentCell(Cell currentCell) {
		this.currentCell = currentCell;
	}

	/**
	 * Display the clue to the case
	 */
	public void showClue() {
		System.out.println("A la case " + this.currentCell + "se trouve quelque chose ");
	}

	/**
	 * Return true if the clue is a Quest
	 * 
	 * @return boolean
	 */
	public boolean isQuest() {
		return false;
	}
}
