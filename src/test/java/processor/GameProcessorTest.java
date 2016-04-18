package processor;

import constant.ErrorCode;
import entity.Game;
import entity.Player;
import entity.Team;
import entity.TeamStat;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Salman on 4/16/2016.
 */
public class GameProcessorTest {

    @Test
    public void givenNullCommandToStartGameTheErrorCodeMustBeReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        String result = processor.processStartGame(null);
        assertEquals(result , ErrorCode.invalidStartGameCommand.getErrorCodeMessage());
    }

    @Test
    public void givenInvalidCommandToStartGameTheErrorCodeMustBeReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        String result = processor.processStartGame("start: ada vc dvd");
        assertEquals(result , ErrorCode.invalidStartGameCommand.getErrorCodeMessage());
    }

    @Test
    public void givenValidCommandToParseAndCreateGameThenResultMustBeAValidGameWithCorrectTeamNames() {
        String command = "Start: 'Juventus' vs. 'Milan'";
        GameProcessor processor = GameProcessor.getInstance();
        Game game = processor.parseCommandAndCreateGame(command);
        assertNotNull(game);
        assertEquals("Juventus" , game.getHomeTeam().getName());
        assertEquals("Milan" , game.getAwayTeam().getName());
    }

    @Test
    public void givenValidCommandToStartGameTheSuccessMessageMustBeReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        String result = processor.processStartGame("Start: 'Juventus' vs. 'Milan'");
        assertEquals(result , "Football match between Juventus and Milan started");
    }

    @Test
    public void givenValidCommandToUpdateGameStatWhenGameNotStartedThenTheErrorCodeMustBeReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        String command ="15 'Juventus' Nedved";
        String result = processor.processUpdateGameStat(command);
        assertEquals(result, ErrorCode.invalidUpdateGameStatCommand.getErrorCodeMessage());
    }

    @Test
    public void givenInValidCommandToUpdateGameStatWhenGameStartedThenTheErrorCodeMustBeReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        String result = processor.processStartGame("Start: 'Juventus' vs. 'Milan'");
        String command ="15 'Madrid' Nedved";
        String updateResult = processor.processUpdateGameStat(command);
        assertEquals(updateResult , ErrorCode.invalidUpdateGameStatCommand.getErrorCodeMessage());
    }

    @Test
    public void givenCommandToUpdateGameStatThenTheCorrespondingTeamStatIsUpdated_homeTeam() {
        GameProcessor processor = GameProcessor.getInstance();
        String result = processor.processStartGame("Start: 'Juventus' vs. 'Milan'");
        processor.parseCommandAndUpdateGameStat("15 'Juventus' Nedved");
        Game game = processor.getGame();
        // juventus is home team and home team just scored
        Team homeTeam = game.getHomeTeam();
        List<TeamStat> homeTeamStats = homeTeam.getStat();
        assertTrue(homeTeamStats.size() == 1);
        TeamStat stat = homeTeamStats.get(0);
        Player scorer = stat.getScorer();
        assertEquals(scorer.getName(), "Nedved");
        assertEquals(15 , stat.getMinute());
        assertEquals(1 , homeTeam.getScore());
    }

    @Test
    public void givenCommandToUpdateGameStatThenTheCorrespondingTeamStatIsUpdated_awayTeam() {
        GameProcessor processor = GameProcessor.getInstance();
        String result = processor.processStartGame("Start: 'Juventus' vs. 'Milan'");
        processor.parseCommandAndUpdateGameStat("89 'Milan' Kaka");
        Game game = processor.getGame();
        // milan is away team and home team just scored
        Team awayTeam = game.getAwayTeam();
        List<TeamStat> awayTeamStat = awayTeam.getStat();
        assertTrue(awayTeamStat.size() == 1);
        TeamStat stat = awayTeamStat.get(0);
        Player scorer = stat.getScorer();
        assertEquals(scorer.getName() , "Kaka");
        assertEquals(89 , stat.getMinute());
        assertEquals(1 , awayTeam.getScore());
    }

    @Test
    public void givenNullCommandToPrintGameStatThenCorrespondingErrorCodeIsReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        String result = processor.processPrintGameStat(null);
        assertEquals(ErrorCode.invalidPrintCommand.getErrorCodeMessage() , result);
    }

    @Test
    public void givenInvalidCommandToPrintGameStatThenCorrespondingErrorCodeIsReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        String result = processor.processPrintGameStat("invalid");
        assertEquals(ErrorCode.invalidPrintCommand.getErrorCodeMessage() , result);
    }

    @Test
    public void givenValidCommandToPrintGameStatWhenThereIsNoScoresThenGameStatIsReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        processor.processStartGame("Start: 'Juventus' vs. 'Milan'");
        String result = processor.processPrintGameStat("print");
        String expectedMessage = "Juventus 0 vs. Milan 0";
        assertEquals(expectedMessage , result);
    }

    @Test
    public void givenValidCommandToPrintGameStatWhenHomeTeamScoresThenGameStatIsReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        processor.processStartGame("Start: 'Juventus' vs. 'Milan'");
        processor.parseCommandAndUpdateGameStat("15 'Juventus' Nedved");
        String result = processor.processPrintGameStat("print");
        String expectedMessage = "Juventus 1 ( Nedved 15' ) vs. Milan 0";
        assertEquals(expectedMessage , result);
    }

    @Test
    public void givenValidCommandToPrintGameStatWhenAwayTeamScoresThenGameStatIsReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        processor.processStartGame("Start: 'Juventus' vs. 'Milan'");
        processor.parseCommandAndUpdateGameStat("25 'Milan' Kaka");
        String result = processor.processPrintGameStat("print");
        String expectedMessage = "Juventus 0 vs. Milan 1 ( Kaka 25' )";
        assertEquals(expectedMessage , result);
    }

    @Test
    public void givenValidCommandToPrintGameStatWhenBothScoresThenGameStatIsReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        processor.processStartGame("Start: 'Juventus' vs. 'Milan'");
        processor.parseCommandAndUpdateGameStat("15 'Juventus' Nedved");
        processor.parseCommandAndUpdateGameStat("63 'Juventus' Delpiero");
        processor.parseCommandAndUpdateGameStat("25 'Milan' Kaka");
        String result = processor.processPrintGameStat("print");
        String expectedMessage = "Juventus 2 ( Nedved 15' Delpiero 63' ) vs. Milan 1 ( Kaka 25' )";
        assertEquals(expectedMessage , result);
    }

    @Test
    public void givenNullCommandToEndGameStatThenCorrespondingErrorCodeIsReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        String result = processor.processEndGame(null);
        assertEquals(ErrorCode.invalidEndCommand.getErrorCodeMessage() , result);
    }

    @Test
    public void givenInvalidCommandToEndGameStatThenCorrespondingErrorCodeIsReturned() {
        GameProcessor processor = GameProcessor.getInstance();
        String result = processor.processEndGame("invalid");
        assertEquals(ErrorCode.invalidEndCommand.getErrorCodeMessage() , result);
    }

    @Test
    public void givenValidCommandToEndGameStatThenCorrespondingGameFinalStatShouldReturn(){
        GameProcessor processor = GameProcessor.getInstance();
        processor.processStartGame("Start: 'Juventus' vs. 'Milan'");
        String result = processor.processEndGame("end");
        String expextedResult = "Soccer match is finished with the following result \n" + "Juventus 0 vs. Milan 0";
        assertEquals(expextedResult , result);
    }
}
