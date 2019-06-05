package colorguard;

import java.util.HashSet;

/**
 * Hopper is a type of Piece that can jump over other Pieces by default
 * Can move 3 spaces in all directions
 * Hopper can be neutralized by HighWall Powerup
 * 
 * @author amaytripathi
 *
 */
public class Hopper extends Piece
{
	private static final long serialVersionUID = -2285918592012447312L;

	/**
	 * Constructor
	 * @param currentLocation Location of the Piece
	 * @param side Side of the Piece ("Blue" or "Red") 
	 */
	public Hopper(Location currentLocation, String side)
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
		
		int change = 3;
		if (super.hasMoveBoost())
			change++;
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
				else if ((board.isSameSide(super.getSide(), new Location(r, c)) 
						|| super.isReversed()) 
						&& board.getObject(r, c).type().equals("piece"))
				{
					Piece p = board.getPiece(r, c);
					if (!p.getSide().equals(super.getSide()) && !p.isInvincible())
					{
						poss.add(new Location(r, c));
					}
					else if (super.hasHighWall())
					{
						break;
					}
				}
				else if (board.getObject(r, c).type().equals("piece") 
						&& super.hasHighWall())
				{
					break;
				}
			}
			
			r++;
		}
		
		r = currLoc.getRow() - 1;
		while (r >= currLoc.getRow() - change)
		{
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && 
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
					else if (super.hasHighWall())
					{
						break;
					}
				}
				else if (board.getObject(r, c).type().equals("piece") 
						&& super.hasHighWall())
				{
					break;
				}
			}
			
			r--;
		}
		
		r = currLoc.getRow();
		c = currLoc.getCol() + 1;
		while (c <= currLoc.getCol() + change)
		{
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && 
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
					else if (super.hasHighWall())
					{
						break;
					}
				}
				else if (board.getObject(r, c).type().equals("piece") 
						&& super.hasHighWall())
				{
					break;
				}
			}
			
			c++;
		}
		
		c = currLoc.getCol() - 1;
		while (c >= currLoc.getCol() - change)
		{
			if (!board.isOutOfBounds(r, c) && !board.isJailSpot(r, c) && 
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
					else if (super.hasHighWall())
					{
						break;
					}
				}
				else if (board.getObject(r, c).type().equals("piece") 
						&& super.hasHighWall())
				{
					break;
				}
			}
			
			c--;
		}
		
		return poss;
	}

	/**
	 * @return "hopper"
	 */
	@Override
	public String pieceType() 
	{
		return "hopper";
	}
	
	@Override
	public String gameName()
	{
		return "Horseman";
	}

}
