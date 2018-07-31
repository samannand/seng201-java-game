package Heroes_Villains.entities.items;

import Heroes_Villains.Game;
import Heroes_Villains.cities.Citys;
import Heroes_Villains.entities.heroes.Hero;

import java.awt.image.BufferedImage;

/**
 * Class describing the map Item.
 * Extends Item.
 */
public class Map extends Item {

    /**
     * Constructs the map item using the default item
     * variables and sets the cost of the map.
     *
     * @param id the id number of the map.
     * @param name the name of the map.
     * @param image the image of the map.
     * @param game the game object containing all of the objects and variables int the game.
     * @param cityClass object containing an array of City.
     * @param cost the cost of the item.
     * @param description the description of the item.
     */
    public Map(int id, String name, BufferedImage image, Game game, Citys cityClass, int cost, String description) {
        super(id, name, image, game, cityClass, description);
        this.cost = cost;
    }

    @Override
    public void use(Hero hero) {
        count--;
        cityClass.cities[game.getPlayer().getCurrentCity()].setHasMap(true); //Sets the city the player is currently in to have a map
    }

    @Override
    public boolean isUseable() {
        return true;
    } //The map is usable every where
}
