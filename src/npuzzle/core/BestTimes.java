package npuzzle.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import java.util.*;

/**
 * Player times.
 */
public class BestTimes implements Iterable<BestTimes.PlayerTime> {
	/** List of best player times. */
	private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

	/**
	 * Returns an iterator over a set of best times.
	 * 
	 * @return an iterator
	 */
	public Iterator<PlayerTime> iterator() {
		return playerTimes.iterator();
	}
	
	/**
	 * Remove each player from best player
	 */
	public void reset(){
		playerTimes.clear();
	}

	/**
	 * Adds player time to best times.
	 * 
	 * @param name
	 *            name of the player
	 * @param time
	 *            player time in seconds
	 */
	public void addPlayerTime(String name, int time) {
		playerTimes.add(new PlayerTime(name, time));
	}

	/**
	 * Returns a string representation of the object.
	 * 
	 * @return a string representation of the object
	 */
	public String toString() {
		int pTime;
		Formatter f = new Formatter();
		f.format("\tPlayers table\n___________________________________\n");
		for (int i = 0; i < playerTimes.size(); i++) {
			pTime = playerTimes.get(i).time;
			f.format("%1d. Name: %s Time: %2d:%2d\n", i + 1, playerTimes.get(i).name, pTime/60, pTime%60);
		}
		return f.toString();
	}

	/**
	 * Player time.
	 */
	public static class PlayerTime{
		/** Player name. */
		private final String name;
		/** Playing time in seconds. */
		private final int time;
		/**
		 * Constructor.
		 * 
		 * @param name
		 *            player name
		 * @param time
		 *            playing game time in seconds
		 */
		public PlayerTime(String name, int time) {
			this.name = name;
			this.time = time;
		}
		
		public String getName() {
			return name;
		}

		public int getTime() {
			return time;
		}
	}
}