package mazeGame.util.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mazeGame.Game;
import mazeGame.util.clue.Clue;
import mazeGame.util.clue.Item;
import mazeGame.util.maze.Cell;

/**
 * 
 * The class Trader
 *
 */
public class Trader extends MobileNPC {

	/** the price of a clue sold*/
	private int price;

	/**
	 * The constructor of the new Trader
	 * 
	 * @param cell the cell of the Trader
	 * @param n the number of times the player can talk to the NPC
	 * @param price  the price of a clue sold
	 */
	public Trader(Cell cell, int n, int price) {
		super(cell, n);
		this.price = price;
	}

	
	/**
	 * The function sell() is a boolean function that takes a player and an arraylist of clues as
	 * parameters. It displays a menu to the user and asks them to choose an option. If the user chooses
	 * option 0, the function calls the actions() function. If the user chooses option 1, the function
	 * checks if the player has enough gold to buy the clue. If the player has enough gold, the function
	 * removes the price of the clue from the player's gold and returns the result of the actions()
	 * function. If the player does not have enough gold, the function displays a message to the user
	 * 
	 * @param p Player
	 * @param c ArrayList of Clue objects
	 * @return A boolean value.
	 */
	public boolean sell(Player p, ArrayList<Clue> c) {
	//	Scanner input = new Scanner(System.in);
		System.out.println("    " + 0 + " - Annuler");
		System.out.printf("    " + 1 + " - Acheter un indice (%d or) %n", this.getPrice());
		int userInput = Game.input.nextInt();
		//input.close();
		if (userInput == 0) {
			this.actions(p, c);
		} else if (userInput == 1) {
			if (p.getGold() < this.getPrice()) {
				System.out.println("Vous ne disposez pas des fonds suffisants");
			} else {
				p.removeGold(this.getPrice());
				System.out.println("Ce fût un plaisir de faire affaire avec vous ! ");
				System.out.println("Voici  votre demande ! ");
				return super.actions(p, c);
			}

		}
		return false;

	}

	/**
	 * The function buy() is a  function that takes a player and an arraylist of clues as
	 * parameters. It displays the inventory of the player and asks them to choose an item to sell. If the user chooses
	 * a parchment, the function displays a message to the player . If the user chooses Jewel, the function
	 * displays all the jewels from the inventory of the player. After the choice of the player, the item is sold to the trader and removed from the inventory.
	 * If the inventory of the player is empty, a message is displayed to the player.
	 * 
	 * @param p Player
	 * @param c ArrayList of Clue
	 */
	public void buy(Player p, ArrayList<Clue> c) {
		//Scanner input = new Scanner(System.in);
		ArrayList<String> choices = p.showItem();
		if (choices.isEmpty()) {
			System.out.println("Vous n'avez rien à vendre");
		} else {
			String finalChoice = "";
			int userInput = Game.input.nextInt();
			if (userInput > 0) {
				finalChoice = choices.get(userInput - 1);
			}
			if (finalChoice.equals("Parchment")) {
				System.out.println("Les parchemins ne peuvent être vendus");
				this.actions(p, c);

			} else if (finalChoice.equals("Jewel")) {
				List<Item> theJewels = p.getItem("Jewel");
				int i = 1;
				for (Item j : theJewels) {

					System.out.println(i + " - " + j);
					i++;

				}

				System.out.println("Que veux tu vendre ? : ");
				userInput = Game.input.nextInt();
				int res = theJewels.get(userInput - 1).getValueItem();
				System.out.println("Ce fût un plaisir de faire affaire avec vous ! ");
				p.removeItem("Jewel", theJewels.get(i - 2));
				p.addGold(res);
			}
		}
		//input.close();

	}

	/**
	 * Get the price of the clue
	 * 
	 * @return the price of the clue
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * Set the price of the clue
	 * 
	 * @param price the price of the clue
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * The function  displays a menu, the player chooses an option, the
	 * function calls another function depending on the choice, and then calls itself again
	 * 
	 * @param p Player
	 * @param c ArrayList of Clue
	 * @return A boolean value.
	 */
	@Override
	public boolean actions(Player p, ArrayList<Clue> c) {
		Scanner input = new Scanner(System.in);
		System.out.println("Salutations jeune héros, voulez vous jeter un coup d'oeil a ma marchandise ?");
		System.out.println("    " + 0 + " - Annuler");
		System.out.println("    " + 1 + " - Acheter ");
		System.out.println("    " + 2 + " - Vendre");
		this.setTalk(1);
		p.updateCondition(1, this.getCell());
		int choice = input.nextInt();
		//input.close();
		switch (choice) {
			case 1:
				this.sell(p, c);
				this.actions(p, c);
				break;
			case 2:
				this.buy(p, c);
				this.actions(p, c);
				break;
			default:
				break;
		}

		return false;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if(this.getTalk()==0) {
			return "un marchand";
		}else {
			return "un marchand qui vous semble familier";
		}
		
	}
}
