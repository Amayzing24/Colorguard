package colorguard;

import java.io.Serializable;

/**
 * Location class represents a 2D point on a grid. (0, 0) is the top left of the
 * grid.
 * 
 * @author atripathi499
 */
public class Location implements Serializable {
	private static final long serialVersionUID = 4335588706005227067L;
	
	private int row;
	private int col;

	/**
	 * Location constructor
	 * 
	 * @param r row coordinate
	 * @param c col coordinate
	 */
	public Location(int r, int c) {
		this.row = r;
		this.col = c;
	}

	/**
	 * @return current row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return current column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Changes the row
	 * 
	 * @param newRow coordinate
	 */
	public void setRow(int newRow) {
		row = newRow;
	}

	/**
	 * Changes the column location
	 * 
	 * @param newCol coordinate
	 */
	public void setCol(int newCol) {
		col = newCol;
	}

	/**
	 * Changes the row and column locations
	 * 
	 * @param newRow coordinate
	 * @param newCol coodinate
	 */
	public void set(int newRow, int newCol) {
		row = newRow;
		col = newCol;
	}

	/**
	 * @return a String representation of this Location
	 */
	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null || other.getClass() != this.getClass())
			return false;
		Location otherLoc = (Location)other;
		return row == otherLoc.getRow() && col == otherLoc.getCol();
	}

	@Override
	public int hashCode() {
		int tmp = (col + ((row + 1) / 2));
		return row + (tmp * tmp);
	}
}
