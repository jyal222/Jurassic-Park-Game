package edu.monash.fit2099.demo.mars;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;


public class Floor extends Ground {

	public Floor() {
		super('.');
	}

	@Override
	public boolean canDrink() {
		return false;
	}

	@Override
	public boolean canDinosaurStand() {
		return false;
	}

	@Override
	public void setDinosaur(Actor actor) {

	}
}
