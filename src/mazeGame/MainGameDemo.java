package mazeGame;

import mazeGame.board.ProjectBoard;
/**
 * The MainGame Class that start a game with a 10 by 10 maze and the algorithm choosen by the user.
 */
public class MainGameDemo {
	/**
     * The main function checks if the user has entered the right
     * number of arguments, and if so, it creates a new board with the right algorithm and starts the game
     * @param args the argument
     */
	public static void main(String[] args) {
        ProjectBoard theboard;
        if (args.length < 1) {
            System.out.println("1 argument est demandé");
        } else {
            if (Integer.parseInt(args[0]) == 1) {
                theboard = new ProjectBoard(10, 10, "DSB");
                Game game = new Game(theboard);
                game.play();
            } else if (Integer.parseInt(args[0]) == 2) {
                theboard = new ProjectBoard(10, 10, "SideWinder");
                Game game = new Game(theboard);
                game.play();
            }else{
                System.out.println("Seulement deux algorithmes sont disponibles, 1 : DeepSearchBacktracking, 2: SideWinder");
            }
            
        }

    }
}
