package colorguard;

import java.util.HashSet;

/**
 * Teleporter is a type of Piece that can move in a 5 by 5 grid around the Piece
 * 
 * @author amaytripathi
 */
public class Teleporter extends Piece
{
	/**
	 * Constructor
	 * @param currentLocation Location of the Piece
	 * @param side Side the Piece is on ("Red" or "Blue")
	 */
	public Teleporter(Location currentLocation, String side) {
		super(currentLocation, side);
	}

	@Override
	public HashSet<Location> getPossibleMovements(GameBoard board) 
	{
		HashSet<Location> poss = new HashSet<Location>();
		
		Location currLoc = super.getLocation();
		poss.add(currLoc);
		
		if (super.isParalysed() || super.isInJail())
		{
			return poss;
		}
		
		int change = 2;
		if (super.hasMoveBoost())
			change++;
		for (int r = currLoc.getRow() - change; r <= currLoc.getRow() + change; r++)
		{
			for (int c = currLoc.getCol() - change; c <= currLoc.getCol() + change; c++)
			{
				if (r == currLoc.getRow() && c == currLoc.getCol())
				{
					poss.add(new Location(r, c));
				}
				else if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && 
						!board.isSameFlagZone(super.getSide(), r, c))
				{
					if (board.getObject(r, c) == null || board.getObject(r, c).type().equals("powerup") || board.getObject(r, c).type().equals("flag"))
					{
						poss.add(new Location(r, c));
					}
					else if ((board.isSameSide(super.getSide(), new Location(r, c)) 
							|| super.isReversed()) 
							&& board.getObject(r, c).type().equals("piece"))
					{
						Piece p = board.getPiece(r, c);
						if (!p.getSide().equals(super.getSide()) && !p.isInvincible())
						{
							poss.add(new Location(r, c));
						}
					}
				}
			}
		}
		
		return poss;
	}

	/**
	 * @return "teleporter" for type of Piece
	 */
	@Override
	public String pieceType() 
	{
		return "teleporter";
	}
	
	/**
	 * @return "Wizard"
	 */
	@Override
	public String gameName()
	{
		return "Wizard";
	}
	
}
