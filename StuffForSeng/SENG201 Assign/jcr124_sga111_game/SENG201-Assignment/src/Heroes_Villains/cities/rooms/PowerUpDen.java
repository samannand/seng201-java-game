package Heroes_Villains.cities.rooms;

import Heroes_Villains.Game;
import Heroes_Villains.entities.DoorWay;
import Heroes_Villains.graphics.Assets;

import java.awt.*;

/**
 * Represents the Power up Den Room in the city.
 * Extends Rooms.
 */
public class PowerUpDen extends Rooms{

    /**
     * Places DoorWay in the room
     */
    private DoorWay exit = new DoorWay(game,1230, 310, Assets.doorWay, true, 4); //Create door

    /**
     * Constructor applying game to the super class
     * and setting the room name to 'Power up den'.
     *
     * @param game object containing all of the objects and variables in the
     *             game.
     */
    public PowerUpDen(Game game) {
        super(game);
        roomName = "Power Up Den";
    }

    @Override
    public void update() {
        //Update objects
        exit.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setFont(Assets.titleFont);
        graphics.drawString("Power up den", 650, 400);
        //Update objects
        exit.render(graphics);
    }
}
