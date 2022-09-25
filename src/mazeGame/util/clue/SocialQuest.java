package mazeGame.util.clue;

import mazeGame.util.maze.Cell;
import java.util.Arrays;
import java.util.List;

/**
 * The class SocialQuest
 */
public class SocialQuest extends Quest {

    /** The number of NPC seen */
    public final int haveSeen;

    /**
     * The constructor of the class SocialQuest
     * 
     * @param cell     the destination of the quest
     * @param haveSeen the number of NOC seen
     */
    public SocialQuest(Cell cell, int haveSeen) {
        super(cell);
        this.haveSeen = haveSeen;
    }

    /**
     * Get the number of NPC seen
     * 
     * @return the number of NPC seen
     */
    public int getPeople() {
        return this.haveSeen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getConditions() {
        return Arrays.asList(this.getCurrentCell(), this.getPeople());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showClue() {
        System.out.println("Il se dit que vous devez avoir vu au moins : " + this.getPeople() + " personnes");
        System.out.println("Il se dit autre chose...");
        System.out.println(
                "Il se dit aussi que que l'endroit que vous cherchez se trouve à la  " + this.getCurrentCell());
    }

}