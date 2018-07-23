package Heroes_Villains.SystemUI;

import Heroes_Villains.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class object for a graphical button
 */
public class UIButton extends UIElement {

    private int width, height;
    private boolean isHovering;
    public boolean clickable;

    /**
     * Constructor for UIButton
     * @param x integer, sets x location
     * @param y integer, sets y locations
     * @param game, game object
     * @param images, BufferedImage[] object containing buttons assets
     * @param width integer, sets button width
     * @param height integer, sets button height
     */
    public UIButton(int x, int y, Game game, BufferedImage[] images, int width, int height) {
        super(x, y, game, images);
        this.images = images;
        this.width = width;
        this.height = height;
        isHovering = false;
    }

    /**
     * method for returning if the mouse cursor is hovering over the area of the button
     * @return boolean, depedning on mouse cursor location, true or false
     */
    public boolean isHovering() {
        return isHovering;
    }

    @Override
    public void update() {
        isHovering = game.getMouseListener().isHovering(x, y, width, height);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(images[0], x, y, width, height, null);
        if(isHovering && images.length == 2) {
            graphics.drawImage(images[1], x, y, width, height, null);
        }
    }

    @Override
    public boolean click() { //Returns if object is clickable ie if the cursor is hovering over the element
        return isHovering;
    }
}

