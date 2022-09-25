package mazeGame.util.character;

import java.util.Random;
import java.util.Scanner;

import mazeGame.Game;
import mazeGame.util.clue.Clue;
import mazeGame.util.maze.Cell;
/**
 * The Sphynx class that represent a sphynx in the game.
 */
public class Sphynx extends Characters {
	/**  A private variable that is used to store the riddles.*/
    private String[][] riddles;

	
	/**
	 * The constructor of new Sphynx
	 * 
	 * @param cell the cell of the Sphynx
     * @param n  the number of times the player can talk to the NPC
	 */
    public Sphynx(Cell cell, int n) {
        super(cell, n);
        this.initializeRiddles();
    }
	
	/**
     * It creates a two-dimensional array of strings,a riddle and his answer  and assigns it to the riddles variable
     */
    private void initializeRiddles() {
		this.riddles = new String[][] {
			{"Quel être, pourvu d'une seule voix, a d'abord quatre jambes le matin, puis deux jambes le midi, et trois jambes le soir ? ","L'Homme"},
			{"Qu'est ce qui réfléchit sans réfléchir ?","Un miroir"},
			{"Je grossis et je ne prends, pourtant, pas de poids. Qui suis-je ?","Une loupe"},
			{"Qu'est-ce qui t'appartient mais que tout le monde utilise ?","Ton nom"},
			{"Quand tu as besoin de moi, tu me jettes. Quand tu n'as plus besoin de moi, tu me ramènes. Que suis-je ?","Une ancre"},
			{"Qu'est ce qui est plus grand que la Tour Eiffel, mais infiniment moins lourd ?","Son ombre"}
		};
        
	}
    
	 /**
      * 
      * Give the array of riddles
      * 
      * @return The riddles array.
      */
     public String[][] getRiddles(){
	        return this.riddles;
	    }
	
	
	/**
     * The Levenshtein distance is the minimum number of single-character edits (insertions, deletions or
     * substitutions) required to change one word into the other
     * 
     * @param str1 The first string to compare
     * @param str2 The string to compare against
     * @return The Levenshtein distance between two strings.
     */
    private int levenshtein(String str1,String str2){
        int[][] dl = new int[str1.length() + 1][str2.length() + 1];
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        for (int i = 0; i <= str1.length(); i++) {
           for (int j = 0; j <= str2.length(); j++) {
               if (i == 0) {
                    dl[i][j] = j;
                }
 
                else if (j == 0) {
                    dl[i][j] = i;
                }else{
                    int cost = str1.charAt(i - 1) == str2.charAt(j - 1) ? 0 :1;
                    int first = Math.min(dl[i-1][j] +1 , dl[i][j-1] +1);
                    dl[i][j] =Math.min(first, dl[i-1][j-1]+cost) ;
                }
           }
        }

        return dl[str1.length()][str2.length()];
    }
	
	/**
     * The function takes a clue, a direction and a Manhattan distance as parameters and returns a boolean, and gives the player a riddle, if he answer correctly he get a clue
     * 
     * 
     * @param c Clue object
     * @param direction the direction the player need to follow
     * @param manhattan the distance between the player and the object
     * @return The method is returning a boolean value.
     */
    @Override
    protected boolean clue(Clue c, String direction, int manhattan) {
        Random random = new Random();
        Scanner input = new Scanner(System.in);
        int index = random.nextInt(this.getRiddles()[0].length);
        String question = this.getRiddles()[index][0];
        String answer = this.getRiddles()[index][1];
        System.out.println(
                "Jeune héros, si vous arrivez à répondre correctement à ma question, je vous donnerais un indice qui vous aidera dans votre quête");
        System.out.println(question);
        String user_answer = input.nextLine(); //FIXME : Input n'as pas le temps d'apparaitre quand appelé de manière Static
        //input.close();
        int diff = levenshtein(answer, user_answer);
        if (diff > 4) {
            System.out.println("Désolé, ce n'est pas la bonne réponse");
            return false;
        } else {
            System.out.println("Bonne Réponse, voici l'indice : ");
            return super.clue(c, direction, manhattan);
        }
        

    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public String toString() {
    	if(this.getTalk()==0) {
    		return "Un sphynx";
    	}else {
    		return "Un sphynx à qui vous avez déjà parlé";
    	}
        
    }

}
