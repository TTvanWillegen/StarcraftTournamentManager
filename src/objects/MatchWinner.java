package objects;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public enum MatchWinner {
	TEAM_A,
	TEAM_B,
	DRAW,
	NONE, //Used when a timeout on lag detection has occured.
	UNPLAYED // Used when the game has not been played yet.
}
