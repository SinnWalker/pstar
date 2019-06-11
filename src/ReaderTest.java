import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public class ReaderTest {

    public static void main(String[] args){
        try(Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream(".//src//textfil1_lasse_solen_i_ogonen.txt"), StandardCharsets.ISO_8859_1)))){

            sc.useDelimiter("[^A-Za-zÄÖÅäöå0-9]");
            while (sc.hasNext()){
                String word = sc.next();
                word.trim().toLowerCase();
                if(!word.isEmpty())
                    System.out.println(word);
            }
        }
        catch (FileNotFoundException f){
            System.out.println("File not found");
        }


    }
}
