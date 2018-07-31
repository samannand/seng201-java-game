package Heroes_Villains.graphics;

import java.awt.*;

/**
 * Draws a string to the graphics object at a
 * specified position, colour and font.
 */
public class DrawText {

    /**
     * Draws the string to the graphics object.
     * @param graphics the object that everything in the game is drawn to.
     * @param string the string to be drawn.
     * @param xPos the x position of the string.
     * @param yPos the y position of the string.
     * @param centre if the text is drawn from the corner or from around the
     *               centre of the string.
     * @param colour the colour for the font.
     * @param font the font to draw with.
     */
    public static void draw(Graphics graphics, String string, int xPos, int yPos, boolean centre, Color colour, Font font) {
        graphics.setColor(colour);
        graphics.setFont(font);
        int x = xPos;
        int y = yPos;
        if(centre) {
            FontMetrics metrics = graphics.getFontMetrics(font);
            x = xPos - metrics.stringWidth(string) / 2;
            y = (yPos - metrics.getHeight() / 2) + metrics.getAscent();
        }
        graphics.drawString(string, x, y);
    }
}
