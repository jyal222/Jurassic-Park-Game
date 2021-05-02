package edu.monash.fit2099.engine;

import game.Brachiosaur;
import game.Bush;
import game.Dirt;
import game.Stegosaur;

import java.util.Random;

/**
 * An Action that moves the Actor.
 */
public class MoveActorAction extends Action {

	protected Location moveToLocation;
	protected String direction;
	protected String hotKey;

	/**
	 * Constructor to create an Action that will move the Actor to a Location in a given Direction, using
	 * a given menu hotkey.
	 *
	 * Note that this constructor does not check whether the supplied Location is actually in the given direction
	 * from the Actor's current location.  This allows for (e.g.) teleporters, etc.
	 *
	 * @param moveToLocation the destination of the move
	 * @param direction the direction of the move (to be displayed in menu)
	 * @param hotKey the menu hotkey for this move
	 */
	public MoveActorAction(Location moveToLocation, String direction, String hotKey) {
		this.moveToLocation = moveToLocation;
		this.direction = direction;
		this.hotKey = hotKey;
	}

	/**
	 * Constructor to create an Action that will move the Actor to a Location in a given Direction.  A currently-unused
	 * menu hotkey will be assigned.
	 *
	 * Note that this constructor does not check whether the supplied Location is actually in the given direction
	 * from the Actor's current location.  This allows for (e.g.) teleporters, etc.
	 *
	 * @param moveToLocation Location to move to
	 * @param direction String describing the direction to move in, e.g. "north"
	 */
	public MoveActorAction(Location moveToLocation, String direction) {
		this.moveToLocation = moveToLocation;
		this.direction = direction;
		this.hotKey = null;
	}

	/**
	 * Allow the Actor to be moved.
	 *
	 * Overrides Action.execute()
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of the Action suitable for the menu
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		if (actor instanceof Stegosaur == false){
			map.moveActor(actor, moveToLocation);
		} else if (((Stegosaur) actor).isUnconscious() == false){
			map.moveActor(actor, moveToLocation);
		}
		// If Brachiosaur step on the Bush, 50% to kill bush
		else if ((actor instanceof Brachiosaur) && (moveToLocation.getGround() instanceof Bush)) {
			Random random = new Random();
			int number = random.nextInt(100) + 1;
			if (number <= 50) {
				moveToLocation.setGround(new Dirt());
			}
		}
		return menuDescription(actor);
	}



	/**
	 * Returns a description of this movement suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Player moves east"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " moves " + direction;
	}

	/**
	 * Returns this Action's hotkey.
	 *
	 * @return the hotkey
	 */
	@Override
	public String hotkey() {
		return hotKey;
	}
}