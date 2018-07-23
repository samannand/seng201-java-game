package Heroes_Villains.cities;

import Heroes_Villains.Game;
import Heroes_Villains.cities.rooms.*;
import Heroes_Villains.entities.Player;
import Heroes_Villains.graphics.Assets;
import Heroes_Villains.utils.RandomNum;

import java.awt.*;

/**
 * Represents the city object containing Rooms.
 */
public class City {

    private int cityNo;
    public Rooms[] rooms;
    private boolean hasMap;
    private Game game;
    private Rooms homeBase, powerUpDen, villainRoom;
    public Hospital hospital;
    public Inn inn;

    /**
     * Constructor takes the game, a list of the object containing
     * all the cities in the game and the player object.
     *
     * Constructor makes one new room for each type and the calls the init()
     * method for each room.
     *
     * @param cityNo Is the index of this city object in the cities array.
     * @param game object containing all objects and variables in the game.
     * @param cities object containing an array of all the cities in the game.
     * @param player object containing all the variables about the player
     */
    public City(int cityNo, Game game, Citys cities, Player player) {
        this.cityNo = cityNo;
        this.game = game;
        //Create Rooms
        rooms = new Rooms[5];
        homeBase = new HomeBase(game, this);
        hospital = new Hospital(game);
        inn = new Inn(game, cities, player);
        powerUpDen = new PowerUpDen(game);
        villainRoom = new VillainRoom(game);
        //Add the Home base to the middle room (last index)
        rooms[4] = homeBase;
        //Assign the rooms a random index
        init(hospital);
        init(inn);
        init(powerUpDen);
        init(villainRoom);
    }

    /**
     * Takes the input of a room then randomly assigns the room to an empty
     * index in the rooms array.
     *
     * @param room the room object to be randomly assigned an index.
     */
    public void init(Rooms room) {
        int index = RandomNum.getNum(4);
        while(rooms[index] != null) { //Continue until the room finds a free index
            index = RandomNum.getNum(4);
        }
        rooms[index] = room; //When free index found add room to index
    }

    /**
     * Checks if the team has a scout, if it does the city
     * set hasMap to true to emulate having used a map item.
     *
     * Method then updates the room that the player is currently in.
     */
    public void update() {
        for(int i=0; i<game.getTeam().size(); i++) {
            if(game.getTeam().get(i).getType() == "Scout") {
                hasMap = true;
            }
        }
        rooms[game.getPlayer().getCurrentRoom()].update(); //Update the room the player is currently in
    }

    /**
     * The city number is drawn to the canvas and the
     * render method is then called for the room the player is currently in.
     *
     * @param graphics the object that everything is drawn to.
     */
    public void render(Graphics graphics) {
        graphics.setFont(Assets.titleFont);
        graphics.drawString("City: " + Integer.toString(cityNo+1), 300, 400);
        rooms[game.getPlayer().getCurrentRoom()].render(graphics); //Render the room the player is currently in

    }

    /**
     * Getes the array of rooms.
     *
     * @return the array of room objects.
     */
    public Rooms[] getRooms() {
        return rooms;
    }

    /**
     * Gets the boolean value for if the city has
     * a map
     *
     * @return the boolean value for if the city has a map.
     */
    public boolean isHasMap() {
        return hasMap;
    }

    /**
     * Set has map, so when using the map from inventory
     * the city can be updated.
     *
     * @param hasMap boolean value for wether the city
     *               has a map object.
     */
    public void setHasMap(boolean hasMap) {
        this.hasMap = hasMap;
    }
}
