package colorguard;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The Colorguard class stores the state of the game and changes certain objects
 * dynamically based on commands Colorguard stores the GameBoard, turns, and the
 * state of a turn
 * 
 * @author amaytripathi
 */
public class Colorguard implements Serializable {
	private static final long serialVersionUID = -8117898599660936199L;

	private GameBoard board;
	private int totalTurns, redTurns, blueTurns;
	private String currentTurn;
	private LinkedList<Piece> movedPieces;
	private String mySide;

	/**
	 * Initializes this Colorguard
	 * 
	 * @param side the side of the player using this Colorguard
	 */
	public Colorguard(String side) {
		board = new GameBoard();
		totalTurns = 0;
		redTurns = 0;
		blueTurns = 0;
		currentTurn = "NA";
		movedPieces = new LinkedList<Piece>();
		mySide = side;
	}

	/**
	 * Starts the current game Sets currentTurn to "Blue" and totalTurns to 1
	 */
	public void startGame() {
		currentTurn = "Blue";
		totalTurns++;
	}

	/**
	 * Adds a new Piece of specified type at given location
	 * 
	 * @param loc  the Location of the new Piece
	 * @param type the type of the new Piece
	 */
	public void addPiece(Location loc, String type) {
		Piece newPiece;
		if (type.equals("basicpiece"))
			newPiece = new BasicPiece(loc, mySide);
		else if (type.equals("diagonal"))
			newPiece = new Diagonal(loc, mySide);
		else if (type.equals("hopper"))
			newPiece = new Hopper(loc, mySide);
		else if (type.equals("longrange"))
			newPiece = new LongRange(loc, mySide);
		else
			newPiece = new Teleporter(loc, mySide);
		board.addPiece(newPiece);
	}

	/**
	 * Adds given Piece to the board
	 * 
	 * @param p the Piece to be added
	 */
	public void addPiece(Piece p) {
		board.addPiece(p);
	}

	/**
	 * Gets all valid starting points for this game. A Location is a valid starting
	 * point if it is in the current player's starting zone and has no object on it
	 * currently.
	 * 
	 * @return a HashSet containing all the possible starting points
	 */
	public HashSet<Location> getStartingPoints() {
		HashSet<Location> poss = new HashSet<Location>();

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 21; j++) {
				if (board.isStartingZone(mySide, new Location(i, j)) && board.getObject(i, j) == null)
					poss.add(new Location(i, j));
			}
		}
		return poss;
	}

	/**
	 * Gets all the possible landing spots for this game. A Location is a valid
	 * landing spot if it is a landing spot for the current turn and has no object
	 * on it currently.
	 * 
	 * @return a HashSet containing all possible landing spots
	 */
	public HashSet<Location> getLandingSpots() {
		HashSet<Location> poss = new HashSet<Location>();

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 21; j++) {
				if (board.isLandingZone(mySide, i, j) && board.getObject(i, j) == null)
					poss.add(new Location(i, j));
			}
		}

		return poss;
	}

	/**
	 * Gets all possible Piece selection locations for this game. A Location is a
	 * possible Piece spot if it contains a Piece on the current turn's side, has
	 * not been moved on this turn yet, and is not in jail.
	 * 
	 * @return
	 */
	public HashSet<Location> getPossiblePieces() {
		HashSet<Location> poss = new HashSet<Location>();
		LinkedList<Piece> currPieces = getCurrentTeamPieces();

		for (Piece p : currPieces) {
			if (!p.isInJail() && !isMoved(p))
				poss.add(p.getLocation());
		}

		return poss;
	}

	/**
	 * Gets the location of the flag holder for the current turn
	 * 
	 * @return the Location of the flag holder, null if not any
	 */
	public Location getFlagHolder() {
		LinkedList<Piece> pieces = getCurrentTeamPieces();
		for (Piece p : pieces) {
			if (p.getHasFlag())
				return p.getLocation();
		}
		return null;
	}

	/**
	 * Gets all possible receivers of a flag after Throw Powerup is applied A
	 * receiver must be on the opposite starting zone and of the same side as the
	 * current turn
	 * 
	 * @return a HashSet of possible throw options
	 */
	public HashSet<Location> getThrowOptions() {
		HashSet<Location> poss = new HashSet<Location>();
		LinkedList<Piece> pieces = getCurrentTeamPieces();
		for (Piece p : pieces) {
			if (board.isStartingZone(getOpposingTurn(), p.getLocation()))
				poss.add(p.getLocation());
		}
		return poss;
	}

	/**
	 * Gets all possible movement locations for the Piece at given Location
	 * 
	 * @param pieceLocation the location of the Piece to get the possible movements
	 *                      for
	 * @return a HashSet containing possible movements for Piece at pieceLocation
	 */
	public HashSet<Location> getPossibleMovements(Location pieceLocation) {
		return board.getPiece(pieceLocation.getRow(), pieceLocation.getCol()).getPossibleMovements(board);
	}

	/**
	 * Gets the Powerup for the current turn at index i
	 * 
	 * @param i the index of the powerup to get. 0 < i < 4 for a red turn and 4 < i
	 *          < 8 for a blue turn.
	 * @return the Powerup at index i
	 */
	public Powerup getPowerupAtIndex(int i) {
		if (currentTurn.equals("Blue"))
			i = i - 4;
		return getCurrentPowerups().remove(i);
	}

	/**
	 * Gets the Powerups of the current turn
	 * 
	 * @return a LinkedList containing the Powerups for current turn
	 */
	public LinkedList<Powerup> getCurrentPowerups() {
		if (currentTurn.equals("Blue"))
			return board.getBluePowerups();
		else
			return board.getRedPowerups();
	}

	/**
	 * Adds a new Powerup to the board. Calls nextPowerup to generate this Powerup
	 * randomly.
	 * 
	 * @return the Powerup that was just added.
	 */
	public Powerup addNewPowerup() {
		Powerup nextPowerup = nextPowerup();
		board.addPowerupToBoard(nextPowerup, nextPowerup.getLocation());
		return nextPowerup;
	}

	/**
	 * Activates a jailbreak for the current turn
	 * 
	 * @return a Queue containing all Pieces freed during this jail break
	 */
	public Queue<Piece> jailbreak() {
		Queue<Piece> freePieces;
		if (mySide().equals("Blue"))
			freePieces = board.blueJailBreak();
		else
			freePieces = board.redJailBreak();
		return freePieces;
	}

	/**
	 * Moves Piece and given location to the new Location. If a Piece existed at old
	 * Location, then sends that Piece to jail. If a Flag existed at old Location,
	 * then gives the Piece that flag. If a Powerup existed at old Location, adds
	 * the Powerup to the current turn's powerups.
	 * 
	 * @param pieceLocation the Location of the Piece to move
	 * @param newLocation   the new Location of the Piece
	 * @return a String containing a description of what happened in the movement
	 */
	public String movePiece(Location pieceLocation, Location newLocation) {
		GameObject oldObject = board.getObject(newLocation.getRow(), newLocation.getCol());
		Piece piece = board.getPiece(pieceLocation.getRow(), pieceLocation.getCol());
		System.out.println(piece.gameName());
		board.movePiece(piece, newLocation);
		movedPieces.add(piece);
		String message = piece.gameName() + " moved to " + newLocation + ". ";

		if (oldObject != null && !oldObject.getLocation().equals(pieceLocation)) {
			if (oldObject.type().equals("piece")) {
				Piece oldPiece = (Piece) oldObject;
				if (oldPiece.isClone())
				{
					if (oldPiece.getSide().equals("Blue"))
					{
						board.getBluePieces().remove(oldPiece);
					}
					else
					{
						board.getRedPieces().remove(oldPiece);
					}
					board.clearSpot(oldPiece.getLocation().getRow(), oldPiece.getLocation().getCol());
				}
				else
				{
					board.sendPieceToJail(oldPiece);
					message += oldPiece.gameName() + " sent to jail.";
				}
			} else if (oldObject.type().equals("flag")) {
				piece.giveFlag((Flag) oldObject);
				message += piece.gameName() + " has flag.";
			} else {
				Powerup powerup = (Powerup) oldObject;
				board.addPowerup(powerup, currentTurn);
				board.getPowerups().remove(oldObject);
				message += currentTurn + " team obtained a " + powerup.gameName() + ". ";
			}
		}

		if (piece.hasTagBoost()) {
			for (int r = newLocation.getRow() - 1; r <= newLocation.getRow() + 1; r++) {
				for (int c = newLocation.getCol() - 1; c <= newLocation.getCol(); c++) {
					if (r != newLocation.getRow() && c != newLocation.getCol()) {
						Piece oldPiece = board.getPiece(r, c);
						if (oldPiece != null && !oldPiece.getSide().equals(currentTurn)) {
							board.sendPieceToJail(oldPiece);
							message += oldPiece.gameName() + " sent to jail.";
						}
					}
				}
			}
		}

		return message;
	}

	/**
	 * @return a LinkedList containing moved Pieces on the current turn
	 */
	public LinkedList<Piece> getMovedPieces() {
		return movedPieces;
	}

	/**
	 * @return the side of the player owning this game
	 */
	public String mySide() {
		return mySide;
	}

	/**
	 * Sets the side of the player owning this game to side passed
	 * 
	 * @param s the new Side of the player owning this game
	 */
	public void setMySide(String s) {
		mySide = s;
	}

	/**
	 * @return the GameBoard of this game
	 */
	public GameBoard getBoard() {
		return board;
	}

	/**
	 * @return a LinkedList containing the current turn's Pieces
	 */
	public LinkedList<Piece> getCurrentTeamPieces() {
		if (currentTurn.equals("Red"))
			return board.getRedPieces();
		else
			return board.getBluePieces();
	}

	/**
	 * Adds given Piece to movedPieces
	 * 
	 * @param p Piece to be added to movedPieces
	 */
	public void pieceMoved(Piece p) {
		movedPieces.add(p);
	}

	/**
	 * Tells if given Piece has been moved or not on this turn
	 * 
	 * @param p Piece to check
	 * @return true if it has been moved, false if not
	 */
	public boolean isMoved(Piece p) {
		return movedPieces.contains(p);
	}

	/**
	 * Updates this Colorguard to the next turn. Increments and updates turn values.
	 * Deactivates Powerups if needed, decrements all Powerups.
	 */
	public void nextTurn() {
		movedPieces.clear();
		totalTurns++;
		if (currentTurn.equals("Blue")) {
			blueTurns++;
			currentTurn = "Red";
		} else {
			redTurns++;
			currentTurn = "Blue";
		}
		LinkedList<Piece> pieces = new LinkedList<Piece>();
		pieces.addAll(board.getBluePieces());
		pieces.addAll(board.getRedPieces());
		Piece clone = null;
		for (Piece p : pieces) {
			if (p.hasPowerup()) {
				p.getCurrentPowerup().decrementTurns();
				if (p.getCurrentPowerup().getTurnsLeft() == 0) {
					if (p.isClone()) {
						clone = p;
					} else {
						p.getCurrentPowerup().deactivate(p);
						p.deactivatePowerup();
					}
				}
			}
		}
		if (clone != null)
		{
			if (clone.getSide().equals("Blue"))
			{
				board.getBluePieces().remove(clone);
			}
			else
			{
				board.getRedPieces().remove(clone);
			}
			board.clearSpot(clone.getLocation().getRow(), clone.getLocation().getCol());
		}
	}

	/**
	 * Tells if the current game is over or not. A game is over when a flag has
	 * reached the opposite starting zone.
	 * 
	 * @return true if game is over, false if not
	 */
	public boolean gameOver() {
		if (board.getBlueFlag().getLocation().getRow() <= 9)
			return true;
		if (board.getRedFlag().getLocation().getRow() >= 10)
			return true;
		return false;
	}

	/**
	 * @return the current turn number of this game
	 */
	public int getTurns() {
		return totalTurns;
	}

	/**
	 * @return the number of red turns that have passed
	 */
	public int getRedTurns() {
		return redTurns;
	}

	/**
	 * @return the number of blue turns that have passed
	 */
	public int getBlueTurns() {
		return blueTurns;
	}

	/**
	 * @return a String representing the current turn ("Blue" or "Red")
	 */
	public String getCurrentTurn() {
		return currentTurn;
	}

	/**
	 * @return a String representing the opposite turn ("Blue" or "Red")
	 */
	public String getOpposingTurn() {
		if (currentTurn.equals("Red"))
			return "Blue";
		else
			return "Red";
	}

	/**
	 * Uses the Swapper Powerup for Pieces at given Locations
	 * 
	 * @param swap the Swapper Powerup to use
	 * @param loc1 Location of Piece 1
	 * @param loc2 Location of Piece 2
	 * @return a String describing the Swap
	 */
	public String useSwapper(Swapper swap, Location loc1, Location loc2) {
		Piece p1 = board.getPiece(loc1.getRow(), loc1.getCol());
		Piece p2 = board.getPiece(loc2.getRow(), loc2.getCol());
		board.swapLocations(p1, p2);
		swap.effect(p1, p2);
		getCurrentPowerups().remove(swap);
		return p1.gameName() + " and " + p2.gameName() + " swapped";
	}

	/**
	 * Uses the Clone Powerup for Piece at pieceLocation and adds it as
	 * cloneLocation
	 * 
	 * @param clone         the Clone Powerup to use
	 * @param pieceLocation Location of the Piece being cloned
	 * @param cloneLocation Location of the Clone
	 * @return a String containing description of cloning
	 */
	public String useClone(Clone clone, Location pieceLocation, Location cloneLocation) {
		Piece origPiece = board.getPiece(pieceLocation.getRow(), pieceLocation.getCol());
		Piece clonePiece = clone.getClone(origPiece, cloneLocation);
		clonePiece.activatePowerup(clone);
		board.addPiece(clonePiece);

		getCurrentPowerups().remove(clone);
		return origPiece.gameName() + " clone placed at " + cloneLocation;
	}

	/**
	 * Uses the HighWall Powerup on team opposite of current turn
	 * 
	 * @param highwall the HighWall Powerup to use
	 * @return a a String containing description of action
	 */
	public String useHighWall(HighWall highwall) {
		LinkedList<Piece> enemies;
		if (currentTurn.equals("Red"))
			enemies = board.getBluePieces();
		else
			enemies = board.getRedPieces();
		highwall.effect(enemies);
		for (Piece piece : enemies) {
			piece.activatePowerup(highwall);
		}
		getCurrentPowerups().remove(highwall);
		return "High wall applied to " + getOpposingTurn() + " team";
	}

	/**
	 * Uses the Paralysis Powerup for enemies surrounding Piece at pieceLocation
	 * 
	 * @param paralysis     the Paralysis Powerup to use
	 * @param pieceLocation the Location of the Piece to paralyze enemies around
	 * @return a String containing a description of all the Pieces paralyzed
	 */
	public String useParalysis(Paralysis paralysis, Location pieceLocation) {
		Piece target = board.getPiece(pieceLocation.getRow(), pieceLocation.getCol());
		String message = "Paralysis applied around " + target.gameName() + ". ";

		Queue<Piece> enemies = paralysis.getSurrounding(target, board);
		for (Piece p : enemies) {
			paralysis.effectEnemy(p);
			p.activatePowerup(paralysis);
			message += p.gameName() + " paralyzed at " + p.getLocation() + ".";
		}

		getCurrentPowerups().remove(paralysis);
		return message;
	}

	/**
	 * Uses the Throw Powerup, transferring Flag from Piece at old Location to Piece
	 * at new Location
	 * 
	 * @param powerup the Throw Powerup to use
	 * @param oldLoc  Location of the Piece with the Flag currently
	 * @param newLoc  Location of the Piece that will be thrown the Flag
	 * @return a String containing description of the throw
	 */
	public String useThrow(Throw powerup, Location oldLoc, Location newLoc) {
		Piece thrower = board.getPiece(oldLoc.getRow(), oldLoc.getCol());
		Piece reciever = board.getPiece(newLoc.getRow(), newLoc.getCol());

		thrower.removeFlag();
		if (reciever.getSide().equals("Red"))
			reciever.giveFlag(board.getRedFlag());
		else
			reciever.giveFlag(board.getBlueFlag());

		return thrower.gameName() + " tossed the flag to " + reciever.gameName();
	}

	/**
	 * Uses given Powerup on Piece at pieceLocation
	 * 
	 * @param powerup       the Powerup to use
	 * @param pieceLocation Location of the Piece to use the Powerup on
	 * @return a String containing description of the Powerup application
	 */
	public String usePowerup(Powerup powerup, Location pieceLocation) {
		Piece target = board.getPiece(pieceLocation.getRow(), pieceLocation.getCol());
		powerup.effect(target);
		target.activatePowerup(powerup);
		getCurrentPowerups().remove(powerup);
		return powerup.gameName() + " used on " + target.gameName() + " at " + pieceLocation;
	}

	/**
	 * Generates a random Powerup at a random Location Each Powerup has a certain
	 * chance, many are different
	 * 
	 * @return a randomly generated Powerup
	 */
	public Powerup nextPowerup() {
		int randRow = (int) (Math.random() * 6) + 7;
		int randCol = (int) (Math.random() * 21);
		while (board.getObject(randRow, randCol) != null) {
			randRow = (int) (Math.random() * 6) + 7;
			randCol = (int) (Math.random() * 21);
		}

		double r = Math.random() * 105;
		if (r < 12.5)
			return new Jailbreak(randRow, randCol);
		else if (r < 25)
			return new MoveBoost(randRow, randCol);
		else if (r < 37.5)
			return new Swapper(randRow, randCol);
		else if (r < 50)
			return new HighWall(randRow, randCol);
		else if (r < 60)
			return new RoleReversal(randRow, randCol);
		else if (r < 70)
			return new TagRadius(randRow, randCol);
		else if (r < 80)
			return new HighJump(randRow, randCol);
		else if (r < 85)
			return new Invincibility(randRow, randCol);
		else if (r < 90)
			return new Invisibility(randRow, randCol);
		else if (r < 95)
			return new Clone(randRow, randCol);
		else if (r < 100)
			return new Paralysis(randRow, randCol);
		else
			return new Clone(randRow, randCol);
	}
}
