package mazeGame.util.clue;

import mazeGame.util.maze.Cell;

/**
 * The Class Jewel.
 */
public class Jewel extends Resalable {
	/**Declaring a constant variable for the name of the Item.*/
	public static final String TYPE = "Jewel";

	/**
	 * Constructor of a new jewel.
	 *
	 * @param cell the cell of the jewel
	 * @param size the size of the jewel
	 * @param value the value of the jewel size 1
	 */
	public Jewel(Cell cell, int size, int value) {
		super(cell, size, value);
	}

	/**
	 * The function toString() returns a string that represents the object
	 * 
	 * @return The size of the jewel and its value in gold.
	 */
	@Override
	public String toString() {
		return "Joyau de taille " + this.getSize() + " d'une valeur de " + this.getValueItem() + " or";
	}

	/**
	 * This function returns the type of the object
	 * 
	 * @return The type of the jewel.
	 */
	@Override
	public String getType() {
		return Jewel.TYPE;
	}
}
