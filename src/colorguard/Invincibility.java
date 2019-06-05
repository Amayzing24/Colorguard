package colorguard;

/**
 * Invincibility is a type of Powerup that makes a Piece immune to tags for 4 turns
 * 
 * @author amaytripathi
 */
public class Invincibility extends Powerup 
{
	private static final long serialVersionUID = -5051285932879553341L;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public Invincibility(int x, int y)
	{
		super(x, y, 4);
	}
	
	/**
	 * Makes given piece invincible
	 * @param p Piece
	 * @return false to indicate to call other effect method
	 */
	public boolean effect(Piece p)
	{
		if (p == null || p.hasPowerup())
			return false;
		p.changeIsInvincible();
		return true;
	}
	
	/**
	 * @return "invincibility"
	 */
	@Override
	public String powerupType()
	{
		return "invincibility";
	}
	
	/**
	 * @return "Invincibility Star"
	 */
	@Override
	public String gameName()
	{
		return "Invincibility Star";
	}
	
	@Override
	public void deactivate(Piece p)
	{
		if (p.isInvincible())
			p.changeIsInvincible();
	}
}
