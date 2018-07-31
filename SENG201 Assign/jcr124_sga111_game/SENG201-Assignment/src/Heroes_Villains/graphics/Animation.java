package Heroes_Villains.graphics;

import java.awt.image.BufferedImage;

/**
 * Handles the frames show for animated objects.
 */
public class Animation {

    private BufferedImage[] images;
    private int index, speed;
    private long lastTime;

    /**
     * Constructs an animation object.
     * @param images image array of the frames for the animation.
     * @param speed the time in milli seconds for the frames to change.
     */
    public Animation(BufferedImage[] images, int speed) {
        this.images = images;
        this.speed = speed * 1000000;
        index = 0;
        lastTime = System.nanoTime();
    }

    /**
     * Checks if the output frame needs to change image
     * due to the time being reached.
     */
    public void update() {
        if ((System.nanoTime() - lastTime) >= speed) {
            index++;
            lastTime = System.nanoTime();
            if(index >= images.length) {
                index = 0;
            }
        }

    }

    /**
     * Returns the current image to be displayed for the animation.
     * @return current image frame.
     */
    public BufferedImage getCurrentImage() {
        return images[index];
    }
}
