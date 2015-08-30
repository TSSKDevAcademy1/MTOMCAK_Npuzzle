package loadSave;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import npuzzle.core.PlayingField;

public class FileGameLoader implements GameLoader {

	private static final String file = "game.bin";

	@Override
	public PlayingField load() throws FileNotFoundException {
		PlayingField pF = null;
		File f = new File("game.bin");
		if (!f.exists()){
			throw new FileNotFoundException();
		}
		try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis);) {
			pF = (PlayingField) ois.readObject();
			System.out.println("Game load from file !" + file);
		} catch (Exception e) {
			System.out.println("File is damaged !" + file);
		}
		return pF;
	}

	@Override
	public void store(PlayingField field) {
		File f = new File(file);
		try (FileOutputStream fos = new FileOutputStream(f); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(field);
			System.out.println("Save Game to file succesfully !");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Save Game to file failed !");
		}
	}
}
