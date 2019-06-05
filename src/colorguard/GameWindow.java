package colorguard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * GameWindow is the GUI of Colorguard. It has a JFrame which contains 3 components:
 * BoardPanel which holds the 20 by 21 grid of the board, PowerupPanel which stores upto 4 Powerups for each team, MessagePane which shows messages to the user
 * 
 * @author amaytripathi
 */
public class GameWindow {

	private static final String FILE_PATH = "../images/";
			
	private static final int BOX_SIZE = 35;
	private static final int POWERUP_WIDTH = 87;
	private static final int ROWS = 20;
	private static final int COLUMNS = 21;
	private static final Dimension BOARD_DIMENSION = new Dimension(COLUMNS * BOX_SIZE, (ROWS + 1) * BOX_SIZE);
	private static final Dimension POWERUP_DIMENSION = new Dimension(POWERUP_WIDTH, 8 * POWERUP_WIDTH);

	private static final Color LINE_COLOR = Color.BLACK;
	private static final Color HIGHLIGHT_COLOR = Color.YELLOW;
	private static final Color JAIL_COLOR = Color.MAGENTA;
	private static final Color FLAG_ZONE_COLOR = Color.GREEN;
	private static final Color RED_SIDE_COLOR = Color.RED;
	private static final Color BLUE_SIDE_COLOR = Color.decode("#7E9AEC");
	private static final Color MID_BLUE_COLOR = Color.CYAN;
	private static final Color MID_RED_COLOR = Color.PINK;

	private Dimension textAreaDimension;
	private static HashMap<String, ImageIcon> images;
	private Location lastValidLocation;
	private HashSet<Location> validLocations;
	private int lastLineCount;
	private int lineCount;

	private JFrame gameFrame;
	private BoardPanel boardPanel;
	private PowerupPanel powerupPanel;
	private JScrollPane messagePane;
	private JTextArea messageArea;
	
	/**
	 * Creates a new GameWindow with components of default sizes.
	 */
	public GameWindow() {
		images = new HashMap<String, ImageIcon>();
		initializeImages();
		lastValidLocation = null;
		validLocations = null;
		lastLineCount = 0;
		lineCount = 0;
		textAreaDimension = new Dimension(300, (ROWS + 1) * BOX_SIZE);

		gameFrame = new JFrame("Colorguard");
		gameFrame.setSize(new Dimension(BOARD_DIMENSION.width + textAreaDimension.width + POWERUP_DIMENSION.width, 
				BOARD_DIMENSION.height));
		gameFrame.setPreferredSize(new Dimension(BOARD_DIMENSION.width + textAreaDimension.width + 
				POWERUP_DIMENSION.width, BOARD_DIMENSION.height));
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLayout(new BorderLayout());
		gameFrame.addKeyListener(new GameKeyListener());

		boardPanel = new BoardPanel();
		boardPanel.addKeyListener(new GameKeyListener());
		
		powerupPanel = new PowerupPanel();
		powerupPanel.addKeyListener(new GameKeyListener());

		messageArea = new JTextArea();
		messageArea.setSize(textAreaDimension);
		messageArea.setPreferredSize(textAreaDimension);
		messageArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		messageArea.setEditable(false);
		messageArea.addKeyListener(new GameKeyListener());
		messagePane = new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		messagePane.setSize(textAreaDimension);
		messagePane.setPreferredSize(textAreaDimension);
		messagePane.addKeyListener(new GameKeyListener());
		messageArea.validate();
		messagePane.validate();
		
		addMessage("Welcome to Colorguard!");

		gameFrame.add(boardPanel, BorderLayout.WEST);
		gameFrame.add(powerupPanel, BorderLayout.CENTER);
		gameFrame.add(messagePane, BorderLayout.EAST);
		gameFrame.pack();
		gameFrame.validate();
	}

	/**
	 * Displays this GameWindow
	 */
	public void show() {
		gameFrame.setVisible(true);
	}
	
	/**
	 * Initializes HashMap images's content by accessing images stored in images folder
	 */
	public void initializeImages()
	{
		images.put("bluebasicpiece", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"bluebasicpiece.png")));
		images.put("bluediagonal", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"bluediagonal.png")));
		images.put("blueflag", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"blueflag.png")));
		images.put("bluehopper", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"bluehopper.png")));
		images.put("bluelongrange", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"bluelongrange.png")));
		images.put("blueteleporter", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"blueteleporter.png")));
		images.put("clone", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"clone.png")));
		images.put("highjump", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"highjump.png")));
		images.put("highwall", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"highwall.png")));
		images.put("invincibility", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"invincibility.png")));
		images.put("invisibility", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"invisibility.png")));
		images.put("jailbreak", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"jailbreak.png")));
		images.put("moveboost", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"moveboost.png")));
		images.put("paralysis", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"paralysis.png")));
		images.put("redbasicpiece", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"redbasicpiece.png")));
		images.put("reddiagonal", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"reddiagonal.png")));
		images.put("redflag", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"redflag.png")));
		images.put("redhopper", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"redhopper.png")));
		images.put("redlongrange", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"redlongrange.png")));
		images.put("redteleporter", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"redteleporter.png")));
		images.put("rolereversal", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"rolereversal.png")));
		images.put("swapper", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"swapper.png")));
		images.put("tagradius", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"tagradius.png")));
		images.put("throw", new ImageIcon(this.getClass().getResource(FILE_PATH + 
				"throw.png")));
	}

	/**
	 * Prompts the user to click a valid location.
	 * Updates buttons at validLocs to be highlighted.
	 * 
	 * @param validLocs a HashSet containing a list of Locations to set as valid
	 */
	public void promptUser(HashSet<Location> validLocs) {
		lastValidLocation = null;
		validLocations = validLocs;

		powerupPanel.highlightSpots(validLocs);
		boardPanel.highlightSpots(validLocs);
		gameFrame.validate();
	}

	/**
	 * Adds given message to the messagePane. Resizes if needed.
	 * 
	 * @param message the message to be added
	 */
	public void addMessage(String message) {
		System.out.println(message);
		messageArea.append(message + "\n");
		lineCount++;
		
		if (lineCount == lastLineCount + 30)
		{
			lastLineCount = lineCount;
			textAreaDimension = new Dimension(textAreaDimension.width, textAreaDimension.height + 350);
			messageArea.setSize(textAreaDimension);
			messageArea.setPreferredSize(textAreaDimension);
		}
		
		messageArea.validate();
		messagePane.validate();
		gameFrame.validate();
	}

	/**
	 * Updates the icon square at given Location of the board.
	 * 
	 * @param loc Location of the square to update
	 * @param board the GameBoard to use
	 */
	public void updateSquare(Location loc, GameBoard board)
	{
		GameObject g = board.getObject(loc.getRow(), loc.getCol());
		if (g == null)
			boardPanel.updateIcon(loc, null);
		else if (g.type().equals("powerup"))
		{
			Powerup powerup = (Powerup)g;
			boardPanel.updateIcon(loc, images.get(powerup.powerupType()));
		}
		else if (g.type().equals("flag"))
		{
			Flag f = (Flag)g;
			if (board.getBlueFlag() == f)
				boardPanel.updateIcon(loc, images.get("blueflag"));
			else
				boardPanel.updateIcon(loc, images.get("redflag"));
		}
		else
		{
			Piece p = (Piece)g;
			String key = (p.getSide() + p.pieceType()).toLowerCase();
			boardPanel.updateIcon(loc, images.get(key));
			if (p.getHasFlag())
			{
				String key2;
				if (p.getFlag() == board.getBlueFlag())
					key2 = "blueflag";
				else
					key2 = "redflag";
				boardPanel.updateRolloverIcon(loc, images.get(key2));
			}
			else if (p.hasPowerup())
			{
				String key2 = p.getCurrentPowerup().powerupType();
				boardPanel.updateRolloverIcon(loc, images.get(key2));
			}
			else
			{
				boardPanel.updateRolloverIcon(loc, null);
			}
			System.out.println(p.pieceType() + " " + p.hasInvisibility());
		}
		gameFrame.validate();
	}
	
	/**
	 * Updates the GUI for the given board
	 * 
	 * @param game the current state of the game to use
	 */
	@SuppressWarnings("static-access")
	public void updateBoard(Colorguard game)
	{
		GameBoard board = game.getBoard();
		for (int r = 0; r < board.ROWS; r++)
		{
			for (int c = 0; c < board.COLUMNS; c++)
			{
				Piece piece = board.getPiece(r, c);
				if (piece == null || !(piece.hasInvisibility() &&
						!piece.getSide().equals(game.mySide())))
					updateSquare(new Location(r, c), board);
			}
		}
		
		LinkedList<Powerup> redPowerups = board.getRedPowerups();
		int r = 0;
		for (Powerup p : redPowerups)
		{
			powerupPanel.updateIcon(r, resizeIcon(images.get(p.powerupType()), POWERUP_WIDTH, POWERUP_WIDTH));
			r++;
		}
		while (r < 4)
		{
			powerupPanel.updateIcon(r, null);
			r++;
		}
		
		LinkedList<Powerup> bluePowerups = board.getBluePowerups();
		r = 4;
		for (Powerup p : bluePowerups)
		{
			powerupPanel.updateIcon(r, resizeIcon(images.get(p.powerupType()), POWERUP_WIDTH, POWERUP_WIDTH));
			r++;
		}
		while (r < 8)
		{
			powerupPanel.updateIcon(r, null);
			r++;
		}
		
		gameFrame.validate();
	}

	/**
	 * Gets the last valid Location clicked by the user.
	 * Waits until notifyThread() is called before acting.
	 * Unhighlights all spots after action is complete
	 * 
	 * @return the last valid Location clicked by user
	 * @throws InterruptedException
	 */
	public synchronized Location getLastValidLocation() throws InterruptedException {
		while (lastValidLocation == null)
			this.wait();
		boardPanel.unhighlightSpots(validLocations);
		powerupPanel.unhighlightSpots(validLocations);
		return lastValidLocation;
	}

	/**
	 * Helper method for managing threads.
	 * Resumes all threads for this class.
	 */
	public synchronized void notifyThread() {
		this.notifyAll();
	}
	
	/**
	 * Resizes given icon to new width and height
	 * 
	 * @param icon ImageIcon to be resized
	 * @param width width of the resized icon
	 * @param height height of the resized icon
	 * @return the resized icon
	 */
	public ImageIcon resizeIcon(ImageIcon icon, int width, int height)
	{
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( width, height, java.awt.Image.SCALE_SMOOTH ) ;  
		return new ImageIcon( newimg );
	}
	
	/**
	 * The PowerupPanel class represents the Powerups of a game.
	 * Red Powerups are denoted by top 4 buttons.
	 * Blue Powerups are denoted by bottom 4 buttons.
	 * Locations of GameButtons stored by PowerupPanel must have a col = 21
	 * 
	 * @author amaytripathi
	 */
	private class PowerupPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private GameButton[] buttons;
		
		/**
		 * Creates a new PowerupPanel of POWERUP_DIMENSION.
		 * Initializes a GridLayout (8 by 1) of GameButtons.
		 */
		public PowerupPanel() {
			super(new GridLayout(8, 1, 0, 0));
			this.setSize(POWERUP_DIMENSION);
			this.setPreferredSize(POWERUP_DIMENSION);
			this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			
			buttons = new GameButton[8];
			for (int i = 0; i < 8; i++)
			{
				GameButton newButton = new GameButton(i, 21);
				buttons[i] = newButton;
				newButton.setSize(POWERUP_WIDTH, POWERUP_WIDTH);
				newButton.setPreferredSize(new Dimension(POWERUP_WIDTH, POWERUP_WIDTH));
				newButton.validate();
				this.add(newButton);
			}
			
			this.validate();
		}
		
		@Override
		public void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D)g;
			
			int offset = 10;
			
			g2.setColor(RED_SIDE_COLOR);
			g2.fillRect(0, 0, POWERUP_WIDTH, POWERUP_WIDTH * 4 + offset);
			
			g2.setColor(BLUE_SIDE_COLOR);
			g2.fillRect(0, 4 * POWERUP_WIDTH + offset, POWERUP_WIDTH, POWERUP_WIDTH * 4 + offset);
		}
		
		/**
		 * Highlights the given spots
		 * 
		 * @param locs to be higlighted
		 */
		public void highlightSpots(HashSet<Location> locs) {
			for (Location loc : locs) {
				if (loc.getCol() == 21)
					buttons[loc.getRow()].highlight();
			}
			this.validate();
		}
		
		/**
		 * Unhighlights the given spots
		 * 
		 * @param locs to be unhiglighted
		 */
		public void unhighlightSpots(HashSet<Location> locs)
		{
			for (Location loc : locs) {
				if (loc.getCol() == 21)
					buttons[loc.getRow()].unhighlight();
			}
			this.validate();
		}
		
		/**
		 * Sets the icon of the Powerup at row
		 * 
		 * @param row row
		 */
		public void updateIcon(int row, ImageIcon icon)
		{
			GameButton gButton = buttons[row];
			gButton.setIcon(icon);
			gButton.validate();
			this.validate();
		}
	}

	/**
	 * BoardPanel is the visual representation of the GameBoard.
	 * It displays the colors, highlights, and icons associated with each square.
	 * 
	 * @author amaytripathi
	 */
	private class BoardPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		private GameButton[][] buttonGrid;

		/**
		 * Makes a new BoardPanel of Dimension BOARD_DIMENSION
		 */
		public BoardPanel() {
			super(new GridLayout(ROWS, COLUMNS, 0, 0));
			this.setSize(BOARD_DIMENSION);
			this.setPreferredSize(BOARD_DIMENSION);
			this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			buttonGrid = new GameButton[ROWS][COLUMNS];
			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLUMNS; c++) {
					GameButton newButton = new GameButton(r, c);
					buttonGrid[r][c] = newButton;
					this.add(newButton);
				}
			}

			this.validate();
		}
		
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			int offset = 6;
			
			Graphics2D g2 = (Graphics2D)g;
			
			// Print red side
			g2.setColor(RED_SIDE_COLOR);
			g2.fillRect(0, offset, COLUMNS * BOX_SIZE, 7 * BOX_SIZE);
			
			// Print mid red side
			g2.setColor(MID_RED_COLOR);
			g2.fillRect(0, offset + 7 * BOX_SIZE, COLUMNS * BOX_SIZE, 3 * BOX_SIZE);
			
			// Print mid blue side
			g2.setColor(MID_BLUE_COLOR);
			g2.fillRect(0, offset + 10 * BOX_SIZE, COLUMNS * BOX_SIZE, 3 * BOX_SIZE);
			
			// Print blue side
			g2.setColor(BLUE_SIDE_COLOR);
			g2.fillRect(0, offset + 13 * BOX_SIZE, COLUMNS * BOX_SIZE, 7 * BOX_SIZE);
			
			// Print jails
			g2.setColor(JAIL_COLOR);
			g2.fillRect(19 * BOX_SIZE, offset, 2 * BOX_SIZE, 3 * BOX_SIZE);
			g2.fillRect(0, offset + 17 * BOX_SIZE, 2 * BOX_SIZE,  3 * BOX_SIZE);
			
			// Print flag zones
			g2.setColor(FLAG_ZONE_COLOR);
			g2.fillRect(9 * BOX_SIZE, offset, 3 * BOX_SIZE, 3 * BOX_SIZE);
			g2.fillRect(9 * BOX_SIZE, offset + 17 * BOX_SIZE, 3 * BOX_SIZE, 3 * BOX_SIZE);
		}

		/**
		 * Highlights the given spots
		 * 
		 * @param locs to be higlighted
		 */
		public void highlightSpots(HashSet<Location> locs) {
			for (Location loc : locs) {
				if (loc.getCol() < 21)
					buttonGrid[loc.getRow()][loc.getCol()].highlight();
			}
			this.validate();
		}
		
		/**
		 * Unhighlights the given spots
		 * 
		 * @param locs to be unhiglighted
		 */
		public void unhighlightSpots(HashSet<Location> locs)
		{
			for (Location loc : locs) {
				if (loc.getCol() < 21)
					buttonGrid[loc.getRow()][loc.getCol()].unhighlight();
			}
			this.validate();
		}
		
		/**
		 * Updates the icon at given location
		 * 
		 * @param loc Location to update icon at
		 * @param icon the new icon
		 */
		public void updateIcon(Location loc, ImageIcon icon)
		{
			GameButton gButton = buttonGrid[loc.getRow()][loc.getCol()];
			gButton.setIcon(icon);
			gButton.validate();
			this.validate();
		}
		
		public void updateRolloverIcon(Location loc, ImageIcon icon)
		{
			GameButton gButton = buttonGrid[loc.getRow()][loc.getCol()];
			gButton.setRolloverEnabled(true);
			gButton.setRolloverIcon(icon);
			gButton.validate();
			this.validate();
		}
	}

	/**
	 * GameButton is a button used by BoardPanel and PowerupPanel
	 * 
	 * @author amaytripathi
	 */
	private class GameButton extends JButton {
		private static final long serialVersionUID = 1L;

		private Location location;

		/**
		 * Creates a new GameButton
		 * 
		 * @param row row of the new button
		 * @param col column of the new button
		 */
		public GameButton(int row, int col) {
			super("");
			
			this.location = new Location(row, col);

			this.setSize(BOX_SIZE, BOX_SIZE);
			this.setPreferredSize(new Dimension(BOX_SIZE, BOX_SIZE));
			this.setBorder(BorderFactory.createLineBorder(LINE_COLOR, 1));

			this.setEnabled(true);
			this.setOpaque(false);
			this.setContentAreaFilled(false);
			this.addActionListener(new GameButtonListener());
			this.addKeyListener(new GameKeyListener());
			
			this.validate();
		}

		/**
		 * Highlights this button by setting its color to HIGHLIGHT_COLOR
		 */
		public void highlight() {
			this.setOpaque(true);
			this.setContentAreaFilled(true);
			this.setForeground(HIGHLIGHT_COLOR);
			this.setBackground(HIGHLIGHT_COLOR);
			this.validate();
		}
		
		/**
		 * Unhighlights this button by clearing its color
		 */
		public void unhighlight()
		{
			this.setOpaque(false);
			this.setContentAreaFilled(false);
			this.validate();
		}

		/**
		 * @return the Location of this button
		 */
		public Location getBoardLocation() {
			return location;
		}
	}

	/**
	 * GameButtonListener checks if a given button that is pressed is a part of validLocations
	 * If it is, then notifyThread is called and validLocation is updated
	 * If not, an error message is displayed on messagePane
	 * 
	 * @author amaytripathi
	 */
	private class GameButtonListener implements ActionListener {
		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			GameButton b = (GameButton) e.getSource();
			Location buttonLoc = b.getBoardLocation();
			System.out.println(buttonLoc);
			System.out.println(b.getLocation());
			if (validLocations != null && validLocations.contains(buttonLoc)) {
				lastValidLocation = buttonLoc;
				notifyThread();
			} else {
				addMessage("Invalid move. Try again.");
			}
		}

	}
	
	/**
	 * GameKeyListener is applied onto all components of gameFrame.
	 * If "esc" is pressed at any time, notifyThread() is called and lastValidLocation is set to (-1, -1)
	 * 
	 * @author amaytripathi
	 */
	private class GameKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("Typed");
			
		}

		@Override
		public synchronized void keyPressed(KeyEvent e) {
			System.out.println("Pressed " + e.getKeyChar());
			if (e.getKeyCode() == 27)
			{
				lastValidLocation = new Location(-1, -1);
				notifyThread();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("Typed");
			
		}
		
	}
}
