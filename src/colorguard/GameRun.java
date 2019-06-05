package colorguard;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class GameRun {
	public final static String[] pieceTypes = new String[] { "basicpiece", "basicpiece", "diagonal", "hopper",
			"longrange", "teleporter" };
	public final static String[] pieceNames = new String[] { "Trooper 1", "Trooper 2", "Ninja", "Horseman", "Dasher",
			"Wizard" };

	/**
	 * Creates a new Colorguard game owned by given side. For networking, runGame
	 * takes a server and client. One of these must be null. runGame manages GUI,
	 * networking, and game logic together. runGame updates some states of the game,
	 * such as jailbreaks.
	 * 
	 * @param side   the game is owned by
	 * @param server the server to use for connection (null, if no server)
	 * @param client the client to use for connection (null, if no client)
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void runGame(String side, Server server, Client client) throws InterruptedException, IOException {
		// Initialize Colorguard game, GameWindow window, GameBoard board. Update GUI
		Colorguard game = new Colorguard(side);
		GameWindow window = new GameWindow();
		window.show();

		// Get starting spots and prompt player
		HashSet<Location> possSpots = game.getStartingPoints();
		for (int i = 0; i < pieceTypes.length; i++) {
			window.addMessage("Place " + pieceNames[i]);
			window.promptUser(possSpots);
			Location loc = window.getLastValidLocation();
			game.addPiece(loc, pieceTypes[i]);
			possSpots.remove(loc);
			window.updateSquare(loc, game.getBoard());
			window.addMessage(pieceNames[i] + " placed at " + loc);
		}
		window.updateBoard(game);

		// Gets opponent initialized Pieces and adds them to the game
		window.addMessage("Pieces initialized. Waiting for opponent...");
		Object input;
		if (server == null) {
			client.sendObject(game);
			input = client.getSentObject();
		} else {
			server.sendObject(game);
			input = server.getSentObject();
		}
		Colorguard oppGame = (Colorguard) input;
		LinkedList<Piece> newPieces;
		if (game.mySide().equals("Blue"))
			newPieces = oppGame.getBoard().getRedPieces();
		else
			newPieces = oppGame.getBoard().getBluePieces();
		for (Piece p : newPieces) {
			game.addPiece(p);
			window.addMessage("Opponent intialized " + p.gameName() + " at " + p.getLocation());
		}

		game.startGame();

		window.updateBoard(game);

		// Display opening message of the game
		window.addMessage(game.getCurrentTurn() + " turn! (Turn: " + game.getTurns() + ")");

		while (!game.gameOver()) {

			// Updates game and the side if it is not your turn currently
			if (!game.mySide().equals(game.getCurrentTurn())) {

				window.addMessage("Opponent turn...");
				if (server == null)
					input = client.getSentObject();
				else
					input = server.getSentObject();
				game = (Colorguard) input;
				if (game.mySide().equals("Blue"))
					game.setMySide("Red");
				else
					game.setMySide("Blue");
				window.updateBoard(game);
				if (game.gameOver())
					break;
				// Handles actions when it is your turn
			} else {

				window.addMessage(game.getCurrentTurn() + " turn! (Turn: " + game.getTurns() + ")");

				// Powerup handling
				LinkedList<Powerup> yourPowerups = game.getCurrentPowerups();
				if (yourPowerups != null && yourPowerups.size() != 0)
					window.addMessage("Your powerups: " + yourPowerups);

				if (game.getTurns() % 3 == 0) {
					Powerup nextPowerup = game.addNewPowerup();
					window.addMessage("New Powerup " + nextPowerup.gameName() + " at " + nextPowerup.getLocation());
				}

				window.updateBoard(game);

				// Jailbreak handling
				if (game.getTurns() % 8 == 0) {
					handleJailbreak(game, window);
				}

				window.updateBoard(game);

				// Piece movement handling
				while (game.getMovedPieces().size() < 7) {
					if (game.gameOver())
						break;
					possSpots = game.getPossiblePieces();
					if (possSpots.size() == 0)
						break;
					int i = 0;
					if (game.getCurrentTurn().equals("Blue"))
						i = 4;
					for (Powerup p : yourPowerups) {
						possSpots.add(new Location(i, 21));
						i++;
					}
					window.addMessage("Move a piece or use a powerup. Enter ESC to end turn.");
					window.promptUser(possSpots);
					Location loc = window.getLastValidLocation();

					// End turn option
					if (loc.getRow() == -1 && loc.getCol() == -1) {
						break;

						// Powerup option
					} else if (loc.getCol() == 21) {
						// Fetches powerup based on location external from board
						Powerup powerup = game.getPowerupAtIndex(loc.getRow());

						// Handles different kinds of Powerups
						if (powerup.powerupType().equals("swapper")) {
							window.addMessage("Enter effected Piece 1");
							possSpots = game.getPossiblePieces();
							window.promptUser(possSpots);
							Location loc1 = window.getLastValidLocation();
							possSpots.remove(loc1);

							window.addMessage("Enter effected Piece 2");
							window.promptUser(possSpots);
							Location loc2 = window.getLastValidLocation();

							String message = game.useSwapper((Swapper) powerup, loc1, loc2);
							window.addMessage(message);
						} else if (powerup.powerupType().equals("jailbreak")) {
							handleJailbreak(game, window);
						} else if (powerup.powerupType().equals("clone")) {
							window.addMessage("Enter Piece to clone");
							possSpots = game.getPossiblePieces();
							window.promptUser(possSpots);
							Location pieceLocation = window.getLastValidLocation();
							possSpots.remove(pieceLocation);

							window.addMessage("Enter location of the clone");
							possSpots = game.getStartingPoints();
							window.promptUser(possSpots);
							Location cloneLocation = window.getLastValidLocation();

							String message = game.useClone((Clone) powerup, pieceLocation, cloneLocation);
							window.addMessage(message);
						} else if (powerup.powerupType().equals("highwall")) {
							String message = game.useHighWall((HighWall) powerup);
							window.addMessage(message);
						} else if (powerup.powerupType().equals("paralysis")) {
							window.addMessage("Enter Piece to paralyze enemies around");
							possSpots = game.getPossiblePieces();
							;
							window.promptUser(possSpots);
							Location pieceLocation = window.getLastValidLocation();

							String message = game.useParalysis((Paralysis) powerup, pieceLocation);
							window.addMessage(message);
						} else if (powerup.powerupType().equals("throw")) {
							Location poss = game.getFlagHolder();
							if (poss == null) {
								window.addMessage("Nobody has flag. Cannot use Quarterback");
								break;
							}
							window.addMessage("Enter the thrower");
							HashSet<Location> locs = new HashSet<Location>();
							locs.add(poss);
							window.promptUser(locs);
							Location thrower = window.getLastValidLocation();

							locs = game.getThrowOptions();
							if (locs.size() == 0) {
								window.addMessage("No possible recievers. Cannot use Quarterback");
								break;
							}
							window.addMessage("Enter the reciever");
							window.promptUser(locs);
							Location rec = window.getLastValidLocation();

							String message = game.useThrow((Throw) powerup, thrower, rec);
							window.addMessage(message);
						} else {
							window.addMessage("Enter Piece to use " + powerup.gameName() + " on");
							possSpots = game.getPossiblePieces();
							window.promptUser(possSpots);
							Location pieceLocation = window.getLastValidLocation();

							String message = game.usePowerup(powerup, pieceLocation);
							window.addMessage(message);
						}

						window.updateBoard(game);

						// Piece movement option
					} else {
						window.addMessage("Click new location. ESC to cancel");
						possSpots = game.getPossibleMovements(loc);
						window.promptUser(possSpots);
						Location newLoc = window.getLastValidLocation();

						// Cancel option
						if (newLoc.getRow() == -1 && newLoc.getCol() == -1) {
							window.addMessage("Cancelled move");

							// Handle Piece movement
						} else {
							String message = game.movePiece(loc, newLoc);
							window.addMessage(message);

							// Check for jail break
							if (game.getCurrentTurn().equals("Red")) {
								if ((newLoc.getRow() == 16 && newLoc.getCol() >= 0 && newLoc.getCol() <= 2)
										|| (newLoc.getCol() == 2 && newLoc.getRow() >= 17 && newLoc.getRow() <= 19))
									handleJailbreak(game, window);
							}
							else
							{
								if ((newLoc.getCol() == 18 && newLoc.getRow() >= 0 && newLoc.getRow() <= 2)
										|| (newLoc.getRow() == 3 && newLoc.getCol() >= 18 && newLoc.getCol() <= 20))
									handleJailbreak(game, window);
							}
						}

						window.updateBoard(game);
					}

					// Send game to other side
					if (client == null)
						server.sendObject(game);
					else
						client.sendObject(game);
					System.out.println("Sent");

					window.updateBoard(game);
				}
				
				if (game.gameOver())
					break;

				window.addMessage("Turn ended");
				game.nextTurn();

				window.updateBoard(game);

				if (game.getTurns() % 8 == 0) {
					handleJailbreak(game, window);
				}

				window.updateBoard(game);

				// Send game to other side
				if (client == null)
					server.sendObject(game);
				else
					client.sendObject(game);
				System.out.println("Sent");
			}
		}

		window.addMessage("Game over! " + game.getCurrentTurn() + " wins!!!!");
	}

	/**
	 * Helper method that handles a situation for jailbreak Prompts user for
	 * Location to put Pieces after a jailbreak.
	 * 
	 * @param game   the current game
	 * @param window the window to update GUI for
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void handleJailbreak(Colorguard game, GameWindow window) throws IOException, InterruptedException {
		Queue<Piece> freePieces = game.jailbreak();
		HashSet<Location> possSpots = game.getLandingSpots();
		while (!freePieces.isEmpty()) {
			Piece free = freePieces.remove();
			System.out.println(free + " " + free.getLocation());
			window.addMessage("Enter landing zone for " + free.gameName());
			window.promptUser(possSpots);
			Location newLoc = window.getLastValidLocation();
			game.getBoard().movePiece(free, newLoc);
			window.addMessage(free.gameName() + " landed at " + newLoc);
			possSpots.remove(newLoc);
			window.updateBoard(game);
		}
	}
}
