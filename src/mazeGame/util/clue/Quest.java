package mazeGame.util.clue;

import java.util.*;

import mazeGame.util.maze.Cell;

/**
 * The Class Quest.
 */
public class Quest extends Clue {

	/**
	 * Constructor of a new quest.
	 *
	 * @param cell the destination of the quest
	 */
	public Quest(Cell cell) {
		super(cell);
	}

	/**
	 * It checks if the conditions are met and return true or false if parameters
	 * are the same
	 * 
	 * @param n the list of parameters
	 * @return A boolean value.
	 */
	public boolean checkConditions(List<Object> n) {

		for (int i = 0; i < n.size(); i++) {
			if (i == 0 && !n.get(i).equals(this.getConditions().get(i))) {
				return false;
			} else if (i > 0) {
				int entierP = (int) n.get(i);
				int entierQ = (int) this.getConditions().get(i);
				if (entierP < entierQ) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Return a list of conditions of the quest
	 * 
	 * @return List of all the conditions of the Quest as a list of objects.
	 */
	public List<Object> getConditions() {
		return Arrays.asList(this.getCurrentCell());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isQuest() {
		return true;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void showClue() {
		System.out.println("A la " + this.currentCell + "se trouve quelque chose d'important ");
	}

}
