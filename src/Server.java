import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server {

    private int port;
    private String filePath;

    public Server(int port, String filePath) {

        try(ServerSocket server = new ServerSocket(port);
            Socket client = server.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.ISO_8859_1)))
        ){
            sc.useDelimiter("[^A-Za-zÄÖÅäöå0-9]");
            while (sc.hasNext()){
                String word = sc.next();
                word.trim().toLowerCase();
                if(!word.isEmpty()){
                    out.print(word + " ");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }
}
