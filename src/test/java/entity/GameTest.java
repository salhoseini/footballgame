package entity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Salman on 4/18/2016.
 */
public class GameTest {

    @Test
    public void testConstructor() {
        Team homeTeam = new Team("Juventus");
        Team awayTeam = new Team("Milan");

        Game game = new Game(homeTeam,awayTeam);

        assertEquals(homeTeam.getName() , game.getHomeTeam().getName());
        assertEquals(awayTeam.getName() , game.getAwayTeam().getName());
    }

}
