package colorguard;

/**
 * TagRadius class gives a Piece the ability to tag enemies in a 5 by 5 square around them.
 * Pieces do not have to move in order to tag enemies with this powerup, provided they are in range.
 * 
 * @author atripathi499
 */
public class TagRadius extends Powerup
{
	/**
	 * Constructor for TagRadius
	 * @param x
	 * @param y
	 */
	public TagRadius(int x, int y)
	{
		super(x, y, 5);
	}
	
	/**
	 * Gives Piece p ability to tag opponents in a 5 by 5 square
	 * @param p Piece
	 * @return false if effect fails
	 */
	public boolean effect(Piece p)
	{
		if (p == null || p.hasPowerup())
			return false;
		p.changeHasTagBoost();
		return true;
	}
	
	/**
	 * @return 0.125 rarity for RoleReversal powerup
	 */
	public double rarity()
	{
		return 0.125;
	}
	
	/**
	 * @return "tagradius" for specific type of powerup
	 */
	public String powerupType()
	{
		return "tagradius";
	}
	
	/**
	 * @return "Tag Radius"
	 */
	@Override
	public String gameName()
	{
		return "Tag Radius";
	}
	
	@Override
	public void deactivate(Piece p)
	{
		if (p.hasTagBoost())
			p.changeHasTagBoost();
	}
}
