package npuzzle.consoleui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Locale;

import loadSave.FileGameLoader;
import loadSave.PlayerTimeLoader;
import npuzzle.Npuzzle;
import npuzzle.core.BestTimes.PlayerTime;
import npuzzle.core.PlayingField;

/**
 * Console UI - game console with read input, update field and check game state.
 */
public class ConsoleUI {
	/** Playing field represent game and logic */
	private PlayingField field;
	/** File game loader - save and load game from/to file. */
	private FileGameLoader fileGame = new FileGameLoader();
	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	/** Player time loader */
	private PlayerTimeLoader playertimeLoad = new PlayerTimeLoader();
	private String userName = System.getProperty("user.name");
	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * New game started. Load game from file or database if exist otherwise
	 * generate new playing field.
	 */
	public void newGameStarted() {
		try {
			System.out.println(playertimeLoad.load());
			field = fileGame.load();
		} catch (FileNotFoundException e) {
			System.out.println("File not found ! \n Generate playing field !");
			field = new PlayingField(5, 5);
		}

		System.out.printf("\tWelcome player " + userName + "!\n\n");

		do {
			update();
			processInput();
			// check if player win
			if (field.isSolved()) {
				System.out.println("Congratulations You won !!!");
				playertimeLoad.store(new PlayerTime(userName, Npuzzle.getInstance().getPlayingSeconds()));
				update();
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		try {
			handleInput(readLine().toLowerCase());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void handleInput(String input) throws WrongFormatException, IOException {
		// check if insert is exit or new game.
		if (input.equals("exit")) {
			fileGame.store(field);
			playertimeLoad.store(new PlayerTime(userName, Npuzzle.getInstance().getPlayingSeconds()));
			System.exit(0);
		} else if (input.equals("new")) {
			newGameStarted();
		}

		switch (input) {
		case "w":
		case "up":
			field.Move(-1, 0);
			break;
		case "s":
		case "down":
			field.Move(1, 0);
			break;
		case "a":
		case "left":
			field.Move(0, -1);
			break;
		case "d":
		case "right":
			field.Move(0, 1);
			break;
		default:
			throw new WrongFormatException("Wrong command insert: " + input + "!");
		}
	}

	/**
	 * Refresh playing field. Check value of cell and print on console.
	 */
	public void update() {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.US);

		// Playing time
		long playingTime = Npuzzle.getInstance().getPlayingSeconds();
		formatter.format("Playing time: %2d:%2d \n ", playingTime / 60, playingTime % 60);

		// Print field using formatter
		for (int r = 0; r < field.getRowCount(); r++) {
			for (int c = 0; c < field.getColumnCount(); c++) {
				formatter.format("\t%s ", field.getCell(r, c).toString());
			}
			formatter.format("\n\n");
		}
		// information panel
		formatter.format("Please enter your selection new, exit, w (up), s (down), a (left), d(right): ");
		formatter.close();
		System.out.println(sb);
	}
}
