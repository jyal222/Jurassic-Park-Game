package edu.monash.fit2099.engine;

import game.Dinosaur;
import game.Player;

public class FeedDinosaurAction extends Action{

    Food food;
    Dinosaur dinosaur;
    Location location;

    /**
     * Feed Dinosaur Food Action Constructor
     * @param food food to feed dinosaur
     * @param dinosaur dinosaur to be fed
     * @param location location of dinosaur to be fed
     */
    public FeedDinosaurAction(Food food, Dinosaur dinosaur, Location location) {
        this.food = food;
        this.dinosaur = dinosaur;
        this.location = location;
    }

    /**
     * Food item will be removed from inventory if player chooses to feed dinosaur
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @throws IllegalArgumentException if the actor parameter is not of type Player
     * @return string showing the chosen dinosaur to feed and its location
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Player){
            actor.removeItemFromInventory(this.getFood());
            int foodLevelPoints = this.getFood().pointsGained;
            this.getDinosaur().setFoodLevel(this.getDinosaur().getFoodLevel() + foodLevelPoints);
        } else {
            throw new IllegalArgumentException("Actor must be an instance of type player to perform this action");
        }
        return menuDescription(actor);
    }

    /**
     * Returns a string showing the food item to be fed to the dinosaur at the location
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        String message = "Feed " + food.getName() + " to " + this.getDinosaur().name + " at (" + this.location.x() + ", " + this.location.y() + ")";
        return message;
    }

    /**
     * To get the Food type attribute
     * @return a Food child instance
     */
    public Food getFood() {
        return food;
    }

    /**
     * To set the Food type attribute
     * @param food an instance of type food eg. Fruit, Egg, MealKit
     */
    public void setFood(Food food) {
        this.food = food;
    }

    /**
     * The dinosaur to be fed
     * @return A dinosaur instance based on the type of dinosaur
     */
    public Dinosaur getDinosaur() {
        return dinosaur;
    }

    /**
     * Sets the dinosaur instance to be fed.
     * @param dinosaur A dinosaur instance
     */
    public void setDinosaur(Dinosaur dinosaur) {
        this.dinosaur = dinosaur;
    }
}
