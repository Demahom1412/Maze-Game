package mazeGame.util.clue;

import java.util.*;

import mazeGame.util.maze.Cell;

/**
 * The class GoldQuest
 */
public class GoldQuest extends Quest {

    /** The number of gold to have */
    private int gold;

    /**
     * The constructor of the class GoldQuest
     * 
     * @param cell the destination of the quest
     * @param gold the number of gold to have for the quest
     */
    public GoldQuest(Cell cell, int gold) {
        super(cell);
        this.gold = gold;
    }

    /**
     * Get the number of gold
     * 
     * @return the number of gold possessed
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getConditions() {
        return Arrays.asList(this.getCurrentCell(), this.getGold());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showClue() {
        System.out.println(
                "Il se dit que vous devez avoir récolté  : " + this.getGold() + " or, pour accomplir votre quête");
        System.out.println("Il se dit autre chose...");
        System.out.println(
                "Il se dit aussi que que l'endroit que vous cherchez se trouve à la  " + this.getCurrentCell());
    }

}