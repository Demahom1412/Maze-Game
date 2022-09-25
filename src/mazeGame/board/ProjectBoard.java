package mazeGame.board;

import mazeGame.util.character.*;
import mazeGame.util.clue.*;
import mazeGame.util.maze.Cell;
/**
 * The ProjectBoard Class that generate a board following the project condition.
 */
public class ProjectBoard extends Board {

  /**
	 * Constructor of a new ProjectBoard.
	 *
	 * @param x : the width of the maze
   * @param y : the height of the maze
   * @param theMaze : the algorithm to generate the maze
	 */
  public ProjectBoard(int x, int y, String theMaze) {
    super(x, y, theMaze);
  }


  /**
   * The function set the quest with a GoldQuest at the coordinate 8,3 with a goal of 5 gold.
   */
  @Override
  protected void setQuest() {
    Cell theCell = getMaze().getCell(8, 3);
    int argent = 0;
    for (Resalable r : this.getMarchandises()) {
      argent += r.getValueItem();
    }
    while (this.getMarchandises().isEmpty() || argent<5) {
      int xR = this.getRand().nextInt(getMaze().getX());
      int yR = this.getRand().nextInt(getMaze().getY());
      if (this.getMaze().getCell(xR, yR).getItem() == null) {
        Jewel jewel = new Jewel(this.getMaze().getCell(xR, yR), 4, 5);
        this.getMaze().getCell(xR, yR).setItem(jewel);
        this.getMarchandises().add(jewel);
      }
    }

    
    this.setQuest(new GoldQuest(theCell, 5));
    this.setPlayer(new GoldPlayer(getMaze().getCell(0, 0)));
    int xT = this.getRand().nextInt(getMaze().getX());
    int yT = this.getRand().nextInt(getMaze().getY());
    Characters trader = new Trader(getMaze().getCell(xT, yT), -1, 4);
    this.getMaze().getCell(xT, yT).setCharacter(trader);
    this.getClues().add(getQuest());
  }
}
