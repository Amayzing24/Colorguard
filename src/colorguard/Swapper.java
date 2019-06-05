package colorguard;

/**
 * Swapper classes flips any 2 given pieces on the board for one side
 * 
 * @author atripathi499
 */
public class Swapper extends Powerup
{
	private static final long serialVersionUID = 2959751162934761653L;

	/**
	 * Constructor for Swapper
	 * @param x
	 * @param y
	 */
	public Swapper(int x, int y)
	{
		super(x, y, 0);
	}
	
	/**
	 * No effect for Swapper class
	 * @param p Piece
	 * @return false to indicate calling of other effect
	 */
	public boolean effect(Piece p)
	{
		return false;
	}
	
	/**
	 * Swaps given 2 pieces
	 * @param p1 first Piece
	 * @param p2 second Piece
	 * @return true if success
	 */
	public boolean effect(Piece p1, Piece p2)
	{
		if (p1 == null || p2 == null)
			return false;
		Location temp = p1.getLocation();
		p1.moveTo(p2.getLocation());
		p2.moveTo(temp);
		return true;
	}
	
	
	/**
	 * @return "swapper" for type of Powerup
	 */
	@Override
	public String powerupType()
	{
		return "swapper";
	}
	
	/**
	 * @return "Swapper"
	 */
	@Override
	public String gameName()
	{
		return "Swapper";
	}
	
	@Override
	public void deactivate(Piece p)
	{
		return;
	}
}
