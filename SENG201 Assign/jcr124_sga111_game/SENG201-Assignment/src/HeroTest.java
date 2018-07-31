import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

    private Hero hero;

    @Before
    public void setUp() throws Exception {
        hero = new Hero("Sam");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        assertEquals("Sam", hero.getName());
    }
}