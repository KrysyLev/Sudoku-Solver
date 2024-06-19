import java.io.*;
import java.net.*;

public class SudokuClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public String readMessage() throws IOException {
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) {
        SudokuClient client = new SudokuClient();
        try {
            client.startConnection("127.0.0.1", 6666);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = consoleInput.readLine()) != null) {
                client.sendMessage(input);
                System.out.println(client.readMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
