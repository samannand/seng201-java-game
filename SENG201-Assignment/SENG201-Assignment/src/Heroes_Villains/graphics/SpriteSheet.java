package Heroes_Villains.graphics;

import java.awt.image.BufferedImage;

/**
 * Allows the loading of sprite sheets and the cropping of images from them.
 */
public class SpriteSheet {

    private String fileName;
    private BufferedImage sheet;

    /**
     * Constructs a sprite sheet from a file.
     * @param fileName the path of the image file.
     */
    public SpriteSheet(String fileName) {
        this.fileName = fileName;
        this.sheet = ImageHandler.loadImage(fileName);
    }

    /**
     * Crops the image from the sprite sheet and returns a image.
     * @param x the x start position in pixels.
     * @param y the y start position in pixels.
     * @param width the width to cut in pixels.
     * @param height the height to cut in pixels.
     * @return
     */
    public BufferedImage getImage(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y,  width, height);
    }
}
