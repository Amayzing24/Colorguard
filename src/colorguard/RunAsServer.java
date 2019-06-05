package colorguard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Creates a game at a new Server of specified port. Must run RunAsClient as well at the same IP address in order to work.
 * @author amaytripathi
 *
 */
public class RunAsServer {
	private final static int PORT = 6664;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = new Server(PORT);
		
		GameRun g = new GameRun();
		g.runGame("Blue", server, null);
		
		return;
	}
}

