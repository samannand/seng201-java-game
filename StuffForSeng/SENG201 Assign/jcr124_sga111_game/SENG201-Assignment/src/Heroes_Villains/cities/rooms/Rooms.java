package Heroes_Villains.cities.rooms;

import Heroes_Villains.Game;

import java.awt.*;

/**
 * Absract class for the Room object to be modelled from.
 */
public abstract class Rooms {

    /**
     * Sets assessable variables for the game object and
     * the room object name.
     */
    protected Game game;
    public String roomName;

    /**
     * Constructor that requires the game object to be pass
     * for all sub classes.
     *
     * @param game object containing all objects and variables in the game.
     */
    public Rooms(Game game) {
        this.game = game;
    }

    /**
     * Abstract method to update all
     * objects in the given room.
     */
    public abstract void update();

    /**
     * Abstract method to render all
     * objects in a given room.
     *
     * @param graphics object which everything is drawn to.
     */
    public abstract void render(Graphics graphics);
}
