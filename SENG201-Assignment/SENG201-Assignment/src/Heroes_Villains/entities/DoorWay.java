package Heroes_Villains.entities;

import Heroes_Villains.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Represents the DoorWay
 */
public class DoorWay extends Entities{

    private BufferedImage[] images;
    protected Game game;
    private boolean isColliding;
    private boolean vertical;
    private int room;


    /**
     * Constructs the DoorWay entity.
     * @param game object containg all the objects and variables.
     * @param x the x position of the door in pixels.
     * @param y the y position of the door in pixels.
     * @param images the image of the door.
     * @param vertical if the door is vertical or horizontal.
     * @param room the index of the room the player gets sent to
     *             if they interact with the door.
     */
    public DoorWay(Game game, int x, int y, BufferedImage[] images, boolean vertical, int room) {
        super(x, y);
        this.images = images;
        this.game = game;
        this.isColliding = false;
        this.vertical = vertical;
        this.room = room;



    }

    /**
     * Returns if the player is currently colliding with the door.
     * @return if the player is currently colliding with the door.
     */
    public boolean getIsColliding() {
        return isColliding;
    }

    @Override
    public void update() {
        Rectangle doorRect = new Rectangle((int) x, (int) y, images[0].getWidth(), images[0].getHeight());
        Rectangle playerRect = new Rectangle((int) game.getPlayer().getX(), (int) game.getPlayer().getY(), game.getPlayer().getWidth(), game.getPlayer().getHeight());
        if (doorRect.intersects(playerRect)) { //Checks if the player and the door overlap
            isColliding = true;
            if (game.getKeyboardListener().f) {
                game.getKeyboardListener().f = false;
                game.getPlayer().setCurrentRoom(room);
                game.getPlayer().setX(640 - (game.getPlayer().getWidth())/2);
                game.getPlayer().setY(360 - (game.getPlayer().getHeight())/2);

            }
        } else {
            isColliding = false;
        }
    }

    @Override
    public void render(Graphics graphics) {
        if (vertical) {
            if (isColliding) {
                graphics.drawImage(images[1], (int) x, (int) y, null);
            } else {
                graphics.drawImage(images[0], (int) x, (int) y, null);
            }
        } else {
            if (isColliding) {
                graphics.drawImage(images[1], (int) x, (int) y, null);
            } else {
                graphics.drawImage(images[0], (int) x, (int) y, null);
            }
        }
    }
}
