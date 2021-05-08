package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree extends Ground {
	private int age = 0;
	private List<Fruit> fruits = new ArrayList<>();


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

		List<Fruit> fruitsToDrop = new ArrayList<>();
		for (Fruit fruit : fruits) {
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
	 * To get a list of fruits in a tree.
	 * @return a list of fruit in the tree.
	 */
	public List<Fruit> getFruits() {
		return fruits;
	}

}
