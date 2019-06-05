package colorguard;

// A Java program for a Client 
import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * Server is used for networking
 * 
 * @author amaytripathi
 */
public class Server {
	private ServerSocket serverSocket;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	/**
	 * Creates a new server at ip address of localhost of specified port number
	 * 
	 * @param portNumber port number of the new Server
	 */
	public Server(int portNumber)
	{
		try {
			serverSocket = new ServerSocket(portNumber);
			System.out.println("Server started");
			socket = serverSocket.accept();
			System.out.println("Server set");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the IP address of this server
	 */
	public String getIP()
	{
		return serverSocket.getInetAddress().getHostAddress();
	}
	
	/**
	 * Sends given object to client connected to this server
	 * 
	 * @param o object to send
	 */
	public void sendObject(Object o)
	{
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(o);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Receives object from client connected to this server
	 * 
	 * @return the object sent by Client
	 */
	public Object getSentObject()
	{
		try {
			in = new ObjectInputStream(socket.getInputStream());
			Object o = in.readObject();
			return o;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}