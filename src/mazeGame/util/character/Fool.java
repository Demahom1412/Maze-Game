package mazeGame.util.character;

import java.util.ArrayList;
import java.util.List;

import mazeGame.util.clue.Clue;
import mazeGame.util.maze.Cell;
/**
 * The Fool Class that represent a fool.
 */
public class Fool extends MobileNPC{
  /**
   * Constructor of a new Fool.
   *
   * @param cell the cell of the Fool
   * @param n the number of times the player can talk to the Fool
   */

  public Fool(Cell cell, int n) {
    super(cell, n);
  }

  /**
   * The function takes a clue, a direction, and a Manhattan distance as parameters. It then removes the
   * direction from a list of directions, and picks a new direction from the list. It then picks a
   * random number between 0 and 1, and if the number is 0, it prints out a hint with the new Manhattan
   * distance, and if the number is 1, it prints out a hint with the new direction.
   * So the player will have a false hint to the clue.
   * 
   * @param c the clue that is being searched for
   * @param direction the direction of the clue
   * @param manhattan the distance between the player and the clue
   * @return A boolean value.
   */
  @Override
  protected boolean clue(Clue c, String direction, int manhattan) {
    List<String> directions = new ArrayList<>(
            List.of("Nord",
                    "Sud",
                    "Ouest",
                    "Est",
                    "Nord Est",
                    "Nord Ouest",
                    "Sud Ouest" ,
                    "Sud Est",
                    "Autour de nous"    ));
    directions.remove(direction);
    String newDirection = directions.get(this.getRand().nextInt(7));
    int newManhattan = manhattan-1;
    int hint = this.getRand().nextInt(2);

		if (hint == 0) {
			System.out.println("Il y a quelque chose à " + newManhattan + "cases d'ici");
		} else {
			if (direction.equals("Autour de nous")) {
				System.out.println(direction + " ,se trouve quelque chose");
			} else {
				System.out.println("Si vous prenez la direction " + newDirection + " ,vous finirez par trouver quelque chose");
			}
    } 
    return false;
  }

  
}