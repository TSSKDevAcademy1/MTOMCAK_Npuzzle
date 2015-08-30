package npuzzle;

import npuzzle.consoleui.ConsoleUI;

/**
 * @author Matus
 * @Npuzzle The 15-puzzle (also called Gem Puzzle, Boss Puzzle, Game of Fifteen,
 *          Mystic Square and many others) is a sliding puzzle that consists of
 *          a frame of numbered square tiles in random order with one tile
 *          missing. The puzzle also exists in other sizes, particularly the
 *          smaller 8-puzzle. If the size is 3×3 tiles, the puzzle is called the
 *          8-puzzle or 9-puzzle, and if 4×4 tiles, the puzzle is called the
 *          15-puzzle or 16-puzzle named, respectively, for the number of tiles
 *          and the number of spaces. The object of the puzzle is to place the
 *          tiles in order (see diagram) by making sliding moves that use the
 *          empty space.
 * 
 *          For more information use this link:
 *          {@link https://en.wikipedia.org/wiki/15_puzzle}
 */

public class Npuzzle {

	/** Instance of stone puzzle. */
	private static Npuzzle instance = null;
	/** Start time of the game. */
	private long startMillis;

	/** Constructor */
	private Npuzzle() {
		instance = this;

		/** Set start game time. */
		startMillis = System.currentTimeMillis();

		/** Start console interface. */
		ConsoleUI ui = new ConsoleUI();
		ui.newGameStarted();
	}

	/** Sigelton of theNpuzzle. */
	public static Npuzzle getInstance() {
		if (instance == null) {
			new Npuzzle();
		}
		return instance;
	}

	/** Main function. */
	public static void main(String[] args) {
		Npuzzle.getInstance();
	}
	
	/**
	 * @param startMillis the startMillis to set
	 */
	public void setStartMillis(long startMillis) {
		this.startMillis = startMillis;
	}

	/**
	 * Playing time.
	 * 
	 * @return time in seconds.
	 */
	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}
}
