import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String num = br.readLine();

        int sum = 0;
        for (int idx = 0; idx < N; idx++) {
            sum += num.charAt(idx) - '0';
        }

        System.out.println(sum);
    }
}
