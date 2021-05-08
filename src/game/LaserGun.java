package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A type of weapon that players can own and zap stegosaurs with
 */
public class LaserGun extends WeaponItem {

    private int price;

    /**
     * A constructor for LaserGun which is a WeaponItem
     * A LaserGun can be used to hit stegosaurs and kill them within one or two hits
     * It is a portable item
     */
    public LaserGun() {
        super("LaserGun", 'g', 100, "hits");
        this.price = 500;
    }

    @Override
    public int getPrice() {
        return price;
    }
}