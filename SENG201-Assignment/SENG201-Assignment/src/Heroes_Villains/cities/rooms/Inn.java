package Heroes_Villains.cities.rooms;

import Heroes_Villains.Game;
import Heroes_Villains.cities.Citys;
import Heroes_Villains.entities.DoorWay;
import Heroes_Villains.entities.Player;
import Heroes_Villains.entities.npc.InnKeeper;
import Heroes_Villains.graphics.Assets;

import java.awt.*;

/**
 * Represents the Inn room in the city.
 * Extends Rooms
 */
public class Inn extends Rooms{

    /**
     * Add new DoorWay to the room.
     */
    private DoorWay exit = new DoorWay(game,1230, 310, Assets.doorWay, true, 4);
    /**
     * Add new InnKeeper variable.
     */
    private InnKeeper innKeeper;

    /**
     * Constructor placing the InnKeeper in the room,
     * and setting the roomName to 'Inn'.
     *
     * @param game game object containing all objects and variables in the game.
     * @param cities object containg an array of all City objects in the game.
     * @param player object containg all the data about the player.
     */
    public Inn(Game game, Citys cities, Player player) {
        super(game);
        innKeeper = new InnKeeper(640, 120, "Innkeeper", Assets.innkeep, game, cities, player, 72, 100); //Create innkeeper
        roomName = "Inn";
    }

    @Override
    public void update() {
        //Update objects
        exit.update();
        innKeeper.update();

    }

    @Override
    public void render(Graphics graphics) {
        //Drawing Inn elements
        graphics.drawImage(Assets.innFloor, 0, 0, null);
        graphics.drawImage(Assets.inn, 0, 0, null);
        graphics.setColor(Color.WHITE);
        graphics.setFont(Assets.titleFont);
        graphics.drawString("Inn", 650, 400);
        //Render objects
        exit.render(graphics);
        innKeeper.render(graphics);
    }

    /**
     * return the Innkeeper object
     * @return InnKeep object containing the shop.
     */
    public InnKeeper getInnKeeper() {
        return innKeeper;
    } //Getters
}
