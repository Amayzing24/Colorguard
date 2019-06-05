package colorguard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Runs a game from the client side. Must have active server at IP, portNumber to work.
 * 
 * @author amaytripathi
 */
public class RunAsClient {
	private static final int PORT = 6664;
	private static final String IP = "10.18.83.180";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Client client = new Client(IP, PORT);
		
		GameRun g = new GameRun();
		g.runGame("Red", null, client);
		
		return;
	}
}
