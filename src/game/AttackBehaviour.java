package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * This class is one of the behaviour of the dinosaur.
 */
public class AttackBehaviour extends DinosaurBehaviour {

    /**
     * This method is for the allosaur to attack conscious stegosaur.
     *
     * @param dinosaur the dinosaur acting
     * @param map      the GameMap containing the Dinosaur
     * @return null if condition does not fulfilled
     */
    @Override
    public Action getAction(Dinosaur dinosaur, GameMap map) {
        if (dinosaur instanceof Allosaur) {
            Allosaur allosaur = (Allosaur) dinosaur;
            // Allosaur attacks other types of dinosaur
            for (Exit exit : map.locationOf(allosaur).getExits()) {
                Location loc = exit.getDestination();
                if (loc.getActor() instanceof Stegosaur && loc.getActor().isConscious()) {
                    Dinosaur stegosaur = (Dinosaur) loc.getActor();
                    // cannot attack the same dinosaur for 20 turns
                    if (allosaur.getAttackedDinosaursList().contains(stegosaur)) {
                        stegosaur.setAttackTurns(stegosaur.getAttackTurns() + 1);
                        if (stegosaur.getAttackTurns() > 20) {
                            return allosaur.getAttackAction(stegosaur);
                        }
                    } else {
                        allosaur.getAttackedDinosaursList().add(stegosaur);
                        return allosaur.getAttackAction(stegosaur);
                    }
                }
            }
        }
        return null;
    }

}
