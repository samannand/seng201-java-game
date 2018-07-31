package Heroes_Villains.SystemUI;

import Heroes_Villains.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *  Abstract parent class for elements based on user interaction
 */
public abstract class UIElement {

    public int x, y;
    protected Game game;
    protected BufferedImage[] images;

    /**
     * Constructor method for UIElement
     * @param x integer, x position on screen
     * @param y integer, y position on screen
     * @param game Game object, passed down through code hierarchy
     * @param images BufferedImage[] object with the assets for the element
     */
    public UIElement(int x, int y, Game game, BufferedImage[] images) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.images = images;
    }

    public abstract void update();

    public abstract void render(Graphics graphics);

    public abstract boolean click();
}
