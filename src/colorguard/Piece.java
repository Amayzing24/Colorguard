package colorguard;

import java.util.HashSet;

/**
 * This class is the general abstract Piece class where it holds all the generic characteristics
 * of a piece. This includes boolean variable for all the variables keeping track if a piece has a 
 * powerup or not. There is also a boolean 2d array that is kept track of in order to know if a move 
 * is valid. This grid is updated every time the piece is moved to a certain location.
 * @author Shri
 *
 */
public abstract class Piece extends GameObject
{
	private static final long serialVersionUID = 4645780881234927464L;
	
	private boolean isJailed;
	private boolean locInFlagZone;
	private boolean isInvincible;
	private boolean isReversed;
	private boolean hasMoveBoost;
	private boolean hasTagBoost;
	private boolean hasInvisibility;
	private boolean canThrowFlag;
	private boolean hasHighJump;
	private boolean hasHighWall;
	private boolean isParalysed;
	private boolean isClone;
	private boolean hasPowerup;
	private Powerup currentPowerup;
	private boolean hasFlag;
	private Flag flag;
	private String side;
	
	/**
	 * the constructor initializes all the powerups to false.
	 * Initializes the boolean grid which will 
	 * @param currentLocation
	 * 	the Location of the piece
	 */
	public Piece( Location currentLocation, String side )
	{
		super(currentLocation);
		isJailed  = false;
		locInFlagZone = false;
		isInvincible = false;
		isReversed = false;
		hasMoveBoost = false;
		hasTagBoost = false;
		hasInvisibility = false;
		canThrowFlag = false;
		hasHighJump = false;
		hasHighWall = false;
		isParalysed = false;
		isClone = false;	
		hasPowerup = false;
		currentPowerup = null;
		this.side = side;
		hasFlag = false;
		flag = null;
	}
	
	/**
	 * @return "piece" for type of GameObject
	 */
	public String type()
	{
		return "piece";
	}
	
	/**
	 * The method that updates the boolean 2d array of possible moves
	 * @param board
	 * 	GameBoard object holding the states of all Pieces in the game
	 * @return a HashSet containing all possible movement Locations for this Piece
	 */
	public abstract HashSet<Location> getPossibleMovements(GameBoard board);
	
	/**
	 * @return the type of this Piece
	 */
	public abstract String pieceType();
	
	/**
	 * @return the game name used for the front end for this Piece
	 */
	public abstract String gameName();
	
	/**
	 * 
	 * @param nextLoc
	 * 	the location where the piece has to move
	 * @param
	 * 	GameBoard of current game
	 * @return true or false if the the move has happened
	 */
	public void moveTo( Location nextLoc )
	{
		super.moveTo(nextLoc);
		// boolGrid = getMovementGrid(g);
		if (hasFlag)
			flag.moveTo(nextLoc);			
	}
	
	/**
	 * 
	 * @return locInFlagZone
	 * 	returns the boolean variable that keeps of the locInFlagZone powerup
	 */
	public boolean isInFlagZone()
	{
		return locInFlagZone;
	}
	
	/**
	 * flips the boolean value of the locInFlagZone powerup
	 */
	public void changeIfFlagZone()
	{
		locInFlagZone = !locInFlagZone;
	}
	
	/**
	 * 
	 * @return isJailed
	 * 	returns the boolean variable that keeps of the isJailed powerup
	 */
	public boolean isInJail()
	{
		return isJailed;
	}
	
	/**
	 * flips the boolean value of the isJailed powerup
	 */
	public void changeIfJail()
	{
		isJailed = !isJailed;
	}

	/**
	 * 
	 * @return isInvincible
	 * 	returns the boolean variable that keeps of the isInvincible powerup
	 */
	public boolean isInvincible()
	{
		return isInvincible;
	}
	
	/**
	 * flips the boolean value of the isInvincible powerup
	 */
	public void changeIsInvincible()
	{
		isInvincible = !isInvincible;
	}
	
	/**
	 * 
	 * @return isReversed
	 * 	returns the boolean variable that keeps of the isReversed powerup
	 */
	public boolean isReversed()
	{
		return isReversed;
	}
	
	/**
	 * flips the boolean value of the isReversed powerup
	 */
	public void changeisReversed()
	{
		isReversed = !isReversed;
	}
	
	/**
	 * 
	 * @return hasMoveBoost
	 * 	returns the boolean variable that keeps of the hasMoveBoost powerup
	 */
	public boolean hasMoveBoost()
	{
		return hasMoveBoost;
	}
	
	/**
	 * flips the boolean value of the hasMoveBoost powerup
	 */
	public void changeHasMoveBoost()
	{
		hasMoveBoost = !hasMoveBoost;
	}
	
	/**
	 * 
	 * @return hasTagBoost
	 * 	returns the boolean variable that keeps of the hasTagBoost powerup
	 */
	public boolean hasTagBoost()
	{
		return hasTagBoost;
	}
	
	/**
	 * flips the boolean value of the hasTagBoost powerup
	 */
	public void changeHasTagBoost()
	{
		hasTagBoost = !hasTagBoost;
	}
	
	/**
	 * 
	 * @return hasInvisibility
	 * 	returns the boolean variable that keeps of the hasInvisibility powerup
	 */
	public boolean hasInvisibility()
	{
		return hasInvisibility;
	}
	
	/**
	 * flips the boolean value of the hasInvisibility powerup
	 */
	public void changeHasInvisibility()
	{
		hasInvisibility = !hasInvisibility;
	}
	
	/**
	 * 
	 * @return canThrowFlag
	 * 	returns the boolean variable that keeps of the canThrowFlag powerup
	 */
	public boolean canThrowFlag()
	{
		return canThrowFlag;
	}
	
	/**
	 * flips the boolean value of the canThrowFlag powerup
	 */
	public void changeCanThrowFlag()
	{
		canThrowFlag = !canThrowFlag;
	}
	
	/**
	 * 
	 * @return hasHighJump
	 * 	returns the boolean variable that keeps of the hasHighJump powerup
	 */
	public boolean hasHighJump()
	{
		return hasHighJump;
	}
	
	/**
	 * flips the boolean value of the hasHighJump powerup
	 */
	public void changeHasHighJump()
	{
		hasHighJump = !hasHighJump;
	}
	
	/**
	 * 
	 * @return hasHighWall
	 * 	returns the boolean variable that keeps of the hasHighWall powerup
	 */
	public boolean hasHighWall()
	{
		return hasHighWall;
	}
	
	/**
	 * flips the boolean value of the hasHighWall powerup
	 */
	public void changeHasHighWall()
	{
		hasHighWall = !hasHighWall;
	}
	
	/**
	 * 
	 * @return isParalysed
	 * 	returns the boolean variable that keeps of the isParalysed powerup
	 */
	public boolean isParalysed()
	{
		return isParalysed;
	}
	
	/**
	 * flips the boolean value of the isParalysed powerup
	 */
	public void changeIsParalysed()
	{
		isParalysed = !isParalysed;
	}
	
	/**
	 * 
	 * @return hasClone
	 * 	returns the boolean variable that keeps of the hasClone powerup
	 */
	public boolean isClone()
	{
		return isClone;
	}
	
	/**
	 * flips the boolean value of the hasClone powerup
	 */
	public void changeIsClone()
	{
		isClone = !isClone;
	}	
	
	/**
	 * 
	 * @return hasPowerup
	 * 	returns whether Piece has Powerup or not
	 */
	public boolean hasPowerup()
	{
		return hasPowerup;
	}
	
	/**
	 * Sets hasPowerup to true and sets currentPowerup to p
	 */
	public void activatePowerup(Powerup p)
	{
		hasPowerup = true;
		currentPowerup = p;
	}
	
	/**
	 * Sets hasPowerup to false
	 */
	public void deactivatePowerup()
	{
		hasPowerup = false;
		currentPowerup = null;
	}
	
	/**
	 * 
	 * @return currentPowerup
	 * Return current powerup Piece is using (null if none)
	 */
	public Powerup getCurrentPowerup()
	{
		return currentPowerup;
	}
	
	/**
	 * 
	 * @return side
	 * Return which side the Piece is on (red or blue)
	 */
	public String getSide()
	{
		return side;
	}
	
	/**
	 * @return the flag this Piece is holding, null if not any
	 */
	public Flag getFlag()
	{
		return flag;
	}
	
	/**
	 * Gives a Flag to this Piece and sets hasFlag to true
	 * 
	 * @param f Flag to give
	 */
	public void giveFlag(Flag f)
	{
		hasFlag = true;
		flag = f;
	}
	
	/**
	 * Removes the Flag from this Piece and sets hasFlag to false
	 */
	public void removeFlag()
	{
		hasFlag = false;
		flag = null;
	}
	
	/**
	 * @return if this Piece has a Flag or not
	 */
	public boolean getHasFlag()
	{
		return hasFlag;
	}

}