package Heroes_Villains.SystemUI;

import Heroes_Villains.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class for a radio button cluster
 */
public class RadioButtons extends UIElement{

    private int numButtons;
    private int spacing;
    private boolean horzizontal;
    protected RadioButton[] buttons;
    private BufferedImage[][] imagesArray;
    private int width, height;
    public int currentlyClicked;

    /**
     * Radio button constructor
     * @param x integer, x position on screen
     * @param y integer, y position on screen
     * @param game game object
     * @param images BufferedImage[] object, assets for the Radio buttons
     * @param numButtons integer, number of buttons in the cluster
     * @param spacing integer, spacing between the buttons
     * @param horizontal boolean, wether the cluster is rendered horizontally or vertically
     * @param width integer, width of buttons
     * @param height integer, height of buttons
     */
    public RadioButtons(int x, int y, Game game, BufferedImage[] images, int numButtons, int spacing, boolean horizontal, int width, int height) {
        super(x, y, game, images);
        this.numButtons = numButtons;
        this.spacing = spacing;
        this.horzizontal = horizontal;
        this.width = width;
        this.height = height;
        buttons = new RadioButton[numButtons];
        imagesArray = new BufferedImage[numButtons][3];
        int imageIndex = 0;
        this.currentlyClicked = 0;
        for(int i=0; i<numButtons; i++) {
            imagesArray[i][0] = images[imageIndex];
            imageIndex++;
            imagesArray[i][1] = images[imageIndex];
            imageIndex++;
            imagesArray[i][2] = images[imageIndex];
            imageIndex = imageIndex - 2;
        }
        int currX = x;
        int currY = y;
        for(int i=0; i<numButtons; i++) {
            if(horizontal) {
                buttons[i] = new RadioButton(currX, currY, game, imagesArray[i], width, height, i, this);
                currX += (width + spacing);
            }
            if(!horizontal) {
                buttons[i] = new RadioButton(currX, currY, game, imagesArray[i], width, height, i, this);
                currY += (height + spacing);
            }

        }
    }

    /**
     * Method for getting the array of buttons
     * @return RadioButton[] array
     */
    public RadioButton[] getButtons() {
        return buttons;
    }

    /**
     * Sets the button at index: buttonClicked to clicked
     * @param buttonClicked integer, button to be set to clicked
     */
    public void clicked(int buttonClicked) {
        for(int i=0; i<numButtons; i++) {
            buttons[i].setClicked(false);
        }
        buttons[buttonClicked].setClicked(true);
        currentlyClicked = buttonClicked;
    }

    /**
     * Method for looping through each button and calling its .update() method
     */
    public void update() {
        for(int i=0; i<numButtons; i++) {
            buttons[i].update();
        }
    }

    /**
     * Method for looping through each button and calling its .render() method
     */
    public void render(Graphics graphics) {
        for(int i=0; i<numButtons; i++) {
            buttons[i].render(graphics);
        }
    }


    @Override
    public boolean click() {
        return false;
    }


}
