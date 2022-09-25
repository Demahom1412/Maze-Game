package mazeGame.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import mazeGame.util.character.*;
import mazeGame.util.clue.*;
import mazeGame.util.maze.*;
/**
 * The abstract Class Board.
 */
public abstract class Board {
 /**A variable that is used to store the maze.*/
  private MazeGenerator maze;
  /**  A private variable that is used to store the quest.*/
  private Quest theQuest;
  /**  A private variable that is used to store the player.*/
  private Player player;
  /**A list of clues*/
  private ArrayList<Clue> theClues;

  /**Used to calculate the number of items and NPCs in the maze.*/
  private int ratio;
  /**A list of items that can be sold.*/
  private ArrayList<Resalable> marchandises;
  /**Creating a new random object to generate random numbers.*/
  private Random random = new Random();

  /**A list of NPCs that can move.*/
  private ArrayList<MobileNPC> mobileNPCs;

  /**
	 * Constructor of a new Board.
	 *
	 * @param x : the width of the maze
   * @param y : the height of the maze
   * @param theMaze : the algorithm to generate the maze
	 */
  protected Board(int x, int y, String theMaze) {
    this.initMaze(theMaze, x, y);
    this.ratio = (maze.getX() * maze.getY()) / 2;
    this.theClues = new ArrayList<>();
    this.mobileNPCs = new ArrayList<>();
    this.marchandises = new ArrayList<>();
    maze.initMaze();
    this.initboard();

  }
  /**
   * @return An ArrayList of Resalable objects.
   */
  public ArrayList<Resalable> getMarchandises(){
    return this.marchandises;
  }
  /**
   * This function sets the player variable to the player that is passed in
   * 
   * @param p The player to set the player to.
   */
  public void setPlayer(Player p){
    this.player = p;
  }

  /**
   * @return The number of items and NPCs in the maze.
   */
  public int getRatio(){
    return this.ratio;
  }

 /**
  *  This function sets the quest variable to the quest that is passed in
  * 
  * @param q The quest to set.
  */
  public void setQuest(Quest q){
    this.theQuest = q;
  }
  /**
   * This function returns the maze
   * 
   * @return The maze.
   */
  public MazeGenerator getMaze() {
    return this.maze;
  }

 /**
  * This function returns the random object
  * 
  * @return The random object.
  */
  public Random getRand() {
    return this.random;
  }

  /**
   * It takes a string as parameter, and generate a maze with the algorithm passed in parameter.
   * 
   * @param theMaze The algorithm used for the generation of the maze .
   * @param x The width of the maze
   * @param y The height of the maze
   */
  private void initMaze(String theMaze, int x, int y) {
    switch (theMaze) {
      case "SideWinder":
        this.maze = new SideWinder(x, y);
        break;
      case "DSB":
        this.maze = new DeepSearchBacktracking(x, y);
        break;
      default:
        break;
    }
  }

  /**
   * Init the board by passing the NPC,items and the quest.Then create a path for all mobile NPC.
   */
  private void initboard() {
    this.placeNPC();
    this.placeItem();
    this.setQuest();
    this.givePath();      
}
  /**
   * This function sets the quest in quest variable.
   */
  protected abstract void setQuest();

  /**
   * It places a random NPC in a random cell of the maze
   */
  protected void placeNPC() {

    int x;
    int y;
    int i = ratio/2;
    while (i > 0) {
      x = random.nextInt(maze.getX());
      y = random.nextInt(maze.getY());

      if (maze.getCell(x, y).getCharacter() == null || maze.getCell(x, y).getMobileNPC().isEmpty()) {// NEW FEATURE
        int rand = this.random.nextInt(4);
        Cell dest = maze.getCell(x, y);
        switch (rand) {
          case 0:
            Sphynx sphynx = new Sphynx(dest, 1);
            dest.setCharacter(sphynx);
            break;
          case 1:
            Trader trader = new Trader(dest, -1, 3);
            dest.getMobileNPC().add(trader);
            this.getCharacters().add(trader);
            break;
          case 2:
            Characters c = new Characters(dest, 1);
            dest.setCharacter(c);
            break;
          case 3:
            Fool fool = new Fool(dest, 1);
            dest.getMobileNPC().add(fool);
            this.getCharacters().add(fool);
            break;
          default:
            break;
        }
        i--;
      }

    }

  }

  /**
   * It takes a random cell in the maze and finds a path to it from the current cell of the NPC
   */
  protected void givePath() {
    for (MobileNPC mc : this.getCharacters()) {
      boolean found = false;
      while (!found) {
        int x = random.nextInt(maze.getX());
        int y = random.nextInt(maze.getY());

        if (!mc.getCell().equals(this.getMaze().getCell(x, y))) {
          mc.setPaths(this.pathmaker(mc.getCell(), this.getMaze().getCell(x, y)));
          found = true;
        }
      }

    }
  }

  /**
   * It returns an ArrayList of MobileNPCs
   * 
   * @return An ArrayList of MobileNPCs
   */
  public ArrayList<MobileNPC> getCharacters() { 
    return this.mobileNPCs;
  }

 /**
  * It places a random item in a random cell of the maze
  */
  protected void placeItem() {
    int x;
    int y;
    int i = ratio/2;
    int maxParchments = (ratio + 10 - 1) / 10;
    while (i > 0) {
      x = random.nextInt(maze.getX());
      y = random.nextInt(maze.getY());
      if (maze.getCell(x, y).getItem() == null) {
        int rand;
        if (maxParchments > 0) {
          rand = 0;
        } else {
          rand = 1;
        }

        Cell dest = maze.getCell(x, y);
        switch (rand) {
          case 0:
            Parchment parchment = new Parchment(dest);
            theClues.add(parchment);
            dest.setItem(parchment);
            maxParchments--;
            break;
          case 1:
            Jewel jewel = new Jewel(dest, random.nextInt((5-1)+1)+1, 1);
            theClues.add(jewel);
            dest.setItem(jewel);
            marchandises.add(jewel);
            break;
          default:
            break;
        }
        i--;
      }

    }
  }

  /**
   * It returns the quest.
   * 
   * @return The Quest object.
   */
  public Quest getQuest() {
    return theQuest;
  }

  /**
   * This function returns the player object
   * 
   * @return The player object.
   */
  public Player getPlayer() {
    return this.player;
  }

 /**
  * This function returns the ArrayList of Clues that is stored in the ClueList object
  * 
  * @return The ArrayList of Clues.
  */
  public ArrayList<Clue> getClues() {
    return this.theClues;
  }

  /**
   * If the x and y coordinates are within the bounds of the maze, return the cell at that location.
   * Otherwise, return null
   * 
   * @param x the x coordinate of the cell
   * @param y The y coordinate of the cell
   * @return The cell at the given x and y coordinates.
   */
  protected Cell index(int x, int y) {
    if (x < 0 || y < 0 || x > this.getMaze().getX() - 1 || y > this.getMaze().getY() - 1) {
      return null;

    } else {
      return this.getMaze().getCell(x, y);
    }
  }

  /**
   * It returns a list of all the neighbors of a cell that are not visited by the pathfinder and are
   * not blocked by a wall
   * 
   * @param current the current cell
   * @return A list of neighbors.
   */
  protected List<Cell> checkNeighbors(Cell current) {
    List<Cell> neighbors = new ArrayList<>();
    Cell top = this.index(current.getX(), current.getY() - 1);
    Cell left = this.index(current.getX() - 1, current.getY());
    Cell bottom = this.index(current.getX(), current.getY() + 1);
    Cell right = this.index(current.getX() + 1, current.getY());

    if (top != null && !top.isVisitedByPathFinder() && !current.north()) {
      neighbors.add(top);
    }
    if (left != null && !left.isVisitedByPathFinder() && !current.west()) {
      neighbors.add(left);
    }
    if (bottom != null && !bottom.isVisitedByPathFinder() && !current.south()) {
      neighbors.add(bottom);
    }
    if (right != null && !right.isVisitedByPathFinder() && !current.east()) {
      neighbors.add(right);
    }
    if (!neighbors.isEmpty()) {
      return neighbors;
    } else {
      return Collections.emptyList();
    }

  }

  /**
   * The Deep First Search Algorithm 
   * If the start cell is the end cell, return true. Otherwise, set the start cell to visited, check
   * the neighbors of the start cell, and for each neighbor, set the neighbor's parent to the start
   * cell, and call recursively the function on each neighbors.Return true if a path has beed found, otherwise return false.
   * 
   * @param start The starting cell
   * @param end The end cell
   * @return The method is returning a boolean value.
   */
  protected boolean recursiveDFS(Cell start, Cell end) {
    if (start.equals(end)) {
      return true;
    }
    start.setVisitedByPathFinder(true);
    List<Cell> neighbors = this.checkNeighbors(start);
    for (int i = 0; i < neighbors.size(); i++) {
      neighbors.get(i).setParent(start);

      if (this.recursiveDFS(neighbors.get(i), end)) {
        return true;

      }
    }
    return false;
  }

  /**
   * This function sets all the cells in the maze to not visited by the pathfinder
   */
  protected void resetVisited() { // NEW FEATURE
    for (int i = 0; i < this.maze.getX(); i++) {
      for (int j = 0; j < this.maze.getY(); j++) {
        this.maze.getCell(i, j).setVisitedByPathFinder(false);
      }
    }
  }

  /**
   * Make a list of cell that used for the path created by the DFS algorithm
   * @param start the starting cell
   * @param end the end cell
   * @return A list of cells that make up the path from start to end.
   */
  protected List<Cell> pathmaker(Cell start, Cell end) {
    this.resetVisited();
    this.recursiveDFS(start, end);

    List<Cell> path = new ArrayList<>();
    path.add(end);
    Cell current = end;
    boolean complete = false;
    while (!complete) {
      current = current.getParent();
      path.add(current);
      if (current.equals(start)) {
        complete = true;
      }
    }
    Collections.reverse(path);
    return path;
  }

}
