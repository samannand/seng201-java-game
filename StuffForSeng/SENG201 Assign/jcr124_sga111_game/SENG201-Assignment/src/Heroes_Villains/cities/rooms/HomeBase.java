package Heroes_Villains.cities.rooms;

import Heroes_Villains.Game;
import Heroes_Villains.cities.City;
import Heroes_Villains.entities.DoorWay;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.graphics.DrawText;

import java.awt.*;

/**
 * Represents the HomeBase room in each city.
 * Extends Rooms
 */
public class HomeBase extends Rooms{

    /**
     * Creating new DoorWay objects in the room
     * and places them.
     */
    private DoorWay leftDoor = new DoorWay(game,0, 310, Assets.doorWay, true, 0);
    private DoorWay rightDoor = new DoorWay(game, 1230, 310, Assets.doorWay, true, 2);
    private DoorWay topDoor = new DoorWay(game, 590, 0, Assets.doorWayH, false, 1);
    private DoorWay bottomDoor = new DoorWay(game, 590, 670, Assets.doorWayH, false, 3);

    /**
     * If hasMap is true the names of the rooms will drawn to the canvas.
     */
    private boolean hasMap;

    /**
     * Constructor for the HomeBase class the game and city variables are passed up to the
     * Rooms class.
     *
     * @param game the game object containing all the objects and the variables for the game.
     * @param city the object city that this room in in.
     */
    public HomeBase(Game game, City city) {
        super(game);
        roomName = "Home Base";
        hasMap = city.isHasMap(); //Checks if the city has a map
    }

    @Override
    public void update() {
        hasMap = game.gameState.masterCities.cities[game.getPlayer().getCurrentCity()].isHasMap(); //Continually checks if the city has a map

        //Update objects
        leftDoor.update();
        rightDoor.update();
        topDoor.update();
        bottomDoor.update();

    }

    @Override
    public void render(Graphics graphics) {
        if(hasMap) { //If the city has a map render the name of each room near by the door
            DrawText.draw(graphics, game.gameState.masterCities.cities[game.getPlayer().getCurrentCity()].rooms[0].roomName, 100, 300, true, Color.BLACK, Assets.smallFont);
            DrawText.draw(graphics, game.gameState.masterCities.cities[game.getPlayer().getCurrentCity()].rooms[1].roomName, 640, 100, true, Color.BLACK, Assets.smallFont);
            DrawText.draw(graphics, game.gameState.masterCities.cities[game.getPlayer().getCurrentCity()].rooms[2].roomName, 1180, 300, true, Color.BLACK, Assets.smallFont);
            DrawText.draw(graphics, game.gameState.masterCities.cities[game.getPlayer().getCurrentCity()].rooms[3].roomName, 640, 620, true, Color.BLACK, Assets.smallFont);
        }
        graphics.setFont(Assets.titleFont);
        graphics.drawString("Home Base", 650, 400); //Draw the Home base name to the canvas

        //Render the objects
        leftDoor.render(graphics);
        rightDoor.render(graphics);
        topDoor.render(graphics);
        bottomDoor.render(graphics);

    }
}