package colorguard;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * GameBoard class represents the board the game is played on It stores the
 * jails, flag zones, and powerups To update the board, several methods are
 * available, mainly movePiece(Piece p)
 * 
 * @author amaytripathi
 *
 */
public class GameBoard implements Serializable {
	private static final long serialVersionUID = 7628398215863736158L;

	public final static int ROWS = 20;
	public final static int COLUMNS = 21;

	private GameObject[][] board;
	private Flag redFlag;
	private Flag blueFlag;
	private LinkedList<Piece> redJail;
	private LinkedList<Piece> blueJail;
	private LinkedList<Piece> redFlagZone;
	private LinkedList<Piece> blueFlagZone;
	private LinkedList<Piece> redPieces;
	private LinkedList<Piece> bluePieces;
	private LinkedList<Powerup> powerups;
	private LinkedList<Powerup> redPowerups;
	private LinkedList<Powerup> bluePowerups;

	/**
	 * Constructor for GameBoard Initializes board to a 20 by 21 board that is empty
	 * All the lists are declared to be an empty list
	 */
	public GameBoard() {
		board = new GameObject[20][21];
		redFlag = new Flag(new Location(1, 10));
		blueFlag = new Flag(new Location(18, 10));
		board[1][10] = redFlag;
		board[18][10] = blueFlag;
		redJail = new LinkedList<Piece>();
		blueJail = new LinkedList<Piece>();
		redFlagZone = new LinkedList<Piece>();
		blueFlagZone = new LinkedList<Piece>();
		redPieces = new LinkedList<Piece>();
		bluePieces = new LinkedList<Piece>();
		powerups = new LinkedList<Powerup>();
		redPowerups = new LinkedList<Powerup>();
		bluePowerups = new LinkedList<Powerup>();
	}

	/**
	 * @return the 2D GameObject array
	 */
	public GameObject[][] getBoard() {
		return board;
	}

	/**
	 * @return a LinkedList containing red Pieces in jail
	 */
	public LinkedList<Piece> getRedJail() {
		return redJail;
	}

	/**
	 * @return a LinkedList containing blue Pieces in jail
	 */
	public LinkedList<Piece> getBlueJail() {
		return blueJail;
	}

	/**
	 * @return a LinkedList containing red Pieces in flag zone
	 */
	public LinkedList<Piece> getRedFlagZone() {
		return redFlagZone;
	}

	/**
	 * @return a LinkedList containing blue Pieces in flag zone
	 */
	public LinkedList<Piece> getBlueFlagZone() {
		return blueFlagZone;
	}

	/**
	 * @return a LinkedList containing all red Pieces on the board
	 */
	public LinkedList<Piece> getRedPieces() {
		return redPieces;
	}

	/**
	 * @return a LinkedList containing all blue Pieces on the board
	 */
	public LinkedList<Piece> getBluePieces() {
		return bluePieces;
	}

	/**
	 * @return a LinkedList containing all Powerups on the board
	 */
	public LinkedList<Powerup> getPowerups() {
		return powerups;
	}

	/**
	 * @return a LinkedList containing all the Powerups owned by blue team
	 */
	public LinkedList<Powerup> getBluePowerups() {
		return bluePowerups;
	}

	/**
	 * @return a LinkedList containing all the Powerups owned by red team
	 */
	public LinkedList<Powerup> getRedPowerups() {
		return redPowerups;
	}

	/**
	 * @return the red Flag
	 */
	public Flag getRedFlag() {
		return redFlag;
	}

	/**
	 * @return the blue Flag
	 */
	public Flag getBlueFlag() {
		return blueFlag;
	}

	/**
	 * Gets the GameObject at given Location (r, c)
	 * 
	 * @param r row of the location
	 * @param c column of the location
	 * @return a GameObject if an object is at (r, c), null if location is out of
	 *         bounds or there is no object at (r, c)
	 */
	public GameObject getObject(int r, int c) {
		if (r >= 20 || r < 0 || c >= 21 || c < 0)
			return null;
		return board[r][c];
	}

	/**
	 * Gets the Piece at given Location (r, c)
	 * 
	 * @param r row of the location
	 * @param c column of the location
	 * @return the Piece at (r, c), null if (r, c) is out of bounds or there is a
	 *         non Piece at (r, c)
	 */
	public Piece getPiece(int r, int c) {
		if (r >= 20 || r < 0 || c >= 21 || c < 0 || board[r][c] == null || !board[r][c].type().equals("piece"))
			return null;
		return (Piece) board[r][c];
	}

	/**
	 * Checks if given Location is out of bounds
	 * 
	 * @param r row of the location
	 * @param c column of the location
	 * @return true if in bounds, false if out of bounds
	 */
	public boolean isOutOfBounds(int r, int c) {
		return r >= 20 || r < 0 || c >= 21 || c < 0;
	}

	/**
	 * Checks if given location is a jail spot
	 * 
	 * @param r row
	 * @param c column
	 * @return true if a jail location, false if not
	 */
	public boolean isJailSpot(int r, int c) {
		return ((r >= 0 && r <= 2 && (c == 19 || c == 20)) || (r >= 17 && r <= 19 && (c == 0 || c == 1)));
	}

	/**
	 * Checks if given location is part of the flag zone for given side
	 * 
	 * @param side side of the Piece
	 * @param r    row
	 * @param c    column
	 * @return true if in same flag zone, false if not
	 */
	public boolean isSameFlagZone(String side, int r, int c) {
		if (side.equals("Red") && r >= 0 && r <= 2 && c >= 9 && c <= 11)
			return true;
		if (side.equals("Blue") && r >= 17 && r <= 19 && c >= 9 && c <= 11)
			return true;
		return false;
	}

	/**
	 * Checks if the given Location loc is on a side
	 * 
	 * @param side "Red" or "Blue" to indicate the side to check
	 * @param loc  Location to check
	 * @return true if loc is on side, false otherwise
	 */
	public boolean isSameSide(String side, Location loc) {
		if (side.equals("Red") && loc.getRow() < 10) {
			return true;
		} else if (side.equals("Blue") && loc.getRow() >= 10) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if given location is on the starting zone of side
	 * 
	 * @param side "Red" or "Blue" to indicate the side to check
	 * @param loc  the Location to check
	 * @return true if starting zone, false if not
	 */
	public boolean isStartingZone(String side, Location loc) {
		if (side.equals("Red") && !isSameFlagZone(side, loc.getRow(), loc.getCol())
				&& !isJailSpot(loc.getRow(), loc.getCol()) && loc.getRow() <= 6)
			return true;
		else if (side.equals("Blue") && !isSameFlagZone(side, loc.getRow(), loc.getCol())
				&& !isJailSpot(loc.getRow(), loc.getCol()) && loc.getRow() >= 13)
			return true;
		return false;
	}

	/**
	 * Checks if given Location is on the landing zone of side
	 * 
	 * @param side "Red" or "Blue" to indicate the side to check
	 * @param r    row
	 * @param c    column
	 * @return true if landing zone, false if not
	 */
	public boolean isLandingZone(String side, int r, int c) {
		if (side.equals("Red") && r == 4)
			return true;
		else if (side.equals("Blue") && r == 15)
			return true;
		return false;
	}

	/**
	 * Adds given Piece p to board and its Piece list (bluePieces or redPieces)
	 * 
	 * @param p Piece to be added
	 */
	public void addPiece(Piece p) {
		Location loc = p.getLocation();
		if (board[loc.getRow()][loc.getCol()] == null) {
			board[loc.getRow()][loc.getCol()] = p;
			if (p.getSide().equals("Blue"))
				bluePieces.add(p);
			else
				redPieces.add(p);
		}
	}

	/**
	 * Moves given Piece p to Location loc on the board overrides original Piece at
	 * that location
	 * 
	 * @param p   Piece to be moved
	 * @param loc new Location of the Piece
	 */
	public void movePiece(Piece p, Location loc) {
		System.out.println(p.gameName() + " " + loc);
		board[p.getLocation().getRow()][p.getLocation().getCol()] = null;
		p.moveTo(loc);
		board[loc.getRow()][loc.getCol()] = p;
	}

	/**
	 * Helper method for Throw Powerup usage - Swaps two Pieces locations on the
	 * grid
	 * 
	 * @param p1 first Piece to be swapped
	 * @param p2 other Piece to be swapped
	 */
	public void swapLocations(Piece p1, Piece p2) {
		board[p1.getLocation().getRow()][p1.getLocation().getCol()] = p2;
		board[p2.getLocation().getRow()][p2.getLocation().getCol()] = p1;
	}

	/**
	 * Sends given Piece p to blueJail or redJail, and moves it to an open jail spot
	 * 
	 * @param p Piece to send into the jail
	 */
	public void sendPieceToJail(Piece p) {
		p.changeIfJail();
		if (p.getSide().equals("Red")) {
			for (int r = 17; r < 20; r++) {
				for (int c = 0; c < 2; c++) {
					if (board[r][c] == null) {
						p.moveTo(new Location(r, c));
						board[r][c] = p;
						redJail.add(p);
						return;
					}
				}
			}
		} else {
			for (int r = 0; r < 3; r++) {
				for (int c = 19; c < 21; c++) {
					if (board[r][c] == null) {
						p.moveTo(new Location(r, c));
						board[r][c] = p;
						blueJail.add(p);
						return;
					}
				}
			}
		}
	}

	/**
	 * Clears the redJail and outputs all Pieces freed
	 * 
	 * @return a Queue containing red Pieces initially in jail
	 */
	public Queue<Piece> redJailBreak() {
		Queue<Piece> q = new LinkedList<Piece>();
		for (Piece p : redJail) {
			q.add(p);
			p.changeIfJail();
		}
		redJail.clear();
		for (int r = 17; r < 20; r++) {
			for (int c = 0; c < 2; c++) {
				board[r][c] = null;
			}
		}
		return q;
	}

	/**
	 * Clears the blueJail and outputs all Pieces freed
	 * 
	 * @return a Queue containing blue Pieces initially in jail
	 */
	public Queue<Piece> blueJailBreak() {
		Queue<Piece> q = new LinkedList<Piece>();
		for (Piece p : blueJail) {
			q.add(p);
			p.changeIfJail();
		}
		blueJail.clear();
		for (int r = 0; r < 3; r++) {
			for (int c = 19; c < 21; c++) {
				board[r][c] = null;
			}
		}
		return q;
	}

	/**
	 * Adds a Powerup to the board at given Location loc Breaks out if location is
	 * not valid
	 * 
	 * @param p   Powerup to be added
	 * @param loc Location of the Powerup
	 */
	public void addPowerupToBoard(Powerup p, Location loc) {
		if (p == null || board[p.getLocation().getRow()][p.getLocation().getCol()] != null)
			return;
		board[loc.getRow()][loc.getCol()] = p;
		p.moveTo(loc);
		powerups.add(p);
	}

	/**
	 * Adds a Powerup to given side
	 * 
	 * @param p    Powerup to be added
	 * @param side the side the Powerup is added to
	 */
	public void addPowerup(Powerup p, String side) {
		if (side.equals("Red"))
			redPowerups.add(p);
		else
			bluePowerups.add(p);
	}

	/**
	 * Clears the given spot on the board (sets to null)
	 * 
	 * @param r row
	 * @param c column
	 */
	public void clearSpot(int r, int c) {
		board[r][c] = null;
	}

	/**
	 * Moves the red Flag of this board to given location
	 * 
	 * @param loc new Location of the red Flag
	 */
	public void moveRedFlag(Location loc) {
		redFlag.moveTo(loc);
	}

	/**
	 * Moves the red Flag of this board to given location
	 * 
	 * @param loc new Location of the red Flag
	 */
	public void moveBlueFlag(Location loc) {
		blueFlag.moveTo(loc);
	}
}
