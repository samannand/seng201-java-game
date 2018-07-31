package Heroes_Villains.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Loads images to be assigned in Assets.
 */
public class ImageHandler {

    /**
     * Returns a Buffered Image from a file.
     * @param path the path of the image file.
     * @return a buffered image from a file.
     */
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(ImageHandler.class.getResource(path));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;

    }
}
