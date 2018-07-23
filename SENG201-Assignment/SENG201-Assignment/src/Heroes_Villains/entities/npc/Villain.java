package Heroes_Villains.entities.npc;

import Heroes_Villains.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Represents the villain NPC used to assess the battle state.
 * Extends NPC.
 */
public class Villain extends NPC {

    private BufferedImage image;
    private Rectangle bounds;
    private int width, height;
    private Game game;

    /**
     * The constructor of the Villain.
     * @param x the x position of the villain in pixels.
     * @param y the y position of the villain in pixels.
     * @param image the image of the villain.
     * @param width the width of the villain.
     * @param height the width of the villain.
     * @param game the object containing all the objects and variables in the game.
     */
    public Villain(float x, float y, BufferedImage image, int width, int height, Game game) {
        super(x, y, "Villain");
        this.image = image;
        this.width = width;
        this.height = height;
        this.game = game;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    @Override
    public void update() {
        if(game.getPlayer().bounds.intersects(bounds) && game.getKeyboardListener().keyJustPressed(KeyEvent.VK_F)) { //Checks if the player is overlapping with the villain and player is pressing f
            game.getStateHandler().setState(game.getBattleState()); //Change the state to the battle state
        }
    }

    @Override
    public void render(Graphics graphics) {
        //Render the villain
        graphics.drawImage(image, (int) x, (int) y, width, height, null);
    }

}
