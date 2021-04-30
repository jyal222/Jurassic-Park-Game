package edu.monash.fit2099.engine;

import game.Player;
import game.Stegosaur;

public class LaserGunAction extends Action {

    Stegosaur stegosaur;
    GameMap stegosaurMap;

    /**
     * To execute the action of attacking a Stegosaur
     * The stegosaur will be killed in one or two turns, which one turn is 50
     * Stegosaur has only a maximum of 100 points (health)
     * @param actor actor performing the action (player)
     * @param map where the action is
     * @throws IllegalArgumentException if the actor parameter is not of type
     * @return a string which shows a message either if stegosaur is killed or not. If not killed, health points left will be shown.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message;
        if (actor instanceof Player) {
            this.getStegosaur().hurt(50);
            if (this.getStegosaur().hitPoints <= 0) {
                this.getStegosaur().setDead(true);
                this.getStegosaur().setDeadTurns(0);
                this.getStegosaur().setUnconscious(true);
                message = "Player has killed Stegosaur!";
            }
            else {
                message = "Player has attacked Stegosaur, left " + this.getStegosaur().hitPoints + " health remaining";
            }
        } else {
            throw new IllegalArgumentException("Actor must be an instance of type player to perform this action");
        }
        return message;
    }

    /**
     *
     * @param actor The actor who performs the action.
     * @return a string (eg. Hits Stegosaur at (8,6))
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Hits Stegosaur at (" + this.getStegosaurMap().locationOf(this.getStegosaur()).x() + ", " + this.getStegosaurMap().locationOf(this.getStegosaur()).y() + ")";
    }

    /**
     * To get the instance variable stegosaur
     * @return a stegosaur instance
     */
    public Stegosaur getStegosaur() {
        return stegosaur;
    }

    /**
     * To set the instance variable stegosaur
     * @param stegosaur a stegosaur instance
     */
    public void setStegosaur(Stegosaur stegosaur) {
        this.stegosaur = stegosaur;
    }

    /**
     * To get the instance variable stegosaurMap
     * @return a GameMap instance showing where the stegosaur is
     */
    public GameMap getStegosaurMap() {
        return stegosaurMap;
    }

    /**
     * To set the instance variable stegosaurMap
     * @param stegosaurMap a GameMap instance showing where the stegosaur is
     */
    public void setStegosaurMap(GameMap stegosaurMap) {
        this.stegosaurMap = stegosaurMap;
    }
}
