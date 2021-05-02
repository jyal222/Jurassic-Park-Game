package edu.monash.fit2099.engine;

import edu.monash.fit2099.interfaces.GroundInterface;
import game.Bush;
import game.Dirt;
import game.Fruit;
import game.Tree;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class representing terrain type
 */
public abstract class Ground implements GroundInterface, Capable, Printable {

	private Capabilities capabilities = new Capabilities();
	protected char displayChar;

	/**
	 * Constructor.
	 *
	 * @param displayChar character to display for this type of terrain
	 */
	public Ground(char displayChar) {
		this.displayChar = displayChar;
	}

	public char getDisplayChar() {
		return displayChar;
	}

	/**
	 * Returns an empty Action list.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a new, empty collection of Actions
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions();
	}

	/**
	 * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor the Actor to check
	 * @return true
	 */
	public boolean canActorEnter(Actor actor) {
		return true;
	}

	/**
	 * Ground can also experience the joy of time.
	 * @param location The location of the Ground 
	 */
	public void tick(Location location) {
		Bush bush = new Bush();
		int noAdjacentBush = 0;

		// Get adjacent locations
		ArrayList<Location> adjLocation = new ArrayList();

		// Append adjacent locations
		if (location.locationValid(location.x(), location.y()-1)){
			adjLocation.add(location.getNorth());
		}
		if (location.locationValid(location.x(), location.y()+1)){
			adjLocation.add(location.getSouth());
		}
		if (location.locationValid(location.x() + 1, location.y())){
			adjLocation.add(location.getEast());
		}
		if (location.locationValid(location.x() - 1, location.y())){
			adjLocation.add(location.getWest());
		}

		int noTree = 0;
		Random random = new Random();
		int number = random.nextInt(100) + 1;

		if (location.getGround() instanceof Dirt) {
			for (Location lct : adjLocation) {
				if (lct.getGround() instanceof Bush) {
					noAdjacentBush += 1;
				} else if (lct.getGround() instanceof Tree) {
				//	location.setGround(dirt);
					noTree += 1;
				}
			}

			// If no tree in adjacent location, 1% to grow bush
			if (noTree == 0){
				if (number <= 1){
					location.setGround(bush);
				}
			}

			// If at least 2 squares of bush in adjacent location, 10% to grow bush
			if (noAdjacentBush >= 2) {
				if (number <= 10){
					location.setGround(bush);
				}

			}

			/** A tree has 50% chance to produce ripe fruit and a 10% bush
			 *  A ripe fruit on the tree has 5% chance to fall
			 */
			if (location.getGround() instanceof Tree){
				if (number <= 50) {
					Fruit ripeFruit = new Fruit();
					if (number <= 5) {
						location.addItem(ripeFruit);
					}
				}
			}

			// Bush has a 10% chance to produce fruit
			// drop fruit from bush
			if (location.getGround() instanceof Bush){
				if (number <= 10) {
					Fruit ripeFruit = new Fruit();
					location.addItem(ripeFruit);
					}
				}
			}
	}
	
	/**
	 * Override this to implement terrain that blocks thrown objects but not movement, or vice versa
	 * @return true
	 */
	public boolean blocksThrownObjects() {
		return false;
	}

	/**
	 * Check whether this Ground type has the given Capability.
	 * 
	 * @param capability the Capability to check
	 * @return true if and only if this Ground has the given capability.
	 */
	public boolean hasCapability(Enum<?> capability) {
		return capabilities.hasCapability(capability);
	}

	/**
	 * Add the given Capability to this Ground.
	 * 
	 * @param capability the Capability to add
	 */
	public void addCapability(Enum<?> capability) {
		capabilities.addCapability(capability);
	}

	/**
	 * Remove the given Capability from this Ground.
	 * 
	 * @param capability the Capability to remove.
	 */
	public void removeCapability(Enum<?> capability) {
		capabilities.removeCapability(capability);
	}
}
