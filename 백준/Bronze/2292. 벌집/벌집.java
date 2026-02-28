import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        long layer = 1;   
        long current = 1;    

        while (N > current) {
            current += 6 * layer;
            layer++;
        }

        System.out.println(layer);
    }
}
