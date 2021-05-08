package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();
	int ecoPoints = 1000;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Ground currentGround = map.locationOf(this).getGround();
		actions.add(currentGround.allowableActions(this, map.locationOf(this), "same"));
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
	 * To set the number of EcoPoints of the player
	 *
	 * @param ecoPoints the amount of starting ecopoints of a player.
	 */
//    public void setEcoPoints(int ecoPoints) {
//        this.ecoPoints = ecoPoints;
//    }
	public boolean canSpend(int ecoPoints) {
		return ecoPoints <= this.ecoPoints;
	}

	public void earn(int ecoPoints) {
		this.ecoPoints += ecoPoints;
	}

	public void spend(int ecoPoints) {
		if (canSpend(ecoPoints)) {
			this.ecoPoints -= ecoPoints;
		}
	}
}

