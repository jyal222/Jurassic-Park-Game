package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Dinosaur target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 *
	 * @param target the Actor to attack
	 */
	public AttackAction(Dinosaur target) {
		this.target = target;
	}

	/**
	 * This method is to attack dinosaur with weapon in inventory of player.
	 *
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return a line showing if the player has successfully attacked the dinosaur.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		System.out.println(target.isConscious());
		if (!target.isConscious()) {
			target.die(map);
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)
				drop.execute(target, map);

			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/**
	 * This method will return a string showing which actor successfully attack which dinosaur.
	 *
	 * @param actor The actor performing the action.
	 * @return a string line
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
