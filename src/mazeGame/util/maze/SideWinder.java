package mazeGame.util.maze;


/**
 * The Class SideWinder.
 */
public class SideWinder extends MazeGenerator {

	/**
	 * Constructor of Sidewinder.
	 *
	 * @param x the numbers of cell in x
	 * @param y the numbers of cell in y
	 */
	public SideWinder(int x, int y) {
		super(x, y);
	}

	/**
	 * Inits the maze following the SideWinder algorithm.
	 */
	@Override
	public void initMaze() {
		 for (int y = 0; y < this.getY(); y++) {
	            int pathrun=0;
	            for (int x = 0; x < this.getX(); x++) {
	                if (y >0 &&(x+1 == this.getX() || this.getRand().nextInt(2)==0)){
	                    int cell = pathrun + this.getRand().nextInt(x-pathrun+1);
	                    this.getCell(cell,y).destroyWall("N");
	                    this.getCell(cell,y-1).destroyWall("S");
	                    pathrun = x+1;
	                }else if(x+1<this.getX()){
	                    this.getCell(x,y).destroyWall("E");
	                    this.getCell(x+1,y).destroyWall("O");
	                }
	            }   
	        }

	}

}
