package loadSave;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import npuzzle.core.PlayingField;

public class FileGameLoader implements GameLoader {

	private static final String file = "game.bin";

	@Override
	public PlayingField load() throws IOException {
		PlayingField pF = new PlayingField(0, 0);
		File f = new File("game.bin");
		if (!f.exists())
			return pF;
		try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis);) {
			pF = (PlayingField) ois.readObject();
			System.out.println("Game load from file !" + file);
			return pF;
		} catch (Exception e) {
			System.out.println("File don't exist !" + file);
		}
		return pF;
	}

	@Override
	public void store(PlayingField field) {
		File f = new File(file);
		System.out.println(file);
		try (FileOutputStream fos = new FileOutputStream(f); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			// write object to file
			oos.writeObject(field);
			System.out.println("Save Game to file!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
