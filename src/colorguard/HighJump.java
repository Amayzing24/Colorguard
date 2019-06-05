package colorguard;

/**
 * HighJump class enables a Piece to jump over defenses for 3 turns
 * 
 * @author atripathi499
 */
public class HighJump extends Powerup
{
	private static final long serialVersionUID = 4287203922937351296L;

	/**
	 * Constructor for HighJump
	 * @param x
	 * @param y
	 */
	public HighJump(int x, int y)
	{
		super(x, y, 3);
	}
	
	/**
	 * Gives Piece p ability to jump over defenses
	 * @param p Piece
	 * @return false if effect fails
	 */
	public boolean effect(Piece p)
	{
		if (p == null || p.hasPowerup())
		{
			return false;
		}
		p.changeHasHighJump();
		return true;
	}
	
	/**
	 * @return "highjump"
	 */
	@Override
	public String powerupType()
	{
		return "highjump";
	}
	
	/**
	 * @return "Levitate"
	 */
	@Override
	public String gameName()
	{
		return "Air Jordan";
	}

	@Override
	public void deactivate(Piece p)
	{
		if (p.hasHighJump())
			p.changeHasHighJump();
	}
}
