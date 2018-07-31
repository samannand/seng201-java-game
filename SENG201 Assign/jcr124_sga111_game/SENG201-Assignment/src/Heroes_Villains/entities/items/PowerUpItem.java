package Heroes_Villains.entities.items;

import Heroes_Villains.Game;
import Heroes_Villains.cities.Citys;
import Heroes_Villains.cities.rooms.PowerUpDen;
import Heroes_Villains.entities.heroes.Hero;

import java.awt.image.BufferedImage;

/**
 * Class representing the power up item.
 * Extends Item.
 */
public class PowerUpItem extends Item {

    private String type;

    /**
     * Constructs the power up item using the default item constructor
     * and the cost.
     *
     * @param id the id number of the item.
     * @param name the name of the item.
     * @param image the image of the item.
     * @param game the object containing all the objects and variables in the game.
     * @param cityClass the object containing the array of City objects.
     * @param type the type for power up item.
     * @param cost the cost of the power up item.
     * @param description the despcription of the item.
     */
    public PowerUpItem(int id, String name, BufferedImage image, Game game, Citys cityClass, String type, int cost, String description) {
        super(id, name, image, game, cityClass, description);
        this.type = type;
        this.cost = cost;
    }

    @Override
    public void use(Hero hero) {
        count --;
        //Checks the type of the power up item
        if (type.equals("HEALTH")) {
            hero.setMaxHealth(hero.getMaxHealth() + 50);
        } else if (type.equals("ABILITY")) {
            hero.setAbilityUsed(false);
        } else if (type.equals("SPEED")) {
            game.getPlayer().setSpeed(game.getPlayer().getSpeed() + 10);
        }
    }

    /**
     * Returns if the item is currently usable.
     *
     * @return if the item is currently usable.
     */
    public boolean isUseable() {
        try {
            if (cityClass.cities[game.getPlayer().getCurrentCity()].rooms[game.getPlayer().getCurrentRoom()] instanceof PowerUpDen) { //Checks if the player is currently in the power up den to use the power up item
                return true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return false;
    }
}
