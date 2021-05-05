package game;

import edu.monash.fit2099.engine.*;

import static game.Capability.breed;

public class BreedBehaviour implements Behaviour {


    @Override
    public Action getAction(Actor actor, GameMap map) {

        // TODO check adjacent location for dinosaur

        // if adjacent location no dinosaur, find the nearest dinosaur from the whole map
        NumberRange xRange = map.getXRange();
        NumberRange yRange = map.getYRange();
        Location curLocation = map.locationOf(actor);
        int x1 = curLocation.x();
        int y1 = curLocation.y();
        Dinosaur dinosaur = (Dinosaur) actor;
        double shortestDistance = 999;
        Location nearestLct = null;

        for (Integer x : xRange) {
            for (Integer y : yRange) {
                Location lct = map.at(x, y);
                if (map.isAnActorAt(lct) && map.getActorAt(lct) instanceof Dinosaur) {
                    Dinosaur act = (Dinosaur) map.getActorAt(lct);
                    //TODO add and remove capability for breed in Dinosaur class
                    if (act.getClass() == dinosaur.getClass() && act.getGender() != dinosaur.getGender() && act.hasCapability(breed)) {
                        double distance = Math.sqrt(Math.pow((x - x1), 2) + Math.pow((y - y1), 2));
                        if (distance < shortestDistance) {
                            shortestDistance = distance;
                            nearestLct = lct;
                        }
                    }
                }
            }
        }
        if (nearestLct != null) {
            return findDirection(curLocation, nearestLct, map, actor, true);

        }
        return null;



        // If dinosaur can breed
        if (s.getFoodLevel() > 50) {
            // Check locations for breedable stegosaurs
            for (Location adj : adjacentLocations) {
                if (l.containsAnActor()) {
                    Actor a = adj.getActor();
                    if (a instanceof Stegosaur) {
                        if (!s.isPregnant() && !((Stegosaur) a).isPregnant()) {
                            if (s.breed((Dinosaur) a)) {
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
        return null;
    }

    public MoveActorAction findDirection(Location currentLct, Location nearestLct, GameMap map, Actor actor, boolean preferY) {

        int x2 = nearestLct.x();
        int y2 = nearestLct.y();
        int x1 = currentLct.x();
        int y1 = currentLct.y();
        int dx = x2 - x1;
        int dy = y2 - y1;

        if (dy >= dx && dy != 0 && preferY) {
            if (dy > 0) {
                //TODO move upwards
                MoveActorAction action = map.at(x1, y1 + 1).getMoveAction(actor, "north", "a");
                if (action == null) {
                    return findDirection(currentLct, nearestLct, map, actor, false);
                }
            } else {
                //move downwards
                MoveActorAction action = map.at(x1, y1 - 1).getMoveAction(actor, "north", "a");
                if (action == null) {
                    return findDirection(currentLct, nearestLct, map, actor, false);
                }

            }
        } else if (dy < dx && dx != 0) {
            //move in x axis
            if (dx > 0) {
                //TODO move right
                return map.at(x1 + 1, y1).getMoveAction(actor, "north", "a");

                } else {
                    //move left
                    return map.at(x1 - 1, y1).getMoveAction(actor, "north", "a");


                }
            }
        }
    }
}
