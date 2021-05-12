package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Bush(), new VendingMachine());

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
				"............+++.......................................+++.......................",
				"................................................................................",
				".........................................................................++.....",
				"........................................................................++.++...",
				".........................................................................++++...",
				"..........................................................................++....",
				"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);

		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));

		// Place a pair of stegosaurs in the middle of the map

		gameMap.at(32, 12).addActor(new Stegosaur());
		gameMap.at(33, 12).addActor(new Stegosaur());

		gameMap.at(50, 12).addActor(new Brachiosaur());
		gameMap.at(52, 12).addActor(new Brachiosaur());
		gameMap.at(53, 12).addActor(new Brachiosaur());
		gameMap.at(54, 12).addActor(new Brachiosaur());
		gameMap.at(55, 12).addActor(new Brachiosaur());
		gameMap.at(56, 12).addActor(new Brachiosaur());
		gameMap.at(57, 12).addActor(new Brachiosaur());
		gameMap.at(58, 12).addActor(new Brachiosaur());


		gameMap.at(15, 9).setGround(new VendingMachine());

		// grow bush at the beginning of the game (1% chance)
		gameMap.tick();

		world.run();
	}
}