package npuzzle;

import npuzzle.consoleui.ConsoleUI;

public class Npuzzle {

	private static Npuzzle instance = null;
	private long startMillis;

	private Npuzzle() {
		instance = this;

		startMillis = System.currentTimeMillis();
		ConsoleUI ui = new ConsoleUI();
		ui.newGameStarted();
	}

	public static Npuzzle getInstance() {
		if (instance == null) {
			new Npuzzle();
		}
		return instance;
	}

	public static void main(String[] args) {
		Npuzzle.getInstance();

	}

	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}
}
