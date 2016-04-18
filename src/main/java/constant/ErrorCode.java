package constant;

/**
 * Created by Salman on 4/16/2016.
 */
public enum ErrorCode {
    invalidStartGameCommand("input error - please start a game through typing 'Start: '<Name of Home Team>' vs. '<Name of Away Team>'"),
    invalidUpdateGameStatCommand("input error - please type 'print' for game details"),
    invalidPrintCommand("input error - please type 'print' for game details"),
    invalidEndCommand("input error - please type 'print' for game details"),
    noGameInProgress("No game currently in progress");

    private String errorCodeMessage;

    ErrorCode(String message) {
        errorCodeMessage = message;
    }

    public String getErrorCodeMessage() {
        return errorCodeMessage;
    }
}
