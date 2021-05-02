package game;


import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	private Behaviour behaviour;

	/** 
	 * Constructor.
	 * All Stegosaurs are represented by a 's' and have 100 hit points.
	 * @param name
	 */
	public Stegosaur(String name) {
		super("stegosaur", 's', 100);
		behaviour = new WanderBehaviour();
		super.setGender(this.randomiseGender());
		super.setFoodLevel(50);
		super.setMaxFoodLevel(100);
		super.setUnconsciousTurns(20);
		super.setPregnantTurns(10);
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new AttackAction(this));
	}

	/**
	 * Figure out what to do next.
	 * 
	 * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
	 * just stands there.  That's boring.
	 * 
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Action wander = behaviour.getAction(this, map);
		if (wander != null)
			return wander;
		
		return new DoNothingAction();
	}

	/**
	 * To let the pregnant dinosaur lay egg if passed the pregnant turns
	 * @param l location of dinosaur
	 */
	private void layEgg(Location l) {
		if (this.getPregnantTurns() >= 10) {
			Egg egg = new Egg(name);
			l.addItem(egg);
		}
	}

	/**
	 * To set a baby dinosaur to adult after number of turns alive of a baby dinosaur is sufficient
	 */
	private void babyDinosaurGrows(){
		this.setNumTurnsAlive(this.getNumTurnsAlive() + 1);
		if(this.getNumTurnsAlive() >= 30){
			this.setStage("adult");
		}
	}

	/**
	 * This method ticks all the brachiosaur across the gamemap.
	 * @param l location of the dinosaur
	 * @param g game map
	 */
	@Override
	public void tick(Location l, GameMap g) {
		this.setFoodLevel(this.getFoodLevel() - 1);
		ArrayList<Location> adjacentLocations = l.validAdjacentLocations();

		// Baby dinosaurs grow up
		if(this.getStage().equals("baby")){
			babyDinosaurGrows();
		}

		if (this.getFoodLevel() > 0) {


			// If dinosaur getting hungry
			if (this.getFoodLevel() <=20) {
				System.out.println(name + " at (" + l.x() + ", " + l.y() + ") is getting hungry!");
			}

			// If dinosaur can breed
			if (this.getFoodLevel() > 50) {
				// Check locations for breedable stegosaurs
				for (Location adj : adjacentLocations) {
					if (l.containsAnActor()) {
						Actor a = adj.getActor();
						if (a instanceof Stegosaur) {
							if (breed((Dinosaur) a)) {
								break;
							}
						}
					}
				}
			}

			// Potentially lay egg
			if (this.isPregnant()) {
				layEgg(l);
			}

		}
		// For unconscious dinosaurs
		else {
			setUnconscious(true);
			setUnconsciousTurns(getUnconsciousTurns() + 1);
			if (getUnconsciousTurns() >= 20){
				setDead(true);
				setDeadTurns(getDeadTurns() + 1);

				// Carcass of dead dinosaur still remains for 20 turns
				if(getDeadTurns() >= 20){
					g.removeActor(this);
					g.at(l.x(), l.y()).setGround(new Dirt());
				}
			}
		}

	}
}
