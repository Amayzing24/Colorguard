package colorguard;

/**
 * Clone is a powerup, hence it extends Powerup. Clone does not implement any
 * real functionality in the effect(Piece p) method Clone instead has a method
 * getClone which gets a copy of a Piece that lasts 6 turns
 * 
 * @author amaytripathi
 *
 */
public class Clone extends Powerup {
	private static final long serialVersionUID = -3611459563540067097L;

	/**
	 * Initializes this clone at Location (r, c)
	 * 
	 * @param r the row of the Clone
	 * @param y the column of the Clone
	 */
	public Clone(int r, int c) {
		super(r, c, 6);
	}

	/**
	 * Has no impact for Clone class
	 * 
	 * @param p
	 * @return false
	 */
	public boolean effect(Piece p) {
		return false;
	}

	/**
	 * Returns a clone for Piece p that lasts 6 turns and is of the same type as
	 * Piece
	 * 
	 * @param p   the Piece to be cloned
	 * @param loc Location of the clone (must be in the starting zone)
	 * @return a clone of p, isClone is true for this return value
	 */
	public Piece getClone(Piece p, Location loc) {
		Piece clone;
		if (p.pieceType().equals("diagonal"))
			clone = new Diagonal(loc, p.getSide());
		else if (p.pieceType().equals("teleporter"))
			clone = new Teleporter(loc, p.getSide());
		else if (p.pieceType().equals("basicpiece"))
			clone = new BasicPiece(loc, p.getSide());
		else if (p.pieceType().equals("hopper"))
			clone = new Hopper(loc, p.getSide());
		else if (p.pieceType().equals("longrange"))
			clone = new LongRange(loc, p.getSide());
		else
			return null;
		clone.changeIsClone();
		this.gameName();
		return clone;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return "clone"
	 */
	@Override
	public String powerupType() {
		return "clone";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return "Clone"
	 */
	@Override
	public String gameName() {
		return "Clone";
	}

	/**
	 * {@inheritDoc} Sets isClone for p to false
	 */
	@Override
	public void deactivate(Piece p) {
		if (p.isClone())
			p.changeIsClone();
	}
}
