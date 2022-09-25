package mazeGame.util.clue;

import mazeGame.util.maze.Cell;

/**
 * The Class Item .
 */
public class Item extends Clue {
	/** Declaring a constant variable for the name of the Item.*/
	public static final String TYPE = "Item";

	/**
	 * Constructor of a new item.
	 *
	 * @param cell the cell of the item
	 */
	public Item(Cell cell) {
		super(cell);
	}

	/**
	 * A method that returns the type of the item.
	 * @return the type of the item
	*/
	public String getType() {
		return Item.TYPE;
	}

	/**
	 * Generate a clue for the object c
	 * 
	 * @param c An object to found
	 * @return a boolean, here always false, because an item don't generate a clue
	 */
	public boolean clue(Clue c) {
		return false;
	}

	/**
	 * This function returns the value of the item
	 * 
	 * @return 0 because an Item have no value
	 */
	public int getValueItem() {
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Item) {
			Item other = (Item) o;
			return this.getType().equals(other.getType()) && this.getCurrentCell().equals(other.getCurrentCell()) && this.getValueItem() == other.getValueItem();
		}else {
			return false;
		}
	}
}
