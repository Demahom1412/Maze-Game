package mazeGame.board;

import mazeGame.util.character.*;
import mazeGame.util.clue.*;
import mazeGame.util.maze.Cell;
/**
 * The ClassicalBoard Class that generate randomly a board.
 */
public class ClassicBoard extends Board {

  /**
	 * Constructor of a new ClassicBoard.
	 *
	 * @param x : the width of the maze
   * @param y : the height of the maze
   * @param theMaze : the algorithm to generate the maze
	 */
  public ClassicBoard(int x, int y, String theMaze) {
    super(x,y,theMaze);
}
  /**
   * The function will choose between all the possible quests and will add item or NPC if needed by the quest
   * 
   */
  @Override
  protected void setQuest() {
    int nbQ = this.getRand().nextInt(3);
    int x = this.getRand().nextInt(getMaze().getX());
    int y = this.getRand().nextInt(getMaze().getY());
    Cell theCell = getMaze().getCell(x, y);
    if (nbQ == 0) {
        this.setQuest(new Quest(theCell));
        this.setPlayer(new Player(getMaze().getCell(0, 0))) ;
       this.getClues().add(getQuest());
    } else if (nbQ == 1) {
        this.setQuest(new SocialQuest(theCell, this.getRatio() / 2));
        this.setPlayer(new SocialPlayer(getMaze().getCell(0, 0)));

       this.getClues().add(getQuest());
    } else {
        if (this.getMarchandises().isEmpty()) {
            int xR = this.getRand().nextInt(getMaze().getX());
            int yR = this.getRand().nextInt(getMaze().getY());
            Jewel jewel = new Jewel(this.getMaze().getCell(xR, yR), 4, 5);
            this.getMaze().getCell(xR, yR).setItem(jewel);
            this.getMarchandises().add(jewel);
        }
        int argent = 0;
        for (Resalable r : this.getMarchandises()) {
            argent += r.getValueItem();
        }
        this.setQuest(new GoldQuest(theCell, argent / 2));
        this.setPlayer(new Player(getMaze().getCell(0, 0)));
        int xT = this.getRand().nextInt(getMaze().getX());
        int yT = this.getRand().nextInt(getMaze().getY());
        Characters trader = new Trader(getMaze().getCell(xT, yT), -1, 4);
        this.getMaze().getCell(xT, yT).setCharacter(trader);
       this.getClues().add(getQuest());
    }
		
	}


}
