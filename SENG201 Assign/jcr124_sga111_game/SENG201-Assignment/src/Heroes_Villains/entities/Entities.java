package Heroes_Villains.entities;

import java.awt.*;

public abstract class Entities {

    protected float x, y;

    /**
     * Returns the x current position of the entity in pixels.
     * @return the current x position in pixels.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the current y position of the entity in pixels.
     * @return the current x position in pixels.
     */
    public float getY() {
        return y;
    }

    /**
     * The default constructor for entities.
     * @param x the x position in pixels.
     * @param y the y position in pixels.
     */
    public Entities(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the x position in pixels.
     * @param x the x position in pixels.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Sets the y position in pixels.
     * @param y the y position in pixels.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Updates the entity variables and all variables below it.
     */
    public abstract void update();

    /**
     * Draws the entity and all object below it.
     * @param graphics the object everything is drawn to.
     */
    public abstract void render(Graphics graphics);
}

