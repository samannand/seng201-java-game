package Heroes_Villains.entities.items;

import Heroes_Villains.Game;
import Heroes_Villains.cities.Citys;
import Heroes_Villains.graphics.Assets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {

    private Map testMap;
    private Game game;
    private Citys citys;

    @Before
    public void setUp() throws Exception {
        testMap = new Map(6, "Map", Assets.map, game, citys, 30, "A Map");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isUseable() {
        assertEquals(true, testMap.isUseable());
    }
}