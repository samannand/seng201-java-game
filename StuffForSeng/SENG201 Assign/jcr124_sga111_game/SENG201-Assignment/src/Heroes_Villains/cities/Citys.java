package Heroes_Villains.cities;

import Heroes_Villains.Game;
import Heroes_Villains.entities.Player;

import java.awt.*;

public class Citys {

    public int noOfCities;
    public Game game;
    public City[] cities;
    private Player player;

    /**
     * Constructor setting the chosen number of cities to the length
     * of the city object array.
     *
     * The init method is then called.
     *
     * @param game object containing all of the objects and variables in the game.
     * @param player object containing all of the objects and variables to do with player.
     */
    public Citys(Game game, Player player) {
        this.game = game;
        this.player = player;
        noOfCities = game.noOfCities;
        cities = new City[game.noOfCities]; //Create array to the size of the number of cities in the game.

        //create the array of City objects
        init();
    }

    /**
     * Cycles through the empty City array and adds a new city
     * at each index.
     */
    public void init() {
        for(int x=0; x < noOfCities; x++) { //Cycle index from 0 to the required number of cities
            City tempCity = new City(x, game, this, player); //Create new city
            cities[x] = tempCity; //Set the city (num) to index (num)
        }
    }

    /**
     * Calls the update method for the city at the index the
     * player is currently in.
     */
    public void update() {
        cities[game.getPlayer().getCurrentCity()].update();
    } //Update the city the player is currently in

    /**
     * Calls the render method for the city at the index the
     * player is currently in.
     * @param graphics
     */
    public void render(Graphics graphics) {
        cities[game.getPlayer().getCurrentCity()].render(graphics);
    } //Render the city the player is currently in
}
