package game;

import edu.monash.fit2099.engine.*;

/**
 * A dinosaur action to attack other dinosaur.
 */
public class DinosaurAttackAction extends DinosaurAction {

    private Dinosaur target;

    /**
     * Constructor
     *
     * @param target to be attacked
     */
    public DinosaurAttackAction(Dinosaur target) {
        this.target = target;
    }

    /**
     * @param dinosaur attacking
     * @param map      game map
     * @return string showing which dinosaur attack which dinosaur
     */
    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        if (dinosaur instanceof Allosaur) {
            Allosaur allosaur = (Allosaur) dinosaur;
            if (allosaur.getStage() == Dinosaur.Stage.adult) {
                allosaur.heal(20);
            } else {
                allosaur.heal(10);
            }
            String result = dinosaur + " attacked " + target;
            target.hurt(20);
            target.setAttackTurns(0);
            if (!target.isConscious()) {
                target.die(map);
                allosaur.getAttackedDinosaursList().remove(target);

                Actions dropActions = new Actions();
                for (Item item : target.getInventory())
                    dropActions.add(item.getDropAction());
                for (Action drop : dropActions)
                    drop.execute(target, map);

                result += System.lineSeparator() + target + " is dead.";
            }

            return result;
        }
        return null;
    }

    /**
     * This method is to return string showing which dinosaur attack which dinosaur
     *
     * @param actor The actor performing the action.
     * @return string showing which dinosaur attack which dinosaur
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacked " + target;
    }

}
