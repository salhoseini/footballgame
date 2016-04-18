package entity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Salman on 4/18/2016.
 */
public class PlayerTest {

    @Test
    public void testConstructor() {
        Player player = new Player("Nedved");

        assertEquals("Nedved",player.getName());
    }
}
