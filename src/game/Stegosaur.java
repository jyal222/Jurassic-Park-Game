package game;


import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static game.Capability.breed;
import static game.Dinosaur.Stage;

/**
 * A herbivorous dinosaur.
 */
public class Stegosaur extends Dinosaur {

	public static final String NAME = Stegosaur.class.getSimpleName();

	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	private Behaviour behaviour;

	/**
	 * Constructor.
	 * All Stegosaurs are represented by a 's' and have 100 hit points.
	 *
	 * @param foodLevel
	 */
	public Stegosaur(int foodLevel) {
		super("stegosaur", 's', 100, 90, 50, 20);
		super.setGender(super.randomiseGender());
		super.setFoodLevel(foodLevel);
		super.setMaxFoodLevel(100);
		super.setUnconsciousTurns(20);
		super.setPregnantThreshold(10);
		super.setBabyThreshold(30);

		behaviourMap.put(Behaviour.Type.WanderBehaviour, new WanderBehaviour());
		behaviourMap.put(Behaviour.Type.EatBehaviour, new EatBehaviour());
		behaviourMap.put(Behaviour.Type.BreedBehaviour, new BreedBehaviour());
	}

	public Stegosaur() {
		this(50);
	}

	//TODO add feed dinosaur action
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new AttackAction(this));
	}

	/**
	 * Figure out what to do next.
	 * <p>
	 * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
	 * just stands there.  That's boring.
	 *
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
//    @Override
//    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//        Action action = null;
//
//        if (getFoodLevel() <= hungryThreshold) {
//            System.out.println(name + " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ") is getting hungry!");
//            action = behaviourMap.get(Behaviour.Type.EatBehaviour).getAction(this, map);
//        }
//
//        if (hasCapability(breed)) {
//            action = behaviourMap.get(Behaviour.Type.BreedBehaviour).getAction(this, map);
//        }
//
//        if (action == null) {
//            action = behaviourMap.get(Behaviour.Type.WanderBehaviour).getAction(this, map);
//        }
//
//        if (action != null)
//            return action;
//
//        return new DoNothingAction();
//    }

	@Override
	public EatAction getEatAction(Location location) {
		Ground ground = location.getGround();
		if (ground instanceof Bush || ground instanceof Tree) {
			for (Item item : location.getItems()) {
				if (item instanceof Eatable && canEat((Eatable) item)) {
					List<Eatable> foodList = new ArrayList<>();
					foodList.add((Food) item);
					location.removeItem(item);
					return new EatAction(foodList);
				}
			}
		}
		return null;
	}

	@Override
	public boolean canEat(Eatable food) {
		return (food instanceof Fruit || food instanceof MealKit);
	}

	@Override
	public int getFoodLevel() {
		return 50;
	}
}

