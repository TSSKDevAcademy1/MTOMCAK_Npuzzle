package loadSave;

import java.io.IOException;

import npuzzle.core.PlayingField;

public interface GameLoader {
	
	/**Load field game.*/
	PlayingField load() throws IOException;

	/**Save field game.*/
	void store(PlayingField field);
}
