package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A dinosaur action to attack other dinosaur.
 */
public class DinosaurAttackAction extends DinosaurAction {

    private Dinosaur dinosaur;

    /**
     * Constructor
     * @param dinosaur to be attacked
     */
    public DinosaurAttackAction(Dinosaur dinosaur) {
        this.dinosaur = dinosaur;
    }

    /**
     *
     * @param dinosaur to be attacked
     * @param map game map
     * @return string showing which dinosaur attack which dinosaur
     */
    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        if (dinosaur instanceof Allosaur) { //TODO stegosaur is passed in as argument
            Allosaur allosaur = (Allosaur) dinosaur;
            if (allosaur.getStage() == Dinosaur.Stage.adult) {
                allosaur.setFoodLevel(allosaur.getFoodLevel() + 20);
            } else {
                allosaur.setFoodLevel(allosaur.getFoodLevel() + 10);
            }
            dinosaur.setFoodLevel(dinosaur.getFoodLevel() - 20);
            dinosaur.setAttackTurns(0);
            return menuDescription(dinosaur);
        }
        return null;
    }

    /**
     * This method is to return string showing which dinosaur attack which dinosaur
     * @param actor The actor performing the action.
     * @return string showing which dinosaur attack which dinosaur
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacked " + dinosaur;
    }

}
