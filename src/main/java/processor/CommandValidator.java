package processor;

import entity.Game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Salman on 4/16/2016.
 */
public class CommandValidator {

    public static final String GAME_START_COMMAND_PATTERN = "Start: '.+' vs\\. '.+'";
    public static final String GAME_STAT_UPDATE_COMMAND_PATTERN = "\\d{1,2} '.+' \\D+";
    public static final String GAME_END_COMMAND = "END";
    public static final String PRINT_STAT_COMMAND = "PRINT";

    public static boolean validateGameStartCommand(String command) {
        boolean result = false;
        if(command == null) {
            return result;
        }
        else {
            Pattern startCommandPattern = Pattern.compile(GAME_START_COMMAND_PATTERN);
            Matcher matcher = startCommandPattern.matcher(command);
            result = matcher.matches();
            return result;
        }
    }

    public static boolean validateTeamScoreCommandFormat(String command) {
        Pattern startCommandPattern = Pattern.compile(GAME_STAT_UPDATE_COMMAND_PATTERN);
        Matcher matcher = startCommandPattern.matcher(command);
        boolean regexResult = matcher.matches();
        if(!regexResult) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validateTeamScoreCommand(String command , Game currentGame) {
        boolean result = false;
        if(command == null || currentGame == null) {
            return result;
        } else {
            boolean regexResult = validateTeamScoreCommandFormat(command);
            if(!regexResult) {
                return result;
            }
            String[] commandParts = command.split(" ");
            String commandTeamName = commandParts[1].replaceAll("'","");
            if(!commandTeamName.equalsIgnoreCase(currentGame.getHomeTeam().getName()) &&
                    !commandTeamName.equalsIgnoreCase(currentGame.getAwayTeam().getName())) {
                return result;
            }
            Integer minute = Integer.parseInt(commandParts[0]);
            if(minute > 90) {
                return result;
            }
            result = true;
        }
        return result;
    }

    public static boolean validateEndGameCommand(String command) {
        boolean result = false;
        if(command == null) {
            return result;
        }
        if(command.equalsIgnoreCase(GAME_END_COMMAND)) {
            result = true;
        }

        return result;
    }

    public static boolean validatePrintGameStatCommand(String command) {
        boolean result = false;
        if(command == null) {
            return result;
        }
        if(command.equalsIgnoreCase(PRINT_STAT_COMMAND)) {
            result = true;
        }

        return result;
    }

}
