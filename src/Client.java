import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class Client {
    private Map<String, Integer> wordCounter;

    public Client(){

        wordCounter = new HashMap<>();
        String host = "127.0.0.1";
        int port = 13456;

        try(Socket socket = new Socket(host, port);
            Scanner in = new Scanner(
                    new InputStreamReader(socket.getInputStream()));){


            while (in.hasNext()) {
                String word = in.next().trim().toLowerCase();
                System.out.println(word);
                if(wordCounter.containsKey(word)){
                    wordCounter.put(word, wordCounter.get(word) + 1);
                }
                else {
                    wordCounter.put(word, 1);
                }
            }
             Map<String, Integer> sorted = wordCounter
                     .entrySet()
                     .stream()
                     .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                     .limit(5)
                     .collect(
                             toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                     LinkedHashMap::new));
            for(String s : sorted.keySet()){
                System.out.println(s + " " + wordCounter.get(s));
            }
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    host);
            System.exit(1);
        }
    }

    public static void main(String[] args){
        Client client = new Client();
    }
}
