package mazeGame.util.character;

import java.util.List;
import java.util.ListIterator;

import mazeGame.util.maze.Cell;
/**
 * The MobileNPC class that represent a mobile npc in the game.
 */
public class MobileNPC extends Characters {
	 /**A boolean variable that is used to determine if the NPC is moving forward or backward.*/
	  private boolean reverse = false;
	  /** A private variable that is used to iterate through the list of cells that the NPC will follow.*/
	  private ListIterator<Cell> iterator ;

	  /**
	   * Constructor of a new MobileNPC.
	   *
	   * @param cell the cell of the MobileNPC
	   * @param n the number of times the player can talk to the NPC
	   */
	  public MobileNPC(Cell cell, int n) {
	    super(cell, n);
	  }
	  
  /**
   * This function is used to set the path of the NPC
   * 
   * @param paths the list of cells that the NPC will follow
   */  
  public void setPaths(List<Cell> paths){
    this.iterator =paths.listIterator();
    this.iterator.next();
  }
  
  /**
   * This function returns the iterator of the list
   * 
   * @return The iterator is being returned.
   */
  public ListIterator<Cell> getIterator(){
    return this.iterator;
  }
  
  /**
   * This function sets the reverse variable to the boolean value passed in.
   * 
   * @param bool true or false
   */
  private void setReverse(boolean bool){
    this.reverse = bool;
  }
  
  /**
   * If the character is not in reverse mode, it will move forward, if it is in reverse mode, it will
   * move backward
   */
  public void move(){
    Cell newCell ;
    
    if(!reverse){
      if (this.getIterator().hasNext()){
        newCell =this.getIterator().next();
        
        this.updateCell(this.getCell(), newCell);
        
      }else{
        this.setReverse(true);
        this.getIterator().previous();
        newCell = this.getIterator().previous();
        this.updateCell(this.getCell(), newCell);
      }
    }else{
      if (this.getIterator().hasPrevious()){
        newCell =this.getIterator().previous();
        this.updateCell(this.getCell(), newCell);
      }else{
        this.setReverse(false);
        this.getIterator().next();
        newCell = this.getIterator().next();
        this.updateCell(this.getCell(), newCell);
      }
    }

  }

  /**
   * Change the cell of the NPC, and update the the old and new cell
   * @param old The cell that the NPC is currently in.
   * @param newCell The new cell that the NPC will be moving to.
   */
  private void updateCell(Cell old,Cell newCell){
      old.getMobileNPC().remove(this);
      newCell.getMobileNPC().add(this);
      this.setCell(newCell);
  }
}
