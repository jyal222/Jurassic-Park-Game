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

	/**
	 * This method is to tick all the tree in the game map.
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {

		super.tick(location);

		Random random = new Random();

		if (random.nextInt(100) + 1 <= 50) {
			fruits.add(new Fruit(5));
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
	 * @param actor the Actor acting
	 * @param location the current Location
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
	 * @return a list of fruit in the tree.
	 */
	@Override
	public List<Food> getFruits() {
		return fruits;
	}

	/**
	 * To remove fruit from list.
	 * @param fruitToRemove fruit to remove
	 * @return boolean
	 */
	@Override
	public boolean removeFruit(Food fruitToRemove) {
		return fruits.remove(fruitToRemove);
	}

	@Override
	public String toString() {
		return "Tree";
	}
}
