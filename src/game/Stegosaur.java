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
		this.setPregnantTurns(this.getPregnantTurns() + 1);
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
	//@Override
	public static void dinosaurTick(Stegosaur s, Location l, GameMap g) {
		s.setFoodLevel(s.getFoodLevel() - 1);
		ArrayList<Location> adjacentLocations = l.validAdjacentLocations();

		// Baby dinosaurs grow up
		if(s.getStage().equals("baby")){
			s.babyDinosaurGrows();
		}

		if (s.getFoodLevel() > 0) {


			// If dinosaur getting hungry
			if (s.getFoodLevel() <= 90) {
				System.out.println(s.name + " at (" + l.x() + ", " + l.y() + ") is getting hungry!");
			}

			// eat fruits from bush and ground
			if (l.getGround() instanceof Bush || l.getGround() instanceof Ground) {
				for (Item i : l.getItems()){
					if (i instanceof Fruit) {
						Fruit f = (Fruit) i;
						s.setFoodLevel(s.getFoodLevel() + 10);
						System.out.println(s.name + " at (" + l.x() + ", " + l.y() + ") eats " + f.getName());
					}
				}
			}

			// If dinosaur can breed
			if (s.getFoodLevel() > 50) {
				// Check locations for breedable stegosaurs
				for (Location adj : adjacentLocations) {
					if (l.containsAnActor()) {
						Actor a = adj.getActor();
						if (a instanceof Stegosaur) {
							if (!s.isPregnant() && !((Stegosaur) a).isPregnant()) {
								if (s.breed(s, (Dinosaur) a)) {
									break;
								}
							}
							if (s.isPregnant()) {
								//s.setPregnantTurns(s.getPregnantTurns() + 1);
								s.layEgg(l);
							} else if (((Stegosaur) a).isPregnant()) {
								//((Stegosaur) a).setPregnantTurns(((Stegosaur) a).getPregnantTurns() + 1);
								((Stegosaur) a).layEgg(l);
							}
						}
					}
				}
			}

			/*
			// Potentially lay egg
			if (s.isPregnant()) {
				s.layEgg(l);
			}

			 */

		}
		// For unconscious dinosaurs
		else {
			s.setUnconscious(true);
			s.setUnconsciousTurns(s.getUnconsciousTurns() + 1);
			if (s.getUnconsciousTurns() >= 20){
				s.setDead(true);
				s.setDeadTurns(s.getDeadTurns() + 1);

				// Carcass of dead dinosaur still remains for 20 turns
				if(s.getDeadTurns() >= 20){
					g.removeActor(s);
					g.at(l.x(), l.y()).setGround(new Dirt());
				}
			}
		}

	}
}
