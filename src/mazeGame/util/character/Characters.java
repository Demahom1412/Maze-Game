package mazeGame.util.character;

import java.util.Random;
import java.util.ArrayList;
import mazeGame.util.clue.Clue;
import mazeGame.util.maze.Cell;
/**
 * The Characters class that represent a character in the game.
 */
public class Characters {
	/** The current cell of the NPC in maze. */
	protected Cell currentCell;
	/** the Random object */
	protected Random rand = new Random();
	/** The number of times the player can talk to the NPC. */
	protected final int MAXTALKING;

	/**
	 * A variable that is used to keep track of how many times the player has talked
	 * to the NPC.
	 */
	protected int talked;

	/**
	 * Constructor of a new NPC.
	 *
	 * @param cell the cell of the NPC
	 * @param n the number of times the player can talk to the NPC
	 */
	public Characters(Cell cell, int n) {
		this.currentCell = cell;
		this.MAXTALKING = n;
	}

	/**
	 * This function returns the current cell of the NPC
	 * 
	 * @return The current cell.
	 */
	public Cell getCell() {
		return this.currentCell;
	}

	/**
	 * This function sets the current cell to the cell that is passed in
	 * 
	 * @param c The new cell of the NPC.
	 */
	public void setCell(Cell c) {
		this.currentCell = c;
	}

	/**
	 * This function is used to give the player a clue about the location of
	 * parameter c
	 * 
	 * @param c         the clue
	 * @param direction String : the direction of the clue
	 * @param manhattan  int : the distance between the player and the clue
	 * @return A boolean value.
	 */
	protected boolean clue(Clue c, String direction, int  manhattan) {

		int hint = rand.nextInt(3);

		if (hint == 0) {
			System.out.println("Il y a quelque chose à " + manhattan + "cases d'ici");
		} else if (hint == 1) {
			if (direction.equals("Autour de nous")) {
				System.out.println(direction + " ,se trouve quelque chose");
			} else {
				System.out.println("Si vous prenez la direction " + direction + " ,vous finirez par trouver quelque chose");
			}

		} else {
			c.showClue();
			if (c.isQuest()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * This function returns the number of times the player has talked to the NPC
	 * 
	 * @return The number of times the NPC has talked.
	 */
	public int getTalk() {
		return this.talked;
	}

	/**
	 * This function sets the value of the variable talked to the value of the
	 * parameter n
	 * 
	 * @param n The number of times the player has talked to the NPC.
	 */
	public void setTalk(int n) {
		this.talked = n;
	}

	/**
	 * The function returns a boolean value, and it takes two parameters, a Player
	 * object and an ArrayList of Clue objects.
	 * With theses parameters generate a Manhattan Distance between the player and
	 * the clue chosen, a direction to take to found the clue chosen
	 * And call the Clue method to give the player the right clue
	 * 
	 * @param p the player
	 * @param c ArrayList of Clue objects
	 * @return A boolean
	 */
	public boolean actions(Player p, ArrayList<Clue> c) {
		if (this.getTalk() == this.MAXTALKING) {
			System.out.println("Ah, vous revoilà, désolé je n'ai plus d'informations à vous donner");
			return false;
		} else if (this.getTalk() == 0 || this.MAXTALKING == -1) {
			Clue theClue;
			int hint = this.getRand().nextInt(2);
			theClue = (hint == 0) ? c.get(this.getRand().nextInt(c.size())) : c.get(c.size() - 1);
			int xb = theClue.getCurrentCell().getX();
			int yb = theClue.getCurrentCell().getY();
			int xa = this.getCell().getX();
			int ya = this.getCell().getY();
			String destX = "Autour de nous";
			String destY = "";
			if (xa > xb) {
				destX = "Ouest";
			} else if (xb > xa) {
				destX = "Est";
			}
			if (ya > yb) {
				destX = "Nord";
			} else if (yb > ya) {
				destX = "Sud";
			}
			String direction = destX + destY;

			int manhattan = Math.abs((xb - xa)) + Math.abs((yb - ya));
			if (this.getTalk() == 0) {
				p.updateCondition(1, this.getCell());
				p.getConditions();
			}
			this.setTalk(1);

			return this.clue(theClue, direction, manhattan);

		}

		return false;
	}

	
	/**
	 * This function returns the random number generator
	 * 
	 * @return The random number generator.
	 */
	public Random getRand() {
		return this.rand;
	}

	/**
	 * This function returns the type of the object
	 * 
	 * @return The type of the NPC.
	 */
	@Override
	public boolean equals (Object o) {
		if(o instanceof Characters) {
			Characters others = (Characters)o;
			return this.getCell().equals(others.getCell()) && this.getTalk() == others.getTalk() && this.MAXTALKING == others.MAXTALKING && this.toString().equals(others.toString());
		}else {
			return false;
		}
	}
	@Override
	public String toString() {
		if(this.getTalk()==0) {
			return "Une personne";
		}else {
			return "Une personne qui vous semble familière";
		}
		
	}
}
