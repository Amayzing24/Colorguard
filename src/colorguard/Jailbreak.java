package colorguard;

import java.util.LinkedList;
import java.util.Set;

/**
 * Jailbreak is a specific powerup. Jailbreak takes a Piece out of jail.
 * @author atripathi499
 */
public class Jailbreak extends Powerup 
{
	private static final long serialVersionUID = -5684635776041666507L;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public Jailbreak(int x, int y)
	{
		super(x, y, 0);
	}
	
	/**
	 * This method has no purpose for Jailbreak
	 * @param p Piece
	 * @return false to indicate to call other effect method
	 */
	public boolean effect(Piece p)
	{
		return false;
	}
	
	/**
	 * Gets all given pieces out of all jail
	 * @param pieces to be freed
	 * @return true if success
	 */
	public boolean effect(LinkedList<Piece> pieces)
	{
		for (Piece p : pieces)
		{
			if (p == null)
				return false;
			if (!p.isInJail())
				p.changeIfJail();
		}
		return true;
	}
	
	/**
	 * @return "jailbreak"
	 */
	@Override
	public String powerupType()
	{
		return "jailbreak";
	}
	
	/**
	 * @return "Get out of jail card"
	 */
	@Override
	public String gameName()
	{
		return "Get out of jail card";
	}
	
	@Override
	public void deactivate(Piece p)
	{
		return;
	}
}
