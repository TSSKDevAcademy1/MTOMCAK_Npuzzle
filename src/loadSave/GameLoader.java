package loadSave;

import java.io.IOException;

import npuzzle.core.PlayingField;

public interface GameLoader {

	/**
	 * Load playing field.
	 * 
	 * @return playing field.
	 */
	PlayingField load() throws IOException;

	/** Save playing field. */
	void store(PlayingField field);
}
