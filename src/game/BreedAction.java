package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class BreedAction extends Action {

    public BreedAction() {
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (this.name == d2.name) {
            if (this.getFoodLevel() > 50 && d2.getFoodLevel() > 50 && (!this.getGender().equals(d2.getGender())) && (!this.getStage().equals("baby")) && (!this.getStage().equals("baby"))) {
                if (this.getGender().equals("female")) {
                    this.setPregnant(true);
                    System.out.println("A pair of " + name + " have just bred");
                } else if (d2.getGender().equals("female")) {
                    d2.setPregnant(true);
                    System.out.println("A pair of " + name + " have just bred");
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
