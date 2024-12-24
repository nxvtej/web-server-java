import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {
    public void run() throws IOException, UnknownHostException, IOException, SocketException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(50000);

        try {

            while (true) {
                try {

                    System.out.println("Server is listening on port: " + port);
                    Socket acceptedConnection = socket.accept();
                    System.out.println("Accepted connections" + acceptedConnection.getRemoteSocketAddress());

                    PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                    BufferedReader fromClient = new BufferedReader(
                            new InputStreamReader(acceptedConnection.getInputStream())); // cause it takes inputstream

                    toClient.println("Hello from the server");
                    toClient.flush();

                    String line = fromClient.readLine();
                    System.out.println("Received from the client: " + line);

                    acceptedConnection.close();

                    /*
                     * not important as the socket will be closed when the connection is closed
                     * toClient.close();
                     * fromClient.close();
                     */

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            socket.close();
            System.out.println("Server is shutting down");
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace(); // kya kya kha se ho chuka bo print kr dea
        }
    }
}

// why this error
/*
 * 
 * PS C:\Projects\web server java> java Server.java
 * Error: Could not find or load main class Server.java
 * Caused by: java.lang.ClassNotFoundException: Server.java
 * PS C:\Projects\web server java>
 * 
 */