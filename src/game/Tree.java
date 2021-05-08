package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree extends Ground implements Producible {

	private int age = 0;
	private List<Food> fruits = new ArrayList<>();

	/**
	 * Constructor.
	 */
	public Tree() {
		super('+');
	}

	public void incrementPlayersEcoPoints(GameMap map) {
		// Get all players on map
		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getYRange();
		List<Player> players = new ArrayList<>();
		for (Integer x : xRange) {
			for (Integer y : yRange) {
				Location loc = map.at(x, y);
				if (loc.containsAnActor() && loc.getActor() instanceof Player) {
					players.add((Player) loc.getActor());
				}
			}
		}
		// Iterate through every player and increment eco points
		for (Player p : players) {
			p.earn(1);
		}
	}

	/**
	 * This method is to tick all the tree in the game map.
	 *
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {

		super.tick(location);

		Random random = new Random();

		if (random.nextInt(100) + 1 <= 50) {
			fruits.add(new Fruit(5));
			incrementPlayersEcoPoints(location.map());
		}

		List<Food> fruitsToDrop = new ArrayList<>();
		for (Food fruit : fruits) {
			if (random.nextInt(100) + 1 <= 5) {
				fruit.setFoodLevel(10);
				location.addItem(fruit);
				fruitsToDrop.add(fruit);
			}
		}
		fruits.removeAll(fruitsToDrop);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';
	}

	/**
	 * Returns an Action list.
	 *
	 * @param actor     the Actor acting
	 * @param location  the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
		if (location.map().locationOf(actor) == location) {
			actions.add(new PickFruitAction(this));
		}
		return actions;
	}

	/**
	 * To get a list of fruits in a tree.
	 *
	 * @return a list of fruit in the tree.
	 */
	@Override
	public List<Food> getFruits() {
		return fruits;
	}

	/**
	 * To remove fruit from list.
	 *
	 * @param fruitToRemove fruit to remove
	 * @return boolean
	 */
	@Override
	public boolean removeFruit(Food fruitToRemove) {
		return fruits.remove(fruitToRemove);
	}

	@Override
	public boolean removeFruits(List<Food> fruitsToRemove) {
		return fruits.removeAll(fruitsToRemove);
	}

	@Override
	public String toString() {
		return "Tree";
	}
}
