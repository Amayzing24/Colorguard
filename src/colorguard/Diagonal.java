package colorguard;

import java.util.HashSet;

/**
 * The Diagonal class is a type of Piece and thus extends Piece. It can move up
 * to 3 spaces diagonally in all directions.
 * 
 * @author amaytripathi
 */
public class Diagonal extends Piece {
	private static final long serialVersionUID = -6058280870093239893L;

	/**
	 * Initializes the current Diagonal's location and side
	 * 
	 * @param currentLocation the location of the Piece
	 * @param side            the side the Piece belongs to
	 */
	public Diagonal(Location currentLocation, String side) {
		super(currentLocation, side);
	}

	@Override
	public HashSet<Location> getPossibleMovements(GameBoard board) {
		HashSet<Location> poss = new HashSet<Location>();

		Location currLoc = super.getLocation();
		poss.add(currLoc);

		if (super.isParalysed() || super.isInJail()) {
			return poss;
		}

		int change = 3;
		if (super.hasMoveBoost())
			change++;
		int r = currLoc.getRow() + 1;
		int c = currLoc.getCol() + 1;
		while (r <= currLoc.getRow() + change) {
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && !board.isSameFlagZone(super.getSide(), r, c)) {
				if (board.getObject(r, c) == null || board.getObject(r, c).type().equals("powerup")
						|| board.getObject(r, c).type().equals("flag")) {
					poss.add(new Location(r, c));
				} else if ((board.isSameSide(super.getSide(), new Location(r, c)) || super.isReversed())
						&& board.getObject(r, c).type().equals("piece")) {
					Piece p = board.getPiece(r, c);
					if (!p.getSide().equals(super.getSide()) && !p.isInvincible()) {
						poss.add(new Location(r, c));
					} else if (!super.hasHighJump() || super.hasHighWall()) {
						break;
					}
				} else if (board.getObject(r, c).type().equals("piece")
						&& (!super.hasHighJump() || super.hasHighWall())) {
					break;
				}
			}

			r++;
			c++;
		}

		r = currLoc.getRow() - 1;
		c = currLoc.getCol() - 1;
		while (r >= currLoc.getRow() - change) {
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && !board.isSameFlagZone(super.getSide(), r, c)) {
				if (board.getObject(r, c) == null || board.getObject(r, c).type().equals("powerup")
						|| board.getObject(r, c).type().equals("flag")) {
					poss.add(new Location(r, c));
				} else if ((board.isSameSide(super.getSide(), new Location(r, c)) || super.isReversed())
						&& board.getObject(r, c).type().equals("piece")) {
					Piece p = board.getPiece(r, c);
					if (!p.getSide().equals(super.getSide()) && !p.isInvincible()) {
						poss.add(new Location(r, c));
					} else if (!super.hasHighJump() || super.hasHighWall()) {
						break;
					}
				} else if (board.getObject(r, c).type().equals("piece")
						&& (!super.hasHighJump() || super.hasHighWall())) {
					break;
				}
			}

			r--;
			c--;
		}

		r = currLoc.getRow() + 1;
		c = currLoc.getCol() - 1;
		while (r <= currLoc.getRow() + change) {
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && !board.isSameFlagZone(super.getSide(), r, c)) {
				if (board.getObject(r, c) == null || board.getObject(r, c).type().equals("powerup")
						|| board.getObject(r, c).type().equals("flag")) {
					poss.add(new Location(r, c));
				} else if ((board.isSameSide(super.getSide(), new Location(r, c)) || super.isReversed())
						&& board.getObject(r, c).type().equals("piece")) {
					Piece p = board.getPiece(r, c);
					if (!p.getSide().equals(super.getSide()) && !p.isInvincible()) {
						poss.add(new Location(r, c));
					} else if (!super.hasHighJump() || super.hasHighWall()) {
						break;
					}
				} else if (board.getObject(r, c).type().equals("piece")
						&& (!super.hasHighJump() || super.hasHighWall())) {
					break;
				}
			}

			r++;
			c--;
		}

		r = currLoc.getRow() - 1;
		c = currLoc.getCol() + 1;
		while (r >= currLoc.getRow() - change) {
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && !board.isSameFlagZone(super.getSide(), r, c)) {
				if (board.getObject(r, c) == null || board.getObject(r, c).type().equals("powerup")
						|| board.getObject(r, c).type().equals("flag")) {
					poss.add(new Location(r, c));
				} else if ((board.isSameSide(super.getSide(), new Location(r, c)) || super.isReversed())
						&& board.getObject(r, c).type().equals("piece")) {
					Piece p = board.getPiece(r, c);
					if (!p.getSide().equals(super.getSide()) && !p.isInvincible()) {
						poss.add(new Location(r, c));
					} else if (!super.hasHighJump() || super.hasHighWall()) {
						break;
					}
				} else if (board.getObject(r, c).type().equals("piece")
						&& (!super.hasHighJump() || super.hasHighWall())) {
					break;
				}
			}

			r--;
			c++;
		}

		return poss;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return "diagonal"
	 */
	@Override
	public String pieceType() {
		return "diagonal";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return "Ninja"
	 */
	@Override
	public String gameName() {
		return "Ninja";
	}

}
