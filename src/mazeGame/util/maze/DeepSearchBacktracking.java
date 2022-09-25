package mazeGame.util.maze;
import java.util.*;


/**
 * The Class DeepSearchBacktracking.
 */
public class DeepSearchBacktracking extends MazeGenerator{
	
	/** The current cell of the algorithm. */
	private Cell current;
	
	
	/**
	 * Constructor of  deep search backtracking.
	 *
	 * @param x the numbers of cell in x 
	 * @param y the numbers of cell in y
	 */
	public DeepSearchBacktracking(int x,int y) {
		super(x,y);
		this.current = this.theCells[0][0];
		current.setVisited();
	}
	
	

	/**
	 * The method index will check if the index given are out of the band or not. This method is working with checkNeighbors() method  
	 *
	 * @param x the index at x
	 * @param y the index at  y
	 * @return Cell : the cell or null if out of band
	 */
	public Cell index(int x,int y) {
		if (x<0 || y<0 || x> this.getX()-1 ||y>this.getY()-1) {
			return null;
			
		}else {
			return this.theCells[x][y];
		}
	} 
	
	
	/**
	 * The method checkNeighbors will check around the current cell, the cells who hasn't been visited yet and one of them will be returned 
	 *
	 * @param current the current cell
	 * @return one of the cell who hasn't been visited or null if all the cells around the current cell have been visited
	 */
	public Cell checkNeighbors(Cell current) {
		List<Cell> neighbors =  new ArrayList<>();
		Cell top = this.index(current.getX(), current.getY()-1);
		Cell left =   this.index(current.getX()-1,current.getY());
		Cell bottom =  this.index(current.getX(),current.getY()+1);
		Cell right =    this.index(current.getX()+1,current.getY());
		
		if(top != null && !top.isVisited()) {
			neighbors.add(top);
		}
		if(left !=null && !left.isVisited()) {
			neighbors.add(left);
		}
		if(bottom != null &&!bottom.isVisited()) {
			neighbors.add(bottom);
		}
		if(right != null &&!right.isVisited()) {
			neighbors.add(right);
		}
		if(neighbors.size()>0) {
			int pick = this.getRand().nextInt(neighbors.size());
			return neighbors.get(pick);
		}else {
			return null;
		}
		
		
	}
	
	/**
	 * Removes the walls between the cell "a" and the cell "b".
	 *
	 * @param a the first cell
	 * @param b the second cell
	 */
	public void removeWalls(Cell a, Cell b) {
		int x = a.getX()- b.getX();
		int y = a.getY()-b.getY();
		if(x==-1) {
			a.destroyWall("E");
			b.destroyWall("O");
		}else if(x==1)  {
			a.destroyWall("O");
			b.destroyWall("E");
		}
		if(y==-1) {
			a.destroyWall("S");
			b.destroyWall("N");
		}else if(y==1) {
			a.destroyWall("N");
			b.destroyWall("S");
		}
	}
	
	/**
	 * This method will build the maze following the deep search Backtracking algorithm  .
	 */
	public void initMaze() {
		Stack<Cell> stack = new Stack<>();
		stack.push(current);
		while(!stack.empty()) {
		Cell next = this.checkNeighbors(this.current);
		if(next != null) {
			next.setVisited();
			stack.push(current);
			
			this.removeWalls(current,next);
			
			this.current = next;
		}else if (!stack.empty()) {
			this.current=stack.pop();
		}
		
	}
	}
	
}
