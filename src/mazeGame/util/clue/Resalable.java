package mazeGame.util.clue;

import mazeGame.util.maze.Cell;

/**
 * The class Resalable that group resalable items
 */
public class Resalable extends Item {

	/** The size of the jewel. */
	private int size;

	/** The value of a jewel with a size 1. */
	private final int value;

	/**
	 * The constructor of the class Resalable
	 * 
	 * @param cell  the cell of the item
	 * @param size  the size of the item
	 * @param value the value of the item
	 */
	public Resalable(Cell cell, int size, int value) {
		super(cell);
		this.size = size;
		this.value = value;
	}

	/**
	 * Get the size of the item
	 * 
	 * @return the size of the item
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Get the value of the item
	 * 
	 * @return the value of the item
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * This function returns the value of the item(size multiply by value)
	 * 
	 * @return The value of the item.
	 */
	@Override
	public int getValueItem() {
		return this.getSize() * this.getValue();
	}
}
