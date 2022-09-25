package mazeGame.util.clue;

import mazeGame.util.maze.Cell;

/**
 * The Class Parchment.
 */
public class Parchment extends Item {

	/**Declaring a constant variable for the name of the Item.*/
	public static final String TYPE = "Parchment";

	/**
	 * Constructor of a new parchment.
	 *
	 * @param cell the cell of the parchment
	 */
	public Parchment(Cell cell) {
		super(cell);

	}

	/**
	 * Call the method showClue from the param c
	 *
	 * @param c : a object from the class Clue to make the clue between the
	 *          parameter and the parchment
	 * @return a boolean, here always true, because a parchment give you a clue for
	 *         the Quest
	 */
	@Override
	public boolean clue(Clue c) {
		c.showClue();
		return true;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getType() {
		return Parchment.TYPE;
	}

	/**
	 * This function returns the type of the object
	 * 
	 * @return The type of the parchment.
	 */
	@Override
	public String toString() {
		return "Un parchemin";
	}
}
