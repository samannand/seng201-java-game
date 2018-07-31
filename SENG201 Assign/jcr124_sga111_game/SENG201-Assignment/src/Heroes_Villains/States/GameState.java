package Heroes_Villains.States;

import Heroes_Villains.Game;
import Heroes_Villains.cities.Citys;
import Heroes_Villains.entities.Player;
import Heroes_Villains.entities.items.ItemHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Class for game state of the game, this is the state in which the player is moving between rooms and cities, it includes core gameplay functionality
 */
public class GameState extends State {

    public Player player;
    public Citys masterCities;
    public ItemHandler itemHandler;

    /**
     * Constructor method for game state, initializes a player, and an amount of cities and sets the player to be in the homebase of the first city
     * @param game game object, generic pass-through
     */
    public GameState(Game game) {
        super(game);
        player = new Player(576, 296, "Da Boi", game);
        masterCities = new Citys(game, player);
        player.setCurrentCity(0);
        player.setCurrentRoom(4);
        itemHandler = new ItemHandler(game, masterCities, player);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        masterCities.update();
        player.update();
        //itemHandler.coins.count = game.getPlayer().money;
        if(game.getKeyboardListener().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            game.getStateHandler().setState(game.getPauseState());
        }
    }

    @Override
    public void render(Graphics graphics) {
        masterCities.render(graphics);
        player.render(graphics);
    }

    public Citys getCities() {
        return masterCities;
    }
}
