package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();
	int ecoPoints = 1000;
	protected ActorLocations actorLocations = new ActorLocations();
	ArrayList<GameMap> gameMaps;
	GameMap playersMap;

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

	/**
	 * Returns a collection of the Actions that the otherActor can do to the current Actor.
	 *
	 * @param actor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return
	 */
	@Override
	public Actions getAllowableActions(Actor actor, String direction, GameMap map) {
		Actions actions = new Actions();
		GameMap firstMap = this.gameMaps.get(0);
		GameMap secondMap = this.gameMaps.get(1);
		playersMap = actorLocations.locationOf(actor).map();
		if (this.gameMaps.size() == 2) {
			if (actor instanceof Player) {

					if (actorLocations.locationOf(actor).map().equals(firstMap) && actorLocations.locationOf(actor).y() == 0) {
						actions.add(new CrossMapAction(map));
					} else if (actorLocations.locationOf(actor).map().equals(secondMap) && actorLocations.locationOf(actor).y() == 24) {
						actions.add(new CrossMapAction(map));
					}
				}
			}

		actions.add(super.getAllowableActions(actor, direction, map));
		return actions;
	}

	@Override
	public boolean canEnterWater() {
		return false;
	}
}

