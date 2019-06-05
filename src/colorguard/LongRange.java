
package colorguard;

import java.util.HashSet;

/**
 * LongRange is a type of Piece that can move 6 spots vertically.
 * It can move 2 spots horizontally on its own side, and 1 spot horizontally on enemy territory
 * 
 * @author amaytripathi
 */
public class LongRange extends Piece 
{
	private static final long serialVersionUID = 2846616295477334431L;

	/**
	 * Creates a new LongRange
	 * 
	 * @param currentLocation Location of the Piece
	 * @param side side the LongRange is on
	 */
	public LongRange(Location currentLocation, String side) 
	{
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
		
		int change = 6;
		int sideChange = 1;
		if( board.isSameSide(super.getSide(), super.getLocation()))
		{
			sideChange = 2;
		}
		if (super.hasMoveBoost())
		{
			change++;
			sideChange++;
		}
			
		int r = currLoc.getRow() + 1;
		int c = currLoc.getCol();
		while (r <= currLoc.getRow() + change)
		{
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && 
					!board.isSameFlagZone(super.getSide(), r, c))
			{
				if (board.getObject(r, c) == null || board.getObject(r, c).type().equals("powerup") || board.getObject(r, c).type().equals("flag"))
				{
					poss.add(new Location(r, c));
				}
				else if ((board.isSameSide(super.getSide(), new Location(r, c)) || super.isReversed()) 
						&& board.getObject(r, c).type().equals("piece"))
				{
					Piece p = board.getPiece(r, c);
					if (!p.getSide().equals(super.getSide()) && !p.isInvincible())
					{
						poss.add(new Location(r, c));
					}
					else if (!super.hasHighJump() || super.hasHighWall())
					{
						break;
					}
				}
				else if (board.getObject(r, c).type().equals("piece") && (!super.hasHighJump() || super.hasHighWall()))
				{
					break;
				}
			}
			
			r++;
		}
		
		r = currLoc.getRow() - 1;
		c = currLoc.getCol();
		while (r >= currLoc.getRow() - change)
		{
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && 
					!board.isSameFlagZone(super.getSide(), r, c))
			{
				if (board.getObject(r, c) == null || board.getObject(r, c).type().equals("powerup") || board.getObject(r, c).type().equals("flag"))
				{
					poss.add(new Location(r, c));
				}
				else if ((board.isSameSide(super.getSide(), new Location(r, c)) || super.isReversed()) 
						&& board.getObject(r, c).type().equals("piece"))
				{
					Piece p = board.getPiece(r, c);
					if (!p.getSide().equals(super.getSide()) && !p.isInvincible())
					{
						poss.add(new Location(r, c));
					}
					else if (!super.hasHighJump() || super.hasHighWall())
					{
						break;
					}
				}
				else if (board.getObject(r, c).type().equals("piece") && (!super.hasHighJump() || super.hasHighWall()))
				{
					break;
				}
			}
			
			r--;
		}
		
		r = currLoc.getRow();
		c = currLoc.getCol() - 1;
		while (c >= currLoc.getCol() - sideChange )
		{
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && 
					!board.isSameFlagZone(super.getSide(), r, c))
			{
				if (board.getObject(r, c) == null || board.getObject(r, c).type().equals("powerup") || board.getObject(r, c).type().equals("flag"))
				{
					poss.add(new Location(r, c));
				}
				else if ((board.isSameSide(super.getSide(), new Location(r, c)) || super.isReversed()) 
						&& board.getObject(r, c).type().equals("piece"))
				{
					Piece p = board.getPiece(r, c);
					if (!p.getSide().equals(super.getSide()) && !p.isInvincible())
					{
						poss.add(new Location(r, c));
					}
					else if (!super.hasHighJump() || super.hasHighWall())
					{
						break;
					}
				}
				else if (board.getObject(r, c).type().equals("piece") && (!super.hasHighJump() || super.hasHighWall()))
				{
					break;
				}
			}
			c--;
		}
		
		r = currLoc.getRow();
		c = currLoc.getCol() + 1;
		while (c <= currLoc.getCol() + sideChange)
		{
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && 
					!board.isSameFlagZone(super.getSide(), r, c))
			{
				if (board.getObject(r, c) == null || board.getObject(r, c).type().equals("powerup") || board.getObject(r, c).type().equals("flag"))
				{
					poss.add(new Location(r, c));
				}
				else if ((board.isSameSide(super.getSide(), new Location(r, c)) || super.isReversed()) 
						&& board.getObject(r, c).type().equals("piece"))
				{
					Piece p = board.getPiece(r, c);
					if (!p.getSide().equals(super.getSide()) && !p.isInvincible())
					{
						poss.add(new Location(r, c));
					}
					else if (!super.hasHighJump() || super.hasHighWall())
					{
						break;
					}
				}
				else if (board.getObject(r, c).type().equals("piece") && (!super.hasHighJump() || super.hasHighWall()))
				{
					break;
				}
			}
			c++;
		}
		
		return poss;
	}

	/**
	 * @return "longrange"
	 */
	@Override
	public String pieceType() 
	{
		return "longrange";
	}
	
	/**
	 * @return "Dasher"
	 */
	@Override
	public String gameName()
	{
		return "Dasher";
	}

}