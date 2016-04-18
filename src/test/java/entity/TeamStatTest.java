package entity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Salman on 4/18/2016.
 */
public class TeamStatTest {

    @Test
    public void testConstructor() {
        Player player = new Player("Nedved");
        TeamStat stat = new TeamStat(player , 45);

        assertEquals("Nedved",stat.getScorer().getName());
    }
}
