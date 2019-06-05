package colorguard;

/**
 * RoleReversal powerup allows a Piece to tag an opponent on the other side.
 * RoleReversal is not like Invincibility as it does not make you invincible on the other side.
 * 
 * @author atripathi499
 */
public class RoleReversal extends Powerup
{
	private static final long serialVersionUID = -817757893292916417L;

	/**
	 * Constructor for RoleReversal
	 * @param x
	 * @param y
	 */
	public RoleReversal(int x, int y)
	{
		super(x, y, 3);
	}
	
	/**
	 * Gives Piece p ability to tag opponents on the other side
	 * @param p Piece
	 * @return false if effect fails
	 */
	public boolean effect(Piece p)
	{
		if (p == null || p.hasPowerup())
			return false;
		p.changeisReversed();
		return true;
	}
	
	/**
	 * @return "rolereversal" for specific type of powerup
	 */
	public String powerupType()
	{
		return "rolereversal";
	}
	
	/**
	 * @return "Role Reversal"
	 */
	@Override
	public String gameName()
	{
		return "Role Reversal";
	}
	
	@Override
	public void deactivate(Piece p)
	{
		if (p.isReversed())
			p.changeisReversed();
	}
}
