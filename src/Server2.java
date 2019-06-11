import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server2 {

    public Server2() {

        int port = 13457;

        try(ServerSocket server = new ServerSocket(port);
            Socket client = server.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(".//src//textfil2_lasse_varning_for_ras.txt"), StandardCharsets.ISO_8859_1)))
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

    public static void main(String[] args){
        Server2 server2 = new Server2();
    }
}