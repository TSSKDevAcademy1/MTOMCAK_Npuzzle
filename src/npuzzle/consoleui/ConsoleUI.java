package npuzzle.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Locale;

import loadSave.FileGameLoader;
import loadSave.GameLoader;
import npuzzle.Npuzzle;
import npuzzle.core.PlayingField;

public class ConsoleUI {
	/** Playing field represent game and logic */
	private PlayingField field;

	private FileGameLoader fileGame = new FileGameLoader();
	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

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

	public void newGameStarted() {

		try {
			field = fileGame.load();
			System.out.printf("\tWelcome player " + System.getProperty("user.name") + "!\n\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("nejde");
			field = new PlayingField(5, 5);
		}

		// System.out.printf("\tWelcome player
		// "+System.getProperty("user.name")+"!\n\n");
		do {
			update();
			processInput();
			// check if player win
			if (field.isSolved()) {
				System.out.println("Congratulations You won !!!");
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
		field = new PlayingField(4, 4);
		try {
			handleInput(readLine().toUpperCase());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void handleInput(String input) throws WrongFormatException, IOException {
		int col = 0, row = 0;
		// get lower case
		input = input.toLowerCase();
		// filter insert command
		inputFilter(input);

		if (input.equals("exit")) {
			fileGame.store(field);
			System.exit(0);
		} else if (input.equals("new")) {
			newGameStarted();
		}
		// get position of empty cell in playing field
		String position = field.getPosOfEmptyCell();
		// convert position to integer value
		row = Integer.parseInt(position.substring(0, position.indexOf(" ")));
		col = Integer.parseInt(position.substring(position.indexOf(" ") + 1, position.length()));

		switch (input) {
		case "w":
			field.moveUp(row, col);
			break;
		case "s":
			field.moveDown(row, col);
			break;
		case "a":
			field.moveRight(row, col);
			break;
		case "d":
			field.moveLeft(row, col);
			break;
		default:
			throw new WrongFormatException("Wrong command insert: " + input + "!");
		}
	}

	/**
	 * Filter inserted input string.
	 */
	private String inputFilter(String input) {
		if (input.equals("up"))
			input = "w";
		if (input.equals("down"))
			input = "s";
		if (input.equals("left"))
			input = "a";
		if (input.equals("right"))
			input = "d";
		return input;
	}

	/**
	 * Refresh playing field. Check value of cell and print on console.
	 */
	public void update() {
		// Create formatter for StringBuilder
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.US);

		// System.out.println(Npuzzle.getInstance().getPlayingSeconds());
		formatter.format("Playing time: %d s\n ", Npuzzle.getInstance().getPlayingSeconds());

		// Print field using format
		for (int r = 0; r < field.getRowCount(); r++) {
			for (int c = 0; c < field.getColumnCount(); c++) {
				formatter.format("\t%s ", field.getCell(r, c).toString());
			}
			formatter.format("\n\n");
		}
		formatter.format("Please enter your selection new, exit, w (up), s (down), a (left), d(right): ");
		formatter.close();
		System.out.println(sb);
	}
}
