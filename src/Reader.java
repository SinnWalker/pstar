import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    public static void main(String[] args){
        File file =
                new File("C:\\Users\\sinas\\OneDrive\\Desktop\\example.txt");
        try(Scanner sc = new Scanner(file)){
            sc.useDelimiter("\\W");
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
