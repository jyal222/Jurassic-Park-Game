package game;


import edu.monash.fit2099.engine.*;

/**
 * A children class that inherited from Dinosaur class, which is a herbivore dinosaur.
 */
public class Stegosaur extends Dinosaur {

	public static final String STEGOSAUR = "stegosaur";
	public static final int BABY_FOOD_LEVEL = 10;

	/**
	 * Constructor.
	 * All Stegosaurs are represented by a 's' and have 100 hit points,hungry threshold of 90, breed threshold of 50 and dead threshold of 20.
	 *
	 * @param hitPoints starting hit points of Stegosaur
	 */
	public Stegosaur(int hitPoints) {
		super(STEGOSAUR, 's', hitPoints, 100);
		super.pregnantThreshold = 10;
		super.eggHatchThreshold = 15;
		super.babyThreshold = 30;
		super.unconsciousThreshold = 20;
		super.deadThreshold = 20;
		super.hungryThreshold = 90;
		super.breedThreshold = 50;
		super.corpseFoodLevel = 50;
		super.eggEcoPoints = 100;
	}

	/**
	 * A constructor to set initial food level of stegosaur
	 */
	public Stegosaur() {
		this(50);
	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to the current Actor.
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		if (otherActor instanceof Player) {
			actions.add(new AttackAction(this));
		}
		actions.add(super.getAllowableActions(otherActor, direction, map));
		return actions;
	}

	/**
	 * This method is to create a new eat action when the food in the location is eatable for stegosaur.
	 *
	 * @param location of the stegosaur
	 * @return a new EatAction(foodList) if condition is met, null if location not valid or food is not eatable.
	 */
	@Override
	public DinosaurAction getEatAction(Location location) {
		Ground ground = location.getGround();
		if (ground instanceof Bush) {
			Bush bush = (Bush) ground;
			for (Food food : bush.getFruits()) {
				if (canEat(food)) {
					return new EatAction(food, bush);
				}
			}
		}
		if (ground instanceof Tree) {
			for (Item item : location.getItems()) {
				if (item instanceof Eatable && canEat((Eatable) item)) {
					return new EatAction((Food) item);
				}
			}
		}
		return null;
	}

	/**
	 * This method is to check whether the food is eatable for stegosaur. In this case, only fruit and mealkit are eatable.
	 *
	 * @param food to be checked
	 * @return boolean depends on whether the food is eatable
	 */
	@Override
	public boolean canEat(Eatable food) {
		return (food instanceof Fruit || food instanceof VegetarianMealKit);
	}

}

