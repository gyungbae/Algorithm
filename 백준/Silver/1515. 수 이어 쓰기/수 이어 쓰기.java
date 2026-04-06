import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int pointer = 0;
        int num = 1;
        while (true) {
            String current = String.valueOf(num);

            for (int idx = 0; idx < current.length(); idx++) {
                if (pointer < input.length() && current.charAt(idx) == input.charAt(pointer)) {
                    pointer++;
                }
            }

            if(pointer == input.length())
                break;

            num++;
        }

        System.out.println(num);
    }
}
