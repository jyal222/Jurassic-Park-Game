package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	/**
	 * Constructor for PortableItem
	 * @param name
	 * @param displayChar
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}

	/**
	 * To get price of the item
	 * @return int 0
	 */
	@Override
	public int getPrice() {
		return 0;
	}
}
