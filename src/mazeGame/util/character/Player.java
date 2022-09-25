package mazeGame.util.character;

import java.util.*;

import mazeGame.util.clue.Item;
import mazeGame.util.maze.Cell;

/**
 * The Class Player.
 */
public class Player {

	/** The amount of gold that player have. */
	protected int gold;
	/** A boolean to track if the player the cell of the Quest. */
	protected boolean know = false;

	/** The current cell of the player. */
	protected Cell currentCell;

	/** The items that player have. */
	protected Map<String, ArrayList<Item>> items;

	/**
	 * Constructor of a new player.
	 *
	 * @param cell the cell of the player
	 */
	public Player(Cell cell) {
		this.gold = 0;
		this.items = new HashMap<>();
		this.initializeMap();
		this.currentCell= cell;
	}

	/**
	 * Initialize the HashMap with the keys and their ArrayList
	 */
	protected void initializeMap() {
		this.items.put("Parchment", new ArrayList<>());
		this.items.put("Jewel", new ArrayList<>());
	}

	/**
	 * Adds the param item to the ArrayList attached to the key to the param s .
	 * 
	 * @param s    : the key
	 * @param item the item
	 */
	public void addItems(String s, Item item) {
		this.items.get(s).add(item);
	}

	/**
	 * Return the list of item attached to the key s
	 * 
	 * @param s : the key
	 * @return ArrayListItem
	 */
	public ArrayList<Item> getItem(String s) {
		return this.items.get(s);
	}

	/**
	 * Return the map to display in the game the inventory of the player
	 * 
	 * @return Map : String, ArrayList Item 
	 */
	public Map<String, ArrayList<Item>> getAllItems() {
		return this.items;
	}

	/**
	 * Remove the item from the list attached to the key s
	 * 
	 * @param s    : the key
	 * @param item : the item to be removed
	 */
	public void removeItem(String s, Item item) {
		this.items.get(s).remove(item);
	}

	/**
	 * Change the cell of the player
	 * 
	 * @param cell , the new cell
	 */
	public void changeCell(Cell cell) {
		this.currentCell = cell;
	}

	/**
	 * Return the amount of gold of the player
	 * 
	 * @return int : gold
	 */
	public int getGold() {
		return this.gold;
	}

	/**
	 * This function add gold from the player's inventory
	 * 
	 * @param gold, The amount of gold to add to the player
	 */
	public void addGold(int gold) {
		this.gold += gold;
	}

	/**
	 * This function removes gold from the player's inventory
	 * 
	 * @param gold The amount of gold to remove from the player.
	 */
	public void removeGold(int gold) {
		this.gold -= gold;
	}

	/**
	 * This function sets the know variable to true
	 */
	public void setKnow() {
		this.know = true;
	}

	/**
	 * This function returns the value of the boolean variable know
	 * 
	 * @return The boolean value of the variable know.
	 */
	public boolean know() {
		return this.know;
	}

	/**
	 * Get the current cell
	 * 
	 * @return the current cell
	 */
	public Cell getCurrentCell() {
		return this.currentCell;
	}

	/**
	 * It prints out the items in the inventory and returns an arraylist of the
	 * items in the inventory
	 * 
	 * @return The choices of the items in the menu.
	 */
	public ArrayList<String> showItem() {
		int count = 1;
		ArrayList<String> choices = new ArrayList<>();
		for (String s : this.getAllItems().keySet()) {
			if (!this.getItem(s).isEmpty()) {
				System.out.println(count + " - " + this.getItem(s));
				choices.add(s);
				count++;
			}

		}
		return choices;
	}

	/**
	 * This function returns the condition as list of object
	 * 
	 * @return A list of object.
	 */
	public List<Object> getConditions() {
		return Arrays.asList(this.getCurrentCell());
	}

	/**
	 * The player is removed from the current cell and added
	 * to the next cell
	 * 
	 * @param n    the number of the condition to be changed
	 * @param next the cell that the player is moving to
	 */
	public void updateCondition(int n, Cell next) {
		if (n == 0) {
			this.currentCell.setPlayer(null);
			this.changeCell(next);
			next.setPlayer(this);
		}
	}

	/**
	 * It returns a string that says "Le joueur avec " followed by the amount of
	 * gold the player has
	 * 
	 * @return The player
	 */
	@Override
	public String toString() {
		return "Le joueur avec " + this.getGold() + " or";
	}
	
	@Override
	public boolean equals (Object o) {
		if(o instanceof Player) {
			Player other = (Player) o;
			return this.getCurrentCell().equals(other.getCurrentCell()) &&  this.getConditions().equals(other.getConditions());
		}else {
			return false;
		}
	}

}
