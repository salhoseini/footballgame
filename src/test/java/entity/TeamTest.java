package entity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Salman on 4/18/2016.
 */
public class TeamTest {

    @Test
    public void testConstructor() {
        Team team = new Team("Juventus");

        assertNotNull(team.getStat());
        assertTrue(team.getStat().size() == 0);
        assertTrue(team.getScore() == 0);
        assertEquals("Juventus" , team.getName());
    }


}
