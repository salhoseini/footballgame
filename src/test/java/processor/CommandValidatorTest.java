package processor;

import entity.Game;
import entity.Team;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Salman on 4/16/2016.
 */
public class CommandValidatorTest {
    @Test
    public void givenANullCommandThenGameCommandVlidatorReturnsFalse() {
        boolean validationResult = CommandValidator.validateGameStartCommand(null);
        assertFalse(validationResult);
    }

    @Test
    public void givenNonNullCommandWhenCommandNotValidThenGameValidatorResturnsFalse() {
        boolean validationResult = CommandValidator.validateGameStartCommand("invalidCommand");
        assertFalse(validationResult);
    }

    @Test
    public void givenNonNullCommandWhenCommandNotValidThenGameValidatorResturnsFalse_EmptyTeamName() {
        boolean validationResult = CommandValidator.validateGameStartCommand("Start: '' vs. 'Milan'");
        assertFalse(validationResult);
    }

    @Test
    public void givenNonNullCommandWhenCommandValidThenGameValidatorResturnsTrue() {
        boolean validationResult = CommandValidator.validateGameStartCommand("Start: 'Juventus' vs. 'Milan'");
        assertTrue(validationResult);
    }

    @Test
    public void givenEmptyCommandWhenCommandNotValidThenGameValidatorResturnsFalse() {
        boolean validationResult = CommandValidator.validateGameStartCommand("");
        assertFalse(validationResult);
    }

    @Test
    public void givenNotCommandWhenCommandNotValidThenGameValidatorResturnsFalse_detailed1() {
        boolean validationResult = CommandValidator.validateGameStartCommand("Start: 'Juventus' vs. 'Milan");
        assertFalse(validationResult);
    }

    @Test
    public void givenNotCommandWhenCommandNotValidThenGameValidatorResturnsFalse_detailed2() {
        boolean validationResult = CommandValidator.validateGameStartCommand("Start: 'Juventus' vs 'Milan'");
        assertFalse(validationResult);
    }

    @Test
    public void givenANullCommandThenGameStatUpdateCommandVlidatorReturnsFalse() {
        Team homeTeam = new Team("Juventus");
        Team awayTeam = new Team("Milan");
        Game game = new Game(homeTeam , awayTeam);
        boolean validationResult = CommandValidator.validateTeamScoreCommand(null , game);
        assertFalse(validationResult);
    }

    @Test
    public void givenANotNullCommandAndNullGameThenGameStatUpdateCommandVlidatorReturnsFalse() {
        boolean validationResult = CommandValidator.validateTeamScoreCommand("NotNullCommand" , null);
        assertFalse(validationResult);
    }

    @Test
    public void givenValidCommandAndNullGameThenGameStatUpdateCommandVlidatorReturnsFalse() {

        boolean validationResult = CommandValidator.validateTeamScoreCommand("10 'Juventus' Nedved" , null);
        assertFalse(validationResult);
    }

    @Test
    public void givenValidCommandAndCorrectGameThenGameStatUpdateCommandVlidatorReturnsTrue() {
        Team homeTeam = new Team("Juventus");
        Team awayTeam = new Team("Milan");
        Game game = new Game(homeTeam , awayTeam);
        boolean validationResult = CommandValidator.validateTeamScoreCommand("10 'Juventus' Nedved" , game);
        assertTrue(validationResult);
    }

    @Test
    public void givenValidCommandAndCorrectGameWithMinuteGreaterThan90ThenGameStatUpdateCommandVlidatorReturnsFalse() {
        Team homeTeam = new Team("Juventus");
        Team awayTeam = new Team("Milan");
        Game game = new Game(homeTeam , awayTeam);
        boolean validationResult = CommandValidator.validateTeamScoreCommand("110 'Juventus' Nedved" , game);
        assertFalse(validationResult);
    }

    @Test
    public void givenValidCommandWithWrongTeamNameThenGameStatUpdateCommandVlidatorReturnsFalse() {
        Team homeTeam = new Team("Juventus");
        Team awayTeam = new Team("Milan");
        Game game = new Game(homeTeam , awayTeam);
        boolean validationResult = CommandValidator.validateTeamScoreCommand("10 'Madrid' Nedved" , game);
        assertFalse(validationResult);
    }

    @Test
    public void givenValidCommandWithWrongGameMinuteThenGameStatUpdateCommandVlidatorReturnsFalse() {
        Team homeTeam = new Team("Juventus");
        Team awayTeam = new Team("Milan");
        Game game = new Game(homeTeam , awayTeam);
        boolean validationResult = CommandValidator.validateTeamScoreCommand("110 'Juventus' Nedved" , game);
        assertFalse(validationResult);
    }

    @Test
    public void givenNullCommandThenEndGameCommandValidatorReturnsFalse() {
        boolean validationResult = CommandValidator.validateEndGameCommand(null);
        assertFalse(validationResult);
    }

    @Test
    public void givenNonNullCommandWhenCommandIsNotValidThenEndGameCommandValidatorReturnsFalse() {
        boolean validationResult = CommandValidator.validateEndGameCommand("invalid command");
        assertFalse(validationResult);
    }

    @Test
    public void givenNonNullCommandWhenCommandIsValidThenEndGameCommandValidatorReturnsTrue() {
        boolean validationResult = CommandValidator.validateEndGameCommand("end");
        assertTrue(validationResult);
    }

    @Test
    public void givenNullCommandThenPrintGameStatCommandValidatorReturnsFalse() {
        boolean validationResult = CommandValidator.validatePrintGameStatCommand(null);
        assertFalse(validationResult);
    }

    @Test
    public void givenNonNullCommandWhenCommandIsNotValidThenPrintGameStatCommandValidatorReturnsFalse() {
        boolean validationResult = CommandValidator.validatePrintGameStatCommand("invalid command");
        assertFalse(validationResult);
    }

    @Test
    public void givenNonNullCommandWhenCommandIsValidThenPrintGameStatCommandValidatorReturnsTrue() {
        boolean validationResult = CommandValidator.validatePrintGameStatCommand("print");
        assertTrue(validationResult);
    }
}
