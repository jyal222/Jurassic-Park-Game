package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

    public static final int INITIAL_ECO_POINTS = 1000;

    private Menu menu = new Menu();
    private int ecoPoints = INITIAL_ECO_POINTS;
    protected ActorLocations actorLocations = new ActorLocations();
    protected int turns = 0;

    /**
     * Constructor for Player class
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Player's every play turn
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        turns++;

        // Interact with my ground
        Ground currentGround = map.locationOf(this).getGround();
        actions.add(currentGround.allowableActions(this, map.locationOf(this), "same"));

        // Add quit game action
        actions.add(new EndGameAction());

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();
        return menu.showMenu(this, actions, display);
    }

    /**
     * To get the number of EcoPoints that the player currently having
     *
     * @return ecoPoints
     */
    public int getEcoPoints() {
        return ecoPoints;
    }

    /**
     * Check if the ecoPoints can be spent
     *
     * @param ecoPoints
     * @return boolean
     */
    public boolean canSpend(int ecoPoints) {
        return ecoPoints <= this.ecoPoints;
    }

    /**
     * Eco Points earn
     *
     * @param ecoPoints
     */
    public void earn(int ecoPoints) {
        this.ecoPoints += ecoPoints;
    }

    /**
     * Eco Points spend
     *
     * @param ecoPoints
     */
    public void spend(int ecoPoints) {
        if (canSpend(ecoPoints)) {
            this.ecoPoints -= ecoPoints;
        }
    }

    /**
     * To check if player is able to enter the water
     *
     * @return boolean
     */
    @Override
    public boolean canEnterWater() {
        return false;
    }
}

