package Heroes_Villains.SystemUI;

import Heroes_Villains.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class for individual radio button object as part of a RadioButtons cluster
 */
public class RadioButton extends UIElement {

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int width, height;
    public boolean clicked;
    private int buttonNum;
    private RadioButtons buttons;

    /**
     * RadioButton constructor method
     * @param x integer, x position on screen
     * @param y integer, y position on screen
     * @param game game object
     * @param images BufferedImage[] object with assets for radio button
     * @param width integer, width of radio button
     * @param height integer, height of radio button
     * @param buttonNum integer, number of button
     * @param buttons RadioButtons object to pass back through itself
     */
    public RadioButton(int x, int y, Game game, BufferedImage[] images, int width, int height, int buttonNum, RadioButtons buttons) {
        super(x, y, game, images);
        this.buttonNum = buttonNum;
        this.width = width;
        this.height = height;
        this.buttons = buttons;
        clicked = false;
    }

    /**
     * Method for checking whether or not the button is being hovered over
     * @return boolean, true or false
     */
    public boolean isHovering() {
        return game.getMouseListener().isHovering(x, y, width, height);
    }

    @Override
    public void update() {
        this.isHovering();
        if(isHovering() && game.getMouseListener().isLeftClicked()) {
            buttons.clicked(buttonNum);
        }
    }

    @Override
    public void render(Graphics graphics) {
        if(isHovering() && !clicked) {
            graphics.drawImage(images[1], x, y, width, height, null);
        }
        else if(clicked) {
            graphics.drawImage(images[2], x, y, width, height, null);
        } else{
            graphics.drawImage(images[0], x, y, width, height, null);
        }
    }

    @Override
    public boolean click() {
        if(isHovering()) {
            return true;
        }
        return false;
    }

    /**
     * Method for setting the button to clicked
     * @param clicked boolean, true or false
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
