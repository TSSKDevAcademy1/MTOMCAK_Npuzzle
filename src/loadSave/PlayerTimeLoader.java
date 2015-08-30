package loadSave;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import npuzzle.core.BestTimes;
import npuzzle.core.BestTimes.PlayerTime;

public class PlayerTimeLoader {

	/** set DB parameters */
	public static final String URL = "jdbc:mysql://localhost/ntiles";
	public static final String USER = "root";
	public static final String PASSWORD = "root";

	/** set DB query */
	public static final String QUERYCREATE = "CREATE TABLE IF NOT exists playerTime (id MEDIUMINT NOT NULL AUTO_INCREMENT, name VARCHAR(32) NOT NULL, playingTime INT NOT NULL, PRIMARY KEY (id))";
	public static final String QUERYINSERT = "INSERT INTO playerTime (name, playingTime) VALUES (?, ?)";
	public static final String QUERYSELECT = "SELECT * FROM playertime ORDER BY playingTime";
	
	/**
	 * Load player time from database.
	 * 
	 * @return player time.
	 */
	public BestTimes load() {
		BestTimes bTimes = new BestTimes();
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);) {
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(QUERYSELECT);) {
				while (rs.next()) {
					bTimes.addPlayerTime(rs.getString(2), Integer.parseInt(rs.getString(3)));
				}
			}
		} catch (SQLException e) {
			System.out.println("Table Player time doesn't exist !");
		}
		return bTimes;
	}

	/**
	 * Save player time to database.
	 */
	public void store(PlayerTime playerTime) {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);) {
			try (Statement stmt = con.createStatement();) {
				// create new table of player time of not exist
				stmt.executeUpdate(QUERYCREATE);
			}
			try (PreparedStatement stmtPrepare = con.prepareStatement(QUERYINSERT);) {
				// insert player time into database
					stmtPrepare.setString(1, playerTime.getName());
					stmtPrepare.setInt(2, playerTime.getTime());
					stmtPrepare.executeUpdate();
				System.out.println("Save register to Database successful !");
			}
		} catch (SQLException e) {
			System.out.println("Save register to Database failed !");
			e.printStackTrace();
		}
	}
}
