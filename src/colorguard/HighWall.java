package colorguard;

import java.util.LinkedList;
import java.util.Set;

/**
 * HighWall class prevents all enemies from jumping over defenses, excluding Teleporter
 * 
 * @author atripathi499
 */
public class HighWall extends Powerup
{
	private static final long serialVersionUID = 44294731041297415L;

	/**
	 * Constructor for HighWall
	 * @param x
	 * @param y
	 */
	public HighWall(int x, int y)
	{
		super(x, y, 6);
	}
	
	/**
	 * No effect for HighWall
	 * @param p Piece
	 * @return false to indicate calling of other effect method
	 */
	public boolean effect(Piece p)
	{
		return false;
	}
	
	/**
	 * Prevents all opposing pieces from jumping over defenders
	 * @param opponentPieces
	 * @return true if success
	 */
	public boolean effect(LinkedList<Piece> opponentPieces)
	{
		for (Piece p : opponentPieces)
		{
			if (p == null)
				return false;
			p.changeHasHighWall();
		}
		return true;
	}
	
	/**
	 * @return "highwall"
	 */
	@Override
	public String powerupType()
	{
		return "highwall";
	}
	
	/**
	 * @return "High Gravity"
	 */
	@Override
	public String gameName()
	{
		return "High Gravity";
	}
	
	@Override
	public void deactivate(Piece p)
	{
		if (p.hasHighWall())
			p.changeHasHighWall();
	}
}
