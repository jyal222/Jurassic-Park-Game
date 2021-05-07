package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class DinosaurAttackAction extends DinosaurAction {

    private Dinosaur dinosaur;

    public DinosaurAttackAction(Dinosaur dinosaur) {
        this.dinosaur = dinosaur;
    }

    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        if (dinosaur instanceof Allosaur) {
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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacked " + dinosaur;
    }

}
