package colorguard;

/**
 * Flag is a type of GameObject. It represents the objective of the game.
 * 
 * @author amaytripathi
 *
 */
public class Flag extends GameObject {
	private static final long serialVersionUID = -1034142748517286021L;

	/**
	 * Creates a new Flag at given Location
	 * 
	 * @param loc Location of the Flag
	 */
	public Flag(Location loc) {
		super(loc);
	}

	/**
	 * Creates a new Flag at given Location (r, c)
	 * 
	 * @param r row of flag
	 * @param c column of flag
	 */
	public Flag(int r, int c) {
		super(r, c);
	}

	/**
	 * @return "flag"
	 */
	@Override
	public String type() {
		return "flag";
	}

	/**
	 * @return "Flag"
	 */
	@Override
	public String gameName() {
		return "Flag";
	}
}
