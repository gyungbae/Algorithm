import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int sum = 0;

        String[] arr = input.split("-");
        for (String str : arr[0].split("\\+")) {
            sum += Integer.parseInt(str);
        }

        for (int idx = 1; idx < arr.length; idx++) {
            for (String str : arr[idx].split("\\+")) {
                sum -= Integer.parseInt(str);
            }
        }

        System.out.println(sum);
    }
}
