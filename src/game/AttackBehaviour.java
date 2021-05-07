package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class AttackBehaviour extends DinosaurBehaviour {

    @Override
    public Action getAction(Dinosaur dinosaur, GameMap map) {
        if (dinosaur instanceof Allosaur) {
            Allosaur allosaur = (Allosaur) dinosaur;
            // Allosaur attacks other types of dinosaur
            for (Exit exit : map.locationOf(allosaur).getExits()) {
                Location loc = exit.getDestination();
                if (loc.getActor() instanceof Stegosaur) {
                    Dinosaur stegosaur = (Dinosaur) loc.getActor();
                    if (!stegosaur.isUnconscious && !stegosaur.isDead) {
                        // cannot attack the same dinosaur for 20 turns
                        if (allosaur.getDinosaursAttackedList().contains(stegosaur)) {
                            stegosaur.setAttackTurns(stegosaur.getAttackTurns() + 1);
                            if (stegosaur.getAttackTurns() > 20) {
                                return allosaur.getAttackAction(stegosaur);
                            }
                        } else {
                            allosaur.getDinosaursAttackedList().add(stegosaur);
                            return allosaur.getAttackAction(stegosaur);
                        }
                    }
                    if (stegosaur.isDead) {
                        allosaur.getDinosaursAttackedList().remove(stegosaur);
                    }
                }
            }
        }
        return null;
    }

}
