package colorguard;

/**
 * Powerup inherits GameObject and serves as a superclass to all powerups in ther game.
 * Powerup defines its type, but its effect on a certain Piece is not yet decided.
 * @author atripathi499
 */
public abstract class Powerup extends GameObject 
{
	private static final long serialVersionUID = 2262065655548685521L;
	
	private int turnsLeft;
	
	/**
	 * Calls super constructor to assign position of object
	 * @param x coordinate of object
	 * @param y coordinate of object
	 * @param turns powerup lasts
	 */
	public Powerup(int x, int y, int turns)
	{
		super(x, y);
		turnsLeft = turns;
	}
	
	/**
	 * Returns "powerup" as type of GameObject
	 */
	public String type()
	{
		return "powerup";
	}
	
	/**
	 * @return turns left on this powerup
	 */
	public int getTurnsLeft()
	{
		return turnsLeft;
	}
	
	/**
	 * Decrements turns left on powerup by 1
	 */
	public void decrementTurns()
	{
		turnsLeft--;
	}
	
	/**
	 * Applies an effect on given Piece p
	 * @param p to be altered
	 * @return true if success, false if not
	 */
	public abstract boolean effect(Piece p);
	
	/**
	 * Gives specific type of powerup
	 * @return a String (lowercase) for the specific powerup name
	 */
	public abstract String powerupType();
	
	/**
	 * Disactivates this Powerup for given Piece p
	 */
	public abstract void deactivate(Piece p);
}
