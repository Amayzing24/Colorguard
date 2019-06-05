package colorguard;

import java.io.Serializable;

/**
 * GameObject serves as the superclass for all objects in the game (Pieces and
 * Powerups). GameObject defines a location, but its type is not yet defined.
 * 
 * @author atripathi499
 */
public abstract class GameObject implements Serializable {
	private static final long serialVersionUID = 4654695229492654290L;

	private Location loc;

	/**
	 * Constructor for GameObject - defines location
	 * 
	 * @param row of a grid ((0, 0) is the top left of the grid)
	 * @param col of a grid
	 */
	public GameObject(int r, int c) {
		loc = new Location(r, c);
	}

	/**
	 * Constructor for GameObject
	 * 
	 * @param loc of the object
	 */
	public GameObject(Location loc) {
		this.loc = loc;
	}

	/**
	 * Moves object to new location (newX, newY)
	 * 
	 * @param newRow location
	 * @param newCol location
	 */
	public void moveTo(int newRow, int newCol) {
		loc.set(newRow, newCol);
	}

	/**
	 * Moves GameObject to new location
	 * 
	 * @param newLoc new location of object
	 */
	public void moveTo(Location newLoc) {
		loc = newLoc;
	}

	/**
	 * Gets current location of object
	 * 
	 * @return loc
	 */
	public Location getLocation() {
		return loc;
	}

	/**
	 * @return type of GameObject
	 */
	public abstract String type();

	/**
	 * Gets the name of this object that is used on the front end
	 * 
	 * @return the game name for this object
	 */
	public abstract String gameName();
}
