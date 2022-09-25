package mazeGame;
import mazeGame.util.maze.*;


import java.util.*;
import mazeGame.board.*;
import mazeGame.util.character.*;
import mazeGame.util.clue.*;
/**
 * The Game class that manage the game.
 */
public class Game {
	/**A variable current to track the cell of the player.*/
    private Cell current;

    /**A variable won to track if the player has won or not*/
    private boolean won;
    /**The board game.*/
    private Board theBoard;

   /**A variable that will contain the player.*/
    private Player player;
   /**Creating a new Scanner object called input.*/
    public static Scanner input = new Scanner(System.in);
    
    /**The class that manage the game
     * @param board : the board of the game
     *  
     */
    public Game(Board board) {
    this.theBoard = board;
    this.won = false;
    this.player = theBoard.getPlayer();
    }
    
    /**
     * the main method of the game
     * The player is placed in the first cell of the maze, then the maze is shown.
     * We will do this until the player has won:
     *  - the player can interact with things around him until he moves, 
     *  - then the mobile characters moves, then the maze is shown again, and so on.
     */
    public void play() {
        this.setCurrentCell(this.getTheBoard().getMaze().getCell(0, 0));
        //this.getPlayer().changeCell(this.getCurrentCell());
        this.getCurrentCell().setPlayer(player);
        this.getTheBoard().getMaze().show();
        while (!this.hasWon()) {

            this.setCurrentCell(this.getPlayer().getCurrentCell()); 

            this.showInfos();
            this.menu();

            this.getTheBoard().getMaze().show();


            // Check de la win
            if (this.getPlayer().know() && this.getTheBoard().getQuest().checkConditions(this.getPlayer().getConditions())) {
                this.setWon();
                System.out.println("Toutes les conditions ont été remplis ! ");
                System.out.println("Bravo vous avez gagné ! ");
            }
            this.moveCharacters();
            System.out.println("La case du joueur est maintenant : " + this.getPlayer().getCurrentCell());
        }
        

}
    
    
    /**
     * It prints a line of dashes
     */
    private void printLine() {
        System.out.println("-----------------------------------------------------------");
    }
    
    /**
     * It's a menu that allows the player to move, use, ask, pickup, show the map, show the infos, show
     * the help, or quit the game
     */
    private void menu() {

        String userInput = "";
        boolean moved = false;
        while (!moved) {
            System.out.println("Que voulez faire (taper 'aide' pour aider)");
            userInput = input.nextLine();
            switch (userInput) {
                case "aide":
                    this.help();
                    break;
                case "regarder":
                    this.showInfos();
                    break;
                case "utiliser":
                    this.use();
                    break;
                case "parler":
                    this.ask();
                    break;
                case "bouger":
                    moved = this.move();
                    break;
                case "ramasser":
                    this.pickup();
                    break;
                case "carte":
                    this.getTheBoard().getMaze().show();
                    break;
                case "quitter":
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }
        printLine();
    }
    
    
    /**
     * The function pickup() is used to pick up an item from the current cell
     */
    private void pickup() {
        
        Item item = this.getCurrentCell().getItem(); 
        if(item==null){
          System.out.println("Il n'as rien à ramasser");
          this.menu();
        } else {
          String name = item.getType();
          System.out.println("Que voulez vous ramasser : ");
          System.out.println("    " + 0 + " - Annuler");
          System.out.println("    " + 1 + " - " + item);
          int choice = input.nextInt();
          if (choice == 0) {
            this.menu();
          } else if (choice == 1) {
            this.getPlayer().addItems(name, item);
            this.getTheBoard().getClues().remove(item);
            System.out.println("Vous venez de récuperer l'objet suivant : " + item);
            this.current.removeitem();
          } else {
            this.menu();
          }
        }
        
    }
    
    /**
     * It takes a string input from the user, checks if it's a valid input, and if it is, it moves the
     * player to the next cell.
     * </code>
     * 
     * @return A boolean value.
     */
    private boolean move() {
    	//FIXME : si plusieurs erreurs intervienne, probleme d'update de cellule, provient surement de l'argument current qui s'update pas contrairement au joueur
        this.printLine();
        System.out.println("Annuler : revenir au menu");
        ArrayList<String> path = this.getPaths(this.getCurrentCell());
        path.add("Annuler");

        // Scanner input = new Scanner(System.in);
        String thechoice = input.nextLine();
        boolean res = false;
        Cell next;
        if (path.contains(thechoice)) {

            Cell curCell = this.getCurrentCell(); 
            switch (thechoice) {
                case "Annuler":
                    break;
                case "Nord":
                    next = this.getTheBoard().getMaze().getCell(curCell.getX(), curCell.getY() - 1);
                    this.getPlayer().updateCondition(0, next);
                    res = true;
                    break;
                case "Sud":
                    next = this.getTheBoard().getMaze().getCell(curCell.getX(), curCell.getY() + 1);
                    this.getPlayer().updateCondition(0, next);
                    res = true;
                    break;
                case "Ouest":
                    next = this.getTheBoard().getMaze().getCell(curCell.getX() - 1, curCell.getY());
                    this.getPlayer().updateCondition(0, next);
                    res = true;
                    break;
                case "Est":
                    next = this.getTheBoard().getMaze().getCell(curCell.getX() + 1, curCell.getY());
                    this.getPlayer().updateCondition(0, next);
                    res = true;
                    break;
                default:
                    break;
            }

        } else {
            this.move();
        }
        return res;

    }
    
    /**
     * It asks the player to choose a character to ask a question to
     */
    private void ask() {
        ArrayList<Characters> choices = this.getCurrentCell().displayNPCForAsk();
        int pick=0;
        try {
          pick = input.nextInt();
        } catch (Exception e) {
          System.out.println("Un chiffre est attendu");
        }
        if(pick ==0){
            this.menu();
        }else if(!choices.isEmpty()){
            choices.get(pick-1).actions(this.getPlayer(), this.getTheBoard().getClues());
        }else{
          System.out.println("Ceci n'est pas un choix valide");
          this.ask();
        }

    }
    
    /**
     * The function is supposed to take the user's input and use the item that corresponds to the input
     */
    private void use() {
        // Scanner input = new Scanner(System.in);
        System.out.println(0 + " - Annuler");
        ArrayList<String> choices = this.getPlayer().showItem(); 
        int userInput = input.nextInt();
        String finalChoice = "";
        if (userInput > 0) {
            finalChoice = choices.get(userInput - 1);
        }
        switch (finalChoice)

        {
            case "":
                this.menu();
                break;
            case "Parchment":
                Item p = this.getPlayer().getItem("Parchment").get(0); // CAST INUTILE JE CROIS A CHANGER
                int index = getTheBoard().getClues().size();
                Clue c = this.getTheBoard().getClues().get(index - 1);
                boolean know = p.clue(c);
                if (know) {
                    this.getPlayer().setKnow();
                }
                this.getPlayer().getItem("Parchment").remove(p);
                break;
            case "Jewel":
                System.out.println("Les joyaux ne peuvent être utilisé uniquement avec un marchand");
                break;
            default:
                break;
        }

    }
    
    
    /**
     * This function prints out a list of commands that the user can use to play the game
     */
    private void help() {
        System.out.println("aide : pour obtenir ce message d'aide");
        System.out.println("parler: pour parler à un personnage");
        System.out.println("regarder : pour regarder autour de soi");
        System.out.println("ramasser : pour ramasser un objet");
        System.out.println("utiliser: pour utiliser un objet de l'inventaire");
        System.out.println("bouger : pour se déplacer dans le labyrinthe");
        System.out.println("carte : pour voir le labyrinthe");
        System.out.println("quitter : pour quitter le jeu");
    }
    
    
    /**
     * It returns an ArrayList of String containing the possible directions to go from the current cell
     * 
     * @param current the current cell
     * @return The possible paths from the current cell.
     */
    private ArrayList<String> getPaths(Cell current) {
        ArrayList<String> possiblePath = new ArrayList<>();
        boolean top = current.north();
        boolean west = current.west();
        boolean east = current.east();
        boolean south = current.south();

        System.out.println("Autour c'est : ");
        if (!top) {
            System.out.println("    Nord : " + this.getTheBoard().getMaze().getCell(current.getX(), current.getY() - 1));
            possiblePath.add("Nord");
        }
        if (!west) {
            System.out.println("    Ouest : " + this.getTheBoard().getMaze().getCell(current.getX() - 1, current.getY()));
            possiblePath.add("Ouest");
        }
        if (!east) {
            System.out.println("    Est : " + this.getTheBoard().getMaze().getCell(current.getX() + 1, current.getY()));
            possiblePath.add("Est");
        }
        if (!south) {
            System.out.println("    Sud : " + this.getTheBoard().getMaze().getCell(current.getX(), current.getY() + 1));
            possiblePath.add("Sud");
        }
        return possiblePath;

    }
    
    
    private void showInfos() {
        System.out.println("Ici se trouve : ");
        System.out.println("    " + this.getPlayer());
        if (this.getCurrentCell().getItem() != null) { 
            System.out.println("    " + this.getCurrentCell().getItem()); 
        } 
        this.current.displayNPC();
        this.getPaths(this.getCurrentCell()); 
    }
    
    



 /**
  * Moving the mobile characters through the game board.
  * 
  * */
    private void moveCharacters() {
      for(MobileNPC mc :this.getTheBoard().getCharacters()){
          mc.move();
      }

  }
    
    
    /**
     * This function returns the value of the boolean variable won
     * 
     * @return The boolean value of the variable won.
     */
    public boolean hasWon() {
        return this.won;
    }
    
    /**
     * This function returns the value of the boolean variable won
     * 
     * @return The boolean value of the variable won.
     */
    public boolean getWon() {
        return this.won;
    }
    
    /**
     * This function sets the value of the boolean variable won to true
     */
    public void setWon() {
        this.won = true;
    }
    
    /**
     * This function returns the current cell of the player
     * 
     * @return The current cell.
     */
    public Cell getCurrentCell() {
        return this.current;
    }
    
    /**
     * This function sets the current cell to the cell passed in as a parameter
     * 
     * @param cell The cell to set as the current cell.
     */
    public void setCurrentCell(Cell cell) {
        this.current = cell;
    }
    
    /**
     * This function returns the board
     * 
     * @return The board object.
     */
    public Board getTheBoard() {
        return this.theBoard;
    }

    /**
     * This function returns the player object
     * 
     * @return The player object.
     */
    public Player getPlayer() {
        return this.player;
    }


}
