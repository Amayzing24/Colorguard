package colorguard;

/**
 * MoveBoost powerup that increases a Pieces existing movement ability 
 * by 1 in all directions for 5 turns.
 * 
 * @author atripathi499
 */
public class MoveBoost extends Powerup 
{
	private static final long serialVersionUID = -7481587086409445431L;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public MoveBoost(int x, int y)
	{
		super(x, y, 5);
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
		p.changeHasMoveBoost();
		return true;
	}
	
	/**
	 * @return "moveboost" for specific type of powerup
	 */
	@Override
	public String powerupType()
	{
		return "moveboost";
	}
	
	/**
	 * @return "Move Boost"
	 */
	@Override
	public String gameName()
	{
		return "Move Boost";
	}
	
	@Override
	public void deactivate(Piece p)
	{
		if (p.hasMoveBoost())
			p.changeHasMoveBoost();
	}
}
