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

    public Client() {

        wordCounter = new HashMap<>();
        String host = "127.0.0.1";

        final Thread thread1 = new Thread() {
            @Override
            public void run() {
                int port = 13456;

                try (Socket socket = new Socket(host, port);
                     Scanner in = new Scanner(
                             new InputStreamReader(socket.getInputStream()));) {


                    while (in.hasNext()) {
                        String word = in.next().trim().toLowerCase();
                        if (wordCounter.containsKey(word)) {
                            wordCounter.put(word, wordCounter.get(word) + 1);
                        } else {
                            wordCounter.put(word, 1);
                        }
                    }
                } catch (UnknownHostException e) {
                    System.err.println("Unknown host " + host);
                    System.exit(1);
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to " +
                            host);
                    System.exit(1);
                }
            }
        };

        thread1.run();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                int port = 13457;

                try (Socket socket = new Socket(host, port);
                     Scanner in = new Scanner(
                             new InputStreamReader(socket.getInputStream()));) {


                    while (in.hasNext()) {
                        String word = in.next().trim().toLowerCase();
                        if (wordCounter.containsKey(word)) {
                            wordCounter.put(word, wordCounter.get(word) + 1);
                        } else {
                            wordCounter.put(word, 1);
                        }
                    }
                } catch (UnknownHostException e) {
                    System.err.println("Unknown host " + host);
                    System.exit(1);
                } catch (IOException e) {
                    System.err.println("Couldn't get I/O for the connection to " +
                            host);
                    System.exit(1);
                }
            }
        };
        thread2.run();

        Map<String, Integer> sorted = wordCounter
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(5)
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        System.out.println("Word" + "\t\t" + "Count");
        System.out.println();

        for (String s : sorted.keySet()) {
            System.out.println(s + "\t\t\t" + wordCounter.get(s));
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
