package mazeGame.util.maze;

import java.util.ArrayList;
import java.util.LinkedList;

import mazeGame.util.character.*;
import mazeGame.util.clue.*;

/**
 * The Class Cell.
 */
public class Cell {
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The walls. */
	private boolean[] theWalls;
	
	/** The boolean visited to track if the cell has been visited by the maze. */
	private boolean visited;

	/**A reference to the player object.*/
	private Player player;
	
	/**A reference to the character object.*/
	private Characters character;
	
	/**the item in the cell.*/
	private Item item;
	
	/**The list of all the movable NPC present in the cell*/
	private LinkedList<MobileNPC> movableNPC = new LinkedList<>();
	
	/** A boolean that is used to track if the cell has been visited by the path finder.*/
	private boolean visitedByPathFinder=false;
	
	/**A reference to the parent cell of the current cell,it will be used to get the final path.*/
	private Cell parent = null;
	
	/**
	 * Instantiates a new cell.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Cell(int x,int y) {
		this.x=x;
		this.y=y;
		this.theWalls = new boolean[]{true,true,true,true};
		this.visited=false;
		this.player = null;
		this.character = null;
	}
	/**
	 * Give the player present on the cell
	 * @return  the player present on the cell
	 */
	public Player getPlayer() {
		return this.player;
	}
	/**
	 * Set a player on the cell
	 * @param p the player to set up on the cell
	*/ 
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	/**
	 * This function returns a boolean value that indicates whether or not the current cell has been
	 * visited by the path finder
	 * 
	 * @return The boolean value of the visitedByPathFinder variable.
	 */
	public boolean isVisitedByPathFinder(){
		return this.visitedByPathFinder;
	}

	/**
	 * This function sets the boolean value of the visitedByPathFinder variable to the boolean value
	 * passed in as a parameter
	 * 
	 * @param bool true if the node has been visited by the path finder, false otherwise
	 */
	public void setVisitedByPathFinder(boolean bool){
		this.visitedByPathFinder=bool;
	}

	/**
	 * This function returns the parent of the current cell
	 * 
	 * @return The parent of the cell.
	 */
	public Cell getParent(){
		return this.parent;
	}
	/**
	 * It returns a LinkedList of MobileNPC objects
	 * 
	 * @return The LinkedList of MobileNPCs
	 */
	public LinkedList<MobileNPC> getMobileNPC(){
		return this.movableNPC;
	}

	/**
	 * This function sets the parent of the current cell to the cell passed in as a parameter
	 * 
	 * @param parent The parent cell of the current cell.
	 */
	public void setParent(Cell parent){
		this.parent = parent;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Checks if is visited.
	 *
	 * @return true, if is visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Sets the visited.
	 */
	public void setVisited() {
		this.visited = true;
	}
	
	
	/**
	 * This function returns the character of the current cell
	 * 
	 * @return The character .
	 */
	public Characters getCharacter() {
		return this.character;
	}
	/**
	 * This function removes the character from the cell
	 */
	public void removeCharacter(){
		this.character =null;
	}

	/**
	 * This function sets the character on the cell
	 * 
	 * @param character The character to set on the cell.
	 */
	public void setCharacter(Characters character) {
		this.character = character;
	}

	/**
	 * This function returns the item present on the cell
	 * 
	 * @return The item.
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * This function sets the item on the current cell to the item passed in as a parameter
	 * 
	 * @param item The item to put in the cell.
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * This function removes the item from the cell
	 */
	public void removeitem() {
		this.item = null;
	}
	
	/**
	 * This function takes a string as a parameter and sets the corresponding wall to false
	 * 
	 * @param w The wall to be destroyed.
	 */
	public void destroyWall(String w) {
		if(w.equals("N")) {
			this.theWalls[0] = false;
		}
		if(w.equals("O")) {
			this.theWalls[1] = false;
		}
		if(w.equals("S")) {
			this.theWalls[2] = false;
		}
		if(w.equals("E")){
			this.theWalls[3] = false;
		}
	}
	
	
	/**
	 * Returns true if there is a wall to the north of the current cell
	 * 
	 * @return The boolean value of theWalls[0]
	 */
	public boolean north() {
		return this.theWalls[0];
	}
	
	
	/**
	 * Returns true if there is a wall to the west of the current cell
	 * 
	 * @return The boolean value of theWalls[1]
	 */
	public boolean west() {
		return this.theWalls[1];
	}
	
	
	/**
	 * This function returns true if there is a wall to the south of the current cell
	 * 
	 * @return The boolean value of theWalls[2]
	 */
	public boolean south() {
		return this.theWalls[2];
	}
	

	/**
	 *Returns true if there is a wall to the east of the current cell
	 * 
	 * @return The boolean value of theWalls[3]
	 */
	public boolean east() {
		return this.theWalls[3];
	}
	
	
	
	/**
	 * It displays the characters in the cell and returns an arraylist of the characters in the cell
	 * 
	 * @return An ArrayList of Characters
	 */
	public ArrayList<Characters> displayNPCForAsk(){
		ArrayList<Characters> choices = new ArrayList<>();
		System.out.println("As qui voulez vous parler ?  : ");
		System.out.println("    " + 0 + " - Annuler");
		int count = 1;
		if(this.getCharacter()!=null){
			choices.add(this.getCharacter());
			System.out.println("    " + count + " - " + this.getCharacter());
			count++;
		}

		for(MobileNPC mc : this.getMobileNPC()){
			System.out.println("    " + count + " - " + mc);
			choices.add(mc);
			count++;
		}
		return choices;

	}

	/**
	 * This function displays the character and the mobile NPCs in the cell
	 */
	public void displayNPC(){
		if(this.getCharacter()!=null){
			System.out.println("    "+ this.getCharacter());
		}

		for(MobileNPC mc : this.getMobileNPC()){
			System.out.println("    " + mc);
		}
	}
	
	/**
	 * Equals.
	 *
	 * @param o the object to compare with
	 * @return true, the objects are equals
	 */
	@Override
	public boolean equals(Object o) {
		if(o instanceof Cell) {
			Cell other = (Cell) o;
			return this.getX() == other.getX() && this.getY() == other.getY();
		}else {
			return false;
		}
	}

	/**
	 * The function returns a string that contains the coordinates of the case
	 * 
	 * @return The x and y coordinates of the case.
	 */
	public String toString() {
		return "case (" + this.getX() + "," + this.getY() + ")";
	}

	

}
