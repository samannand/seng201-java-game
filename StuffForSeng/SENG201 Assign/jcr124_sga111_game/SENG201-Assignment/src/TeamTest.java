import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {

    private Team testTeam;

    @Before
    public void setUp() throws Exception {
        testTeam = new Team(3, "TestTeam");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMaxSize() {
        assertEquals(3, testTeam.getMaxSize());
    }

    @Test
    public void getTeamName() {
        assertEquals("TestTeam", testTeam.getTeamName());
    }

    @Test
    public void setName() {
        assertEquals("TestTeam", testTeam.getTeamName());
        testTeam.setName("NewName");
        assertEquals("NewName", testTeam.getTeamName());
    }

    @Test
    public void setMaxSize() {
        assertEquals(3, testTeam.getMaxSize());
        testTeam.setMaxSize(5);
        assertEquals(5, testTeam.getMaxSize());
    }

    @Test
    public void addHero() {
        assertEquals(true, testTeam.addHero(new Hero("Name")));
    }
}