package npuzzle.core;

import java.io.Serializable;
import java.util.Random;

public class PlayingField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Playing field cells.
	 */
	private final Cell[][] playingField;

	/**
	 * Rows count. Rows are indexed from 0 to (columnCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Number Of cell. Number of all cell in playing filed.
	 */
	private final int numberOfCell;

	/**
	 * Constructor.
	 *
	 * @param rowCount
	 *            row count
	 * @param columnCount
	 *            column count
	 */
	public PlayingField(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		numberOfCell = rowCount * columnCount;
		playingField = new Cell[rowCount][columnCount];
		// generate the field content
		generate();
	}

	/**
	 * Generates playing field.
	 */
	private void generate() {
		Random cellValue = new Random();
		int ranValue;
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				if (getCell(r, c) == null) {
					do {
						ranValue = cellValue.nextInt(numberOfCell);
						if (!ifExistValue(ranValue)) {
							playingField[r][c] = new Cell(ranValue);
						}
					} while (!(getCell(r, c) instanceof Cell));
				}
			}
		}
	}

	/**
	 * Returns true if value exist in playing field otherwise false.
	 *
	 * @return true if value exist in playing field
	 */
	private boolean ifExistValue(int value) {
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				if (getCell(r, c) instanceof Cell && (getCell(r, c).getValue() == value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns true if game is solved, false otherwise.
	 *
	 * @return true if game is solved, false otherwise
	 */
	public boolean isSolved() {
		int check = 1;
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				if (getCell(r, c).getValue() == check) {
					check++;
				}
			}
		}
		return numberOfCell == check;
	}

	/**
	 * Returns position of empty cell.
	 *
	 * @return column and row of empty cell.
	 */
	public String getPosOfEmptyCell() {
		String position = null;
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				if (getCell(r, c).getValue() == 0) {
					position = r + " " + c;
				}
			}
		}
		return position;
	}

	/**
	 * Move left with cell right of empty cell.
	 */
	public void moveLeft(int row, int col) {
		Cell cell;
		if (col < columnCount) {
			cell = playingField[row][col];
			playingField[row][col] = playingField[row][col + 1];
			playingField[row][col + 1] = cell;
		}
	}
	
	/**
	 * Move right with empty cell.
	 */
	public void moveRight(int row, int col) {
		Cell cell;
		if (col > 0) {
			cell = playingField[row][col];
			playingField[row][col] = playingField[row][col - 1];
			playingField[row][col - 1] = cell;
		}
	}
	
	/**
	 * Move up with with empty cell.
	 */
	public void moveUp(int row, int col) {
		Cell cell;
		if (row > 0) {
			cell = playingField[row][col];
			playingField[row][col] = playingField[row - 1][col];
			playingField[row - 1][col] = cell;
		}
	}
	
	/**
	 * Move down with empty cell.
	 */
	public void moveDown(int row, int col) {
		Cell cell;
		if (row < rowCount) {
			cell = playingField[row][col];
			playingField[row][col] = playingField[row + 1][col];
			playingField[row + 1][col] = cell;
		}
	}

	/**
	 * Returns cell with specified rows and columns, which are indexed from 0.
	 *
	 * @param row
	 *            row number.
	 * @param column
	 *            column number.
	 * @return tiles tile consist of row and column.
	 */
	public Cell getCell(int row, int column) {
		return playingField[row][column];
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}
}
