package edu.monash.fit2099.engine;

/**
 * A type of weapon that players can own and zap stegosaurs with
 */
public class LaserGun extends WeaponItem {
    /**
     * A constructor for LaserGun which is a WeaponItem
     * A LaserGun can be used to hit stegosaurs and kill them within one or two hits
     * It is a portable item 
     */
    public LaserGun() {
        super("LaserGun", 'g', 100, "hits");
        this.setPrice(500);
    }
}