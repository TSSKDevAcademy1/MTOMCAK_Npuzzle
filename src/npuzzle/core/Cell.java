package npuzzle.core;

import java.io.Serializable;

public class Cell implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Value of the cell. */
	private final int value;

	public Cell(int value) {
		this.value = value;
	}

	/**
	 * Constructor.
	 * 
	 * @param value
	 *            value of the cell
	 */
	public int getValue() {
		return value;
	};

	@Override
	public String toString() {
		if (getValue() == 0) {
			return " ";
		}
		return getValue() + "";
	}

}
