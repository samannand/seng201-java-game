package Heroes_Villains.entities.heroes;

public class Hero {

    protected int health;
    protected boolean isDead;
    protected boolean abilityUsed;
    protected String type, name;
    protected int maxHealth;
    private boolean isUsingPotion;

    /**
     * Constructor for the Hero object sets the defult values, and assigns the
     * class variables.
     *
     * @param health Integer for the health remaining.
     * @param type String for the type of hero chosen.
     * @param name String for the name of the hero.
     */
    public Hero(int health, String type, String name) {
        this.health = health;
        this.maxHealth = health;
        isDead = false;
        abilityUsed = false;
        this.type = type;
        this.name = name;
        isUsingPotion = false;
    }

    /**
     * Returns if the heros special ability has been used.
     *
     * @return boolean if the special ability has been used.
     */
    public boolean isAbilityUsed() {
        return abilityUsed;
    }

    /**
     * Sets the value of the boolean abilty used.
     * this is used to reset the heroes abitly after
     * used with a power up.
     *
     * @param abilityUsed boolean if the special ability has been used.
     */
    public void setAbilityUsed(boolean abilityUsed) {
        this.abilityUsed = abilityUsed;
    }

    /**
     * Returns if the hero is currently dead so
     * the hero can be removed from the team.
     *
     * @return boolean if the hero is dead.
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Sets if the hero is dead.
     *
     * @param dead boolean if the hero is dead.
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }

    /**
     * Get the integer of the hero's health.
     *
     * @return the hero's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the heros health
     *
     * @param health variable for the heros health.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Returns a string of the type of hero.
     *
     * @return the string for the type of hero.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the name of the hero.
     *
     * @return name of the hero
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the maximum possible health of the hero.
     * Used for healing up the player to apply
     * a cap to the health.
     *
     * @return the maximum possible health.
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Sets the maximum health. So the health cap can be changed by
     * power ups.
     *
     * @param max the max health.
     */
    public void setMaxHealth(int max) {
        maxHealth = max;
    }

    /**
     * Sets whether of not the hero is currently being
     * healed by a potion.
     *
     * @param using variable if the hero is currently using a potion.
     */
    public void setUsingPotion(boolean using) {
        isUsingPotion = using;
    }

    /**
     * Gets if the hero is currently using a potion.
     *
     * @return variable if the hero is currently using a potion.
     */
    public boolean getUsingPotion() {
        return isUsingPotion;
    }
}
