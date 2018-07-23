import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VillainTest {

    private Villain testVillain;

    @Before
    public void setUp() throws Exception {

        testVillain = new Villain("TestingTheHassle", 80);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkIfDead() {
        assertEquals(false, testVillain.checkIfDead());
    }

    @Test
    public void checkIfMaxHealth() {
        assertEquals(true, testVillain.checkIfMaxHealth());
    }

    @Test
    public void doDamage() {
        testVillain.doDamage(50);
        assertEquals(false, testVillain.checkIfMaxHealth());
    }

    @Test
    public void heal() {
        testVillain.heal(50);
        assertEquals(true, testVillain.checkIfMaxHealth());
    }
}