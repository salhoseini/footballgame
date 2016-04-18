package processor;

import constant.ErrorCode;
import entity.Game;
import entity.Player;
import entity.Team;
import entity.TeamStat;

import java.util.List;

/**
 * Created by Salman on 4/16/2016.
 */
public class GameProcessor {

    private boolean isGameStarted = false;
    private static GameProcessor instance = null;
    private Game game;

    private GameProcessor() {

    }

    public static GameProcessor getInstance() {
        if(instance == null) {
            instance = new GameProcessor();
        }
        return instance;
    }

    public String processStartGame(String command) {
        String result = null;
        boolean validationResult = CommandValidator.validateGameStartCommand(command);
        if(!validationResult) {
            return ErrorCode.invalidStartGameCommand.getErrorCodeMessage();
        }
        game = parseCommandAndCreateGame(command);
        isGameStarted = true;
        result = "Football match between " + game.getHomeTeam().getName() + " and " + game.getAwayTeam().getName() + " started";
        return result;
    }

    public String processUpdateGameStat(String command) {
        String result = null;
        boolean validationResult = CommandValidator.validateTeamScoreCommand(command , game);
        if(!validationResult) {
            return ErrorCode.invalidUpdateGameStatCommand.getErrorCodeMessage();
        }
        parseCommandAndUpdateGameStat(command);
        result = "The game stat is updated";
        return result;
    }

    public String processPrintGameStat(String command) {
        String result = null;
        boolean validationResult = CommandValidator.validatePrintGameStatCommand(command);
        if(!validationResult) {
            return ErrorCode.invalidPrintCommand.getErrorCodeMessage();
        }
        result = printGameStat();
        return result;
    }

    public String processEndGame(String command) {
        String result = null;
        boolean validationResult = CommandValidator.validateEndGameCommand(command);
        if(!validationResult) {
            return ErrorCode.invalidEndCommand.getErrorCodeMessage();
        }

        result = "Soccer match is finished with the following result \n" + processPrintGameStat("print");
        reinitializeGame();
        return result;
    }

    private void reinitializeGame() {
        game = null;
        isGameStarted = false;
    }

    protected String printGameStat() {
        Team homeTeam = game.getHomeTeam();
        Team awayTeam = game.getAwayTeam();
        List<TeamStat> homeTeamStat = homeTeam.getStat();
        List<TeamStat> awayTeamStat = awayTeam.getStat();
        StringBuilder builder = new StringBuilder();
        builder.append(homeTeam.getName());
        builder.append(" ");
        if(homeTeamStat.isEmpty()) {
            builder.append("0");
        } else {
            builder.append(homeTeam.getScore());
            builder.append(" ");
            builder.append("( ");
            for(TeamStat stat : homeTeamStat) {

                builder.append(stat.getScorer().getName());
                builder.append(" ");
                builder.append(stat.getMinute());
                builder.append("'");
                builder.append(" ");

            }
            builder.append(")");
        }
        builder.append(" vs. ");
        builder.append(awayTeam.getName());
        builder.append(" ");
        if(awayTeamStat.isEmpty()) {
            builder.append("0");
        } else {
            builder.append(awayTeam.getScore());
            builder.append(" ");
            builder.append("( ");
            for(TeamStat stat : awayTeamStat) {

                builder.append(stat.getScorer().getName());
                builder.append(" ");
                builder.append(stat.getMinute());
                builder.append("'");
                builder.append(" ");

            }
            builder.append(")");
        }
        /*if(homeTeamStat.isEmpty() && awayTeamStat.isEmpty()) {
            result = homeTeam.getName() + " " + homeTeamScore +" vs. " + awayTeam.getName() + " " + awayTeamScore;
        }*/
        return builder.toString();
    }

    protected void parseCommandAndUpdateGameStat(String command) {
        String[] commandParts = command.split(" ");
        int minute = Integer.parseInt(commandParts[0]);
        String scoringTeam = commandParts[1].replaceAll("'","");
        String scorerName = commandParts[2];
        TeamStat updatedStat = new TeamStat(new Player(scorerName) , minute);
        Team homeTeam = game.getHomeTeam();
        Team awayTeam = game.getAwayTeam();
        if(scoringTeam.equalsIgnoreCase(homeTeam.getName())) {
            homeTeam.incrementScore();
            homeTeam.addStat(updatedStat);
        } else {
            awayTeam.incrementScore();
            awayTeam.addStat(updatedStat);
        }

    }

    protected Game parseCommandAndCreateGame(String command) {
        String[] commandParts = command.split(" ");
        String homeTeamName = commandParts[1];
        homeTeamName = homeTeamName.replaceAll("'","");
        String awayTeamName = commandParts[3];
        awayTeamName = awayTeamName.replaceAll("'","");
        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);
        Game game = new Game(homeTeam, awayTeam);
        return game;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void setIsGameStarted(boolean isGameStarted) {
        this.isGameStarted = isGameStarted;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
