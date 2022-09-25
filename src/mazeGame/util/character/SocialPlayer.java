package mazeGame.util.character;

import java.util.*;

import mazeGame.util.maze.Cell;

/**
 * The class SocialPLayer
 */
public class SocialPlayer extends Player {

    /** Numbers of NPC */
    private int numberOfNPC = 0;

    /**
     * Constructor of the SocialPlayer class
     * 
     * @param cell        the cell of the SocialPlayer
     */
    public SocialPlayer(Cell cell) {
        super(cell);
    }

    /**
     * Add a number of NPC seen
     * 
     * @param npc the number of NPC seen
     */
    public void addNPC(int npc) {
        this.numberOfNPC += npc;
    }

    /**
     * Get the numbers of NPC
     * 
     * @return the numbers on NPC
     */
    public int getNPC() {
        return this.numberOfNPC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getConditions() {
        return Arrays.asList(this.getCurrentCell(), this.getNPC());
    }

    /**
     * The player is removed from the current cell and added
     * to the next cell
     * Update the number of NPC seen by the Player if the param n is 1
     * 
     * @param n    the number of the condition
     * @param next the next cell in the path
     */
    @Override
    public void updateCondition(int n, Cell next) {
        super.updateCondition(0, next);
        if (n == 1) {
            this.addNPC(1);
        }

    }

}