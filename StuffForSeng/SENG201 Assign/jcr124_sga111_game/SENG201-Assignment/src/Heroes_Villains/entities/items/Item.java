package Heroes_Villains.entities.items;

import Heroes_Villains.Game;
import Heroes_Villains.cities.Citys;
import Heroes_Villains.entities.heroes.Hero;

import java.awt.image.BufferedImage;

/**
 * Abstract class for the type item.
 */
public abstract class Item {

    public int id;
    public String name;
    public int count = 1;
    public BufferedImage image;
    protected Game game;
    protected Citys cityClass;
    public int cost;
    private String description;

    /**
     * Defult constructor for all subclasses.
     * Sets the default class variables.
     *
     * @param id the id number for the item.
     * @param name the name of the item.
     * @param image the image of the item.
     * @param game the game object containing all the objects and variables in the game.
     * @param cityClass the object containing the array of City objects.
     * @param description the written description of the item.
     */
    public Item(int id, String name, BufferedImage image, Game game, Citys cityClass, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.game = game;
        this.cityClass = cityClass;
        this.description = description;
    }

    /**
     * Uses the item on the given hero.
     *
     * @param hero the hero the item is used on.
     */
    public abstract void use(Hero hero);

    /**
     * Returns if the item is currently useable.
     *
     * @return if the item is currently useable.
     */
    public abstract boolean isUseable();

    /**
     * Returns if the item can currently be bought, ie if the
     * player currently has enought money.
     *
     * @return if the item is currently usable.
     */
    public boolean isBuyable() {
        if(game.getPlayer().money >= cost) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Buys the item, subtracts the cost and adds the item to the player inventory.
     */
    public void buy() {
        game.getPlayer().money -= cost;
        game.getPlayer().getInventory().addItem(this);
    }

    //Getter

    /**
     * Returns the id number for the item.
     *
     * @return item id number.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the item.
     * @return item name.
     */
    public String getName() {
        return name;
    }

    /**
     * returns the current count of the item.
     * @return current count of the item.
     */
    public int getCount() {
        return count;
    }

    //Setter

    /**
     * Sets the count of the item to a new value.
     * '
     * @param count the count of the item.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Returns the description of the item.
     * @return the description if a item.
     */
    public String getDescription() {
        return description;
    }

}
