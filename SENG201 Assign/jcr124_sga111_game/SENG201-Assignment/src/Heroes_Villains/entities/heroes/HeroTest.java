package Heroes_Villains.entities.heroes;

import static org.junit.Assert.*;

public class HeroTest {

    private Hero testHero;

    @org.junit.Before
    public void setUp() throws Exception {
        testHero = new Hero(100, "Tank", "Homie");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void isAbilityUsed() {
        assertEquals(false, testHero.isAbilityUsed());
    }

    @org.junit.Test
    public void setAbilityUsed() {
        assertEquals(false, testHero.isAbilityUsed());
        testHero.setAbilityUsed(true);
        assertEquals(true, testHero.isAbilityUsed());
    }

    @org.junit.Test
    public void isDead() {
        assertEquals(false, testHero.isDead());
    }

    @org.junit.Test
    public void setDead() {
        assertEquals(false, testHero.isDead());
        testHero.setDead(true);
        assertEquals(true, testHero.isDead());
    }

    @org.junit.Test
    public void getHealth() {
        assertEquals(100, testHero.getHealth());
    }

    @org.junit.Test
    public void setHealth() {
        assertEquals(100, testHero.getHealth());
        testHero.setHealth(80);
        assertEquals(80, testHero.getHealth());

    }

    @org.junit.Test
    public void getType() {
        assertEquals("Tank", testHero.getType());
    }

    @org.junit.Test
    public void getName() {
        assertEquals("Homie", testHero.getName());
    }

    @org.junit.Test
    public void getMaxHealth() {
        assertEquals(100, testHero.getMaxHealth());
    }

    @org.junit.Test
    public void setMaxHealth() {
        assertEquals(100, testHero.getMaxHealth());
        testHero.setMaxHealth(200);
        assertEquals(200, testHero.getMaxHealth());
    }

    @org.junit.Test
    public void setUsingPotion() {
        assertEquals(false, testHero.getUsingPotion());
        testHero.setUsingPotion(true);
        assertEquals(true, testHero.getUsingPotion());
    }

    @org.junit.Test
    public void getUsingPotion() {
        assertEquals(false, testHero.getUsingPotion());
    }
}