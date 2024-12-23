import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    public void run() throws IOException {
        int port = 8010;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);

        while (true) {
            try {
                System.out.println("Server is listening on port" + port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Accepted connections" + acceptedConnection.getRemoteSocketAddress());

                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnection.getInputStream())); // cause it takes inputstream

                toClient.println("Hello from the server");

                acceptedConnection.close();
                toClient.close();
                fromClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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