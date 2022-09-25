package mazeGame;

import mazeGame.board.*;
/**
 * The MainGame Class that start a game with all parameters set up by the user.
 */
public class MainGame {
	/**
     * The main function takes 3 arguments, the first two are the dimensions of the board, the third is the
     * algorithm to use to generate the maze
     * @param args the argument
     */
	public static void main(String[] args) {
        ClassicBoard theboard;
        if (args.length < 3) {
            System.out.println("3 arguments sont demandés");
        } else {
            if (Integer.parseInt(args[2]) == 1) {
                theboard = new ClassicBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]), "DSB");
                Game game = new Game(theboard);
                game.play();
            } else if (Integer.parseInt(args[2]) == 2) {
                theboard = new ClassicBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]), "SideWinder");
                Game game = new Game(theboard);
                game.play();
            }else{
                System.out.println("Seulement deux algorithmes sont disponibles, 1 : DeepSearchBacktracking, 2: SideWinder");
            }
            
        }

    }
}
