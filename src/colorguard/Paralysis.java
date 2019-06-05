package colorguard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Paralysis class that prevents all enemies in a 7 by 7 around a given Piece for 2 turns
 * 
 * @author amaytripathi
 */
public class Paralysis extends Powerup
{
	private static final long serialVersionUID = -5860149066004411087L;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public Paralysis(int x, int y)
	{
		super(x, y, 4);
	}
	
	/**
	 * Has no impact for Paralysis class
	 * @param p Piece
	 * @return false to indicate to call other effect method
	 */
	public boolean effect(Piece p)
	{
		return false;
	}
	
	/**
	 * Gets all enemy pieces in a 7 by 7 around given Piece p
	 * @param p Piece
	 * @return list of all enemy pieces in range
	 */
	public Queue<Piece> getSurrounding(Piece p, GameBoard g)
	{
		Queue<Piece> list = new LinkedList<Piece>();
		
		for (int x = p.getLocation().getRow() - 3; x <= p.getLocation().getRow() + 3; x++)
		{
			for (int y = p.getLocation().getCol() - 3; y <= p.getLocation().getCol() + 3; y++)
			{
				GameObject obj = g.getObject(x, y);
				if (obj != null && obj.type().equals("piece"))
					list.add((Piece)obj);
			}
		}
		
		return list;
	}
	
	/**
	 * Paralyses given Piece p for 
	 * @param p Piece
	 * @return true if success
	 */
	public boolean effectEnemy(Piece p)
	{
		if (p == null || p.isParalysed())
			return false;
		p.changeIsParalysed();
		return true;
	}
	
	/**
	 * @return "paralysis"
	 */
	@Override
	public String powerupType()
	{
		return "paralysis";
	}
	
	/**
	 * @return "Paralyzer"
	 */
	@Override
	public String gameName()
	{
		return "Paralyzer";
	}
	
	@Override
	public void deactivate(Piece p)
	{
		if (p.isParalysed())
			p.changeIsParalysed();;
	}
}
