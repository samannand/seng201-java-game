package Heroes_Villains.entities;

/**
 * Represents all entities with health.
 * Extends Entities.
 */
public abstract class Living extends Entities {

    protected int health;

    /**
     * Default constructor for Living entities.
     * @param x the x position for living entities in pixels.
     * @param y the y position for living entities in pixels.
     */
    public Living(float x, float y) {
        super(x, y);
        health = 100;
    }

}
