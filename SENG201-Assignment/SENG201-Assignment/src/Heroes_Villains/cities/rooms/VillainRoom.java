package Heroes_Villains.cities.rooms;

import Heroes_Villains.Game;
import Heroes_Villains.entities.DoorWay;
import Heroes_Villains.entities.npc.Villain;
import Heroes_Villains.graphics.Assets;

import java.awt.*;

/**
 * Represents the villain room in the city object.
 */
public class VillainRoom extends Rooms {

    /**
     * Places a DoorWay object and a villain object
     * in the room
     */
    private DoorWay exit = new DoorWay(game,1230, 310, Assets.doorWay, true, 4); //Create objects
    private Villain villain = new Villain(50, 328, Assets.villain, 128, 128, game);

    /**
     * Constructor passes the game object to the super
     * class and set the room's name.
     *
     * @param game object which contains all objects and variables in the game.
     */
    public VillainRoom(Game game) {
        super(game);
        roomName = "Villain's Lair";
    }

    @Override
    public void update() {
        //Update objects
        exit.update();
        villain.update();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setFont(Assets.titleFont);
        graphics.drawString("Villain Room", 650, 400);
        //Render objects
        exit.render(graphics);
        villain.render(graphics);
    }
}
