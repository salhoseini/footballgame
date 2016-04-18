# footballgame

This Simple Java application is using maven to build and access libraries.

The application is using command line to perform actions.

As requested by giving the command "Start: '<Name of Home Team>' vs. '<Name of Away Team>'" the game gets started. Before this command is given, no other commands are acceptable and the user 
will be prompted by an appropriate error message. The team name should not have space in it, if so, our application will register the first phrase only.
The acceptable commands are the score command, print command, end command (Only acceptable after the game is started) and exit command.
-score command registers a score for the specified team. the limitations on this command are : 
	1- a goal cannot be scored for a team that is not playing. If you started a match between Juventus and Milan you cannot register a goal for Rome for instance.
	2- a goal cannot be scored at a time after 90. Although a football match can go beyond 90 mins our application only accepts times between 0 and 90.
	3- The application only registers one name for the scorer meaning if a name is entered with spaces in between the application only registers the first phrase in the name. If 'Pavel Nedved'
	scores for Juventus we will register this stat by his first name.
	4-the format of the command is as follows : "<minute> '<Team>' <name of scorer>".
-print command prints the stat and it does not have any limitation.
-End command marks the finish of the match. it will print the final stat and after that the user can start a new game.
-Exit command kills the application.