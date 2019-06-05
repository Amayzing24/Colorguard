package colorguard;

/**
 * Throw class enables a Piece to throw the flag to a team mate in the enemy starting side.
 * 
 * @author atripathi499
 */
public class Throw extends Powerup
{
	/**
	 * Constructor for Throw
	 * @param x
	 * @param y
	 */
	public Throw(int x, int y)
	{
		super(x, y, 0);
	}
	
	/**
	 * Gives Piece p ability to throw flag
	 * @param p Piece
	 * @return false if effect fails
	 */
	public boolean effect(Piece p)
	{
		if (p == null || p.hasPowerup())
			return false;
		p.changeCanThrowFlag();
		return true;
	}
	
	/**
	 * @return 0.125 rarity for Throw powerup
	 */
	public double rarity()
	{
		return 0.125;
	}
	
	/**
	 * @return "throw" for specific type of powerup
	 */
	public String powerupType()
	{
		return "throw";
	}
	
	/**
	 * @return "QuarterBack"
	 */
	public String gameName()
	{
		return "Quarterback";
	}
	
	public void deactivate(Piece p)
	{
		return;
	}
}
