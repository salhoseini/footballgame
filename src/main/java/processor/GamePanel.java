package processor;

import constant.ErrorCode;

import java.util.Scanner;

/**
 * Created by Salman on 4/17/2016.
 */
public class GamePanel {

    private static Scanner scanner = new Scanner(System.in);

    private static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {

        GameProcessor processor = GameProcessor.getInstance();

        System.out.println("Write the start command to start the soccer game between two teams");
        System.out.println("follow this pattern to start the game");
        System.out.println("Start: '<Name of Home Team>' vs. '<Name of Away Team>'");
        System.out.println("At anytime during the game, see the game stat by writing the command 'print'");
        System.out.println("You can end the game with writing the command 'end'");
        System.out.println("You can exit the application with writing the command 'exit'");
        while(true) {

            String command = scanner.nextLine();
            String commandResult = null;
            if(command.equalsIgnoreCase(EXIT_COMMAND)) {
                break;
            }
            if(!processor.isGameStarted()) {
                boolean isUpdateCommandGiven = CommandValidator.validateTeamScoreCommandFormat(command);
                if(isUpdateCommandGiven) {
                    System.out.println(ErrorCode.noGameInProgress.getErrorCodeMessage());
                } else {
                    commandResult = processor.processStartGame(command);
                    System.out.println(commandResult);
                }
            } else {
                if(CommandValidator.PRINT_STAT_COMMAND.equalsIgnoreCase(command)) {
                    commandResult = processor.processPrintGameStat(command);
                } else if (CommandValidator.GAME_END_COMMAND.equalsIgnoreCase(command)) {
                    commandResult = processor.processEndGame(command);
                }  else {
                    commandResult = processor.processUpdateGameStat(command);
                }
                System.out.println(commandResult);
            }
        }

    }
}
