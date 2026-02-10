import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int from = Integer.parseInt(input[0]);
        int to = Integer.parseInt(input[1]);

        StringBuilder sb = new StringBuilder();
        for (int num = from; num <= to; num++) {
            if (num == 1) {
                continue;
            }

            boolean isPrime = true;
            for (int divideNum = 2; divideNum <= Math.sqrt(num); divideNum++) {
                if (num % divideNum == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                sb.append(num).append("\n");
            }
        }

        System.out.println(sb);
    }
}
