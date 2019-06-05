package colorguard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client class uses server sockets and acts as a client that connects to a
 * server
 * 
 * @author amaytripathi
 */
public class Client {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	/**
	 * Initializes the IP address and port number the client will connect to Will
	 * stall and eventually throw time out exception if server under given IP and
	 * port does not exist.
	 * 
	 * @param ip         the ip address of the server
	 * @param portNumber the port number the server is set up on
	 */
	public Client(String ip, int portNumber) {
		try {
			socket = new Socket(ip, portNumber);
			System.out.println("Server found and connected to");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return true if the socket has found a connection, false if not
	 */
	public boolean isConnected() {
		return socket.isConnected();
	}

	/**
	 * Sends given object over to the server
	 * 
	 * @param o the object to send
	 */
	public void sendObject(Object o) {
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(o);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetches the last sent object from the server Will stall out and eventually
	 * throw time out exception if server has not sent anything
	 * 
	 * @return the last object sent by server
	 */
	public Object getSentObject() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			Object o = in.readObject();
			return o;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
