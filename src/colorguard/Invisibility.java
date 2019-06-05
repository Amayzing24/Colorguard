package colorguard;

/**
 * Invisibility class that makes a Piece invisible for some turns
 * 
 * @author atripathi499
 */
public class Invisibility extends Powerup
{
	private static final long serialVersionUID = -4501210444966176949L;

	/**
	 * Constructor for Swapper
	 * @param x
	 * @param y
	 */
	public Invisibility(int x, int y)
	{
		super(x, y, 4);
	}
	
	/**
	 * Makes Piece p invisible for 4 turns
	 * @param p Piece
	 * @return false if does not work
	 */
	public boolean effect(Piece p)
	{
		if (p == null || p.hasPowerup())
			return false;
		p.changeHasInvisibility();
		return true;
	}
	
	/**
	 * @return "invisibility"
	 */
	@Override
	public String powerupType()
	{
		return "invisibility";
	}
	
	/**
	 * @return "Invisibility"
	 */
	@Override
	public String gameName()
	{
		return "Invisibility";
	}
	
	@Override
	public void deactivate(Piece p)
	{
		if (p.hasInvisibility())
			p.changeHasInvisibility();
	}
}
