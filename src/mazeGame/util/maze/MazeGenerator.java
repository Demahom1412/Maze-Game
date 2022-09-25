package mazeGame.util.maze;

import java.util.Random;

/**
 * The Class MazeGenerator.
 */
public abstract class MazeGenerator {
	
	/** The cells. */
	protected Cell[][] theCells;
	
	/** The x. */
	protected int x;
	
	/** The y. */
	protected int y;
	
	/** The Constant for random. */
	protected  final Random rand = new Random();
	
	/**
	 * Instantiates a new maze generator.
	 *
	 * @param x the x
	 * @param y the y
	 */
	protected MazeGenerator(int x,int y) {
		this.x=x;
		this.y=y;
		this.theCells = new Cell[x][y];
		for(int i =0; i< x;i++) {
			for(int j =0;j<y;j++) {
				this.theCells[i][j] = new Cell(i,j);
			}
		}
	}
	
	/**
	 * Inits the maze.
	 */
	public abstract void initMaze();
	
	/**
	 * Gets the cell.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the cell
	 */
	public Cell getCell(int x, int y) {
		return this.theCells[x][y];
	}
	
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Print the maze
	 */
	public void show() {
		for (int i = 0; i < this.y; i++) {
			for (int j = 0; j < this.x; j++) {
				if (this.theCells[j][i].north() && this.theCells[j][i].getX() == 0) {
					System.out.print("+---+");
				} else if (this.theCells[j][i].north()) {
					System.out.print("---+");
				} else if (!this.theCells[j][i].north() && this.theCells[j][i].getX() == 0) {
					System.out.print("+   +");

				} else {
					System.out.print("   +");
				}
			}
			System.out.println();

			for (int j = 0; j < this.x; j++) {
				if (this.theCells[j][i].west() && this.theCells[j][i].getPlayer() != null) {
					System.out.printf("| ◉ ");
				} else if (this.theCells[j][i].getPlayer() != null && !this.theCells[j][i].west()) {
					System.out.printf("  ◉ ");
				} else if (this.theCells[j][i].west()) {
					System.out.print("|   ");
				} else if (!this.theCells[j][i].west()) {
					System.out.print("    ");
				}

			}
			System.out.println("|");

		}

		for (int x = 0; x < this.x; x++) {
			if (x == 0) {
				System.out.print("+---+");
			} else {
				System.out.print("---+");
			}

		}
		System.out.println();
	}
	
	/**
	 * This function returns the random number generator
	 * 
	 * @return The random number generator.
	 */
	public Random getRand() {
		return this.rand;
	}
}
