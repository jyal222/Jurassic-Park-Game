package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the Jurassic World game.
 */
public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        application.start();
    }

    /**
     * To start the display menu
     */
    private void start() {
        Display display = new Display();

        do {
            // Show menu to choose Challenge/Sandbox/Quit
            String message = "\n----- NEW GAME -----\n\n" +
                    "Choose a game mode: " + System.lineSeparator() +
                    "1: Sandbox mode" + System.lineSeparator() +
                    "2: Challenge mode" + System.lineSeparator() +
                    "3: Quit game" + System.lineSeparator();

            List<Character> chars = Arrays.asList('1', '2', '3');
            char key = getUserInput(message, chars, display);

            if (key == '1') {
                getNewWorld(new World(display)).run();
            } else if (key == '2') {
                // must have more than 5 moves
                int moves = getIntegerInput("Enter number of moves (> 5): ", 5, display);
                int ecoPoints = getIntegerInput("Enter number of eco points " + "(> 1100)" + ": ", Player.INITIAL_ECO_POINTS + 100, display);

                getNewWorld(new ChallengeWorld(moves, ecoPoints, display)).run();
            } else if (key == '3') {
                display.println("Bye bye!");
                break;
            }

        } while (true);
    }

    private World getNewWorld(World world) {
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Bush(), new VendingMachine(), new Lake());

        List<String> map = Arrays.asList(
                "................................................................................",
                "................................................................................",
                ".....#######....................................................................",
                ".....#_____#....................................................................",
                ".....#_____#....................................................................",
                ".....###.###....................................................................",
                "................................................................................",
                "......................................+++.......................................",
                ".......................................++++.....................................",
                "...................................+++++........................................",
                ".....................................++++++.....................................",
                "......................................+++.......................................",
                ".....................................+++........................................",
                "................................................................................",
                "............+++.................................................................",
                ".............+++++..............................................................",
                "...............++........................................+++++..................",
                ".............+++....................................++++++++....................",
                "............+++.........++............................+++.......................",
                "........................++~~~...................................................",
                "..........................~~~............................................++.....",
                "........................................................................++.++...",
                ".........................................................................++++...",
                "..........................................................................++....",
                "................................................................................");

        List<String> secondMap = Arrays.asList(
                "................................................................................",
                "................................................................................",
                ".....#######....................................................................",
                ".....#_____#....................................................................",
                ".....#_____#....................................................................",
                ".....###.###....................................................................",
                "................................................................................",
                "......................................+++.......................................",
                ".......................................++++.....................................",
                "...................................+++++........................................",
                "............~~~......................++++++.....................................",
                "............~~~.......................+++.......................................",
                ".....................................+++........................................",
                "................................................................................",
                "............+++.................................................................",
                ".............+++++..............................................................",
                "...............++........................................+++++..................",
                ".............+++....................................++++++++....................",
                "............+++.......................................+++.......................",
                "................................................................................",
                ".........................................................................++.....",
                "........................................................................++.++...",
                ".........................................................................++++...",
                "..........................................................................++....",
                "................................................................................");

        GameMap gameMap = new GameMapSub(groundFactory, map);
        world.addGameMap(gameMap);

        // second game map added
        GameMap secondGameMap = new GameMapSub(groundFactory, secondMap);
        world.addGameMap(secondGameMap);

        Actor player = new Player("Player", '@', 100);
        world.addPlayer(player, gameMap.at(0, 0));

        // Place a pair of stegosaurs in the middle of the map

        gameMap.at(32, 12).addActor(new Stegosaur());
        gameMap.at(33, 12).addActor(new Stegosaur());

        gameMap.at(50, 12).addActor(new Brachiosaur("male"));
        gameMap.at(51, 12).addActor(new Brachiosaur("female"));
//        gameMap.at(53, 12).addActor(new Brachiosaur());
//        gameMap.at(54, 12).addActor(new Brachiosaur());
//        gameMap.at(55, 12).addActor(new Brachiosaur());
//        gameMap.at(56, 12).addActor(new Brachiosaur());
//        gameMap.at(57, 12).addActor(new Brachiosaur());
//        gameMap.at(58, 12).addActor(new Brachiosaur());

        gameMap.at(25, 18).addActor(new Pterodactyls());
        gameMap.at(24, 19).addActor(new Pterodactyls());


        gameMap.at(15, 9).setGround(new VendingMachine());

        // Add Portal at north border of first map
        for (int i = gameMap.getXRange().min(); i <= gameMap.getXRange().max(); i++) {
            Location location = gameMap.at(i, gameMap.getYRange().min());
            location.addItem(new Portal(secondGameMap, location, "north"));
        }
        // Add Portal at south border of second map
        for (int i = secondGameMap.getXRange().min(); i <= secondGameMap.getXRange().max(); i++) {
            Location location = secondGameMap.at(i, secondGameMap.getYRange().max());
            location.addItem(new Portal(gameMap, location, "south"));
        }

        // grow bush at the beginning of the game (1% chance)
        gameMap.tick();

        return world;
    }

    /**
     * To get user input from console (char)
     *
     * @param output
     * @param acceptableInput
     * @param display
     * @return char key
     */
    public char getUserInput(String output, List<Character> acceptableInput, Display display) {
        display.println(output);
        char key;
        do {
            key = display.readChar();
        } while (!acceptableInput.contains(key));
        return key;
    }

    /**
     * To get user input from console (int)
     *
     * @param output
     * @param min
     * @param display
     * @return int key
     */
    public int getIntegerInput(String output, int min, Display display) {
        display.println(output);
        Scanner s = new Scanner(System.in);
        String key;
        do {
            key = s.next();
        } while (!isValidInt(key, min, display));
        return Integer.parseInt(key);
    }

    /**
     * To check if its valid integer input
     *
     * @param input
     * @param min
     * @param display
     * @return boolean
     */
    private boolean isValidInt(String input, int min, Display display) {
        try {
            return Integer.parseInt(input) > min;
        } catch (NumberFormatException ex) {
            display.println("Invalid number!");
            return false;
        }
    }
}