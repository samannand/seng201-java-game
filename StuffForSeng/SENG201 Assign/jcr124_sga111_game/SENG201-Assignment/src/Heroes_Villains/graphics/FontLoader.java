package Heroes_Villains.graphics;

import java.awt.*;
import java.io.IOException;

/**
 * Loads the fonts into the game to be used as assests.
 */
public class FontLoader {


    /**
     * Loads the font and returns a new Font object.
     * @param path the file path for the true type font file.
     * @param size the size of the font.
     * @return a new font object with the input parameters.
     */
    public static Font load(String path, int size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, FontLoader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
