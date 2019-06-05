
package colorguard;

import java.util.HashSet;

/**
 * BasicPiece is a type of Piece with the ability to move 2 squares in each
 * direction. Its game name is "Trooper" and it cannot jump over other pieces by
 * default.
 * 
 * @author amaytripathi
 */
public class BasicPiece extends Piece {
	private static final long serialVersionUID = -8779430624886841019L;

	/**
	 * Initializes this Piece's location and side
	 * 
	 * @param currentLocation the location of the piece
	 * @param side            the side the piece is on ("Red" or "Blue")
	 */
	public BasicPiece(Location currentLocation, String side) {
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

		int change = 2;
		if (super.hasMoveBoost()) {
			change++;
		}

		int r = currLoc.getRow() + 1;
		int c = currLoc.getCol();
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
		}

		r = currLoc.getRow() - 1;
		c = currLoc.getCol();
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
		}

		r = currLoc.getRow();
		c = currLoc.getCol() - 1;
		while (c >= currLoc.getCol() - change) {
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
			c--;
		}

		r = currLoc.getRow();
		c = currLoc.getCol() + 1;
		while (c <= currLoc.getCol() + change) {
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
			c++;
		}

		return poss;
	}

	@Override
	public String pieceType() {
		return "basicpiece";
	}

	@Override
	public String gameName() {
		return "Trooper";
	}

}